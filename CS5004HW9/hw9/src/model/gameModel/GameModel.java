package model.gameModel;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dataClasses.FixtureData;
import model.dataClasses.GameData;
import model.dataClasses.ItemData;
import model.dataClasses.MonsterData;
import model.dataClasses.PuzzleData;
import model.dataClasses.RoomData;
import model.objects.Fixture;
import model.objects.GameObjects;
import model.objects.IFixture;
import model.objects.IGameObjects;
import model.objects.IInventory;
import model.objects.IItem;
import model.objects.IMonster;
import model.objects.IPlayer;
import model.objects.IPuzzle;
import model.objects.IRoom;
import model.objects.IRoomManager;
import model.objects.Inventory;
import model.objects.Item;
import model.objects.Monster;
import model.objects.Player;
import model.objects.Puzzle;
import model.objects.Room;
import model.utilityClass.*;
import model.objects.RoomManager;

/**
 * This is a game model class.
 * GameModel initializes the game using game data.
 */
public class GameModel implements IGameModel {
  private IRoomManager roomManager;
  private IPlayer player;
  private IGameObjects gameObjects;
  private String savedName;
  private GameData gameData;

  private static final String genericItem = "generic_item.png";

  /**
   * Constructor.
   */
  public GameModel(GameData data, String playerName, String savedName) {
    this.gameData = data;
    initializeRoomsFromGameData(this.gameData, playerName);
    this.savedName = savedName;
  }

  /**
   * Create puzzle objects from puzzleData for specific room.
   */
  private IPuzzle createPuzzleForRoom(RoomData roomData, List<PuzzleData> puzzleDataList) {
    if (roomData.getPuzzle() == null || puzzleDataList == null) return null;
    for (PuzzleData p : puzzleDataList) {
      if (p.getName().equalsIgnoreCase(roomData.getPuzzle())) {
        return Puzzle.createIfValid(p);
      }
    }
    return null;
  }

  /**
   * Create list of item objects from itemData for specific room.
   * Create the item only when information is complete.
   */
  private List<IItem> createRoomItems(RoomData roomData, List<ItemData> itemDataList) {
    List<IItem> roomItems = new ArrayList<>();
    if (roomData.getItems() == null || itemDataList == null) return roomItems;
    for (String itemName : roomData.getItems().split(", ?")) {
      itemDataList.stream()
              .filter(i -> i.getName().equalsIgnoreCase(itemName))
              .findFirst()
              .ifPresent(itemData -> {
                IItem item = Item.createIfValid(itemData);
                if (item != null) {
                  roomItems.add(item);
                }
              });
    }
    return roomItems;
  }

  /**
   * Create monster object from monsterData for specific room.
   */
  private Monster createMonsterForRoom(RoomData roomData, List<MonsterData> monsterDataList) {
    if (roomData.getMonster() == null || monsterDataList == null) return null;
    for (MonsterData m : monsterDataList) {
      if (m.getName().equalsIgnoreCase(roomData.getMonster())) {
        return Monster.createIfValid(m);
      }
    }
    return null;
  }

  /**
   *  Create fixture objects list from itemData for specific room.
   *  Create the fixture, only if information is complete.
   */
  private List<IFixture> createFixturesForRoom(RoomData roomData, List<FixtureData> fixtureDataList) {
    List<IFixture> fixtures = new ArrayList<>();
    if (roomData.getFixtures() == null || fixtureDataList == null) return fixtures;
    for (String fixtureName : roomData.getFixtures().split(", ?")) {
      fixtureDataList.stream()
              .filter(f -> f.getName().equalsIgnoreCase(fixtureName))
              .findFirst()
              .ifPresent(fd -> {
                IFixture fixture = Fixture.createIfValid(fd);
                if (fixture != null) {
                  fixtures.add(fixture);
                }
              });
    }
    return fixtures;
  }

  /**
   * Create an inventory object.
   */
  private IInventory createInventory(GameData data) {
    IInventory inventory = new Inventory();
    if (data.getInventory() == null || data.getItemData() == null) return inventory;
    for (String itemName : data.getInventory().getInventoryList().split(", ?")) {
      data.getItemData().stream()
              .filter(i -> i.getName().equalsIgnoreCase(itemName))
              .findFirst()
              .ifPresent(itemData -> {
                IItem item = Item.createIfValid(itemData);
                if (item != null) {
                  inventory.addItem(item);
                }
              });
    }
    return inventory;
  }

  /**
   * Create a player object.
   */
  private IPlayer createPlayer(GameData data, Map<Integer, IRoom> RoomMap, String playerName) {
    if (data.getPlayer() != null) {
      IInventory inventory = createInventory(data);
      IRoom startingRoom = RoomMap.get(data.getPlayer().getRoomNumber());
      return new Player(data.getPlayer(), inventory);
    } else {
      return new Player(playerName);
    }
  }

  /**
   * Create room related objects, player and inventory if data exists.
   */
  private void initializeRoomsFromGameData(GameData data, String playerName) {
    List<IRoom> roomList = new ArrayList<>();
    List<IItem> itemList = new ArrayList<>();
    List<IFixture> fixtureList = new ArrayList<>();
    List<IMonster> monsterList = new ArrayList<>();
    List<IPuzzle> puzzleList = new ArrayList<>();
    Map<Integer, IRoom> tempRoomMap = new HashMap<>();
    String gameName = data.getName();

    for (RoomData roomData : data.getRoomData()) {
      IPuzzle puzzle = createPuzzleForRoom(roomData, data.getPuzzleData());
      if (puzzle != null) puzzleList.add(puzzle);

      List<IItem> roomItems = createRoomItems(roomData, data.getItemData());
      itemList.addAll(roomItems);

      Monster monster = createMonsterForRoom(roomData, data.getMonsterData());
      if (monster != null) monsterList.add(monster);

      List<IFixture> roomFixtures = createFixturesForRoom(roomData, data.getFixtureData());
      fixtureList.addAll(roomFixtures);

      Room room = new Room(roomData, roomItems, puzzle, monster, roomFixtures);
      roomList.add(room);
      tempRoomMap.put(room.getRoomNumber(), room);
    }

    this.roomManager = new RoomManager(tempRoomMap);

    this.player = createPlayer(data, tempRoomMap, playerName);
    itemList.addAll(this.player.getInventory().getInventoryList());
    this.gameObjects = new GameObjects(roomList, itemList, fixtureList, monsterList, puzzleList, gameName);

  }


  @Override
  public IRoomManager getRoomManager() { return roomManager; }

  @Override
  public IPlayer getPlayer() { return player; }

  @Override
  public String getSavedName() {
    return savedName;
  }

  @Override
  public String reload()  {
    Gson gson = new Gson();

    try  {
      String savePath = System.getProperty("user.dir") + File.separator + "saveFolder" + File.separator + "save.json";
      FileReader reader = new FileReader(savePath);
      GameData saved = gson.fromJson(reader, GameData.class);
      // Rebuild game objects
      initializeRoomsFromGameData(saved, "A4");
      this.gameData = saved;
      return "Reload finished" ;
    } catch (FileNotFoundException e) {
      return "No saved version";
    }
  }

  @Override
  public void reName(String name) {
    this.player.reName(name);
  }

  @Override
  public String answer(String answer){
    return Answer.answer(this.player, answer, this.roomManager);
  }

  @Override
  public String drop(String itemName) {
    return DropItem.dropItem(this.player, this.roomManager, itemName);
  }

  @Override
  public String examine(String name) {
    return Examine.examine(this.player, this.roomManager, name);
  }

  @Override
  public String inventoryCheck() {
    return InventoryCheck.inventoryCheck(this.player);
  }

  @Override
  public String[] look() {
    return Look.lookByPlayer(this.player, this.roomManager);
  }

  @Override
  public String[] movePlayer(String direction){
    return Move.move(this.player, direction, this.roomManager);
  }

  @Override
  public String take(String itemName) {
    return TakeItem.takeItem(this.player, this.roomManager, itemName);
  }

  @Override
  public String use(String name) {
    return Use.use(this.player, name, this.roomManager);
  }

  @Override
  public String monsterAttack() {
      return CheckAttack.checkAttack(this.player, this.roomManager);
  }

  @Override
  public String save() {
    String savePath = System.getProperty("user.dir") + File.separator + "saveFolder" + File.separator;
    return Save.save(this.gameObjects, this.player, this.savedName, savePath);
  }

  @Override
  public String quit() {
    return GameEnd.quit(this.player);
  }

  @Override
  public Boolean isEnd(){
    return this.player.getHealth() <= 0;
  }

  @Override
  public String firstMessage() {
    IRoom room = this.roomManager.getRoomByNumber(player.getRoomNumber());
    return "You start your adventure in the " + room.getName() + "\n"
            + Look.firstEnter(player, roomManager)[0];
  }

  @Override
  public String getPlayerStatus() {
    return "Your health status: " + player.getPlayerStatus() + "\n";
  }

  @Override
  public IRoom getCurrentRoom() {
    return this.roomManager.getRoomByNumber(player.getRoomNumber());
  }

  @Override
  public IGameObjects getGameObjects() {
    return this.gameObjects;
  }

  @Override
  public GameData getGameData() {
    return this.gameData;
  }

  @Override
  public List<String> getRoomItems() {
    IRoom room = getCurrentRoom();
    return room.getItemList();
  }

  @Override
  public String[] getItemInfo(String itemName) {
    String[] arr = new String[2];
    for (IItem item: this.gameObjects.getItemList()) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        arr[0] = item.getDescription();
        if (item.getPicture() != null) {
          arr[1] = item.getPicture();
        } else {
          arr[1] = genericItem;
        }
        break;
      }
    }
    return arr;
  }

  @Override
  public List<String> getInventoryItems() {
    return this.player.getInventory().getItemList();
  }

  @Override
  public String[] examineMe() {
    return new String[] {Me.me(this.player), genericItem};
  }

  @Override
  public List<String> getRoomFixtures() {
    IRoom room = getCurrentRoom();
    List<String> fixtureNames = new ArrayList<>();
    for (IFixture fixture : room.getFixtures()) {
      fixtureNames.add(fixture.getName());
    }
    return fixtureNames;
  }

  @Override
  public String[] getFixtureInfo(String name) {
    String[] arr = new String[2];
    for (IFixture fixture: this.gameObjects.getFixtureList()) {
      if (fixture.getName().equalsIgnoreCase(name)) {
        arr[0] = fixture.getDescription();
        if (fixture.getPicture() != null) {
          arr[1] = fixture.getPicture();
        } else {
          arr[1] = genericItem;
        }
        break;
      }
    }
    return arr;
  }

  @Override
  public String userSleep() {
    return GameEnd.showEnd(this.player);
  }

  @Override
  public String getGameName() {
    return this.gameData.getName();
  }
}