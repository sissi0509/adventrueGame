package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controller.IController;

import static javax.swing.JOptionPane.showInputDialog;

public class VisualView extends JFrame implements IView {
  JFrame frame = new JFrame("Layout");

  private JPanel picture;
  private JPanel navigation;
  private JPanel description;
  private JPanel inventory;

  private JPanel arrows;
  private JPanel TakeExamineAnswer;

  private JPanel northPanel;
  private JPanel southPanel;
  private JPanel eastPanel;
  private JPanel westPanel;

  private JButton north;
  private JButton south;
  private JButton east;
  private JButton west;

  private JButton take;
  private JButton examine;
  private JButton answer;

  // for inventory
  private DefaultListModel<String> listModel;
  private JList<String> inventoryList;
  private JPanel inventoryButtons;

  private JButton inspect;
  private JButton use;
  private JButton drop;


  private JTextArea descriptionArea;

  private JMenuItem about;
  private JMenuItem save;
  private JMenuItem reload;
  private JMenuItem exit;

  private JLabel playerStatus;


  public VisualView(String caption) {
    super(caption);
    setSize(1000, 800);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new GridLayout(0, 2));

    picture = new JPanel();
    navigation = new JPanel(new GridLayout(2, 1));
    description = new JPanel();
    inventory = new JPanel(new GridLayout(3, 1));

    // border
    TitledBorder pictureBorder = BorderFactory.createTitledBorder("Picture");
    TitledBorder navigationBorder = BorderFactory.createTitledBorder("Navigation");
    TitledBorder descriptionBorder = BorderFactory.createTitledBorder("Description");
    TitledBorder inventoryBorder = BorderFactory.createTitledBorder("Inventory");

    pictureBorder.setTitleColor(Color.black);
    navigationBorder.setTitleColor(Color.black);
    descriptionBorder.setTitleColor(Color.black);
    inventoryBorder.setTitleColor(Color.black);

    descriptionArea = new JTextArea();
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    descriptionArea.setEditable(false);
    descriptionArea.setOpaque(false);
    description.setLayout(new BorderLayout());
    description.add(descriptionArea, BorderLayout.CENTER);

    picture.setBorder(pictureBorder);
    description.setBorder(descriptionBorder);
    inventory.setBorder(inventoryBorder);

    arrows = new JPanel(new GridLayout(3, 1));
    JPanel eastWest = new JPanel(new GridLayout(1, 2));

    // direction buttons
    north = new JButton();
    north.setActionCommand("N");
    ImageIcon northImage = new ImageIcon(getClass().getResource("/resources/images/north.png"));
    north.setIcon(northImage);
    northPanel = setButton(north);
    arrows.add(northPanel);

    west = new JButton();
    west.setActionCommand("W");
    ImageIcon westImage = new ImageIcon(getClass().getResource("/resources/images/west.png"));
    west.setIcon(westImage);
    westPanel = setButton(west);

    east = new JButton();
    east.setActionCommand("E");
    ImageIcon eastImage = new ImageIcon(getClass().getResource("/resources/images/east.png"));
    east.setIcon(eastImage);
    eastPanel = setButton(east);

    eastWest.add(westPanel);
    eastWest.add(eastPanel);
    arrows.add(eastWest);

    south = new JButton();
    south.setActionCommand("S");
    ImageIcon southImage = new ImageIcon(getClass().getResource("/resources/images/south.png"));
    south.setIcon(southImage);
    southPanel = setButton(south);
    arrows.add(southPanel);

    navigation.add(arrows);
    arrows.setBorder(navigationBorder);

    // take examine answer buttons
    TakeExamineAnswer = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
    navigation.add(TakeExamineAnswer);

    take = new JButton("take");
    examine = new JButton("examine");
    answer = new JButton("answer");

    take.setPreferredSize(new Dimension(100, 20));
    take.setActionCommand("T");

    examine.setActionCommand("X");
    answer.setActionCommand("A");

    examine.setPreferredSize(new Dimension(100, 20));
    answer.setPreferredSize(new Dimension(100, 20));
    //take.setBounds(100,100,20,20);

    TakeExamineAnswer.add(take);
    TakeExamineAnswer.add(examine);
    TakeExamineAnswer.add(answer);

    listModel = new DefaultListModel<>();
    inventoryList = new JList<>(listModel);
    inventory.add(new JScrollPane(inventoryList));

    inventoryButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
    inventory.add(inventoryButtons);

    inspect = new JButton("inspect");
    use = new JButton("use");
    drop = new JButton("drop");

    use.setActionCommand("U");
    drop.setActionCommand("D");

    inspect.setPreferredSize(new Dimension(100, 20));
    use.setPreferredSize(new Dimension(100, 20));
    drop.setPreferredSize(new Dimension(100, 20));

    inventoryButtons.add(inspect);
    inventoryButtons.add(use);
    inventoryButtons.add(drop);

    playerStatus = new JLabel();
    inventory.add(playerStatus);

    this.add(picture);
    this.add(navigation);
    this.add(description);
    this.add(inventory);

    save = new JMenuItem("save");
    save.setActionCommand("save");

    reload = new JMenuItem("reload");
    reload.setActionCommand("reload");

    exit = new JMenuItem("exit");
    exit.setActionCommand("exit");

    about = new JMenuItem("about");
    about.setActionCommand("about");

    this.setJMenuBar(this.buildMenu(save, reload, exit, about));
  }

  @Override
  public String promptUser(String message, String title) {
    String result = showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    if (result == null) {
      System.exit(0);
    }
    return result;
  }

  @Override
  public String promptAnswer(String message, String title) {
    return showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public JMenuBar buildMenu(JMenuItem save, JMenuItem reload, JMenuItem exit, JMenuItem about) {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");

    JFrame savePopup = new JFrame("Save");
    savePopup.setLayout(new GridLayout(2, 1));
    savePopup.setSize(400, 400);
    savePopup.setLocationRelativeTo(this);
    savePopup.setLayout(new BorderLayout());

    savePopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    menu.add(save);
    menu.add(exit);
    menu.add(about);
    menu.add(reload);
    menuBar.add(menu);
    return menuBar;
  }

  private JPanel setButton(JButton button) {
    button.setPreferredSize(new Dimension(150, 45));
    JPanel directionPanel = new JPanel();
    directionPanel.add(button);
    button.setContentAreaFilled(false);

    return directionPanel;
  }

  @Override
  public void setEventHandler(IController controller) {
    this.setActionListener((ActionListener) controller);
  }

  @Override
  public void setActionListener(ActionListener controller) {
    take.addActionListener(controller);
    save.addActionListener(controller);
    inspect.addActionListener(controller);
    examine.addActionListener(controller);
    use.addActionListener(controller);
    drop.addActionListener(controller);
    north.addActionListener(controller);
    south.addActionListener(controller);
    west.addActionListener(controller);
    east.addActionListener(controller);
    answer.addActionListener(controller);
    reload.addActionListener(controller);
    about.addActionListener(controller);
    exit.addActionListener(controller);
  }

  @Override
  public void buildItemMenu(List<String> elements, Consumer<String> selectedItem) {
    JFrame itemFrame = new JFrame("Select");
    itemFrame.setSize(400, 200);
    itemFrame.setLocationRelativeTo(this);
    itemFrame.setLayout(new BorderLayout());

    String[] items = elements.toArray(new String[0]);
    JList<String> selection = new JList<>(items);
    itemFrame.add(new JScrollPane(selection), BorderLayout.CENTER);

    JButton oKButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    oKButton.addActionListener(e -> {
      String selected = selection.getSelectedValue();
      if (selected != null) {
        selectedItem.accept(selected);
        itemFrame.dispose();  // close the window
      }
    });

    cancelButton.addActionListener(e -> itemFrame.dispose());

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(oKButton);
    buttonPanel.add(cancelButton);

    itemFrame.add(buttonPanel, BorderLayout.SOUTH);

    itemFrame.setVisible(true);
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void showFileMessage(String endingMessage, String purpose, URL imagePath, ActionListener event, int type, int fontSize) {
    JFrame exitFrame = new JFrame(purpose);
    exitFrame.setSize(600, 500);

    exitFrame.setLayout(new BorderLayout());
    exitFrame.setLocationRelativeTo(this);
    JTextPane message = new JTextPane();
    message.setEnabled(false);
    SimpleAttributeSet set = new SimpleAttributeSet();
    StyledDocument doc = message.getStyledDocument();
    StyleConstants.setAlignment(set, StyleConstants.ALIGN_CENTER);
    message.setDisabledTextColor(Color.black);

    doc.setParagraphAttributes(0, doc.getLength(), set, false);

    message.setText(endingMessage);
    message.setAlignmentX(Component.CENTER_ALIGNMENT);
    message.setBackground(new Color(239, 239, 239));
    message.setFont(new Font("Arial", type, fontSize));
    message.setPreferredSize(new Dimension(150, 60));
    exitFrame.add(message, BorderLayout.NORTH);
    exitFrame.add(new JLabel(new ImageIcon(imagePath)), BorderLayout.CENTER);

    JButton exitButton = new JButton("OK");
    exitButton.addActionListener(e->exitFrame.dispose());
    exitButton.addActionListener(event);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(150, 40));
    buttonPanel.add(exitButton);
    exitFrame.add(buttonPanel, BorderLayout.SOUTH);

    exitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    exitFrame.setVisible(true);
  }

  @Override
  public void showItemWindow(String description, String imagePath) {
    JFrame itemFrame = new JFrame("Inspecting");
    itemFrame.setSize(600, 400);
    itemFrame.setLocationRelativeTo(this);
    itemFrame.setLayout(new BorderLayout());

    // Left: image
    JLabel imageLabel;
    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/" + imagePath));
    int targetHeight = 200;
    int targetWidth = (icon.getIconWidth() * targetHeight) / icon.getIconHeight();
    Image scaledImage = icon.getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
    imageLabel = new JLabel(new ImageIcon(scaledImage));

    JPanel imagePanel = new JPanel();
    imagePanel.add(imageLabel);


    // Right: description
    JTextArea textArea = new JTextArea(description);
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    textArea.setBorder(BorderFactory.createTitledBorder("Description"));
    JScrollPane scrollPane = new JScrollPane(textArea);

    // Bottom: OK button
    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> itemFrame.dispose());
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(okButton);

    // Layout
    JPanel topPanel = new JPanel(new GridLayout(1, 2));
    topPanel.add(imagePanel);
    topPanel.add(scrollPane);

    itemFrame.add(topPanel, BorderLayout.CENTER);
    itemFrame.add(buttonPanel, BorderLayout.SOUTH);

    itemFrame.setVisible(true);
  }

  @Override
  public void updateInventory(List<String> inventory) {
    listModel.clear();
    String[] itemsList = inventory.toArray(new String[0]);
    for (String item : itemsList) {
      listModel.addElement(item);
    }
  }

  @Override
  public String getSelectedInventoryItem() {
    return inventoryList.getSelectedValue();
  }


  @Override
  public void updatePicture(String pictureName) {
    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/" + pictureName));

    int panelHeight = picture.getHeight() * 9 / 10;
    int imgWidth = icon.getIconWidth();
    int imgHeight = icon.getIconHeight();

    int scaledWidth = (imgWidth * panelHeight) / imgHeight;
    Image scaledImage = icon.getImage().getScaledInstance(scaledWidth, panelHeight, Image.SCALE_SMOOTH);

    JLabel pictureLabel = new JLabel(new ImageIcon(scaledImage));

    picture.setLayout(new GridBagLayout());
    picture.removeAll();
    picture.add(pictureLabel, new GridBagConstraints());
    picture.revalidate();
    picture.repaint();
  }

  @Override
  public void updateDescription(String description) {
    descriptionArea.setText(description);
  }

  @Override
  public ActionListener exitEvent() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
  }

  @Override
  public void setPlayerStatus(String description) {
    this.playerStatus.setText(description);
  }

  @Override
  public void showExitMessage(String message) {
    String[] options = {"Ok"};
    int result = JOptionPane.showOptionDialog(this, message, "Game end", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    if (result == 0) {
      System.exit(0);
    }
  }

  @Override
  public void setWindowTitle(String title) {
    this.setTitle(title);
  }
}
