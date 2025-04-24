package dataClassTest;

import model.dataClasses.GameData;
import model.objects.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the GameData class.
 *
 * This class contains unit tests for verifying the correctness of the
 * GameData constructor and its getter methods.
 *
 * It uses fake implementations of IRoom, IItem,  IPuzzle,
 * IFixture, IMonster, IInventory, IPlayer, and  IGameObjects
 * to provide controlled test data.
 */

public class GameDataTest {

    // Fake Room
    private class FakeRoom implements IRoom {
        @Override public String getName() { return "RoomA"; }
        @Override public Integer getRoomNumber() { return 1; }
        @Override public String getDescription() { return "DescriptionA"; }
        @Override public java.util.Map<String, Integer> getConnections() {
            return java.util.Map.of("N", 2, "S",
                    3, "E", 4, "W", 5);
        }
        @Override public List<IItem> getItems() { return null; }
        @Override public List<String> getItemList() { return Arrays.asList("ItemA", "ItemB"); }
        @Override public List<IFixture> getFixtures() { return Arrays.asList(new FakeFixture()); }
        @Override public IMonster getMonster() { return new FakeMonster(); }
        @Override public IPuzzle getPuzzle() { return new FakePuzzle(); }
        @Override public String getPicture() { return "room.png"; }
        @Override public void setConnectionRoom(String direction, String roomName) {}
        @Override public void setConnectionToNormal() {}
        @Override public void removeItem(String itemName) {}
        @Override public void addItem(IItem item) {}
    }

    // Fake Item
    private class FakeItem implements IItem {
        @Override public String getName() { return "ItemA"; }
        @Override public Integer getWeight() { return 5; }
        @Override public Integer getMaxUses() { return 3; }
        @Override public Integer getUsesRemaining() { return 2; }
        @Override public String getWhenUsed() { return "EffectA"; }
        @Override public Integer getValue() { return 10; }
        @Override public String getDescription() { return "ItemDesc"; }
        @Override public String getPicture() { return "item.png"; }
        @Override public void setUsesRemaining() {}
    }

    // Fake Puzzle
    private class FakePuzzle implements IPuzzle {
        @Override public String getName() { return "PuzzleA"; }
        @Override public Boolean getActive() { return true; }
        @Override public Boolean getAffectsTarget() { return false; }
        @Override public boolean getAffectsPlayer() { return true; }
        @Override public String getSolution() { return "SolutionA"; }
        @Override public Integer getValue() { return 50; }
        @Override public String getDescription() { return "PuzzleDesc"; }
        @Override public String getEffect() { return "EffectX"; }
        @Override public String getTarget() { return "TargetA"; }
        @Override public String getPicture() { return "puzzle.png"; }
        @Override public void setActive() {}
    }

    // Fake Fixture
    private class FakeFixture implements IFixture {
        @Override public String getName() { return "FixtureA"; }
        @Override public Integer getWeight() { return 2; }
        @Override public String getDescription() { return "FixtureDesc"; }
        @Override public String getPuzzle() { return "PuzzleA"; }
        @Override public String getStates() { return "StatesA"; }
        @Override public String getPicture() { return "fixture.png"; }
    }

    // Fake Monster
    private class FakeMonster implements IMonster {
        @Override public String getName() { return "MonsterA"; }
        @Override public Integer getDamage() { return 20; }
        @Override public boolean getCanAttack() { return true; }
        @Override public boolean getAffectsTarget() { return true; }
        @Override public boolean getAffectsPlayer() { return true; }
        @Override public String getAttack() { return "AttackA"; }
        @Override public String getDescription() { return "MonsterDesc"; }
        @Override public String getPicture() { return "monster.png"; }
        @Override public boolean getActive() { return true; }
        @Override public String getEffect() { return "EffectM"; }
        @Override public String getTarget() { return "TargetM"; }
        @Override public String getSolution() { return "SolutionM"; }
        @Override public Integer getValue() { return 100; }
        @Override public void setActive(Boolean state) {}
        @Override public void setCanAttack(Boolean state) {}
    }

    // Fake Inventory
    private class FakeInventory implements IInventory {
        @Override public List<String> getItemList() {
            return Arrays.asList("ItemA", "ItemB");
        }
        @Override public Integer foldWeight() { return 15; }
        @Override public boolean addItem(IItem item) { return false; }
        @Override public boolean removeItem(String itemName) { return false; }
        @Override public IItem getItem(String itemName) { return null; }
        @Override public Integer getWeightLimit() { return null; }
        @Override public List<IItem> getInventoryList() { return null; }
    }

    // Fake Player
    private class FakePlayer implements IPlayer {
        @Override public String getName() { return "Jay"; }
        @Override public Integer getHealth() { return 80; }
        @Override public Integer getRoomNumber() { return 1; }
        @Override public Integer getScore() { return 200; }
        @Override public HEALTH_STATUS getPlayerStatus() { return null; }
        @Override public IInventory getInventory() { return new FakeInventory(); }
        @Override public RANK getRank() { return null; }
        @Override public void setRoomNumber(Integer roomNumber) {}
        @Override public void setHP(Integer value) {}
        @Override public void setScore(Integer value) {}
        @Override public void reName(String name) {}
    }

    // Fake GameObjects
    private class FakeGameObjects implements IGameObjects {
        @Override public List<IRoom> getRoomList() { return Arrays.asList(new FakeRoom()); }
        @Override public List<IPuzzle> getPuzzleList() { return Arrays.asList(new FakePuzzle()); }
        @Override public List<IFixture> getFixtureList() { return Arrays.asList(new FakeFixture()); }
        @Override public List<IItem> getItemList() { return Arrays.asList(new FakeItem()); }
        @Override public List<IMonster> getMonsterList() { return Arrays.asList(new FakeMonster()); }
        @Override public String getGameName() { return "AdventureX"; }
    }

    /**
     * Test the GameData constructor and all getter methods.
     *
     * This test verifies that GameData correctly initializes its fields
     * from the provided IGameObjects and IPlayer objects.
     * It checks the game name, room data, item data, fixture data, monster data,
     * puzzle data, player name, and inventory item list.
     */

    @Test
    public void testConstructorAndGetters() {
        GameData gameData = new GameData(new FakeGameObjects(), new FakePlayer());

        assertEquals("AdventureX", gameData.getName());
        assertEquals(1, gameData.getRoomData().size());
        assertEquals(1, gameData.getItemData().size());
        assertEquals(1, gameData.getFixtureData().size());
        assertEquals(1, gameData.getMonsterData().size());
        assertEquals(1, gameData.getPuzzleData().size());
        assertEquals("Jay", gameData.getPlayer().getName());
        assertEquals("ItemA, ItemB", gameData.getInventory().getInventoryList());
    }
}

