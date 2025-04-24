package objectsTest;

import model.dataClasses.ItemData;
import model.objects.IItem;
import model.objects.Inventory;
import model.objects.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Inventory.
 */
public class InventoryTest {

    private Inventory inventory;

    /**
     * Set up.
     */
    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
    }

    private IItem makeItem(String name, int weight) {
        return new Item(new ItemData(
                name, weight, 3, 3,
                "used", 15,
                "desc", "pic.png"
        ));
    }

    /**
     * test add item successfully.
     */
    @Test
    public void testAddItem_Success() {
        IItem item = makeItem("apple", 5);
        assertTrue(inventory.addItem(item));
        assertEquals(5, inventory.foldWeight());
        assertEquals(List.of("apple"), inventory.getItemList());
    }

    /**
     * test add item too heavy.
     */
    @Test
    public void testAddItem_TooHeavy() {
        inventory.addItem(makeItem("item1", 10));
        assertFalse(inventory.addItem(makeItem("item2", 5)));
        assertEquals(10, inventory.foldWeight());
    }

    /**
     * Test remove item successfully.
     */
    @Test
    public void testRemoveItem_Success() {
        IItem item = makeItem("torch", 4);
        inventory.addItem(item);
        assertTrue(inventory.removeItem("torch"));
        assertEquals(0, inventory.foldWeight());
    }

    /**
     * Test remove item that not exists.
     */
    @Test
    public void testRemoveItem_NotExist() {
        inventory.addItem(makeItem("box", 2));
        assertFalse(inventory.removeItem("nonexistent"));
    }

    /**
     * test remove null.
     */
    @Test
    public void testRemoveItem_Null() {
        assertFalse(inventory.removeItem(null));
    }

    /**
     * test get item successfully.
     */
    @Test
    public void testGetItem_Found() {
        inventory.addItem(makeItem("lantern", 3));
        IItem found = inventory.getItem("lantern");
        assertNotNull(found);
        assertEquals("lantern", found.getName());
    }

    /**
     * Test get item, case insensitive.
     */
    @Test
    public void testGetItem_CaseInsensitive() {
        inventory.addItem(makeItem("Key", 2));
        assertNotNull(inventory.getItem("key"));
        assertNotNull(inventory.getItem("KEY"));
    }

    /**
     * Test get item that not exists.
     */
    @Test
    public void testGetItem_NotFound() {
        assertNull(inventory.getItem("ghost"));
    }

    /**
     * Test get item with null input.
     */
    @Test
    public void testGetItem_NullInput() {
        assertNull(inventory.getItem(null));
    }

    /**
     * Test get empty item list.
     */
    @Test
    public void testGetItemList_Empty() {
        assertTrue(inventory.getItemList().isEmpty());
    }

    /**
     * test get weight limit.
     */
    @Test
    public void testGetWeightLimit() {
        assertEquals(13, inventory.getWeightLimit());
    }

    /**
     * test get inventory list.
     */
    @Test
    public void testGetInventoryList() {
        IItem i1 = makeItem("rock", 1);
        inventory.addItem(i1);
        List<IItem> list = inventory.getInventoryList();
        assertEquals(1, list.size());
        assertEquals("rock", list.get(0).getName());
    }
}