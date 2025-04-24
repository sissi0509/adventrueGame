package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.GameController;
import controller.IController;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.swing.*;

import model.dataClasses.GameData;
import model.dataClasses.RoomData;
import model.gameModel.GameModel;
import model.gameModel.IGameModel;
import model.objects.Fixture;
import model.objects.IFixture;
import model.objects.IGameObjects;
import model.objects.IItem;
import model.objects.IMonster;
import model.objects.IPlayer;
import model.objects.IPuzzle;
import model.objects.IRoom;
import model.objects.IRoomManager;
import model.objects.Monster;
import model.objects.Player;
import model.objects.Puzzle;
import model.objects.Room;
import model.objects.RoomManager;
import view.ITextView;
import view.IView;
import view.TextView;
import view.VisualView;

/**
 * Mock Model to test Controller
 */
class MockModel implements IGameModel {
    private StringBuilder log;
    private String unique;

    /**
     * Constructor of the mock model.
     * @param log log to store the input.
     * @param unique unique data that will be returned.
     */
    public MockModel(StringBuilder log, String unique) {
        this.log = log;
        this.unique = unique;
    }

    /**
     * Mock model's answer method that log the answer and returns unique.
     * @param answer answer input.
     * @return unique String value.
     */
    @Override
    public String answer(String answer) {
        log.append("Choose answer: " + answer + "\n");

        return unique;
    }

    /**
     * Mock model's drop method that log the itemName and returns unique.
     * @param itemName name of the item to be dropped.
     * @return unique String value.
     */
    @Override
    public String drop(String itemName) {
        log.append("Choose answer: " + itemName + "\n");

        return unique;
    }

    /**
     * Mock model's examine method that log the name and returns unique.
     * @param name name of the objects to be examined.
     * @return unique String value.
     */
    @Override
    public String examine(String name) {
        log.append("Choose answer: " + name + "\n");

        return unique;
    }

    /**
     * Mock model's inventoryCheck method that returns String value indicates
     * inventory.
     * @return String value "inventory!".
     */
    @Override
    public String inventoryCheck() {
        log.append("inventory\n");
        return "Inventory!";
    }

    /**
     * Mock model's look method that returns array with values indicates look.
     * @return String array that indicates look.
     */
    @Override
    public String[] look() {
        log.append("look\n");
        String[] result = new String[2];
        result[0] = "look";
        result[1] = unique;
        return result;
    }

    /**
     * Mock model's movePlayer method that log direction and returns array
     * stores direction.
     * @param direction direction to be moved.
     * @return String array contains direction and unique value.
     */
    @Override
    public String[] movePlayer(String direction) {
        log.append("move" + direction + "\n");
        String[] result = new String[2];
        result[0] = direction;
        result[1] = "" + unique;

        return result;
    }

    /**
     * Mock model's take method that log itemName and return unique value.
     * @param itemName name of the item to be taken.
     * @return unique String value.
     */
    @Override
    public String take(String itemName) {
        log.append("Choose answer: " + itemName + "\n");

        return unique;
    }

    /**
     * Mock model's use method that log name and returns unique value.
     * @param name name of the item to be used.
     * @return unique String value.
     */
    @Override
    public String use(String name) {
        log.append("Choose answer: " + name + "\n");

        return unique;
    }

    /**
     * Mock model's 'save' method that returns String indicates save method.
     * @return String value indicates save method.
     */
    @Override
    public String save() {
        log.append("save game\n");
        return "SAVE!";
    }

    /**
     * Mock model's getSavedName method that returns String indicates getSavedName
     * method.
     * @return String value indicates getSavedName method.
     */
    @Override
    public String getSavedName() {
        log.append("get saved name\n");
        return "GETSAVEDNAME!";
    }

    /**
     * Mock model's reload method that returns String indicates reload method.
     * @return String value indicates reload method.
     */
    @Override
    public String reload() {
        log.append("reloading!\n");
        return "RELOAD!";
    }

    /**
     * Mock model's quit method that returns String indicates quit method.
     * @return String value indicates quit method.
     */
    @Override
    public String quit() {
        log.append("Quit game!\n");
        return "QUIT!";
    }

    /**
     * Mock model's reName method that log String indicates reName method
     * @param name name of the user chose.
     */
    @Override
    public void reName(String name) {
        log.append("Choose answer: " + name + "\n");
    }

    /**
     * Mock model's isEnd method that log String indicates isEnd method and return
     * true boolean value.
     * @return true boolean value.
     */
    @Override
    public Boolean isEnd() {
        log.append("game end! thank you!\n");
        return true;
    }

    /**
     * Mock model's firstMessage method that log and return String value indicates
     * firstMessage method
     * @return String value indicates firstMessage method.
     */
    @Override
    public String firstMessage() {
        log.append("this is the first message!\n");
        return "FirstMessage!";
    }

    /**
     * Mock model's getRoomManager method that log String value indicates getRoomManager
     * method and returns RoomManager.
     * @return RoomManager.
     */
    @Override
    public IRoomManager getRoomManager() {
        log.append("this is roomManager\n");
        Map<Integer, IRoom> map = new HashMap<>();
        Map<String, Integer> connection = new HashMap<>();
        connection.put("room1", 1);
        List<IItem> item = new ArrayList<>();
        List<IFixture> fixture = new ArrayList<>();

        RoomData data = new RoomData("Dark room", 2, 3, 4, 5,
                "puzzle", "monster", "item", "fixture", "room.png", 1, "Dungeon");

        IRoom room1 = new Room(data, item, null, null, fixture);

        Map<Integer, IRoom> roomMap = new HashMap<>();
        roomMap.put(0, room1);

        IRoomManager manager = new RoomManager(roomMap);

        return manager;
    }

    /**
     * Mock model's getPlayer method that log String value indicates getPlayer method
     * and returns IPlayer type player.
     * @return IPlayer type of player.
     */
    @Override
    public IPlayer getPlayer() {
        log.append("player\n");
        IPlayer player = new Player("A4");

        return player;
    }

    /**
     * Mock model's getCurrentRoom that log String value indicates getCurrentRoom method
     * and returns IRoom type of room.
     * @return IRoom type of room.
     */
    @Override
    public IRoom getCurrentRoom() {
        log.append("current room\n");
        Map<Integer, IRoom> map = new HashMap<>();
        Map<String, Integer> connection = new HashMap<>();
        connection.put("room1", 1);
        List<IItem> item = new ArrayList<>();
        List<IFixture> fixture = new ArrayList<>();

        RoomData data = new RoomData("Dark room", 2, 3, 4, 5,
                "puzzle", "monster", "item", "fixture", "room.png", 1, "Dungeon");


        IRoom room1 = Room.createIfValid(data, item, null, null, fixture);

        return room1;
    }

    /**
     * Mock model's getInventoryItems method that log String value indicates
     * getInventoryItems method and returns list that contains String value
     * indicates getInventoryItems method.
     * @return list with String "LIST".
     */
    @Override
    public List<String> getInventoryItems() {
        log.append("inventory list\n");
        List<String> list = new ArrayList<>();
        list.add("LIST");

        return list;
    }

    /**
     * Mock model's getGameObjects method that log String value indicates
     * getGameObjects method and returns null.
     * @return null.
     */
    @Override
    public IGameObjects getGameObjects() {
        log.append("game objects\n");
        return null;
    }

    /**
     * Mock model's getGameData method that log String value indicates getGameData
     * method and returns null.
     * @return null.
     */
    @Override
    public GameData getGameData() {
        log.append("game data\n");
        return null;
    }

    /**
     * Mock model's getRoomItems method that log String value indicates getRoomItems
     * method and returns null.
     * @return
     */
    @Override
    public List<String> getRoomItems() {
        log.append("room items\n");
        return new ArrayList<String>();
    }

    /**
     * Mock model's getItemInfo method that log input (itemName) and returns String
     * array with itemName.
     * @param itemName name of item.
     * @return String array with itemName.
     */
    @Override
    public String[] getItemInfo(String itemName) {
        log.append(itemName + "\n");
        String[] itemInfo = new String[2];
        itemInfo[0] = itemName;
        itemInfo[1] = "iteminfo!";

        return itemInfo;
    }

    /**
     * Mock model's examineMe method that log String value indicates examineMe
     * method and returns String array.
     * @return String array with data that indicates examineMe method.
     */
    @Override
    public String[] examineMe() {
        log.append("me!\n");
        String[] examine = new String[1];
        examine[0] = "examineMe!";

        return examine;
    }

    /**
     * Mock model's getRoomFixtures method that log String value indicates getRoomFixtures
     * method and returns String list.
     * @return String list with data for getRoomFixtures.
     */
    @Override
    public List<String> getRoomFixtures() {
        log.append("fixtures\n");
        List<String> fixtures = new ArrayList<>();
        fixtures.add("chair");

        return fixtures;
    }

    /**
     * Mock model's getFixtureInfo method that log String value indicates getFixtureInfo
     * method and returns String array.
     * @param name name of fixture.
     * @return String array with data indicates getFixtureInfo method.
     */
    @Override
    public String[] getFixtureInfo(String name) {
        log.append("fixture informations\n");
        String[] fixtureInfo = new String[0];
        fixtureInfo[0] = "fixtureInto";
        return fixtureInfo;
    }

    /**
     * Mock model's monsterAttack method that log String value indicates monsterAttack method
     * and returns String value.
     * @return String value for monsterAttack method.
     */
    @Override
    public String monsterAttack() {
        log.append("monster attack!\n");
        return "Attack!!";
    }

    /**
     * Mock model's getPlayerStatus method that log String value that indicates getPlayerStatus
     * method and returns String value.
     * @return String value for getPlayerStatus method.
     */
    @Override
    public String getPlayerStatus() {
        log.append("player status: \n");
        return "PlayerStatus";
    }

    /**
     * Mock model's userSleep method that log String value that indicates userSleep method
     * and returns String value.
     * @return String value for userSleep method.
     */
    @Override
    public String userSleep() {
        log.append("sleep\n");
        return "Sleep!";
    }

    /**
     * Mock model's getGameName method that log String value indicates getGameName method
     * and returns String value.
     * @return String value for getGameName method.
     */
    @Override
    public String getGameName() {
        log.append("game name: \n");
        return "GAME NAME!!";
    }
}

/**
 * Mock class for Visual view.
 */
class MockGraphicalView implements IView {
    private Appendable out;
    private Scanner scanner;

    /**
     * Constructor of MockGraphicalView that gets Readable and Appendable.
     * @param in Readable
     * @param out Appendable
     */
    public MockGraphicalView(Readable in, Appendable out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    /**
     * Mock view's promptAnswer method that returns String value indicates
     * promptAnswer method
     * @param message message that prompts users to answer.
     * @param title title of the answer window.
     * @return String value indicates promptAnswer method.
     */
    @Override
    public String promptAnswer(String message, String title) {
        try {
            out.append("message");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ANSWER!";
    }

    /**
     * Mock view's display method.
     */
    @Override
    public void display() {
        try {
            out.append("display");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Mock view's setEventHandler method.
     * @param controller controller of the game.
     */
    @Override
    public void setEventHandler(IController controller) {
        try {
            out.append("setEventHandler");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's setActionListener that gets ActionLister
     * @param controller ActionListener.
     */
    @Override
    public void setActionListener(ActionListener controller) {
        try {
            out.append("setActionListener");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's buildItemMenu.
     * @param elements list of elements.
     * @param selectedItem Consumer type that accepts user's single input (selection).
     */
    @Override
    public void buildItemMenu(List<String> elements, Consumer<String> selectedItem) {
        try {
            out.append("buildItemMenu");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's buildMenu method.
     * @param save menu Item save
     * @param reload menu Item reload
     * @param exit menu Item exit
     * @param about menu Item about
     * @return null
     */
    @Override
    public JMenuBar buildMenu(JMenuItem save, JMenuItem reload, JMenuItem exit, JMenuItem about) {
        return null;
    }

    /**
     * Mock view's showMessage method.
     * @param message message which will show to the user.
     */
    @Override
    public void showMessage(String message) {
        try {
            out.append("message");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's showFileMessage.
     * @param endingMessage message that will show message
     * @param purpose purpose of the pop-up window.
     * @param imagePath path of the image.
     * @param event event for the button.
     * @param type font type.
     * @param fontSize font size.
     */
    @Override
    public void showFileMessage(String endingMessage, String purpose, URL imagePath, ActionListener event, int type, int fontSize) {
        try {
            out.append("showFileMessage!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's showItemWindow method.
     * @param description description of the item.
     * @param imagePath image path of the item.
     */
    @Override
    public void showItemWindow(String description, String imagePath) {
        try {
            out.append("showItemWindow");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's updateInventory method.
     * @param inventory list of items in inventory.
     */
    @Override
    public void updateInventory(List<String> inventory) {
        try {
            out.append("update Inventory!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's getSelectedInventoryItem method.
     * @return String value indicates getSelectedInventoryItem method.
     */
    @Override
    public String getSelectedInventoryItem() {
        try {
            out.append("get selected item");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ITEMLIST";
    }

    /**
     * Mock view's updatePicture method.
     * @param picture picture path of the room.
     */
    @Override
    public void updatePicture(String picture) {
        try {
            out.append("update picture");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's updateDescription method.
     * @param description description of the room.
     */
    @Override
    public void updateDescription(String description) {
        try {
            out.append("update description");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's promptUser method
     * @param message message that prompt user to enter their name.
     * @param title title of the pop-up window.
     * @return String value indicates promptUser method.
     */
    @Override
    public String promptUser(String message, String title) {
        try {
            out.append("prompt user");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "user name!";
    }

    /**
     * Mock view's exitEvent method
     * @return null.
     */
    @Override
    public ActionListener exitEvent() {
        try {
            out.append("exit");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mock view's setPlayerStatus method.
     * @param description description of Player's status.
     */
    @Override
    public void setPlayerStatus(String description) {
        try {
            out.append("set player status");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's showExitMessage method.
     * @param message message that shows when exit the game.
     */
    @Override
    public void showExitMessage(String message) {
        try {
            out.append("show exit message");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mock view's setWindowTitle method.
     * @param title title of the game.
     */
    @Override
    public void setWindowTitle(String title) {
        try {
            out.append("set window title");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Test class that test controller of the game.
 */
class GameControllerTest {

    /**
     * Method that test controller's direction command with text view.
     */
    @Test
    public void testTextDirection() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nN\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "unique!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: NAttack!!PlayerStatus\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for inventory with text view.
     */
    @Test
    public void testTextInventory() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nI\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "unique!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: Inventory!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for look with text view.
     */
    @Test
    public void testTextLook() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nL\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "unique!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: lookAttack!!PlayerStatus\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for use with text view.
     */
    @Test
    public void testTextUse() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nU item\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "use item!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: use item!Attack!!PlayerStatus\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for take with text view.
     */
    @Test
    public void testTextTake() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nT item\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "Take item!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: Take item!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for drop with text view.
     */
    @Test
    public void testTextDrop() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\ndrop item\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "drop item!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: drop item!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for examine with text view.
     */
    @Test
    public void testTextExamine() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nexamine\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "examine!!!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: examine!!!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for Me with text view.
     */
    @Test
    public void testTextMe() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nMe\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "ME!!!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: examineMe!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for answer with text view.
     */
    @Test
    public void testTextAnswer() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nA key\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "ANSWER!!!"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: ANSWER!!!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for save with text view.
     */
    @Test
    public void testTextSave() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nsave\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "save game"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: SAVE!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for reload with text view.
     */
    @Test
    public void testTextReload() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nreload\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "reload game"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: RELOAD!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for quit with text view.
     */
    @Test
    public void testTextQuit() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nq\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "quit game"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: QUIT!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command if input command is invalid command.
     */
    @Test
    public void testTextDefault() {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("Eunjin\nHELLO\nQ");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "no command"), view);
        controller.goText();
        assertEquals("Welcome to the adventure game!\n" +
                "Enter a name for your player avatar: You shalt now be named: Eunjin\n" +
                "\n" + "\n" + "\n" + "FirstMessage!\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: This command does not exist.\n" + "\n" +
                "To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
                "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
                "(T)ake an item, (D)rop an item or e(X)amine something.\n" +
                "(A)nswer a question or provide a text solution.\n" +
                "To end the game, enter (Q)uit to quit and exit.\n" +
                "(SAVE) to save, (RELOAD) to reload.\n" +
                "(ME) to check information of the player.\n" +
                "Your choice: QUIT!\n" +
                "\n", out.toString());
    }

    /**
     * Method that test controller's command for examine with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicExamine() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "take");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "X");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for take with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicTake() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "take");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "T");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for inspect with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicInspect() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "inspect");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "inspect");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "ITEMLIST\n" +
                "ITEMLIST\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for use with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicUse() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "use");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "U");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "Choose answer: ITEMLIST\n" +
                "monster attack!\n" +
                "look\n" +
                "inventory list\n" +
                "player status: \n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for drop with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicDrop() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "drop");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "D");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "Choose answer: ITEMLIST\n" +
                "look\n" +
                "inventory list\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for direction (N,S,E,W) with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicDirection() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("N");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "direction");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "N");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "moveN\n" +
                "monster attack!\n" +
                "player status: \n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for answer with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicAnswer() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "answer");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "A");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "Choose answer: ANSWER!\n" +
                "look\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for exit with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicExit() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "exit");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "exit");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "Quit game!\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for save with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicSave() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "save");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "save");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "save game\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for about with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicAbout() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "about");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "about");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test controller's command for reload with graphical view.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGraphicReload() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        IView view = new MockGraphicalView(in, out);
        IGameModel model = new MockModel(log, "reload");
        IController controller = new GameController(model, view);
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "reload");
        controller.goGraphical();
        ((GameController) controller).actionPerformed(event);
        assertEquals("Choose answer: user name!\n" +
                "look\n" +
                "player status: \n" +
                "game name: \n" +
                "room items\n" +
                "fixtures\n" +
                "reloading!\n" +
                "game name: \n" +
                "look\n" +
                "inventory list\n" +
                "player status: \n" +
                "game end! thank you!\n" +
                "sleep\n", log.toString());
    }

    /**
     * Method that test goBatch method.
     * @throws IOException input/output exception.
     */
    @Test
    public void testGoBatchFile() throws IOException {
        StringBuffer out = new StringBuffer();
        Reader in = new StringReader("");
        StringBuilder log = new StringBuilder();
        ITextView view = new TextView(in, out);
        IController controller = new GameController(new MockModel(log, "use item!"), view);
        controller.goBatch("hw9/test/controllerTest/batch_input.txt");
        assertEquals("Choose answer: A4\n" +
                "this is the first message!\n" +
                "Choose answer: hair clippers\n" +
                "game end! thank you!\n", log.toString());
    }
}