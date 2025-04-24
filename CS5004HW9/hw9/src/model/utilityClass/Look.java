package model.utilityClass;

import model.objects.IMonster;
import model.objects.IPlayer;
import model.objects.IPuzzle;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * Return the description of the room where the player is.
 * {description, picture_name, text_msg}.
 */
public class Look {
  private static final String genericRoom = "generic_location.png";
  private static final String genericMonster = "generic_monster.png";
  private static final String genericPuzzle = "generic_puzzle.png";

  /**
   * Helper function for lookByPlayer.
   * Handle monster and puzzle.
   * if there is a monster or a puzzle,
   * will return monster or puzzle's information instead of room
   * else will return the room information.
   * @param room current room.
   * @return array {description, picture}
   */
  private static String[] look(IRoom room) {
    IMonster monster = room.getMonster(); // should be null if there's no monster
    IPuzzle puzzle = room.getPuzzle(); // should be null if there's no monster
    String[] arr = new String[2];

    // check if monster or puzzle exists
    if (monster != null && monster.getActive()) {
      arr[0] =  monster.getEffect() + "\n"
              + monster.getName() + " " + monster.getAttack() + "\n";
      if (monster.getPicture() != null) {
        arr[1] = monster.getPicture();
      } else {
        arr[1] = genericMonster;
      }
    } else if (puzzle != null && puzzle.getActive()) {
      arr[0] =  puzzle.getEffect();
      if (puzzle.getPicture() != null) {
        arr[1] = puzzle.getPicture();
      } else {
        arr[1] = genericPuzzle;
      }
    } else {
      // print room description when puzzle or monster is inactive
      arr[0] = room.getDescription();
      if (room.getPicture() != null) {
        arr[1] = room.getPicture();
      } else {
        arr[1] = genericRoom;
      }
    }
    return arr;
  }

  /**
   * Call this method when player type look and show corresponding message.
   * @param player player of the game.
   * @param roomManager map(room number: room) of the room.
   * @return {picture_name, msg}.
   */
  public static String[] lookByPlayer(IPlayer player, IRoomManager roomManager) {
    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());
    IMonster monster = room.getMonster();

    String [] desAndPic = look(room);

    String msg = "You are standing in the " + room.getName() + "\n"
            + desAndPic[0] + "\n"
            + "Items you see here: " + String.join(", ", room.getItemList()) + "\n";

    return new String[]{msg, desAndPic[1]};
  }

  /**
   * return message for player when user enters a room.
   * @param player player of the game.
   * @param roomManager map(room number: room) of the room.
   * @return {picture_name, msg}.
   */
  public static String[] firstEnter(IPlayer player, IRoomManager roomManager) {
    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());
    IMonster monster = room.getMonster();

    String [] desAndPic = look(room);

    String msg = "You are entering the " + room.getName() + "\n"
            + desAndPic[0] + "\n"
            + "Items you see here: " + String.join(", ", room.getItemList()) + "\n";

    // {description, picture_name, text_msg, playerHP_msg}
    return new String[]{msg, desAndPic[1]};
  }
}