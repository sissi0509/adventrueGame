package model.objects;

import model.dataClasses.PlayerData;

/**
 * PlayerImpl class that represents a player. Player has name,
 * healthStatus, roomNumber, inventory and score.
 */
public class Player implements IPlayer {
  private String name;
  private Integer healthStatus;
  private Integer roomNumber;
  private IInventory inventory;
  private Integer score;

  // Constants
  private final Integer MINIMUM_HEALTH = 0;
  private final Integer MAXIMUM_HEALTH = 100;

  /**
   * Constructor of PlayerImpl class. PlayerImpl gets name
   * and set its name to input name, healthStatus to 100, roomNumber to 1,
   * inventory to Inventory object and score to 0.
   * @param name name of a player.
   */
  public Player(String name) {
    this.name = setName(name);
    this.healthStatus = 100;
    this.roomNumber = 1;
    this.inventory = new Inventory();
    this.score = 0;
  }

  /**
   * Constructor of player with PlayerData and inventory
   * @param playerData player data
   * @param inventory inventory
   */
  public Player(PlayerData playerData, IInventory inventory) {
    this.name = playerData.getName();
    this.healthStatus = playerData.getHealthStatus();
    this.roomNumber = playerData.getRoomNumber();
    this.inventory = inventory;
    this.score = playerData.getScore();
  }
  /**
   * Method that set the name of the player to default name if input name
   * is null or empty String.
   * @param name name of a player.
   * @return name of a player.
   */
  private String setName(String name) {
    if (name == null || name.isBlank()) {
      return "Default";
    }

    return name;
  }

  /**
   * Method that returns the name of a player.
   * @return name of player.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Method that returns the health of a player.
   * @return health of a player.
   */
  @Override
  public Integer getHealth() {
    return this.healthStatus;
  }

  /**
   * Method that returns the player's status.
   * @return player's status
   */
  @Override
  public HEALTH_STATUS getPlayerStatus() {
    if (this.healthStatus <= 0) {
      return HEALTH_STATUS.SLEEP;
    } else if (this.healthStatus < 40) {
      return HEALTH_STATUS.FATIGUE;
    } else if (this.healthStatus < 70) {
      return HEALTH_STATUS.DIZZY;
    }
    return HEALTH_STATUS.AWAKE;
  }

  /**
   * Method that returns the roomNumber of a player
   * is currently at.
   * @return roomNumber of a player.
   */
  @Override
  public Integer getRoomNumber() {
    return this.roomNumber;
  }

  /**
   * Method that returns the inventory of a player.
   * @return inventory of a player.
   */
  @Override
  public IInventory getInventory() {
    return this.inventory;
  }

  /**
   * Method that returns the score of a player.
   * @return score of a player.
   */
  @Override
  public Integer getScore() {
    return this.score;
  }

  /**
   * Method that returns the rank of a player when player quit the game.
   * @return rank of a player.
   */
  @Override
  public RANK getRank() {
    if (this.score <= 0) {
      return RANK.IRON;
    } else if (this.score <= 500) {
      return RANK.BRONZE;
    } else if (this.score <= 1500) {
      return RANK.SILVER;
    }
    return RANK.GOLD;
  }

  /**
   * Set the roomNumber when a player moves to another room.
   * @param roomNumber roomNumber that a player wants to move.
   */
  @Override
  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  /**
   * Set the healthStatus of the player after a player either
   * loss or gain healthStatus.
   * @param value amount of healthStatus a player should gain/loss.
   */
  @Override
  public void setHP(Integer value) {
    int totalHealthStatus = this.healthStatus + value;

    if (totalHealthStatus < MINIMUM_HEALTH) {
      this.healthStatus = MINIMUM_HEALTH;
    } else if (totalHealthStatus > MAXIMUM_HEALTH) {
      this.healthStatus = MAXIMUM_HEALTH;
    } else {
      this.healthStatus = totalHealthStatus;
    }
  }

  /**
   * Set the score of a player after player gain scores.
   * @param value score that a player earns.
   */
  @Override
  public void setScore(Integer value) {
    this.score += value;
  }

  /**
   * Set the name of the player.
   * @param name name of the player.
   */
  @Override
  public void reName(String name) {
    this.name = name;
  }


}
