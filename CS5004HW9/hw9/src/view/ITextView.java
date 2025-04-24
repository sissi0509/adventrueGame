package view;

/**
 * create an interface of ITextView for TextView.
 */
public interface ITextView {
  /**
   * show different options.
   */
  void showOptions();

  /**
   * prompt message to user, and return the user input.
   * @param message the message to display to the user
   * @return the user's answer.
   */
  String promptUser(String message);

  /**
   * show result to the user.
   * @param message message to be shown to the user.
   */
  void showResult(String message);
}
