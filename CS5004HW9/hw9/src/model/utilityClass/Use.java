package model.utilityClass;

import model.objects.IItem;
import model.objects.IMonster;
import model.objects.IPlayer;
import model.objects.IPuzzle;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * create a utility class to handle use action.
 */
public class Use {

  /**
   * use item to solve puzzle and return the corresponding message.
   * it will not handle the remaining uses of item.
   * @param player player of the game.
   * @param item item to be used.
   * @param puzzle puzzle to be solved.
   * @param room room the player is in.
   * @return the message after item being used.
   * @throws IllegalArgumentException when any input is null.
   */
  private static String solvePuzzle(IPlayer player,
                                    IItem item,
                                    IPuzzle puzzle,
                                    IRoom room) throws IllegalArgumentException {
    if (player == null || item == null || puzzle == null || room == null) {
      throw new IllegalStateException("Inputs can not be null!");
    }
    // if item is correct.
    if (item.getName().equalsIgnoreCase(puzzle.getSolution())) {
      // deal with puzzle and room status
      puzzle.setActive();
      room.setConnectionToNormal();

      // add score
      player.setScore(puzzle.getValue());
      return "SUCCESS! "
              + item.getWhenUsed() + '\n';
    }
    // item is not correct.
    return "You try using the "
            + item.getName().toUpperCase()
            + " but nothing interesting happens.\n";
  }

  /**
   * use item to solve monster and return the corresponding message.
   * it will not handle the remaining uses of item.
   * @param player player of the game.
   * @param item item to be used.
   * @param monster monster to be defeated.
   * @param room room of the player.
   * @return the message after item being used.
   * @throws IllegalArgumentException when any input is null.
   */
  private static String attackMonster(IPlayer player,
                                      IItem item,
                                      IMonster monster,
                                      IRoom room) throws IllegalArgumentException {
    if (player == null || item == null || monster == null || room == null) {
      throw new IllegalStateException("Inputs can not be null!");
    }

    // if item is correct.
    if (item.getName().equalsIgnoreCase(monster.getSolution())) {
      // deal with monster and room status.
      monster.setActive(false);
      monster.setCanAttack(false);
      room.setConnectionToNormal();

      // add score
      player.setScore(monster.getValue());
      return "SUCCESS! "
              + item.getWhenUsed();
    }

    // item is not correct.
    return "You try using the "
            + item.getName().toUpperCase()
            + " but nothing interesting happens.\n";
  }

  /**
   * use item to solve puzzle or attack monster and return the corresponding message.
   * @param player the player play the game.
   * @param itemName the item name of the item to be used.
   * @param roomManager map(room number: room) of the room.
   * @return message after use based on different scenarios.
   * @throws IllegalArgumentException when any input is null.
   */
  public static String use(IPlayer player,
                           String itemName,
                           IRoomManager roomManager) throws IllegalArgumentException {
    if (player == null || itemName == null || roomManager == null) {
      throw new IllegalStateException("Inputs can nt be null!");
    }
    IItem item = player.getInventory().getItem(itemName);
    // if item can not be used.
    if (item == null || item.getUsesRemaining() <= 0) {
      return "Oh no! "
              + itemName
              + " is either empty or cannot be used again!\n";
    }

    // use correct or not correct will all decrease usesRemaining.
    item.setUsesRemaining();

    // get room related information.
    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());
    IPuzzle puzzle = room.getPuzzle();
    IMonster monster = room.getMonster();

    // nothing required to be solved in the room
    if ((puzzle == null && monster == null)
            || (monster != null && !monster.getActive())
            || (puzzle != null && !puzzle.getActive())) {
      return "You try using the "
              + itemName.toUpperCase()
              + " but nothing interesting happens.\n";
    }

    // there is an active puzzle.
    if (puzzle != null) {
      return solvePuzzle(player, item, puzzle, room);
    }

    // there is an active monster.
    return attackMonster(player, item, monster, room);
  }
}