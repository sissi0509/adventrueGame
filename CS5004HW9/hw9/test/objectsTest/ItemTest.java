package objectsTest;

import model.dataClasses.ItemData;
import model.objects.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Item.
 */
public class ItemTest {

    private ItemData makeValidData(String name, Integer weight, Integer maxUses, Integer usesRemaining,
                                   String whenUsed, Integer value, String desc, String pic) {
        return new ItemData(name, weight, maxUses, usesRemaining, whenUsed, value, desc, pic);
    }

    private ItemData defaultValidData() {
        return makeValidData("Torch", 2, 3, 2, "when used", 5, "desc", "pic.png");
    }

    /** Test createIfValid returns a valid item for valid input. */
    @Test
    public void testCreateIfValid_ValidInput() {
        Item item = Item.createIfValid(defaultValidData());
        assertNotNull(item);
        assertEquals("Torch", item.getName());
    }

    /** Test createIfValid returns null when name is null or blank. */
    @Test
    public void testCreateIfValid_InvalidName() {
        assertNull(Item.createIfValid(makeValidData(null, 1, 1, 1, "use", 1, "desc", "p")));
        assertNull(Item.createIfValid(makeValidData("   ", 1, 1, 1, "use", 1, "desc", "p")));
    }

    /** Test createIfValid returns null when whenUsed is null or blank. */
    @Test
    public void testCreateIfValid_InvalidWhenUsed() {
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 1, 1, null, 1, "desc", "p")));
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 1, 1, "   ", 1, "desc", "p")));
    }

    /** Test createIfValid returns null when description is null or blank. */
    @Test
    public void testCreateIfValid_InvalidDescription() {
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 1, 1, "use", 1, null, "p")));
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 1, 1, "use", 1, "   ", "p")));
    }

    /** Test createIfValid returns null when weight is null or negative. */
    @Test
    public void testCreateIfValid_InvalidWeight() {
        assertNull(Item.createIfValid(makeValidData("Torch", null, 1, 1, "use", 1, "desc", "p")));
        assertNull(Item.createIfValid(makeValidData("Torch", -1, 1, 1, "use", 1, "desc", "p")));
    }

    /** Test createIfValid returns null when maxUses is null or negative. */
    @Test
    public void testCreateIfValid_InvalidMaxUses() {
        assertNull(Item.createIfValid(makeValidData("Torch", 1, null, 1, "use", 1, "desc", "p")));
        assertNull(Item.createIfValid(makeValidData("Torch", 1, -1, 1, "use", 1, "desc", "p")));
    }

    /** Test createIfValid returns null when usesRemaining is invalid. */
    @Test
    public void testCreateIfValid_InvalidUsesRemaining() {
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 3, null, "use", 1, "desc", "p")));
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 3, 4, "use", 1, "desc", "p"))); // too much
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 3, -1, "use", 1, "desc", "p"))); // negative
    }

    /** Test createIfValid returns null when value is null or negative. */
    @Test
    public void testCreateIfValid_InvalidValue() {
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 1, 1, "use", null, "desc", "p")));
        assertNull(Item.createIfValid(makeValidData("Torch", 1, 1, 1, "use", -5, "desc", "p")));
    }

    /** Test all getter methods return correct values. */
    @Test
    public void testGetters() {
        Item item = Item.createIfValid(defaultValidData());
        assertEquals("Torch", item.getName());
        assertEquals(2, item.getWeight());
        assertEquals(3, item.getMaxUses());
        assertEquals(2, item.getUsesRemaining());
        assertEquals("when used", item.getWhenUsed());
        assertEquals(5, item.getValue());
        assertEquals("desc", item.getDescription());
        assertEquals("pic.png", item.getPicture());
    }

    /** Test setUsesRemaining decreases usesRemaining and stops at 0. */
    @Test
    public void testSetUsesRemaining() {
        Item item = Item.createIfValid(defaultValidData());
        item.setUsesRemaining(); // from 2 to 1
        assertEquals(1, item.getUsesRemaining());
        item.setUsesRemaining(); // from 1 to 0
        assertEquals(0, item.getUsesRemaining());
        item.setUsesRemaining(); // should stay at 0
        assertEquals(0, item.getUsesRemaining());
    }
}
