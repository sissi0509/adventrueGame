package model.utilityClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.dataClasses.GameData;
import model.objects.IGameObjects;
import model.objects.IPlayer;

/**
 * utility class to handle the save action.
 */
public class Save {

  /**
   * save and return message for saving.
   * @param gameObjects game related objects in the game (except for player).
   * @param player player of the game.
   * @param filename file name of the saved file.
   * @param filePathName path the file belong.
   * @return message for saving.
   */
  public static String save(IGameObjects gameObjects, IPlayer player, String filename, String filePathName) {

    GameData gameData = new GameData(gameObjects, player);

    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      File dir = new File(filePathName);
      // Create the directories if they don't exist
      if (!dir.exists()) {
        dir.mkdirs();
      }

      File file = new File(filePathName, filename);
      FileWriter writer = new FileWriter(file);
      gson.toJson(gameData, writer);
      writer.close();

      return "Saved your current game!";
    } catch (IOException e) {
      e.printStackTrace();
      return "Failed to save";
    }
  }
}