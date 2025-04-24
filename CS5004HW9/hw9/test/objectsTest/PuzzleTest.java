package objectsTest;

import model.dataClasses.PuzzleData;
import model.objects.Puzzle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Puzzle.
 */
public class PuzzleTest {

    private PuzzleData makeValidData() {
        return new PuzzleData(
                "Riddle", true, true, true, "solve it",
                50, "A tricky puzzle", "confuse enemy", "Library", "pic.png"
        );
    }

    /** Test that createIfValid returns a puzzle for valid input. */
    @Test
    public void testCreateIfValid_ValidData() {
        Puzzle puzzle = Puzzle.createIfValid(makeValidData());
        assertNotNull(puzzle);
        assertEquals("Riddle", puzzle.getName());
    }

    /** Test that createIfValid returns null for null name. */
    @Test
    public void testCreateIfValid_NullName() {
        PuzzleData data = new PuzzleData(null, true, true, true, "sol", 10, "desc", "fx", "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null isActive. */
    @Test
    public void testCreateIfValid_NullIsActive() {
        PuzzleData data = new PuzzleData("name", null, true, true, "sol", 10, "desc", "fx", "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null affectsPlayer. */
    @Test
    public void testCreateIfValid_NullAffectsPlayer() {
        PuzzleData data = new PuzzleData("name", true, true, null, "sol", 10, "desc", "fx", "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null solution. */
    @Test
    public void testCreateIfValid_NullSolution() {
        PuzzleData data = new PuzzleData("name", true, true, true, null, 10, "desc", "fx", "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null value. */
    @Test
    public void testCreateIfValid_NullValue() {
        PuzzleData data = new PuzzleData("name", true, true, true, "sol", null, "desc", "fx", "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null description. */
    @Test
    public void testCreateIfValid_NullDescription() {
        PuzzleData data = new PuzzleData("name", true, true, true, "sol", 10, null, "fx", "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null effects. */
    @Test
    public void testCreateIfValid_NullEffects() {
        PuzzleData data = new PuzzleData("name", true, true, true, "sol", 10, "desc", null, "target", "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test that createIfValid returns null for null target. */
    @Test
    public void testCreateIfValid_NullTarget() {
        PuzzleData data = new PuzzleData("name", true, true, true, "sol", 10, "desc", "fx", null, "pic");
        assertNull(Puzzle.createIfValid(data));
    }

    /** Test getters return correct values from valid data. */
    @Test
    public void testGetters() {
        Puzzle puzzle = Puzzle.createIfValid(makeValidData());
        assertEquals("Riddle", puzzle.getName());
        assertTrue(puzzle.getActive());
        assertEquals("confuse enemy", puzzle.getEffect());
        assertEquals("Library", puzzle.getTarget());
        assertEquals("solve it", puzzle.getSolution());
        assertEquals(50, puzzle.getValue());
        assertTrue(puzzle.getAffectsTarget());
        assertTrue(puzzle.getAffectsPlayer());
        assertEquals("A tricky puzzle", puzzle.getDescription());
        assertEquals("pic.png", puzzle.getPicture());
    }

    /** Test setActive deactivates the puzzle. */
    @Test
    public void testSetActive() {
        Puzzle puzzle = Puzzle.createIfValid(makeValidData());
        puzzle.setActive();
        assertFalse(puzzle.getActive());
    }
}
