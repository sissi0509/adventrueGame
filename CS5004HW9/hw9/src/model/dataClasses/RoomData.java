package model.dataClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import model.objects.IRoom;

/**
 * create a dataclass for Room.
 */
public class RoomData {
  @SerializedName("room_name")
  private final String roomName;

  @SerializedName("room_number")
  private final Integer roomNumber;

  private final String description;
  private final Integer N, S, E, W;
  private final String puzzle;
  private final String monster;
  private final String items;
  private final String fixtures;
  private final String picture;


  /**
   * constructor of the RoomData.
   * create a roomData object based on a room object.
   * @param room room object.
   */
  public RoomData(IRoom room) {
    this.roomName = room.getName();
    this.roomNumber = room.getRoomNumber();
    this.description = room.getDescription();
    this.E = room.getConnections().get("E");
    this.S = room.getConnections().get("S");
    this.N = room.getConnections().get("N");
    this.W = room.getConnections().get("W");
    this.puzzle = (room.getPuzzle() != null) ? room.getPuzzle().getName() : null;
    this.monster = (room.getMonster() != null) ? room.getMonster().getName() : null;
    this.items = String.join(", ", room.getItemList());
    List<String> names = room.getFixtures().stream().map(each -> each.getName()).toList();
    String fixtureNames = String.join(", ", names);
    this.fixtures = fixtureNames;
    this.picture = room.getPicture();
  }

  /**
   * Constructor for input parameters.
   */
  public RoomData(String description, Integer n, Integer s, Integer e, Integer w, String puzzle, String monster,
                  String items, String fixtures, String picture, Integer roomNumber, String roomName) {
    this.description = description;
    this.N = n;
    this.S = s;
    this.E = e;
    this.W = w;
    this.puzzle = puzzle;
    this.monster = monster;
    this.items = items;
    this.fixtures = fixtures;
    this.picture = picture;
    this.roomNumber = roomNumber;
    this.roomName = roomName;
  }

  /**
   * getter of room name.
   * @return string of room name.
   */
  public String getRoomName() {
    return roomName;
  }

  /**
   * getter of the room number.
   * @return room number.
   */
  public Integer getRoomNumber() {
    return roomNumber;
  }

  /**
   * getter of description.
   * @return room description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * getter of north room.
   * @return north room number.
   */
  public Integer getN() {
    return N;
  }

  /**
   * getter of south room.
   * @return south room number.
   */
  public Integer getS() {
    return S;
  }

  /**
   * getter of the east room.
   * @return east room number.
   */
  public Integer getE() {
    return E;
  }

  /**
   * getter of the west room.
   * @return west room number.
   */
  public Integer getW() {
    return W;
  }

  /**
   * getter of the puzzle in the room.
   * @return puzzle name in the room.
   */
  public String getPuzzle() {
    return puzzle;
  }

  /**
   * getter of picture of the room.
   * @return picture name of the room.
   */
  public String getPicture() {
    return picture;
  }

  /**
   * getter of items in the room.
   * @return items names of the room.
   */
  public String getItems() {
    return items;
  }

  /**
   * getter of monster in the room.
   * @return monster name in the room.
   */
  public String getMonster() {
    return monster;
  }

  /**
   * getter of fixture in the room.
   * @return fixtures names in the room.
   */
  public String getFixtures() {
    return fixtures;
  }
}