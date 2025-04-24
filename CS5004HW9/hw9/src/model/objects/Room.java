package model.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dataClasses.RoomData;

/**
 * RoomImpl class that represents a room. room has name,
 * name, roomNumber, dsecription, connections, items, fixtures,
 * monster, puzzle and picture.
 */
public class Room implements IRoom {
  private String name;
  private Integer roomNumber;
  private String description;
  private Map<String, Integer> connections;
  private List<IItem> items;
  private List<IFixture> fixtures;
  private IMonster monster;
  private IPuzzle puzzle;
  private String picture;

  /**
   * Constructor for RoomImpl class. RoomImpl class get
   * data of a room.
   * @param data RoomData type data for room.
   */
  public Room(RoomData data, List<IItem> roomItems, IPuzzle puzzle, IMonster monster, List<IFixture> roomIFixtures) {
    this.name = data.getRoomName();
    this.roomNumber = data.getRoomNumber();
    this.description = data.getDescription();
    this.connections = createConnections(data);
    this.items = roomItems;
    this.fixtures = roomIFixtures;
    this.monster = monster;
    this.puzzle = puzzle;
    this.picture = data.getPicture();
  }

  /**
   * Method that create Room if the data is valid.
   * @param data Data of room from Json
   * @param roomItems item list of room
   * @param puzzle puzzle in room
   * @param monster monster in room
   * @param roomIFixtures The list of Fixture in room.
   * @return
   */
  public static Room createIfValid(RoomData data, List<IItem> roomItems, IPuzzle puzzle, IMonster monster, List<IFixture> roomIFixtures) {
    // checks missing data.
    // how about puzzle monster and fixture
    if ((data.getRoomName() == null || data.getRoomNumber() == null) || data.getDescription() == null
            || data.getRoomName().isBlank() || data.getRoomNumber() <= 0 || data.getDescription().isBlank()
            || (data.getN() == null || data.getS() == null || data.getE() == null
            || data.getW() == null)) {
      return null;
    }

    return new Room(data, roomItems, puzzle, monster, roomIFixtures);
  }

  /**
   * Method that creates the connection into Map data type.
   * @param data Data of room.
   * @return Map that has connection to other room.
   */
  private Map<String, Integer> createConnections(RoomData data) {
    Map<String, Integer> map = new HashMap<>();
    map.put("N", data.getN());
    map.put("S", data.getS());
    map.put("E", data.getE());
    map.put("W", data.getW());

    return map;
  }

  /**
   * Gets the name of a room.
   * @return name of a room.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the roomNumber of a room.
   * @return roomNumber of a room.
   */
  @Override
  public Integer getRoomNumber() {
    return this.roomNumber;
  }

  /**
   * Gets the description of a room.
   * @return description of a room.
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the Map that contains the connections of the room to other rooms.
   * @return Map that has connections to other rooms.
   */
  @Override
  public Map<String, Integer> getConnections() {
    return this.connections;
  }

  /**
   * Gets the list of items in a room.
   * @return list of items in a room.
   */
  @Override
  public List<IItem> getItems() {
    return this.items;
  }

  /**
   * Gets the list of fixtures in a room.
   * @return list of fixtures in a room.
   */
  @Override
  public List<IFixture> getFixtures() {
    return this.fixtures;
  }

  /**
   * Gets the monster in a room.
   * @return monster in a room.
   */
  @Override
  public IMonster getMonster() {
    return this.monster;
  }

  /**
   * Gets the puzzle in a room.
   * @return puzzle in a room.
   */
  @Override
  public IPuzzle getPuzzle() {
    return this.puzzle;
  }

  /**
   * Get the String that contains all the names of the items in
   * a room
   *
   * @return String that has name of items.
   */
  @Override
  public List<String> getItemList() {
    List<String> itemNames = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      itemNames.add(items.get(i).getName());
    }
    return itemNames;
  }

  /**
   * Set the connection of the room by change the sign of the roomNumber.
   * @param direction direction of the connected room.
   * @param roomName name of the connected room.
   */
  @Override
  public void setConnectionRoom(String direction, String roomName) {
    if (connections.containsKey(direction)) {
      connections.replace(direction, Math.abs(roomNumber));
    }
  }

  /**
   * Set the current room's to normal environment.
   */
  @Override
  public void setConnectionToNormal() {
    for (Map.Entry<String, Integer> each : connections.entrySet()) {
      each.setValue(Math.abs(each.getValue()));
    }
  }

  /**
   * Removes the item in the room.
   * @param itemName name of the item.
   */
  @Override
  public void removeItem(String itemName) {
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getName().equalsIgnoreCase(itemName)) {
        items.remove(i);
      }
    }
  }

  /**
   * Adds the item in the room.
   * @param item item that player dropped.
   */
  @Override
  public void addItem(IItem item) {
    items.add(item);
  }

  public String getPicture() {
    return picture;
  }
}
