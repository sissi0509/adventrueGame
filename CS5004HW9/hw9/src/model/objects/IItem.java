package model.objects;

/**
 * create an interface of Item.
 */
public interface IItem {
  /**
   * name getter.
   * @return name of the item.
   */
  public String getName();

  /**
   * weight getter.
   * @return weight of the item.
   */
  public Integer getWeight();

  /**
   * maxUses getter.
   * @return maxUses of the item.
   */
  public Integer getMaxUses();

  /**
   * usesRemaining getter.
   * @return usesRemaining of the item.
   */
  public Integer getUsesRemaining();

  /**
   * whenUsed getter.
   * @return whenUsed message of the item.
   */
  public String getWhenUsed();

  /**
   * value getter.
   * @return value of the item.
   */
  public Integer getValue();

  /**
   * description getter.
   * @return description of the item.
   */
  public String getDescription();

  /**
   * decrease the usesRemaining by one.
   */
  public void setUsesRemaining();

  /**
   * get picture name of the item.
   * @return picture name of the item.
   */
  public String getPicture();
}
