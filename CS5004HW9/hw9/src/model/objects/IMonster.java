package model.objects;

/**
 * Interface that represents a monster.
 */
public interface IMonster {

  /**
   * Gets the name of a monster.
   * @return name of a monster.
   */
  public String getName();

  /**
   * Gets rather a monster affects target or not.
   * @return value represents a monster can affects target or not.
   */
  public boolean getAffectsTarget();

  /**
   * Gets rather a monster can affect player or not.
   * @return value represents a monster can affects player or not.
   */
  public boolean getAffectsPlayer();

  /**
   * Gets rather a monster is active or not.
   * @return value represents a monster is active or not.
   */
  public boolean getActive();

  /**
   * Gets rather monster can attack a player or not.
   * @return value represents a monster can attack a player or not.
   */
  public boolean getCanAttack();

  /**
   * Gets an effect message of a monster.
   * @return effect message of a monster.
   */
  public String getEffect();

  /**
   * Gets how much monster can damage a player.
   * @return integer value that a monster can damage a player.
   */
  public Integer getDamage();

  /**
   * Gets the target (where a monster is located) of a monster.
   * @return location where a monster is located (target).
   */
  public String getTarget();

  /**
   * Gets the solution to defeat a monster.
   * @return solution to defeat a monster.
   */
  public String getSolution();

  /**
   * Gets the value of a monster. This will be a value that player
   * will earn after defeating a monster.
   * @return value of a monster.
   */
  public Integer getValue();

  /**
   * Gets the description of a monster.
   * @return description of a monster.
   */
  public String getDescription();

  /**
   * Gets description which explains how a monster attack a player.
   * @return description of a monster's attack.
   */
  public String getAttack();

  /**
   * Get a picture of a monster.
   * @return picture of a monster.
   */
   public String getPicture();

  /**
   * Set the active state of a monster. If monster is defeated,
   */
  public void setActive(Boolean state);

  /**
   * Set the canAttack state of a monster. If monster is defeated,
   */
  public void setCanAttack(Boolean state);

}
