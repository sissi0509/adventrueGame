package objectsTest;

import model.dataClasses.ItemData;
import model.dataClasses.PuzzleData;
import model.dataClasses.RoomData;
import model.dataClasses.MonsterData;
import model.dataClasses.FixtureData;
import model.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RoomManager.
 */
public class RoomManagerTest {

    private RoomManager roomManager;
    private Room room1;
    private Room room2;

    private List<IItem> items;
    private List<IFixture> fixtures;
    private IPuzzle puzzle;
    private IMonster monster;

    /**
     * Set up common dependencies and RoomManager instance before each test.
     */
    @BeforeEach
    public void setUp() {
        // Prepare shared puzzle, monster, item, fixture
        items = List.of(new Item(new ItemData("Key", 1, 1, 1, "used", 10, "desc", "pic.png")));
        fixtures = List.of(new Fixture(new FixtureData("Desk", 3, "desc", "p", "s", "pic.png")));

        puzzle = Puzzle.createIfValid(
                new PuzzleData("Riddle", true, true, true, "answer", 20, "desc",
                        "fx", "target", "p.png"));
        monster = Monster.createIfValid(
                new MonsterData("Ghost", -10, true, true, true, "scream", "desc",
                        "pic", true, "fx", "target", "torch", 30));

        // Create RoomData for room1 and room2
        RoomData data1 = new RoomData("Room 1 desc", 2, 3, 4, 5, "puzzle", "monster", "item",
                "fix", "room1.png", 1, "Room One");
        RoomData data2 = new RoomData("Room 2 desc", 3, 4, 5, 6, "puzzle", "monster", "item",
                "fix", "room2.png", 2, "Room Two");

        room1 = Room.createIfValid(data1, items, puzzle, monster, fixtures);
        room2 = Room.createIfValid(data2, items, puzzle, monster, fixtures);

        Map<Integer, IRoom> roomMap = new HashMap<>();
        roomMap.put(1, room1);
        roomMap.put(2, room2);

        roomManager = new RoomManager(roomMap);
    }

    /** Test getRoomByNumber returns correct room for existing keys. */
    @Test
    public void testGetRoomByNumber_Exists() {
        assertEquals(room1, roomManager.getRoomByNumber(1));
        assertEquals(room2, roomManager.getRoomByNumber(2));
    }

    /** Test getRoomByNumber returns null for missing room numbers. */
    @Test
    public void testGetRoomByNumber_NotExists() {
        assertNull(roomManager.getRoomByNumber(100));
    }
}
