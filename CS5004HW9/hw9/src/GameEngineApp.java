import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import controller.GameController;
import controller.IController;
import model.dataClasses.GameData;
import model.gameModel.GameModel;
import model.gameModel.IGameModel;
import view.ITextView;
import view.IView;
import view.TextView;
import view.VisualView;


/**
 * This class represents an adventure game engine.
 */
public class GameEngineApp {
  private final IController controller;

  /**
   * Constructor for text and batch mode. Require Readable and Appendable.
   */
  public GameEngineApp(String gameFileName, Readable source, Appendable output) throws IOException {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(gameFileName)) {
      GameData data = gson.fromJson(reader, GameData.class);
      // we use our team name as default name.
      IGameModel model = new GameModel(data ,"A4","save.json");
      ITextView textView = new TextView(source, output);
      controller = new GameController(model, textView);
    }
  }

  /**
   * Constructor for graphical mode.
   */
  public GameEngineApp(String gameFileName) throws IOException {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(gameFileName)) {
      GameData data = gson.fromJson(reader, GameData.class);
      IGameModel model = new GameModel(data ,"A4","save.json");
      IView view = new VisualView("Adventure Game");
      controller = new GameController(model, view);
    }
  }

  /**
   * Helper: call controller's method for graphical mode.
   */
  private void startGraphical() throws IOException {
    controller.goGraphical();
  }

  /**
   * Helper: call controller's method for text mode.
   */
  private void startText() throws IOException {
    controller.goText();
  }

  /**
   * Helper: call controller's method for batch mode.
   */
  private void startBatch(String filePath) throws IOException {
    controller.goBatch(filePath);
  }

  /**
   * Starting point of the game.
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 2 && args[1].equalsIgnoreCase("-text")) {
      // Text model
      GameEngineApp app = new GameEngineApp(args[0], new InputStreamReader(System.in), System.out);
      app.startText();
    } else if (args.length == 2 && args[1].equalsIgnoreCase("-graphics")) {
      // graphics model
      GameEngineApp app = new GameEngineApp(args[0]);
      app.startGraphical();
    } else if (args.length == 3 && args[1].equalsIgnoreCase("-batch")) {
      GameEngineApp app = new GameEngineApp(args[0], new InputStreamReader(System.in), System.out);
      app.startBatch(args[2]);
    } else if (args.length == 4 && args[1].equalsIgnoreCase("-batch")) {
      PrintStream fileOut =  new PrintStream(args[3]);
      GameEngineApp app = new GameEngineApp(args[0], new InputStreamReader(System.in),fileOut);
      app.startBatch(args[2]);
    }
    else {
      System.out.println("Invalid arguments.\nUsage:");
      System.out.println("java -jar game_engine.jar <gameFile.json> -text");
      System.out.println("java -jar game_engine.jar <gameFile.json> -graphics");
      System.out.println("java -jar game_engine.jar <gameFile.json> -batch <input>");
      System.out.println("java -jar game_engine.jar <gameFile.json> -batch <input> <output>");
    }
  }
}
