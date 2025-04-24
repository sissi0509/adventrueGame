package model.utilityClass;

import model.objects.IInventory;
import model.objects.IPlayer;

/**
 * utility class to check inventory method.
 */
public class InventoryCheck {

  /**
   * show inventory item lists.
   * @param player of the game
   * @return inventory item lists.
   */
  public static String inventoryCheck(IPlayer player) {
    IInventory inventory = player.getInventory();

    if (inventory.foldWeight() == 0) {
      return "There is nothing in your inventory.";
    } else {
      return "Items in your inventory: " + String.join(", ", inventory.getItemList());
    }
  }
}