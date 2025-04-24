package model.utilityClass;

import model.objects.IFixture;
import model.objects.IItem;
import model.objects.IPlayer;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * Utility class for examine action, examine item and fixture.
 */
public class Examine {

  /**
   * examine item and fixture, return message for examine.
   * @param player player of the game.
   * @param roomManager map(room number: room) of the room.
   * @param name name of the objects to be examined.
   * @return description of the objects being examined.
   */
  public static String examine(IPlayer player, IRoomManager roomManager, String name) {
    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());

    // check item
    for (IItem item: room.getItems()) {
      if (item.getName().equalsIgnoreCase(name)) {
        return "From the " + room.getName() + " you examine the "
                + name + ": " + item.getDescription();
      }
    }

    for (IItem item: player.getInventory().getInventoryList()) {
      if (item.getName().equalsIgnoreCase(name)) {
        return "From your inventory" + " you examine the "
                + name + ": " + item.getDescription();
      }
    }

    // check fixture
    for (IFixture IFixture : room.getFixtures()) {
      if (IFixture.getName().equalsIgnoreCase(name)) {
        return "From the " + room.getName() + " you examine the "
                + name + ": " + IFixture.getDescription();
      }
    }

    // If not found
    return "There's no item or fixture named " + name + " here.";
  }
}