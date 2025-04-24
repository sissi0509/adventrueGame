package dataClassTest;

import model.dataClasses.InventoryData;
import model.objects.IInventory;
import model.objects.IItem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the InventoryData class.
 *
 * This class contains unit tests for verifying the correctness of the
 * InventoryData constructors and their getter methods.
 *
 * It uses a fake implementation of  IInventory to provide controlled test data.
 */

public class InventoryDataTest {

    /**
     * A fake implementation used for testing.
     */

    private class FakeInventory implements IInventory {

        @Override
        public List<String> getItemList() {
            return Arrays.asList("Sword", "Shield", "Potion");
        }

        @Override
        public Integer foldWeight() {
            return 30;
        }


        @Override public boolean addItem(IItem item) { return false; }
        @Override public boolean removeItem(String itemName) { return false; }
        @Override public IItem getItem(String itemName) { return null; }
        @Override public Integer getWeightLimit() { return null; }
        @Override public List<IItem> getInventoryList() { return null; }
    }

    /**
     * Tests the constructor that takes an IInventory object.
     */
    @Test
    public void testConstructorWithIInventory() {
        IInventory inventory = new FakeInventory();
        InventoryData inventoryData = new InventoryData(inventory);

        assertEquals("Sword, Shield, Potion", inventoryData.getInventoryList());
        assertEquals(30, inventoryData.getTotalWeight());
    }

    /**
     * Tests the constructor that takes item list and total weight as parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        InventoryData inventoryData = new InventoryData("Bow, Arrow", 20);

        assertEquals("Bow, Arrow", inventoryData.getInventoryList());
        assertEquals(20, inventoryData.getTotalWeight());
    }
}
