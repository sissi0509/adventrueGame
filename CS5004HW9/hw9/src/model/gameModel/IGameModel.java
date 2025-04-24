package model.gameModel;

import java.util.List;

import model.dataClasses.GameData;
import model.objects.IGameObjects;
import model.objects.IPlayer;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * This is a game model interface.
 * GameModel initializes the game using game data.
 */
public interface IGameModel {

  /**
   * handle answer action.
   * @param answer answer input.
   * @return message related to the answer action.
   */
  String answer(String answer);

  /**
   * handle drop action.
   * @param itemName name of the item to be dropped.
   * @return message related to the drop action.
   */
  String drop(String itemName);

  /**
   * handle examine action.
   * @param name name of the objects to be examined.
   * @return message related to the examine action.
   */
  String examine(String name);

  /**
   * handle Inventory action.
   * @return message related to the check inventory action.
   */
  String inventoryCheck();

  /**
   * handle the look action.
   * @return message related to the look action.
   */
  String[] look();

  /**
   * handle the move related action, like n, s, w, e.
   * @param direction direction to be moved.
   * @return message related to the move action.
   */
  String[] movePlayer(String direction);

  /**
   * handle the take related action.
   * @param itemName name of the item to be taken.
   * @return message related to the take action.
   */
  String take(String itemName);

  /**
   * handle the use related action.
   * @param name name of the item to be used.
   * @return message related to the use action.
   */
  String use(String name);

  /**
   * handle the save action.
   * @return message related to the save action.
   */
  String save();

  /**
   * Get name of the saved file.
   * @return name of the saved fiel.
   */
  String getSavedName();

  /**
   * handle the reload action.
   * @return return message related to the reload action.
   */
  String reload();

  /**
   * handle the quit action.
   * @return message related to the quit action.
   */
  String quit();

  /**
   * set player name for the beginning of the game.
   * @param name name of the user choosed.
   */
  void reName(String name);

  /**
   * check if the game is ended.
   * @return ending message.
   */
  Boolean isEnd();

  /**
   * show beginning message of the game.
   * @return beginning message of the game.
   */
  String firstMessage();

  /**
   * getter of roomManager objects which is a map to handle room number to room.
   * @return roomManger of the game.
   */
  IRoomManager getRoomManager();

  /**
   * getter of the player.
   * @return the player object of the game.
   */
  IPlayer getPlayer();

  /**
   * Return the current room.
   */
  IRoom getCurrentRoom();

  /**
   * Get item list of inventory.
   */
  List<String> getInventoryItems();

  /**
   * Get GameObjects.
   * @return IGameObjects
   */
  IGameObjects getGameObjects();

  /**
   * Get GameData.
   * @return GameData.
   */
  GameData getGameData();

  /**
   * Get item name list of the current room.
   */
  List<String> getRoomItems();

  /**
   * Get item details from gameObjects.
   * Return {description, picture}.
   */
  String[] getItemInfo(String itemName);

  /**
   * Get player information when examine Me.
   * @return {description, picture}
   */
  String[] examineMe();

  /**
   * Get fixture name list of the current room.
   */
  List<String> getRoomFixtures();

  /**
   * Get fixture details from gameObjects.
   * Return {description, picture}.
   */
  String[] getFixtureInfo(String name);

  /**
   * Return message when monster attacks player.
   */
  String monsterAttack();

  /**
   * Return player's health status.
   */
  String getPlayerStatus();

  /**
   * Return game ending message.
   */
  String userSleep();

  /**
   * Get the name of the game.
   */
  String getGameName();
}
