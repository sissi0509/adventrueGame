package objectsTest;

import model.dataClasses.*;
import model.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GameObjects.
 */
public class GameObjectsTest {

    private IRoom room;
    private IItem item;
    private IFixture fixture;
    private IMonster monster;
    private IPuzzle puzzle;
    private GameObjects gameObjects;

    /**
     * Set up all game components using their respective data classes.
     */
    @BeforeEach
    public void setUp() {
        // Item
        item = new Item(new ItemData("Key", 1, 2, 2, "use", 10, "desc", "pic.png"));

        // Fixture
        fixture = new Fixture(new FixtureData("Box", 5, "desc", "puzzle", "state", "pic.png"));

        // Monster
        monster = Monster.createIfValid(new MonsterData(
                "Ghost", -5, true, true, true, "scream",
                "desc", "ghost.png", true, "fear", "cellar", "torch", 50));

        // Puzzle
        puzzle = Puzzle.createIfValid(new PuzzleData(
                "Riddle", true, true, true, "solve", 30,
                "desc", "fx", "target", "pic.png"));

        // Room
        RoomData roomData = new RoomData("Dark Room", 1, 2, 3, 4,
                puzzle.getName(), monster.getName(), item.getName(), fixture.getName(),
                "room.png", 101, "Room101");

        room = Room.createIfValid(roomData, List.of(item), puzzle, monster, List.of(fixture));

        // GameObjects
        gameObjects = new GameObjects(
                List.of(room),
                List.of(item),
                List.of(fixture),
                List.of(monster),
                List.of(puzzle),
                "MyGame"
        );
    }

    /** Test getRoomList returns correct room list. */
    @Test
    public void testGetRoomList() {
        List<IRoom> rooms = gameObjects.getRoomList();
        assertEquals(1, rooms.size());
        assertEquals("Room101", rooms.get(0).getName());
    }

    /** Test getItemList returns correct item list. */
    @Test
    public void testGetItemList() {
        List<IItem> items = gameObjects.getItemList();
        assertEquals(1, items.size());
        assertEquals("Key", items.get(0).getName());
    }

    /** Test getFixtureList returns correct fixture list. */
    @Test
    public void testGetFixtureList() {
        List<IFixture> fixtures = gameObjects.getFixtureList();
        assertEquals(1, fixtures.size());
        assertEquals("Box", fixtures.get(0).getName());
    }

    /** Test getMonsterList returns correct monster list. */
    @Test
    public void testGetMonsterList() {
        List<IMonster> monsters = gameObjects.getMonsterList();
        assertEquals(1, monsters.size());
        assertEquals("Ghost", monsters.get(0).getName());
    }

    /** Test getPuzzleList returns correct puzzle list. */
    @Test
    public void testGetPuzzleList() {
        List<IPuzzle> puzzles = gameObjects.getPuzzleList();
        assertEquals(1, puzzles.size());
        assertEquals("Riddle", puzzles.get(0).getName());
    }

    /** Test getGameName returns the correct game name. */
    @Test
    public void testGetGameName() {
        assertEquals("MyGame", gameObjects.getGameName());
    }
}

