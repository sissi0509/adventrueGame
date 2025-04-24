package view;

import java.io.IOException;
import java.util.Scanner;

/**
 * create a class of textView.
 */
public class TextView implements ITextView {
  private Appendable out;
  private Scanner scanner;

  /**
   * create a constructor of TextView.
   * @param in input.
   * @param out output.
   */
  public TextView(Readable in, Appendable out) {
    this.out = out;
    this.scanner = new Scanner(in);
  }

  @Override
  public void showOptions() {
    try {
      out.append("To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\nOther actions: "
              + "(I)nventory, (L)ook around the location, (U)se an item\n"
              + "(T)ake an item, (D)rop an item or e(X)amine something.\n"
              + "(A)nswer a question or provide a text solution.\n"
              + "To end the game, enter (Q)uit to quit and exit.\n"
              + "(SAVE) to save, (RELOAD) to reload.\n"
              + "(ME) to check information of the player.\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String promptUser(String message) {
    try {
      out.append(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return scanner.nextLine();
  }

  @Override
  public void showResult(String message) {
    try {
      out.append(message + "\n\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}