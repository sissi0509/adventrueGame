package model.objects;

import model.dataClasses.ItemData;

/**
 * create a class of Item.
 */
public class Item implements IItem {
  private String name;
  private Integer weight;
  private Integer maxUses;
  private Integer usesRemaining;
  private String whenUsed;
  private Integer value;
  private String description;
  private String picture;

  /**
   * constructor got data from data class.
   * @param data data from ItemData.
   * @throws IllegalArgumentException
   *            when name or whenUsed or description is null or blank,
   *            or when weight or maxUses or value is negative or null
   */
  public Item(ItemData data) {
    this.name = data.getName();
    this.weight = data.getWeight();
    this.maxUses = data.getMaxUses();
    this.usesRemaining = data.getUsesRemaining();
    this.whenUsed = data.getWhenUsed();
    this.value = data.getValue();
    this.description = data.getDescription();
    this.picture = data.getPicture();
  }

  /**
   * This is a method to create Item if data is valid. Otherwise, return null.
   * @param data ItemData from Json.
   * @return new Item object if data is valid, otherwise null.
   */
  public static Item createIfValid(ItemData data) {
    if (
            data.getName() == null || data.getName().isBlank()
            || data.getWhenUsed() == null || data.getWhenUsed().isBlank()
            || data.getDescription() == null || data.getDescription().isBlank()
    ) {
      return null;
    }

    // weight,maxUses and value must be non-negative and not null.
    if (
            data.getWeight() == null || data.getWeight() < 0
            || data.getMaxUses() == null || data.getMaxUses() < 0
            || data.getUsesRemaining() == null
            || data.getUsesRemaining() >  data.getMaxUses() || data.getUsesRemaining() < 0
            || data.getValue() == null || data.getValue() < 0) {
      return null;
    }

    return new Item(data);
  }

  /**
   * Method that get the name of Item.
   * @return name of Item.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Method that get the weight of the item.
   * @return weight of item.
   */
  @Override
  public Integer getWeight() {
    return this.weight;
  }

  /**
   * Method that get the max use of the item.
   * @return max use.
   */
  @Override
  public Integer getMaxUses() {
    return this.maxUses;
  }

  /**
   * Method that get the remaining use of item.
   * @return remaining use.
   */
  @Override
  public Integer getUsesRemaining() {
    return this.usesRemaining;
  }

  /**
   * Method that get when the Item should be used.
   * @return when the Item should be used.
   */
  @Override
  public String getWhenUsed() {
    return this.whenUsed;
  }

  /**
   * Method that get the value of the item.
   * @return value of the item.
   */
  @Override
  public Integer getValue() {
    return this.value;
  }

  /**
   * Method that get the description of the item.
   * @return description of the item.
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Method that set the use remaining of the item.
   */
  @Override
  public void setUsesRemaining() {
    this.usesRemaining -= 1;
    if(this.usesRemaining < 0) {
      this.usesRemaining = 0;
    }
  }

  @Override
  public String getPicture() {
    return picture;
  }
}
