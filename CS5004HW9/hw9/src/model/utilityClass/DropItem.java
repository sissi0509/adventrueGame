package model.utilityClass;

import model.objects.IInventory;
import model.objects.IItem;
import model.objects.IPlayer;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * Utility class to handle the drop item movement.
 */
public class DropItem {
  /**
   * drop an item from the player's inventory and add it to the room.
   * decrease player's score because of the drop.
   * @param player player of the game.
   * @param roomManager map(room number: room) of the room.
   * @param itemName name of the item
   * @return them message when item is dropped.
   */
  public static String dropItem(IPlayer player, IRoomManager roomManager, String itemName) {
    IInventory inventory = player.getInventory();
    IItem item = inventory.getItem(itemName);
    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());

    if (inventory.removeItem(itemName)) { // successfully remove from inventory
      room.addItem(item); // add item to room
      player.setScore(item.getValue() * -1);
      return itemName + " dropped here in " + room.getName();
    } else {
      return itemName + " is NOT in your inventory. Nothing dropped.";
    }
  }
}