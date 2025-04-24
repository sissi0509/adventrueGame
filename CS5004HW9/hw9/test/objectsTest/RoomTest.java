package objectsTest;

import model.dataClasses.ItemData;
import model.dataClasses.PuzzleData;
import model.dataClasses.RoomData;
import model.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Room.
 */
public class RoomTest {

    private List<IItem> items;
    private List<IFixture> fixtures;
    private IPuzzle puzzle;
    private IMonster monster;

    /**
     * Set up reusable objects for room creation.
     */
    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();
        items.add(new Item(new ItemData("Key", 1, 1, 1, "unlock", 10, "A small key", "key.png")));

        fixtures = new ArrayList<>();
        fixtures.add(new Fixture(new model.dataClasses.FixtureData("Table", 5, "A wooden table", "p", "s", "pic")));

        puzzle = Puzzle.createIfValid(new PuzzleData("Riddle", true, true, true, "answer", 100,
                "desc", "fx", "target", "pic.png"));

        monster = Monster.createIfValid(new model.dataClasses.MonsterData("Ghost", -10, true,
                true, true, "scream", "spooky", "ghost.png", true, "fear", "target", "torch", 30));
    }

    /**
     * Test createIfValid returns a valid Room with correct data.
     */
    @Test
    public void testCreateIfValid_ValidRoom() {
        RoomData data = new RoomData("Dark room", 2, 3, 4, 5,
                "puzzle", "monster", "item", "fixture", "room.png", 1, "Dungeon");

        Room room = Room.createIfValid(data, items, puzzle, monster, fixtures);
        assertNotNull(room);
        assertEquals("Dungeon", room.getName());
    }

    /**
     * Test createIfValid returns null on null name.
     */
    @Test
    public void testCreateIfValid_NullRoomName() {
        RoomData data = new RoomData("desc", 1, 1, 1, 1, "p", "m", "i", "f", "pic", 1, null);
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));
    }

    /**
     * Test createIfValid returns null on invalid room number.
     */
    @Test
    public void testCreateIfValid_InvalidRoomNumber() {
        RoomData data = new RoomData("desc", 1, 1, 1, 1, "p", "m", "i", "f", "pic", 0, "Room");
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));
    }

    /**
     * Test getItemList returns correct item names.
     */
    @Test
    public void testGetItemList() {
        Room room = makeValidRoom();
        List<String> names = room.getItemList();
        assertEquals(1, names.size());
        assertEquals("Key", names.get(0));
    }

    /**
     * Test getConnections returns proper directions.
     */
    @Test
    public void testGetConnections() {
        Room room = makeValidRoom();
        Map<String, Integer> conn = room.getConnections();
        assertEquals(4, conn.size());
        assertEquals(2, conn.get("N"));
    }

    /**
     * Test setConnectionRoom sets value to roomNumber.
     */
    @Test
    public void testSetConnectionRoom() {
        Room room = makeValidRoom();
        room.setConnectionRoom("N", "Next");
        assertEquals(1, room.getConnections().get("N"));
    }

    /**
     * Test setConnectionToNormal makes all values positive.
     */
    @Test
    public void testSetConnectionToNormal() {
        Room room = makeValidRoom();
        room.getConnections().put("S", -1);
        room.setConnectionToNormal();
        assertEquals(1, room.getConnections().get("S"));
    }

    /**
     * Test addItem adds item into room.
     */
    @Test
    public void testAddItem() {
        Room room = makeValidRoom();
        room.addItem(new Item(new ItemData("Lantern", 1, 1, 1, "light", 5, "desc", "pic")));
        assertTrue(room.getItemList().contains("Lantern"));
    }

    /**
     * Test removeItem removes item from room.
     */
    @Test
    public void testRemoveItem() {
        Room room = makeValidRoom();
        assertTrue(room.getItemList().contains("Key"));
        room.removeItem("key");
        assertFalse(room.getItemList().contains("Key"));
    }

    /**
     * Test all getters return expected values.
     */
    @Test
    public void testGetters() {
        Room room = makeValidRoom();
        assertEquals("Dungeon", room.getName());
        assertEquals("Dark room", room.getDescription());
        assertEquals(1, room.getRoomNumber());
        assertEquals("room.png", room.getPicture());
        assertEquals(monster, room.getMonster());
        assertEquals(puzzle, room.getPuzzle());
        assertEquals(fixtures, room.getFixtures());
        assertEquals(items, room.getItems());
    }

    /**
     * Utility method to construct a valid Room object.
     */
    private Room makeValidRoom() {
        RoomData data = new RoomData("Dark room", 2, 3, 4, 5,
                "puzzle", "monster", "item", "fixture", "room.png", 1, "Dungeon");
        return Room.createIfValid(data, new ArrayList<>(items), puzzle, monster, fixtures);
    }

    /** Test createIfValid returns null if room number is null. */
    @Test
    public void testCreateIfValid_NullRoomNumber() {
        RoomData data = new RoomData("desc", 1, 1, 1, 1, "p", "m", "i", "f", "pic", null, "Room");
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));
    }

    /** Test createIfValid returns null if description is null or blank. */
    @Test
    public void testCreateIfValid_NullOrBlankDescription() {
        RoomData data1 = new RoomData(null, 1, 1, 1, 1, "p", "m", "i", "f", "pic", 1, "Room");
        RoomData data2 = new RoomData("  ", 1, 1, 1, 1, "p", "m", "i", "f", "pic", 1, "Room");
        assertNull(Room.createIfValid(data1, items, puzzle, monster, fixtures));
        assertNull(Room.createIfValid(data2, items, puzzle, monster, fixtures));
    }

    /** Test createIfValid returns null if roomName is blank. */
    @Test
    public void testCreateIfValid_BlankRoomName() {
        RoomData data = new RoomData("desc", 1, 1, 1, 1, "p", "m", "i", "f", "pic", 1, "  ");
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));
    }

    /** Test createIfValid returns null if any direction is null. */
    @Test
    public void testCreateIfValid_NullDirections() {
        RoomData data = new RoomData("desc", null, 1, 1, 1, "p", "m", "i", "f", "pic", 1, "Room");
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));

        RoomData data2 = new RoomData("desc", 1, null, 1, 1, "p", "m", "i", "f", "pic", 1, "Room");
        assertNull(Room.createIfValid(data2, items, puzzle, monster, fixtures));
    }

    /** Test setConnectionRoom does nothing if direction is not in map. */
    @Test
    public void testSetConnectionRoom_InvalidDirection() {
        Room room = makeValidRoom();
        int oldSize = room.getConnections().size();
        room.setConnectionRoom("Z", "Unknown");
        assertEquals(oldSize, room.getConnections().size()); // nothing changed
    }

    /** Test removeItem does nothing if item not found. */
    @Test
    public void testRemoveItem_NotFound() {
        Room room = makeValidRoom();
        assertTrue(room.getItemList().contains("Key"));
        room.removeItem("nonexistent");
        assertEquals(1, room.getItemList().size()); // no removal
    }

    /** Test removeItem on empty item list does nothing. */
    @Test
    public void testRemoveItem_EmptyList() {
        RoomData data = new RoomData("desc", 1, 1, 1, 1, "p", "m", "i", "f", "pic", 1, "Room");
        Room room = Room.createIfValid(data, new ArrayList<>(), puzzle, monster, fixtures);
        room.removeItem("whatever"); // nothing to remove
        assertEquals(0, room.getItemList().size());
    }

    /** Test createIfValid returns null when E (east) connection is null. */
    @Test
    public void testCreateIfValid_EastIsNull() {
        RoomData data = new RoomData("desc", 1, 2, null, 4, "p", "m", "i", "f", "pic", 1, "Room");
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));
    }

    /** Test createIfValid returns null when W (west) connection is null. */
    @Test
    public void testCreateIfValid_WestIsNull() {
        RoomData data = new RoomData("desc", 1, 2, 3, null, "p", "m", "i", "f", "pic", 1, "Room");
        assertNull(Room.createIfValid(data, items, puzzle, monster, fixtures));
    }
}
