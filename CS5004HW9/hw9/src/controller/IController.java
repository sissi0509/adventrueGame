package controller;

import java.io.IOException;

/**
 * This is an interface that represents the game controller.
 * Get player's input and call model to handle it.
 */
public interface IController {

  /**
   * Controller for graphical mode.
   */
  void goGraphical() throws IOException;

  /**
   * Controller for text mode.
   */
  void goText();

  /**
   * Controller for batch mode.
   */
  void goBatch(String filePath) throws IOException;
}
