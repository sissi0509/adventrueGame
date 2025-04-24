package model.dataClasses;

import com.google.gson.annotations.SerializedName;

import model.objects.IItem;

/**
 * Data class that holds serializable information about an item in the game world.
 *
 */

public class ItemData {
  private String name;
  private Integer weight;

  @SerializedName("max_uses")
  private Integer maxUses;

  @SerializedName("uses_remaining")
  private Integer usesRemaining;

  private Integer value;

  @SerializedName("when_used")
  private String whenUsed;

  private String description;
  private String picture;


  /**
   * Constructs an item data class based on the given IItem.
   *
   * @param item the IItem object containing all item properties to be stored.
   */
  public ItemData(IItem item) {
    this.name = item.getName();
    this.weight = item.getWeight();
    this.maxUses = item.getMaxUses();
    this.usesRemaining = item.getUsesRemaining();
    this.value = item.getValue();
    this.whenUsed = item.getWhenUsed();
    this.description = item.getDescription();
    this.picture = item.getPicture();
  }

  /**
   * Constructor based on input parameters.
   */
  public ItemData(String name, Integer weight, Integer maxUses, Integer usesRemaining,
                  String whenUsed, Integer value, String description, String picture) {
    this.name = name;
    this.weight = weight;
    this.maxUses = maxUses;
    this.usesRemaining = usesRemaining;
    this.value = value;
    this.whenUsed = whenUsed;
    this.description = description;
    this.picture = picture;
  }

  /**
   * Get the name of the item.
   *
   * @return the name of item.
   */
  public String getName() {
    return name;
  }

  public Integer getWeight() {
    return weight;
  }

  /**
   * Get the max use of the item.
   *
   * @return  the max use of the item.
   */

  public Integer getMaxUses() {
    return maxUses;
  }

  /**
   * Get the use remaining of the item.
   *
   * @return the use remaining of the item.
   */

  public  Integer getUsesRemaining() {
    return usesRemaining;
  }

  /**
   * Get the value of the Item.
   *
   * @return the value of the item.
   */

  public Integer getValue() {
    return value;
  }

  /**
   * Returns the message or effect description shown when the item is used.
   *
   * @return the "when used" string for this item.
   */

  public String getWhenUsed() {
    return whenUsed;
  }

  /**
   * Return the description of the item.
   *
   * @return the description of the item
   */

  public String getDescription() {
    return description;
  }

  /**
   * Get the name of the picture.
   *
   * @return the name of the picture.
   */

  public String getPicture() {
    return picture;
  }
}