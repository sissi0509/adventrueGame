package gameModelTest;


import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.dataClasses.GameData;
import model.gameModel.GameModel;
import model.gameModel.IGameModel;
import model.utilityClass.Answer;
import model.utilityClass.Move;
import model.utilityClass.Save;
import model.utilityClass.Use;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Creates a GameModelTest class to test the methods in the GameModel class.
 * Since the utility class methods are only used as helper functions by the GameModel,
 * testing the GameModel also indirectly tests those utility methods.
 * No separate test classes are created specifically for utility classes.
 */
public class GameModelTest {
  private IGameModel model1;
  private IGameModel model2;
  private IGameModel model3;

  /**
   * set three models for different scenarios.
   * @throws IOException when can not read the file.
   */
  @BeforeEach
  void setup() throws IOException {
    Gson gson = new Gson();
    // model for puzzle.
    FileReader reader = new FileReader(getClass().getResource("/resources/museum.json").getPath());
    GameData gameData = gson.fromJson(reader, GameData.class);

    this.model1 = new GameModel(gameData ,"A4","save.json");

    // model for monster.
    FileReader reader2 = new FileReader(getClass().getResource(
            "/resources/alignquest.json").getPath());
    GameData gameData2 = gson.fromJson(reader2, GameData.class);

    this.model2 = new GameModel(gameData2 ,"cc","save.json");

    // model for missing data.
    FileReader reader3 = new FileReader(getClass().getResource(
            "/resources/empty_rooms_missing_data.json").getPath());
    GameData gameData3 = gson.fromJson(reader3, GameData.class);

    this.model3 = new GameModel(gameData3 ,"aa","save.json");
  }

  /**
   * a smoke test to test most game related logic but not include the monster.
   */
  @Test
  void smokeTest() {
    assertEquals("Museum of Planet of the Apes", model1.getGameName());
    assertEquals(1, model1.getCurrentRoom().getRoomNumber());
    assertEquals("You start your adventure in the Museum Entrance\n"
            + "You are entering the Museum Entrance\n"
            + "There is a turnstile to the north. "
            + "It requires some type of payment or ticket to activate.\n"
            + "Items you see here: Ticket\n", model1.firstMessage());
    assertEquals(1, model1.getCurrentRoom().getRoomNumber());
    assertEquals(">> You cannot go in that direction! <<", model1.movePlayer("N")[0]);
    assertEquals(">> You cannot go in that direction! <<", model1.movePlayer("S")[0]);
    assertEquals(">> You cannot go in that direction! <<", model1.movePlayer("W")[0]);
    assertEquals(">> You cannot go in that direction! <<", model1.movePlayer("E")[0]);
    assertEquals("hair clippers not found in the room.", model1.take("hair clippers"));
    assertEquals("[Ticket]", model1.getRoomItems().toString());
    model1.take("ticket");
    assertEquals("[Ticket]", model1.getInventoryItems().toString());
    String [] itemInfo = model1.getItemInfo("ticket");
    assertEquals("A complimentary museum ticket. It says ADMIT ONE, pwd = Align.", itemInfo[0]);
    assertEquals("generic_item.png",itemInfo[1]);
    model1.use("ticket");
    String [] info = model1.movePlayer("N");
    assertEquals("You are entering the First Exhibit\n"
            + "A computer that seems to control an invisible forcefield blocking your motion. "
            + "A password screen is waiting for an entry.\n"
            + "Items you see here: \n", info[0]);
    assertEquals("generic_puzzle.png", info[1]);
    assertEquals("Try again, this is not the correct one.", model1.answer("apple"));
    assertEquals("SUCCESS! You solved this puzzle with the answer ALIGN", model1.answer("align"));
    assertEquals("Saved your current game!", model1.save());
    int score = model1.getPlayer().getScore();
    assertEquals("Thank you for playing!\n"
            + "Your score is: " + score
            + "\nYour rank is: " + model1.getPlayer().getRank() + "\n", model1.quit());
    assertEquals("Reload finished", model1.reload());
    assertEquals(score, model1.getPlayer().getScore());
    assertEquals("name: A4\n"
            + "health status: 100\n"
            + "score: 305\n"
            + "room number: 2", model1.examineMe()[0]);
  }

  /**
   * test monster related logic.
   */
  @Test
  void smokeTestForMonster() {
    assertEquals("There is no puzzle to be solved in this room.\n", model2.answer("hari clippers"));
    model2.take("hair clippers");
    assertEquals("From the Courtyard you examine the billboard: "
                    + "A large billboard looms in the distance. \n"
                    + "It's hard to read but seems to say "
                    + "'Welcome to Align Quest, adventurer! Enjoy the exploration! - Prof. K'",
            model2.examine("billboard"));
    assertEquals("[Billboard]",model2.getRoomFixtures().toString());
    assertEquals("A large billboard looms in the distance. \n"
                    + "It's hard to read but seems to say "
                    + "'Welcome to Align Quest, adventurer! Enjoy the exploration! - Prof. K'",
            model2.getFixtureInfo("billboard")[0]);
    assertEquals("billboard.png", model2.getFixtureInfo("billboard")[1]);
    assertEquals("", model2.monsterAttack());
    model2.movePlayer("N");
    assertEquals("Oh no! ticket is either empty or cannot be used again!\n",
            model2.use("ticket"));
    assertEquals("You are entering the Foyer\n"
                    + "A monster Teddy Bear growls at you! You cannot get past!\n"
                    + "Teddy Bear hits you with soft, fluffy paws! You might sneeze!\n"
                    + "\n"
                    + "Items you see here: Lamp\n",
            model2.movePlayer("N")[0]);
    assertEquals("Oh no! ticket is either empty or cannot be used again!\n",
            model2.use("ticket"));
    assertEquals("generic_item.png", model2.getFixtureInfo("Chandelier")[1]);
    assertEquals("Player takes -5 damage!\n", model2.monsterAttack());
    assertEquals("Your health status: AWAKE\n", model2.getPlayerStatus());
    for (int i = 0; i < 6; i++) {
      model2.monsterAttack();
    }

    assertEquals("Your health status: DIZZY\n", model2.getPlayerStatus());
    for (int i = 0; i < 6; i++) {
      model2.monsterAttack();
    }

    assertFalse(model2.isEnd());
    assertEquals("Your health status: FATIGUE\n", model2.getPlayerStatus());
    for (int i = 0; i < 7; i++) {
      model2.monsterAttack();
    }
    assertEquals("Your health status: SLEEP\n", model2.getPlayerStatus());
    assertTrue(model2.isEnd());
    assertEquals("Your health has dropped to SLEEP ZONE.\n"
            + "Night-night.\n"
            + "GAME OVER!\n"
            + "Thank you for playing!\n"
            + "Your score is: 5\n"
            + "Your rank is: BRONZE\n", model2.userSleep());
  }

  /**
   * test missing data case.
   */
  @Test
  void missingDataTest() {
    assertEquals("[]", model3.getGameObjects().getItemList().toString());
    assertEquals("[]", model3.getGameObjects().getFixtureList().toString());
    assertEquals("[]", model3.getGameObjects().getMonsterList().toString());
    assertEquals(3, model3.getGameObjects().getRoomList().size());
    assertEquals("generic_location.png", model3.look()[1]);

  }

  /**
   * test initialization correctly for the constructor.
   */
  @Test
  void testInitialization() {
    assertNotNull(model1.getRoomManager());
    assertNotNull(model1.getPlayer());
    assertNotNull(model1.getGameData());
    assertNotNull(model1.getGameObjects());
    assertEquals("save.json", model1.getSavedName());
    assertEquals("A4", model1.getPlayer().getName());
  }

  /**
   * test rename.
   */
  @Test
  void reName() {
    model1.reName("cc");
    assertEquals("cc", model1.getPlayer().getName());
  }

  /**
   * test solvePuzzle.
   */
  @Test
  void solvePuzzle() {
    assertNotNull(model1.getCurrentRoom().getPuzzle());
    assertEquals( "answer something!!!", model1.answer(""));
    assertEquals("This puzzle requires an item!", model1.answer("ticket"));
    model1.take("ticket");
    assertEquals("SUCCESS! You insert the ticket. 'Swish! Beep!'\n", model1.use("Ticket"));
  }

  /**
   * test drop.
   */
  @Test
  void drop() {
    assertEquals("ticket is NOT in your inventory. Nothing dropped.", model1.drop("ticket"));
    model1.take("ticket");
    assertEquals("ticket dropped here in Museum Entrance",  model1.drop("ticket"));
    assertEquals("ticket added to inventory.", model1.take("ticket"));
  }

  /**
   * test examine.
   */
  @Test
  void examine() {
    assertEquals("There's no item or fixture named key here.", model1.examine("key"));
    assertEquals("From the " + model1.getCurrentRoom().getName()
                    + " you examine the ticket: A complimentary museum ticket. It says ADMIT ONE, pwd = Align.",
            model1.examine("ticket"));
    model1.take("ticket");
    assertEquals("From your inventory you examine the ticket: "
                    + "A complimentary museum ticket. It says ADMIT ONE, pwd = Align.",
            model1.examine("ticket"));
  }

  /**
   * test inventoryCheck.
   */
  @Test
  void inventoryCheck() {
    assertEquals("There is nothing in your inventory.", model1.inventoryCheck());
    model1.take("ticket");
    assertEquals("Items in your inventory: Ticket", model1.inventoryCheck());
  }

  /**
   * test look.
   */
  @Test
  void look() {
    assertEquals("You are standing in the Museum Entrance\n"
            + "There is a turnstile to the north. "
            + "It requires some type of payment or ticket to activate.\n"
            + "Items you see here: Ticket\n", model1.look()[0]);
    assertEquals("generic_puzzle.png", model1.look()[1]);
    model1.take("ticket");
    model1.use("ticket");
    assertEquals("You are standing in the Museum Entrance\n"
            + "You're standing at the entrance of the Museum of Natural History. "
            + "There's a turnstile that takes tickets, but it's already been activated and opened.\n"
            + "Items you see here: \n", model1.look()[0]);
    assertEquals("museum.png", model1.look()[1]);
  }

  /**
   * test too much take.
   */
  @Test
  void take() {
    model2.take("hair clippers");
    model2.movePlayer("N");
    model2.take("Thumb Drive");
    model2.take("Modulo 2");
    model2.movePlayer("N");
    model2.use("hair clippers");
    model2.take("lamp");
    model2.movePlayer("E");
    model2.take("key");
    model2.use("modulo 2");
    model2.movePlayer("E");
    model2.take("algorithms booK");
    model2.movePlayer("W");
    model2.movePlayer("N");
    model2.use("lamp");
    model2.movePlayer("W");
    model2.take("frying pan");
    assertEquals("Items in your inventory: "
                    + "Hair Clippers, Thumb Drive, Modulo 2, Lamp, Key, Algorithms Book, Frying Pan",
            model2.inventoryCheck());
    assertTrue(model2.getPlayer().getInventory().foldWeight() < 15);
    model2.movePlayer("S");
    assertEquals("Cannot add item. Weight limit exceeded.", model2.take("carrot"));

  }

  /**
   * test multiple uses and use not existing one.
   */
  @Test
  void use() {
    // can not use if puzzle is inactive.
    model1.take("ticket");
    model1.use("ticket");
    assertEquals("Oh no! ticket is either empty or cannot be used again!\n",
            model1.use("ticket"));


    // use item not exist or use in a room without puzzle and monster.
    model2.take("hair clippers");
    model2.movePlayer("N");
    model2.take("modulo 2");
    assertEquals("You try using the MODULO 2 but nothing interesting happens.\n",
            model2.use("modulo 2"));
    assertEquals("Oh no! ticket is either empty or cannot be used again!\n",
            model2.use("ticket"));

    // no remaining use
    for (int i = 0; i < 4;i++ ) {
      model2.use("hair clippers");
    }
    model2.movePlayer("N");
    assertEquals("Hair Clippers", model2.getCurrentRoom().getMonster().getSolution());
    assertEquals("You try using the MODULO 2 but nothing interesting happens.\n",
            model2.use("modulo 2"));
    assertEquals("Oh no! hair clippers is either empty or cannot be used again!\n",
            model2.use("hair clippers"));
  }

  /**
   * test uses in a room monster is already defeated,
   * no matter the item is the solution or not.
   */
  @Test
  void useInNormalRoom() {
    model2.take("hair clippers");
    model2.movePlayer("N");
    model2.take("modulo 2");
    model2.movePlayer("N");
    model2.use("hair clippers");
    assertEquals("You try using the MODULO 2 but nothing interesting happens.\n",
            model2.use("modulo 2"));
    assertEquals("You try using the HAIR CLIPPERS but nothing interesting happens.\n",
            model2.use("hair clippers"));

    model2.movePlayer("E");
    assertEquals("You try using the HAIR CLIPPERS but nothing interesting happens.\n",
            model2.use("hair clippers"));
    model2.use("modulo 2");
    assertEquals("You try using the MODULO 2 but nothing interesting happens.\n",
            model2.use("modulo 2"));
    assertEquals("You try using the HAIR CLIPPERS but nothing interesting happens.\n",
            model2.use("hair clippers"));
  }

  /**
   * test monsterAttack method after monster been defeated.
   */
  @Test
  void monsterAttackAfterDefeated() {
    model2.take("hair clippers");
    model2.movePlayer("N");
    model2.movePlayer("N");
    model2.use("hair clippers");
    assertEquals("", model2.monsterAttack());
  }

  /**
   * test Save.save method for path is a non-existing directory.
   */
//  @Test
//  void saveWrongFilepathTest() {
//    File tempDir = new File("test_output/new_folder_that_does_not_exist/");
//    String path = tempDir.getPath() + File.separator;
//
//    // Ensure the folder doesn't already exist
//    if (tempDir.exists()) {
//      tempDir.delete();
//    }
//
//    String result = Save.save(model1.getGameObjects(), model1.getPlayer(), "test_save.json", path);
//
//    assertEquals("Saved your current game!", result);
//
//    // Clean up saved file and directory
//    File savedFile = new File(path + "test_save.json");
//    if (savedFile.exists()) {
//      savedFile.delete();
//    }
//    tempDir.delete();
//  }

  /**
   * test null input for Move.move.
   */
  @Test
  void nullInputMove() {
    assertThrows(IllegalArgumentException.class,() -> {
      Move.move(null, "N", model1.getRoomManager());
    });
    assertThrows(IllegalArgumentException.class,() -> {
      Move.move(model1.getPlayer(), "N", null);
    });
  }

  /**
   * test null input for Answer.answer.
   */
  @Test
  void nullInputAnswer() {
    assertThrows(IllegalStateException.class,() -> {
      Answer.answer(null, "align", model1.getRoomManager());
    });
    assertThrows(IllegalStateException.class,() -> {
      Answer.answer(model1.getPlayer(), "align", null);
    });
    assertEquals("answer something!!!",
            Answer.answer(model1.getPlayer(), null, model1.getRoomManager()));
  }

  @Test
  void nullInputUse(){
    model1.take("ticket");
    assertThrows(IllegalStateException.class, () -> {
      Use.use(null, "ticket", model1.getRoomManager());
    });
    assertThrows(IllegalStateException.class, () -> {
      Use.use(model1.getPlayer(), "ticket", null);
    });
    assertThrows(IllegalStateException.class, () -> {
      Use.use(model1.getPlayer(), null, model1.getRoomManager());
    });


  }



}