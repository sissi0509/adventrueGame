package model.dataClasses;

import model.objects.IPlayer;

/**
 * create a dataClass of player.
 */
public class PlayerData {
  private final String name;
  private final Integer healthStatus;
  private final Integer roomNumber;
  private final Integer score;

  /**
   * constructor for the playerData.
   * create a playerData object based on player object.
   * @param player player object.
   */
  public PlayerData(IPlayer player) {
    this.name = player.getName();
    this.healthStatus = player.getHealth();
    this.roomNumber = player.getRoomNumber();
    this.score = player.getScore();
  }

  /**
   * Constructor based on input parameters.
   */
  public PlayerData(String name, Integer healthStatus, Integer roomNumber, Integer score) {
    this.name = name;
    this.healthStatus = healthStatus;
    this.roomNumber = roomNumber;
    this.score = score;
  }

  /**
   * getter of name of the player.
   * @return name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * getter of the healthStatus of the player.
   * @return the health status of the player.
   */
  public int getHealthStatus() {
    return healthStatus;
  }

  /**
   * getter of roomNumber of the player.
   * @return number the room the player is currently in.
   */
  public int getRoomNumber() {
    return roomNumber;
  }

  /**
   * getter of score of the player.
   * @return score of the player.
   */
  public int getScore() {
    return score;
  }

}