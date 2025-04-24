package model.dataClasses;

import model.objects.IInventory;


/**
 * Data class that holds serializable information about inventory in the game world.
 *
 */
public class InventoryData {
  private String inventoryList;
  private Integer totalWeight;

  /**
   * Constructs an inventory data class based on the IInventory.
   *
   * @param inventory The IInventory object containing the current item list and weight.
   */

  public InventoryData(IInventory inventory) {
    this.inventoryList = String.join(", ", inventory.getItemList());
    this.totalWeight = inventory.foldWeight();
  }

  /**
   * Constructor based on input parameters.
   */
  public InventoryData(String inventoryList, Integer totalWeight) {
    this.inventoryList = inventoryList;
    this.totalWeight = totalWeight;
  }

  /**
   * Get the list of the inventory.
   * @return the list of inventory.
   */

  public String getInventoryList() {
    return inventoryList;
  }

  /**
   * Get the total weight of the items in the inventory.
   * @return the total weight of the inventory.
   */

  public Integer getTotalWeight() {
    return totalWeight;
  }
}