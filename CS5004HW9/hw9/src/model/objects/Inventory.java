package model.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * create a concrete inventory class.
 */
public class Inventory implements IInventory {
  private static final Integer WEIGHT_LIMIT = 13; // read from datafile or set an instant?
  private List<IItem> inventoryList;
  private Integer totalWeight;

  /**
   * Constructor. Set empty list.
   */
  public Inventory() {
    this.inventoryList = new ArrayList<>();
    this.totalWeight = 0;
  }

  /**
   * Method that add item to the inventory.
   * @param item item
   * @return boolean tells if the item is successfully added.
   */
  @Override
  public boolean addItem(IItem item) {
    if (this.totalWeight + item.getWeight() <= WEIGHT_LIMIT) {
      this.inventoryList.add(item);
      this.totalWeight += item.getWeight();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method that remove the item from inventory.
   * @param itemName item name to remove.
   * @return boolean tells item is successfully removed.
   */
  @Override
  public boolean removeItem(String itemName) {
    IItem item = getItem(itemName);
    if (item != null) {
      inventoryList.remove(item);
      this.totalWeight -= item.getWeight();
      return true;
    }
    return false;
  }

  /**
   * Method that returns the total weight of item in inventory.
   * @return total weight of item.
   */
  @Override
  public Integer foldWeight() {
    return this.totalWeight;
  }

  /**
   * Method that tells the list of item.
   * @return list of items.
   */
  @Override
  public List<String> getItemList() {
    List<String> itemList = new ArrayList<>();
    for (IItem item : inventoryList) {
      itemList.add(item.getName());
    }
    return itemList;
  }

  /**
   * Method that returns the item after searching with item name.
   * @param itemName item name
   * @return item.
   */
  @Override
  public IItem getItem(String itemName) {
    if (itemName == null) {
      return null;

    }
    for (IItem item: inventoryList) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return item;
      }
    }
    return null; // item not found
  }

  /**
   * Method that returns the weight limit of the item.
   * @return weight limit of the item.
   */
  @Override
  public Integer getWeightLimit() {
    return WEIGHT_LIMIT;
  }

  /**
   * Method that get the list of item.
   * @return list of item.
   */
  @Override
  public List<IItem> getInventoryList() {
    return this.inventoryList;
  }
}
