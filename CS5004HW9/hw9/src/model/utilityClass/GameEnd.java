package model.utilityClass;

import model.objects.IPlayer;

/**
 * utility class to shown ending message for the game.
 */
public class GameEnd {

  /**
   * show message if the player health status turned to sleep.
   * @param player player of the game.
   * @return message if the player health turned to sleep.
   */
  public static String showEnd(IPlayer player){
    return "Your health has dropped to SLEEP ZONE.\nNight-night.\nGAME OVER!\n"
            + quit(player);
  }

  /**
   * show quit message.
   * @param player player of the game.
   * @return quit message.
   */
  public static String quit(IPlayer player) {
    return "Thank you for playing!\n"
            + "Your score is: " + player.getScore()
            + "\nYour rank is: " + player.getRank() + "\n";
  }
}