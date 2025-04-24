package dataClassTest;

import model.dataClasses.ItemData;
import model.objects.IItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the ItemData class.
 */
public class ItemDataTest {

    /**
     * A fake implementation of IItem used for testing.
     */
    private class FakeItem implements IItem {

        @Override
        public String getName() {
            return "Sword";
        }

        @Override
        public Integer getWeight() {
            return 10;
        }

        @Override
        public Integer getMaxUses() {
            return 5;
        }

        @Override
        public Integer getUsesRemaining() {
            return 3;
        }

        @Override
        public String getWhenUsed() {
            return "Slash";
        }

        @Override
        public Integer getValue() {
            return 150;
        }

        @Override
        public String getDescription() {
            return "A sharp sword.";
        }

        @Override
        public String getPicture() {
            return "sword.png";
        }

        @Override
        public void setUsesRemaining() {
        }
    }

    /**
     * Test the constructor that takes the IItem object.
     */
    @Test
    public void testConstructorWithIItem() {
        IItem item = new FakeItem();
        ItemData itemData = new ItemData(item);

        assertEquals("Sword", itemData.getName());
        assertEquals(10, itemData.getWeight());
        assertEquals(5, itemData.getMaxUses());
        assertEquals(3, itemData.getUsesRemaining());
        assertEquals(150, itemData.getValue());
        assertEquals("Slash", itemData.getWhenUsed());
        assertEquals("A sharp sword.", itemData.getDescription());
        assertEquals("sword.png", itemData.getPicture());
    }

    /**
     * Tests the  ItemData constructor that takes item attributes as parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        ItemData itemData = new ItemData("Shield", 20, 10, 8,
                "Block", 200, "Heavy shield.", "shield.png");

        assertEquals("Shield", itemData.getName());
        assertEquals(20, itemData.getWeight());
        assertEquals(10, itemData.getMaxUses());
        assertEquals(8, itemData.getUsesRemaining());
        assertEquals(200, itemData.getValue());
        assertEquals("Block", itemData.getWhenUsed());
        assertEquals("Heavy shield.", itemData.getDescription());
        assertEquals("shield.png", itemData.getPicture());
    }
}
