package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.gameModel.IGameModel;
import view.ITextView;
import view.IView;

/**
 * Controller class that gets player's input and call model to handle it.
 */
public class GameController implements IController, ActionListener {
  private ITextCommandReader commandReader;
  private IGameModel model;
  private IView view;
  private ITextView textView;

  /**
   * Constructor for graphical mode.
   */
  public GameController(IGameModel model, IView view) {
    this.model = model;
    this.view = view;
    this.view.setEventHandler(this);
  }

  /**
   * Constructor for the text and batch mode.
   */
  public GameController(IGameModel model, ITextView textView) {
    this.model = model;
    this.textView = textView;
    this.commandReader = new TextCommandReader(this.textView);
  }


  /**
   * Handle user clicks for graphical mode.
   */
  public void actionPerformed(ActionEvent e) {
    String commands = e.getActionCommand();

    // build examine menu, including items, fixtures and puzzle
    List<String> itemList = model.getRoomItems();
    List<String> fixtureList = model.getRoomFixtures();

    List<String> examineMenu = new ArrayList<>();
    examineMenu.addAll(itemList);
    examineMenu.addAll(fixtureList);
    examineMenu.add("Me");

    switch (commands) {
      case "X": { // examine
        view.buildItemMenu(examineMenu, selectedItem -> {
          if (selectedItem.equals("Me")) {
            view.showItemWindow(model.examineMe()[0], model.examineMe()[1]);
          } else if (itemList.contains(selectedItem)) {
            view.showItemWindow(model.getItemInfo(selectedItem)[0], model.getItemInfo(selectedItem)[1]);
          } else {
            view.showItemWindow(model.getFixtureInfo(selectedItem)[0], model.getFixtureInfo(selectedItem)[1]);
          }
        });
        closeGame();
        break;
      }
      case "T": { // take items from room
        view.buildItemMenu(itemList, selectedItem -> {
          String message = model.take(selectedItem);
          if (message.startsWith("Cannot")) {
            view.showMessage(message);
          }
          view.updateInventory(model.getInventoryItems());
          view.updateDescription(model.look()[0]); // update room items
        });
        closeGame();
        break;
      }
      case "inspect": { // inspect items in inventory
        String item = view.getSelectedInventoryItem();
        view.showItemWindow(model.getItemInfo(item)[0], model.getItemInfo(item)[1]);
        closeGame();
        break;
      }
      case "U": { // use items to solve puzzle or monster
        String item = view.getSelectedInventoryItem();
        String message = model.use(item);
        String attackMessage = model.monsterAttack();
        view.showMessage(message);
        String[] roomInfo = model.look();
        view.updateDescription(roomInfo[0] + "\n" + attackMessage);
        view.updatePicture(roomInfo[1]);
        view.updateInventory(model.getInventoryItems());
        view.setPlayerStatus(model.getPlayerStatus());
        closeGame();
        break;
      }
      case "D": { // drop items
        String item = view.getSelectedInventoryItem();
        model.drop(item);
        view.updateDescription(model.look()[0]);
        view.updateInventory(model.getInventoryItems());
        closeGame();
        break;
      }
      case "N", "S", "W", "E": { // move to specific direction
        String[] messages = model.movePlayer(commands); // {picture_name, msg}
        String attackMessage = model.monsterAttack();

        if (messages[0].equals(">> You cannot go in that direction! <<")) {
          view.showMessage(messages[0]);
        } else {
          view.updateDescription(messages[0] + "\n" + attackMessage);
          view.updatePicture(messages[1]);
        }
        view.setPlayerStatus(model.getPlayerStatus());
        closeGame();
        break;
      }
      case "A": { // answer puzzle
        String playerAnswer = view.promptAnswer("Enter your answer:", "ANSWER");
        view.showMessage(model.answer(playerAnswer.trim()));
        String[] roomInfo = model.look();
        view.updateDescription(roomInfo[0]);
        view.updatePicture(roomInfo[1]);
        closeGame();
        break;
      }
      case "exit": {
        URL path = getClass().getResource("/resources/images/nighty_night.png");
        view.showFileMessage(model.quit(), "Exit", path, view.exitEvent(), Font.PLAIN, 15);
        closeGame();
        break;
      }
      case "save": {
        view.showMessage("Saved your current game.");
        model.save();
        closeGame();
        break;
      }
      case "about": {
        URL path = getClass().getResource("/resources/images/game_engine.png");
        view.showFileMessage("CS5004 Game Engine", "About CS5004 Game Engine", path, null, Font.BOLD, 20);
        closeGame();
        break;
      }
      case "reload": {
        view.showMessage("Restored game!");
        model.reload();
        view.setWindowTitle(model.getGameName());
        String[] messages = model.look();
        view.updateDescription(messages[0]);
        view.updatePicture(messages[1]);
        view.updateInventory(model.getInventoryItems());
        view.setPlayerStatus(model.getPlayerStatus());
        closeGame();
        break;
      }
    }
  }

  @Override
  public void goGraphical() throws IOException {
    // prompt user to enter a name
    String name = view.promptUser("Enter a name for your Player Avatar:", "Avatar Name");
    if (name != null && !name.isBlank()) {
      model.reName(name);
    } else {
      model.reName("DefaultName");
    }

    // display picture and description at the beginning
    String[] messages = model.look();
    view.updateDescription(messages[0]);
    view.updatePicture(messages[1]);
    view.setPlayerStatus(model.getPlayerStatus());

    view.setWindowTitle(model.getGameName());

    view.display();
  }


  @Override
  public void goText() {

    boolean quit = false;
    this.commandReader.getUserName();
    model.reName(this.commandReader.getName());
    textView.showResult(model.firstMessage());

    while (!quit) {
      textView.showOptions();
      this.commandReader.getUserCommand();
      String command = commandReader.getCommand();
      String answer = commandReader.getAnswer();
      quit = handleCommand(command, answer);
      commandReader.setAnswerEmpty();
    }
  }

  @Override
  public void goBatch(String filePath) throws IOException {
    List<String> commands = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
    if (commands.isEmpty()) return;

    // 1. Read player name from first line
    String playerName = commands.get(0).trim();
    model.reName(playerName);

    // 2. Show game intro and welcome from model (fully driven by JSON)

    if (textView != null) {
      String welcome = String.join("\n",
              "Welcome to the adventure game!",
              "Enter your name for your player avatar: " + playerName,
              "You shalt now be named: " + playerName,
              "",
              model.firstMessage()
      );
      textView.showResult(welcome);
    }

    // 3. Process each command from line 1 onward
    boolean quit = false;

    for (int i = 1; i < commands.size() && !quit; i++) {
      String line = commands.get(i).trim();
      if (line.isBlank()) continue;

      String[] parts = line.split(" ", 2);
      String command = parts[0].toUpperCase();
      String argument = parts.length > 1 ? parts[1].trim() : "";
      quit = handleCommand(command, argument);
    }
  }

  /**
   * Helper: handle command for text mode.
   */
  private boolean handleCommand(String command, String answer) {
    switch (command) {
      case "N", "NORTH", "S", "SOUTH", "E", "EAST", "W", "WEST": {
        String roomMsg = model.movePlayer(command.substring(0, 1))[0];
        String monsterMsg = model.monsterAttack();
        String HP = model.getPlayerStatus();
        textView.showResult(roomMsg + monsterMsg + HP);
        return model.isEnd();

      }

      case "I", "INVENTORY": {
        textView.showResult(model.inventoryCheck());
        return model.isEnd();
      }

      case "L", "LOOK": {
        String look = model.look()[0];
        String monsterMsg = model.monsterAttack();
        String HP = model.getPlayerStatus();
        textView.showResult(look + monsterMsg + HP);
        return model.isEnd();
      }

      case "U", "USE": {
        String useMsg = model.use(answer);
        String monsterMsg = model.monsterAttack();
        String HP = model.getPlayerStatus();
        textView.showResult(useMsg + monsterMsg + HP);
        return model.isEnd();
      }

      case "T", "TAKE": {
        textView.showResult(model.take(answer));
        return model.isEnd();
      }

      case "D", "DROP": {
        textView.showResult(model.drop(answer));
        return model.isEnd();
      }

      case "X", "EXAMINE": {
        textView.showResult(model.examine(answer));
        return model.isEnd();
      }

      case "ME": {
        textView.showResult(model.examineMe()[0]);
        return model.isEnd();
      }

      case "A", "ANSWER": {
        textView.showResult(model.answer(answer));
        return model.isEnd();
      }

      case "SAVE": {
        textView.showResult(model.save());
        return model.isEnd();
      }

      case "RELOAD": {
        textView.showResult(model.reload());
        return model.isEnd();
      }

      case "Q", "QUIT": {
        textView.showResult(model.quit());
        return true;
      }

      default:
        textView.showResult("This command does not exist.");
        return false;
    }
  }

  /**
   * Helper: close game.
   */
  private void closeGame() {
    if (model.isEnd()) {
      String endMessage = model.userSleep();
      view.showExitMessage(endMessage);
    }
  }
}


