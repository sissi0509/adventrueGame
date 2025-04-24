package model.utilityClass;

import model.objects.IInventory;
import model.objects.IItem;
import model.objects.IPlayer;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * This is a utility class for taking item from room.
 * Return message for user.
 */
public class TakeItem {

  /**
   * handle take item action and return corresponding message.
   * @param player player of the game.
   * @param roomManager map(room number: room) of the room.
   * @param itemName name of the item to be taken.
   * @return message when take the item.
   */
  public static String takeItem(IPlayer player, IRoomManager roomManager, String itemName) {
    IItem item = null;
    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());

    // Search for the item in the room's item list
    for (IItem i: room.getItems()) {
      if (i.getName().equalsIgnoreCase(itemName)) {
        item = i;
        break;
      }
    }

    // if item not found
    if (item == null) {
      return itemName + " not found in the room.";
    }

    // Add item to inventory. If success, remove item from room.
    IInventory inventory = player.getInventory();
    if (inventory.addItem(item)) {
      room.removeItem(itemName);
      player.setScore(item.getValue());
      return itemName + " added to inventory.";
    } else {
      return "Cannot add item. Weight limit exceeded.";
    }
  }
}