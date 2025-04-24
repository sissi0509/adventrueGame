package model.utilityClass;

import model.objects.IPlayer;
import model.objects.IPuzzle;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * create a utility class for answer action to solve puzzle.
 */
public class Answer {

  /**
   * Solving puzzle by an answer and return the output.
   * @param player player of the game.
   * @param answer answer given by the player.
   * @param roomManager map(room number: room) of the room.
   * @return the message shown when type answer...
   * @throws IllegalArgumentException when one or more input is null.
   */
  public static String answer(IPlayer player,
                              String answer,
                              IRoomManager roomManager) throws IllegalArgumentException {
    if (player == null || roomManager == null) {
      throw new IllegalStateException();
    }

    // no answer input.
    if (answer == null || answer.isBlank()) {
      return  "answer something!!!";
    }

    IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());
    IPuzzle puzzle = room.getPuzzle();
    // if no puzzle in the room
    if (puzzle == null) {
      return "There is no puzzle to be solved in this room.\n";
    }

    // check the solution is String rather than item
    if (!puzzle.getSolution().startsWith("'")){
      return "This puzzle requires an item!";
    }

    String puzzleAnswer = puzzle.getSolution().replace("'", "");

    // if answer correctly.
    if (answer.equalsIgnoreCase(puzzleAnswer)) {
      // update score.
      player.setScore(puzzle.getValue());
      // change puzzle to not active.
      puzzle.setActive();
      // change room connection, set it to be normal status.
      room.setConnectionToNormal();

      return "SUCCESS! You solved this puzzle with the answer "
              + answer.toUpperCase();
    }
    // not correctly
    return "Try again, this is not the correct one.";

  }
}
