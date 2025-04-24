package dataClassTest;

import model.dataClasses.PlayerData;
import model.objects.HEALTH_STATUS;
import model.objects.IInventory;
import model.objects.IPlayer;
import model.objects.RANK;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class used to test PlayerData class.
 */
public class PlayerDataTest {

    /**
     * A fake implementation of IPlayer used for testing.
     */
    private class FakePlayer implements IPlayer {

        @Override
        public String getName() {
            return "Jay";
        }

        @Override
        public Integer getHealth() {
            return 80;
        }

        @Override
        public Integer getRoomNumber() {
            return 5;
        }

        @Override
        public Integer getScore() {
            return 1000;
        }


        @Override public HEALTH_STATUS getPlayerStatus() { return null; }
        @Override public IInventory getInventory() { return null; }
        @Override public RANK getRank() { return null; }
        @Override public void setRoomNumber(Integer roomNumber) {}
        @Override public void setHP(Integer value) {}
        @Override public void setScore(Integer value) {}
        @Override public void reName(String name) {}
    }

    /**
     *Test the PlayerData constructor that takes an IPlayer object.
     */
    @Test
    public void testConstructorWithIPlayer() {
        IPlayer player = new FakePlayer();
        PlayerData playerData = new PlayerData(player);

        assertEquals("Jay", playerData.getName());
        assertEquals(80, playerData.getHealthStatus());
        assertEquals(5, playerData.getRoomNumber());
        assertEquals(1000, playerData.getScore());
    }

    /**
     *Test the PlayerData constructor that takes name, health, room number.
     * and score as parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        PlayerData playerData = new PlayerData("John", 70, 2, 500);

        assertEquals("John", playerData.getName());
        assertEquals(70, playerData.getHealthStatus());
        assertEquals(2, playerData.getRoomNumber());
        assertEquals(500, playerData.getScore());
    }
}
