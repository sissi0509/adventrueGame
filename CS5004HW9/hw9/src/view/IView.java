package view;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

import controller.IController;

import javax.swing.*;

/**
 * Interface for graphical view.
 */
public interface IView {
  /**
   * Prompt user to answer puzzle.
   * @param message message that prompts users to answer.
   * @param title title of the pop-up window.
   * @return user's input.
   */
  String promptAnswer(String message, String title);

  /**
   * Display the window.
   */
  void display();

  /**
   * Set event handler to be input controller.
   * @param controller controller of the game.
   */
  void setEventHandler(IController controller);

  /**
   * Set action listener for all buttons.
   * @param controller ActionListener of controller.
   */
  void setActionListener(ActionListener controller);

  /**
   * Build menu of "take", and return the element selected by user.
   * @param elements list of elements.
   * @param selectedItem Consumer type that accepts user's single input (selection).
   */
  void buildItemMenu(List<String> elements, Consumer<String> selectedItem);

  /**
   * Build file menu bar.
   * @param save menu Item save
   * @param reload menu Item reload
   * @param exit menu Item exit
   * @param about menu Item about
   * @return JMenuBar type of menu bar for game window.
   */
  JMenuBar buildMenu(JMenuItem save, JMenuItem reload, JMenuItem exit, JMenuItem about);

  /**
   * Display message in a new window.
   * @param message message which will show to the user.
   */
  void showMessage(String message);

  /**
   * Method that shows "About" window.
   * @param endingMessage message that will show message
   * @param purpose purpose of the pop-up window.
   * @param imagePath path of the image.
   * @param event event for the button.
   * @param type font type.
   * @param fontSize font size.
   */
  void showFileMessage(String endingMessage, String purpose, URL imagePath, ActionListener event, int type, int fontSize);

  /**
   * Display picture and description of the selected element in a new window.
   * @param description description of the item.
   * @param imagePath image path of the item.
   */
  void showItemWindow(String description, String imagePath);

  /**
   * Update displayed inventory.
   * @param inventory list of items in inventory.
   */
  void updateInventory(List<String> inventory);

  /**
   * Return the name of the selected item in inventory.
   * @return name of the selected item in the inventory.
   */
  String getSelectedInventoryItem();

  /**
   * Update room picture.
   * @param picture picture path of the room.
   */
  void updatePicture(String picture);

  /**
   * Update room description.
   * @param description description of the room.
   */
  void updateDescription(String description);

  /**
   * Prompt user to enter name at the beginning of the game.
   * @param message message that prompt user to enter their name.
   * @param title title of the pop-up window.
   * @return user's input.
   */
  String promptUser(String message, String title);

  /**
   * Create ActionListener to close game.
   * @return ActionListener to close the game.
   */
  ActionListener exitEvent();

  /**
   * Set player health status message.
   * @param description description of Player's status.
   */
  void setPlayerStatus(String description);

  /**
   * Show exit message in the new window.
   * @param message message that shows when exit the game.
   */
  void showExitMessage(String message);

  /**
   * Set title for the JFrame.
   * @param title title of the game.
   */
  void setWindowTitle(String title);
}
