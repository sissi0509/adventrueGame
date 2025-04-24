package model.objects;

import java.util.List;

/**
 * Inventory class has weight limit and a list of items.
 */
public interface IInventory {
  /**
   * Add item to the list and change total weight of inventory.
   * Return true if success, false otherwise.
   */
  public boolean addItem(IItem item);

  /**
   * Remove item. Take item name as input and search for the item in the list.
   * Subtract the item weight from total weight.
   * Return true if success, false otherwise.
   */
  public boolean removeItem(String itemName);

  /**
   * Return the total weight of the inventory.
   */
  public Integer foldWeight();

  /**
   * Return all items' name.
   */
  public List<String> getItemList();

  /**
   * Return an Item object. Return null if not found.
   */
  public IItem getItem(String itemName);

  /**
   * Get weight limit.
   */
  public Integer getWeightLimit();

  /**
   * Get the inventory list.
   */
  public List<IItem> getInventoryList();
}
