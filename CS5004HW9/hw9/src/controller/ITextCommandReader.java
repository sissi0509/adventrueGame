package controller;

/**
 * Interface of ITextCommandReader to read text input from user.
 */
public interface ITextCommandReader {
  /**
   * Prompt user to enter a name at the beginning
   * and set this.userName to be the input name.
   */
  void getUserName();

  /**
   * Reads user's choice during the game playing.
   */
  void getUserCommand();

  /**
   * Reset the answer after finish passing to the model.
   */
  void setAnswerEmpty();

  /**
   * Method that returns users' command.
   */
  String getCommand();

  /**
   * Method that returns user's answer, which comes after the command.
   */
  String getAnswer();

  /**
   * Returns user's name.
   */
  String getName();
}
