package model.objects;

/**
 * Player interface which represents a player.
 */
public interface IPlayer {
  /**
   * Method that returns the name of a player.
   * @return name of player.
   */
  public String getName();

  /**
   * Method that returns the healthStatus of a player.
   * @return healthStatus of a player.
   */
  public Integer getHealth();

  /**
   * Method that returns the player's status.
   * @return player's status.
   */
  public HEALTH_STATUS getPlayerStatus();

  /**
   * Method that returns the roomNumber of a player
   * is currently at.
   * @return roomNumber of a player.
   */
  public Integer getRoomNumber();

  /**
   * Method that returns the inventory of a player.
   * @return inventory of a player.
   */
  public IInventory getInventory();

  /**
   * Method that returns the score of a player.
   * @return score of a player.
   */
  public Integer getScore();

  /**
   * Method that returns the rank of a player when player quit the game.
   * @return rank of a player.
   */
  public RANK getRank();

  /**
   * Set the roomNumber when a player moves to another room.
   * @param roomNumber roomNumber that a player wants to move.
   */
  public void setRoomNumber(Integer roomNumber);

  /**
   * Set the healthStatus of the player after a player either
   * loss or gain healthStatus.
   * @param value amount of healthStatus a player should gain/loss.
   */
  public void setHP(Integer value);

  /**
   * Set the score of a player after player gain scores.
   * @param value score that a player earns.
   */
  public void setScore(Integer value);

  /**
   * reset name of the player.
   */
  public void reName(String name);

}
