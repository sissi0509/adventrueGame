package model.utilityClass;

import model.objects.IPlayer;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * create a utility class to handle move
 */
public class Move {

  /**
   * move method to handle the movement
   * @param player the player of the room
   * @param direction the direction want to move !!!! maybe should use enum.
   * @param roomManager map(room number: room) of the room.
   * @return array {description, picture}
   * @throws IllegalArgumentException when player or roomManager is null.
   */
  public static String[] move(IPlayer player, String direction, IRoomManager roomManager)
          throws IllegalArgumentException {
    //check null input.
    if (player == null || roomManager == null) {
      throw new IllegalArgumentException("No null input allowed.");
    }

    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());

    if (room.getConnections().get(direction) <= 0) {
      return new String[]{">> You cannot go in that direction! <<", ""};
    } else {
      int newRoomNumber = room.getConnections().get(direction);
      player.setRoomNumber(newRoomNumber);
      return Look.firstEnter(player, roomManager); // {picture_name, msg}
    }
  }
}