package model.objects;

/**
 * Interface that represents a Puzzle.
 */
public interface IPuzzle {

  /**
   * Gets the name of a puzzle.
   * @return name of a puzzle.
   */
  public String getName();

  /**
   * Gets the active state of a puzzle.
   * @return active state of a puzzle.
   */
  public Boolean getActive();

  /**
   * Gets the effect of a puzzle.
   * @return effect of a puzzle.
   */
  public String getEffect();

  /**
   * Gets a target (which puzzle is located) of a puzzle.
   * @return target of a puzzle.
   */
  public String getTarget();

  /**
   * Gets a solution of a puzzle.
   * @return solution of a puzzle.
   */
  public String getSolution();

  /**
   * Gets the value of a puzzle.
   * @return value of a puzzle.
   */
  public Integer getValue();

  /**
   * Gets the state of a puzzle rather puzzle can affect
   * the target or not.
   * @return state rather puzzle can affect target or not.
   */
  public Boolean getAffectsTarget();

  /**
   * Gets the state of a puzzle rather puzzle can affect
   * the player or not.
   * @return state rather puzzle can affect a player or not.
   */
  public boolean getAffectsPlayer();

  /**
   * Gets the description of a puzzle.
   * @return description of a puzzle.
   */
  public String getDescription();
  // public String getPicture();

  /**
   * Sets the active state of a puzzle or not.
   */
  public void setActive();

  /**
   * get picture name of the puzzle.
   * @return picture name of the puzzle.
   */
  public String getPicture();

}
