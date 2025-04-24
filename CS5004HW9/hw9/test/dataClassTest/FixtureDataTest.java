package dataClassTest;

import model.dataClasses.FixtureData;
import model.objects.IFixture;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the FixtureData class.
 *
 * This class contains unit tests to verify the correctness of the FixtureData
 * constructors.
 */

public class FixtureDataTest {

    /**
     * A fake implementation of IFixture used for testing purposes.
     */

    private class FakeFixture implements IFixture {
        @Override
        public String getName() {
            return "Lamp";
        }

        @Override
        public Integer getWeight() {
            return 5;
        }

        @Override
        public String getDescription() {
            return "A simple lamp.";
        }

        @Override
        public String getPuzzle() {
            return "TurnOn";
        }

        @Override
        public String getStates() {
            return "Off";
        }

        @Override
        public String getPicture() {
            return "lamp.png";
        }
    }

    /**
     * Test the constructor that takes an IFixture object.
     * Verifies that all fields are correctly initialized based on the IFixture.
     */

    @Test
    public void testConstructorWithIFixture() {
        IFixture fixture = new FakeFixture();
        FixtureData fixtureData = new FixtureData(fixture);

        assertEquals("Lamp", fixtureData.getName());
        assertEquals(5, fixtureData.getWeight());
        assertEquals("TurnOn", fixtureData.getPuzzle());
        assertEquals("Off", fixtureData.getStates());
        assertEquals("A simple lamp.", fixtureData.getDescription());
        assertEquals("lamp.png", fixtureData.getPicture());
    }

    /**
     * Test the constructor that takes individual parameters.
     * Verifies that all fields are correctly initialized based on the given values.
     */

    @Test
    public void testConstructorWithParameters() {
        FixtureData fixtureData = new FixtureData("Chair", 10,
                "A wooden chair.",
                "FixLeg", "Broken", "chair.png");

        assertEquals("Chair", fixtureData.getName());
        assertEquals(10, fixtureData.getWeight());
        assertEquals("FixLeg", fixtureData.getPuzzle());
        assertEquals("Broken", fixtureData.getStates());
        assertEquals("A wooden chair.", fixtureData.getDescription());
        assertEquals("chair.png", fixtureData.getPicture());
    }
}
