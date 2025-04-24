package model.objects;

import java.util.List;
import java.util.Map;

/**
 * Interface that represents the Room
 */
public interface IRoom {
  /**
   * Gets the name of a room.
   * @return name of a room.
   */
  public String getName();

  /**
   * Gets the roomNumber of a room.
   * @return roomNumber of a room.
   */
  public Integer getRoomNumber();

  /**
   * Gets the description of a room.
   * @return description of a room.
   */
  public String getDescription();

  /**
   * Gets the Map that contains the connections of the room to other rooms.
   * @return Map that has connections to other rooms.
   */
  public Map<String, Integer> getConnections();

  /**
   * Gets the list of items in a room.
   * @return list of items in a room.
   */
  public List<IItem> getItems();

  /**
   * Gets the list of fixtures in a room.
   * @return list of fixtures in a room.
   */
  public List<IFixture> getFixtures();

  /**
   * Gets the monster in a room.
   * @return monster in a room.
   */
  public IMonster getMonster();

  /**
   * Gets the puzzle in a room.
   * @return puzzle in a room.
   */
  public IPuzzle getPuzzle();

  /**
   * gets list of item names in a room.
   *
   * @return list of item names in a room.
   */
  public List<String> getItemList();

  /**
   * Set the connection of the room by change the sign of the roomNumber.
   * @param direction direction of the connected room.
   * @param roomName name of the connected room.
   */
  public void setConnectionRoom(String direction, String roomName);

  /**
   * Set the current room's to normal environment.
   */
  public void setConnectionToNormal();

  /**
   * Removes the item in the room.
   * @param itemName name of the item.
   */
  public void removeItem(String itemName);

  /**
   * Adds the item in the room.
   * @param item item object.
   */
  public void addItem(IItem item);

  /**
   * Get picture name of the room.
   * @return picture name of the room.
   */
  public String getPicture();
}
