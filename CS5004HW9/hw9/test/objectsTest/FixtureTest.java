package objectsTest;

import model.dataClasses.FixtureData;
import model.objects.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Fixture.
 */
public class FixtureTest {

    /**
     * Build FixtureData for test.
     */
    private FixtureData validData() {
        FixtureData data = new FixtureData("Table", 10, "A sturdy table.",
                "puzzle1", "state1", "table.png");
        return data;
    }

    /**
     * test CreateIfValid with valid data.
     */
    @Test
    public void testCreateIfValid_AllValid() {
        FixtureData data = validData();
        Fixture fixture = Fixture.createIfValid(data);
        assertNotNull(fixture);
        assertEquals("Table", fixture.getName());
        assertEquals(10, fixture.getWeight());
        assertEquals("A sturdy table.", fixture.getDescription());
        assertEquals("puzzle1", fixture.getPuzzle());
        assertEquals("state1", fixture.getStates());
        assertEquals("table.png", fixture.getPicture());
    }

    /**
     * test CreateIfValid with null name.
     */
    @Test
    public void testCreateIfValid_NullName() {
        FixtureData data = new FixtureData(null, 10, "A table.", "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test CreateIfValid with null weight.
     */
    @Test
    public void testCreateIfValid_NullWeight() {
        FixtureData data = new FixtureData("Chair", null, "Wooden chair.", "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test CreateIfValid with null description.
     */
    @Test
    public void testCreateIfValid_NullDescription() {
        FixtureData data = new FixtureData("Lamp", 5, null, "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test CreateIfValid with blank name.
     */
    @Test
    public void testCreateIfValid_BlankName() {
        FixtureData data = new FixtureData("   ", 10, "Desc", "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test CreateIfValid with zero weight.
     */
    @Test
    public void testCreateIfValid_ZeroWeight() {
        FixtureData data = new FixtureData("Sofa", 0, "A comfy sofa.", "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test CreateIfValid with negative weight.
     */
    @Test
    public void testCreateIfValid_NegativeWeight() {
        FixtureData data = new FixtureData("Bed", -5, "Soft bed.", "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test CreateIfValid with blank description.
     */
    @Test
    public void testCreateIfValid_BlankDescription() {
        FixtureData data = new FixtureData("TV", 5, "   ", "p", "s", "p.png");
        assertNull(Fixture.createIfValid(data));
    }

    /**
     * test getters.
     */
    @Test
    public void testGetters_ReturnCorrectValues() {
        FixtureData data = validData();
        Fixture fixture = Fixture.createIfValid(data);

        assertEquals("Table", fixture.getName());
        assertEquals(10, fixture.getWeight());
        assertEquals("A sturdy table.", fixture.getDescription());
        assertEquals("puzzle1", fixture.getPuzzle());
        assertEquals("state1", fixture.getStates());
        assertEquals("table.png", fixture.getPicture());
    }
}
