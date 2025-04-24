package dataClassTest;

import model.dataClasses.PuzzleData;
import model.objects.IPuzzle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class used to test the PuzzleDayta class.
 */
public class PuzzleDataTest {

    /**
     * A fake implementation of IPuzzle used for testing.
     */
    private class FakePuzzle implements IPuzzle {
        @Override public String getName() { return "MagicPuzzle"; }
        @Override public Boolean getActive() { return true; }
        @Override public Boolean getAffectsTarget() { return true; }
        @Override public boolean getAffectsPlayer() { return false; }
        @Override public String getSolution() { return "UseKey"; }
        @Override public Integer getValue() { return 100; }
        @Override public String getDescription() { return "This is a magic puzzle."; }
        @Override public String getEffect() { return "Unlock"; }
        @Override public String getTarget() { return "Door"; }
        @Override public String getPicture() { return "puzzle.png"; }
        @Override public void setActive() {}
    }

    /**
     *Test the PuzzleData constructor that takes an IPuzzle object.
     */
    @Test
    public void testConstructorWithIPuzzle() {
        IPuzzle puzzle = new FakePuzzle();
        PuzzleData puzzleData = new PuzzleData(puzzle);

        assertEquals("MagicPuzzle", puzzleData.getName());
        assertEquals(true, puzzleData.isActive());
        assertEquals(true, puzzleData.isAffectsTarget());
        assertEquals(false, puzzleData.isAffectsPlayer());
        assertEquals("UseKey", puzzleData.getSolution());
        assertEquals(100, puzzleData.getValue());
        assertEquals("This is a magic puzzle.", puzzleData.getDescription());
        assertEquals("Unlock", puzzleData.getEffects());
        assertEquals("Door", puzzleData.getTarget());
        assertEquals("puzzle.png", puzzleData.getPicture());
    }

    /**
     *Test the PuzzleData constructor that takes puzzle attributes as parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        PuzzleData puzzleData = new PuzzleData("PuzzleX", false,
                false, true, "OpenBox",
                200, "desc", "EffectX",
                "TargetY", "pic.png");

        assertEquals("PuzzleX", puzzleData.getName());
        assertEquals(false, puzzleData.isActive());
        assertEquals(false, puzzleData.isAffectsTarget());
        assertEquals(true, puzzleData.isAffectsPlayer());
        assertEquals("OpenBox", puzzleData.getSolution());
        assertEquals(200, puzzleData.getValue());
        assertEquals("desc", puzzleData.getDescription());
        assertEquals("EffectX", puzzleData.getEffects());
        assertEquals("TargetY", puzzleData.getTarget());
        assertEquals("pic.png", puzzleData.getPicture());
    }
}
