package dataClassTest;

import model.dataClasses.RoomData;
import model.objects.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class used to test the RoomData class.
 */
public class RoomDataTest {

    /**
     * A fake implementation of IFixture used for testing.
     */
    private class FakeFixture implements IFixture {
        @Override
        public String getName() { return "Lamp"; }
        @Override
        public Integer getWeight() { return 1; }
        @Override
        public String getDescription() { return "desc"; }
        @Override
        public String getPuzzle() { return "puzzle"; }
        @Override
        public String getStates() { return "state"; }
        @Override
        public String getPicture() { return "fixture.png"; }
    }

    /**
     * A fake implementation of IMonster used for testing.
     */
    private class FakeMonster implements IMonster {
        @Override public String getName() { return "Ghost"; }
        @Override public boolean getAffectsTarget() { return false; }
        @Override public boolean getAffectsPlayer() { return false; }
        @Override public boolean getActive() { return true; }
        @Override public boolean getCanAttack() { return false; }
        @Override public String getEffect() { return "Scare"; }
        @Override public Integer getDamage() { return 10; }
        @Override public String getTarget() { return "Room"; }
        @Override public String getSolution() { return "Run"; }
        @Override public Integer getValue() { return 100; }
        @Override public String getDescription() { return "Scary"; }
        @Override public String getAttack() { return "Attack"; }
        @Override public String getPicture() { return "monster.png"; }
        @Override public void setActive(Boolean state) {}
        @Override public void setCanAttack(Boolean state) {}
    }

    /**
     * A fake implementation of IPuzzle used for testing.
     */
    private class FakePuzzle implements IPuzzle {
        @Override public String getName() { return "PuzzleX"; }
        @Override public Boolean getActive() { return true; }
        @Override public String getEffect() { return "EffectX"; }
        @Override public String getTarget() { return "Room"; }
        @Override public String getSolution() { return "Solve"; }
        @Override public Integer getValue() { return 20; }
        @Override public Boolean getAffectsTarget() { return false; }
        @Override public boolean getAffectsPlayer() { return true; }
        @Override public String getDescription() { return "desc"; }
        @Override public void setActive() {}
        @Override public String getPicture() { return "puzzle.png"; }
    }

    /**
     * A fake implementation of IRoom used for testing.
     */
    private class FakeRoom implements IRoom {
        @Override public String getName() { return "Living Room"; }
        @Override public Integer getRoomNumber() { return 1; }
        @Override public String getDescription() { return "A nice room"; }
        @Override public Map<String, Integer> getConnections() {
            Map<String, Integer> map = new HashMap<>();
            map.put("N", 2); map.put("S", 3); map.put("E", 4); map.put("W", 5);
            return map;
        }
        @Override public List<IItem> getItems() { return new ArrayList<>(); }
        @Override public List<String> getItemList() { return Arrays.asList("Chair", "Table"); }
        @Override public List<IFixture> getFixtures() {
            return Arrays.asList(new FakeFixture(), new FakeFixture());
        }
        @Override public IMonster getMonster() { return new FakeMonster(); }
        @Override public IPuzzle getPuzzle() { return new FakePuzzle(); }
        @Override public void setConnectionRoom(String direction, String roomName) {}
        @Override public void setConnectionToNormal() {}
        @Override public void removeItem(String itemName) {}
        @Override public void addItem(IItem item) {}
        @Override public String getPicture() { return "room.png"; }
    }

    /**
     *Test the RoomData constructor that takes an IRoom object.
     */
    @Test
    public void testConstructorWithRoom() {
        IRoom room = new FakeRoom();
        RoomData roomData = new RoomData(room);

        assertEquals("Living Room", roomData.getRoomName());
        assertEquals(1, roomData.getRoomNumber());
        assertEquals("A nice room", roomData.getDescription());
        assertEquals(2, roomData.getN());
        assertEquals(3, roomData.getS());
        assertEquals(4, roomData.getE());
        assertEquals(5, roomData.getW());
        assertEquals("PuzzleX", roomData.getPuzzle());
        assertEquals("Ghost", roomData.getMonster());
        assertEquals("Chair, Table", roomData.getItems());
        assertEquals("Lamp, Lamp", roomData.getFixtures());
        assertEquals("room.png", roomData.getPicture());
    }

    /**
     *Test the RoomData constructor that takes room attributes as parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        RoomData roomData = new RoomData("desc", 1, 2, 3, 4,
                "PuzzleY", "MonsterZ", "Item1, Item2", "Fix1, Fix2",
                "pic.png", 9, "Test Room");

        assertEquals("Test Room", roomData.getRoomName());
        assertEquals(9, roomData.getRoomNumber());
        assertEquals("desc", roomData.getDescription());
        assertEquals(1, roomData.getN());
        assertEquals(2, roomData.getS());
        assertEquals(3, roomData.getE());
        assertEquals(4, roomData.getW());
        assertEquals("PuzzleY", roomData.getPuzzle());
        assertEquals("MonsterZ", roomData.getMonster());
        assertEquals("Item1, Item2", roomData.getItems());
        assertEquals("Fix1, Fix2", roomData.getFixtures());
        assertEquals("pic.png", roomData.getPicture());
    }
}
