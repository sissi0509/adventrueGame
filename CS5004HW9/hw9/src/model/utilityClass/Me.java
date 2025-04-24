package model.utilityClass;

import model.objects.IPlayer;

/**
 * utility class to show player information.
 */
public class Me {

  /**
   * return player related information.
   * @param player player of the game.
   * @return information about the player.
   */
  public static String me(IPlayer player) {
    return "name: " + player.getName()
            + "\nhealth status: " + player.getHealth()
            + "\nscore: " + player.getScore()
            + "\nroom number: "+ player.getRoomNumber();
  }
}