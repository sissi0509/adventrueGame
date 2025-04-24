package model.dataClasses;

import java.util.*;
import java.util.stream.Collectors;

import model.objects.IGameObjects;
import model.objects.IPlayer;

/**
 * A data class that holds serializable information about a complete game data state.
 * This class is used to represent the full structure of the game, including rooms, items,
 * puzzles, monsters, player and inventory.
 */
public class GameData {
  private String name;
  private String version;
  private List<RoomData> rooms;
  private List<ItemData> items;
  private List<PuzzleData> puzzles;
  private List<FixtureData> fixtures;
  private List<MonsterData> monsters;
  private PlayerData player;
  private InventoryData inventory;

  /**
   *Constructs a game data based on the objects and player.
   *
   * @param IGameObjects The game objects for this engine.
   * @param player the current player in the room.
   */

  public GameData(IGameObjects IGameObjects, IPlayer player) {
    this.player = new PlayerData(player);

    this.inventory = new InventoryData(player.getInventory());

    this.rooms = IGameObjects.getRoomList().stream()
            .map(RoomData::new)
            .collect(Collectors.toList());

    this.items = IGameObjects.getItemList().stream()
            .map(ItemData::new)
            .collect(Collectors.toList());

    this.puzzles = IGameObjects.getPuzzleList().stream()
            .map(PuzzleData::new)
            .collect(Collectors.toList());

    this.monsters = IGameObjects.getMonsterList().stream()
            .map(MonsterData::new)
            .collect(Collectors.toList());

    this.fixtures = IGameObjects.getFixtureList().stream()
            .map(FixtureData::new)
            .collect(Collectors.toList());

    this.name = IGameObjects.getGameName();
  }


  /**
   * Get the name of game.
   *
   * @return return the game name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the list of the room data in the room.
   *
   * @return the list of RoomData objects.
   */
  public List<RoomData> getRoomData() {
    return rooms;
  }

  /**
   * Get the list of item data in the game.
   *
   * @return the list of ItemData objects.
   */
  public List<ItemData> getItemData() {
    return items;
  }

  /**
   * Get the list of fixture data in the game.
   *
   * @return the list of FixtureData objects.
   */
  public List<FixtureData> getFixtureData() {
    return fixtures;
  }

  /**
   * Get the list of puzzle data in the game.
   *
   * @return the list of puzzleData objects.
   */

  public List<PuzzleData> getPuzzleData() {
    return puzzles;
  }

  /**
   * Get the list of monster data in the game.
   *
   * @return the list of monster data objects.
   */

  public List<MonsterData> getMonsterData() {
    return monsters;
  }

  /**
   * Get the player data in the game.
   *
   */

  public PlayerData getPlayer() {
    return player;
  }

  /**
   * Get inventory data.
   */
  public InventoryData getInventory() {
    return inventory;
  }
}