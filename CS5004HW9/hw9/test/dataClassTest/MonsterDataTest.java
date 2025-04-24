package dataClassTest;

import model.dataClasses.MonsterData;
import model.objects.IMonster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class used to test MonsterData class.
 */
public class MonsterDataTest {

    /**
     * A fake implementation used for testing.
     */
    private class FakeMonster implements IMonster {

        @Override
        public String getName() { return "Dragon"; }

        @Override
        public Integer getDamage() { return 50; }

        @Override
        public boolean getCanAttack() { return true; }

        @Override
        public boolean getAffectsTarget() { return true; }

        @Override
        public boolean getAffectsPlayer() { return true; }

        @Override
        public String getAttack() { return "Fire Breath"; }

        @Override
        public String getDescription() { return "Very Dangerous"; }

        @Override
        public String getPicture() { return "dragon.png"; }

        @Override
        public boolean getActive() { return true; }

        @Override
        public String getEffect() { return "Burn"; }

        @Override
        public String getTarget() { return "Player"; }

        @Override
        public String getSolution() { return "UseIce"; }

        @Override
        public Integer getValue() { return 500; }

        @Override public void setActive(Boolean state) {}
        @Override public void setCanAttack(Boolean state) {}
    }

    /**
     * Test the MonsterData constructor that takes an IMonster object.
     */
    @Test
    public void testConstructorWithIMonster() {
        IMonster monster = new FakeMonster();
        MonsterData monsterData = new MonsterData(monster);

        assertEquals("Dragon", monsterData.getName());
        assertEquals(50, monsterData.getDamage());
        assertEquals(true, monsterData.isCanAttack());
        assertEquals(true, monsterData.isAffectsTarget());
        assertEquals(true, monsterData.isAffectPlayer());
        assertEquals("Fire Breath", monsterData.getAttack());
        assertEquals("Very Dangerous", monsterData.getDescription());
        assertEquals("dragon.png", monsterData.getPicture());
        assertEquals(true, monsterData.isActive());
        assertEquals("Burn", monsterData.getEffects());
        assertEquals("Player", monsterData.getTarget());
        assertEquals("UseIce", monsterData.getSolution());
        assertEquals(500, monsterData.getValue());
    }

    /**
     * Test the MonsterData constructor that takes monster attributes as parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        MonsterData monsterData = new MonsterData("Wolf", 30,
                false, false, true,
                "Bite", "Fast", "wolf.png", false,
                "Bleed", "Room", "Trap", 200);

        assertEquals("Wolf", monsterData.getName());
        assertEquals(30, monsterData.getDamage());
        assertEquals(false, monsterData.isCanAttack());
        assertEquals(false, monsterData.isAffectsTarget());
        assertEquals(true, monsterData.isAffectPlayer());
        assertEquals("Bite", monsterData.getAttack());
        assertEquals("Fast", monsterData.getDescription());
        assertEquals("wolf.png", monsterData.getPicture());
        assertEquals(false, monsterData.isActive());
        assertEquals("Bleed", monsterData.getEffects());
        assertEquals("Room", monsterData.getTarget());
        assertEquals("Trap", monsterData.getSolution());
        assertEquals(200, monsterData.getValue());
    }
}
