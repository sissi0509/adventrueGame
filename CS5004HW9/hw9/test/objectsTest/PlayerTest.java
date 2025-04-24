package objectsTest;

import model.dataClasses.ItemData;
import model.dataClasses.PlayerData;
import model.objects.Player;
import model.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Player.
 */
public class PlayerTest {

    private Inventory inventory;

    /**
     * Set up a fresh inventory before each test.
     */
    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
    }

    private Item makeItem(String name, int weight) {
        return new Item(new ItemData(
                name, weight, 3, 3,
                "used", 15,
                "desc", "pic.png"
        ));
    }

    /**
     * Test constructor with valid name sets default values correctly.
     */
    @Test
    public void testConstructorWithName_Valid() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName());
        assertEquals(100, player.getHealth());
        assertEquals(1, player.getRoomNumber());
        assertEquals(0, player.getScore());
        assertNotNull(player.getInventory());
    }

    /**
     * Test constructor with null or blank name uses default name.
     */
    @Test
    public void testConstructorWithName_NullOrBlank() {
        Player p1 = new Player(null);
        assertEquals("Default", p1.getName());

        Player p2 = new Player("   ");
        assertEquals("Default", p2.getName());
    }

    /**
     * Test constructor with PlayerData sets all fields correctly.
     */
    @Test
    public void testConstructorWithPlayerData() {
        PlayerData data = new PlayerData("Bob", 50, 3, 200);
        Player player = new Player(data, inventory);
        assertEquals("Bob", player.getName());
        assertEquals(50, player.getHealth());
        assertEquals(3, player.getRoomNumber());
        assertEquals(200, player.getScore());
        assertEquals(inventory, player.getInventory());
    }

    /**
     * Test getPlayerStatus for all health levels.
     */
    @Test
    public void testGetPlayerStatus() {
        Player player = new Player("Test");

        player.setHP(-200); // should clamp to 0
        assertEquals(HEALTH_STATUS.SLEEP, player.getPlayerStatus());

        player.setHP(10);  // now 10
        assertEquals(HEALTH_STATUS.FATIGUE, player.getPlayerStatus());

        player.setHP(40); // now 50
        assertEquals(HEALTH_STATUS.DIZZY, player.getPlayerStatus());

        player.setHP(50); // now 100
        assertEquals(HEALTH_STATUS.AWAKE, player.getPlayerStatus());
    }

    /**
     * Test getRank for all score levels.
     */
    @Test
    public void testGetRank() {
        Player player = new Player("Ranker");

        assertEquals(RANK.IRON, player.getRank());

        player.setScore(100);
        assertEquals(RANK.BRONZE, player.getRank());

        player.setScore(500);
        assertEquals(RANK.SILVER, player.getRank());

        player.setScore(1000);
        assertEquals(RANK.GOLD, player.getRank());
    }

    /**
     * Test setHP clamps health between 0 and 100.
     */
    @Test
    public void testSetHPClamping() {
        Player player = new Player("Clamp");

        player.setHP(-150);
        assertEquals(0, player.getHealth());

        player.setHP(250);
        assertEquals(100, player.getHealth());
    }

    /**
     * Test setRoomNumber changes the room number.
     */
    @Test
    public void testSetRoomNumber() {
        Player player = new Player("Roomie");
        player.setRoomNumber(5);
        assertEquals(5, player.getRoomNumber());
    }

    /**
     * Test setScore accumulates score correctly.
     */
    @Test
    public void testSetScore() {
        Player player = new Player("Scorer");
        player.setScore(100);
        player.setScore(50);
        assertEquals(150, player.getScore());
    }

    /**
     * Test reName changes the name of the player.
     */
    @Test
    public void testReName() {
        Player player = new Player("OldName");
        player.reName("NewName");
        assertEquals("NewName", player.getName());
    }

    /**
     * Test inventory remains consistent and functional.
     */
    @Test
    public void testInventoryUsage() {
        Player player = new Player("Inv");
        IInventory inv = player.getInventory();
        assertTrue(inv.addItem(makeItem("Sword", 5)));
        assertEquals(5, inv.foldWeight());
        assertEquals("Sword", inv.getItem("sword").getName());
    }

}
