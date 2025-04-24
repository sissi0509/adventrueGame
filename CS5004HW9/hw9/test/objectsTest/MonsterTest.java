package objectsTest;

import model.dataClasses.MonsterData;
import model.objects.Monster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Monster.
 */
public class MonsterTest {

    private MonsterData makeValidData() {
        return new MonsterData(
                "Zombie", -10, true, true, true,
                "swipe", "Undead horror", "zombie.png", true,
                "loses HP", "Graveyard", "use torch", 50
        );
    }

    /** Test createIfValid returns a valid Monster object when data is valid. */
    @Test
    public void testCreateIfValid_ValidData() {
        Monster monster = Monster.createIfValid(makeValidData());
        assertNotNull(monster);
        assertEquals("Zombie", monster.getName());
    }

    /** Test createIfValid returns null when any required field is null. */
    @Test
    public void testCreateIfValid_NullRequiredFields() {
        assertNull(Monster.createIfValid(new MonsterData(
                null, -5, true, true, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 10)));
        assertNull(Monster.createIfValid(new MonsterData(
                "X", null, true, true, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 10)));
        assertNull(Monster.createIfValid(new MonsterData(
                "X", -5, null, true, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 10)));
        assertNull(Monster.createIfValid(new MonsterData(
                "X", -5, true, null, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 10)));
        assertNull(Monster.createIfValid(new MonsterData(
                "X", -5, true, true, null, "atk", "desc", "pic", true, "fx", "loc", "sol", 10)));
        assertNull(Monster.createIfValid(new MonsterData(
                "X", -5, true, true, true, null, "desc", "pic", true, "fx", "loc", "sol", 10)));
    }

    /** Test createIfValid returns null when blank strings or invalid values exist. */
    @Test
    public void testCreateIfValid_BlankOrInvalidFields() {
        assertNull(Monster.createIfValid(new MonsterData(
                " ", -5, true, true, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 10)));

        assertNull(Monster.createIfValid(new MonsterData(
                "M", -5, true, true, true, "atk", " ", "pic", true, "fx", "loc", "sol", 10)));

        assertNull(Monster.createIfValid(new MonsterData(
                "M", -5, true, true, true, "atk", "desc", "pic", true, " ", "loc", "sol", 10)));

        assertNull(Monster.createIfValid(new MonsterData(
                "M", -5, true, true, true, "atk", "desc", "pic", true, "fx", " ", "sol", 10)));

        assertNull(Monster.createIfValid(new MonsterData(
                "M", -5, true, true, true, "atk", "desc", "pic", true, "fx", "loc", " ", 10)));

        assertNull(Monster.createIfValid(new MonsterData(
                "M", 0, true, true, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 10))); // damage ≥ 0

        assertNull(Monster.createIfValid(new MonsterData(
                "M", -5, true, true, true, "atk", "desc", "pic", true, "fx", "loc", "sol", 0))); // value ≤ 0
    }

    /** Test all getter methods return expected values. */
    @Test
    public void testAllGetters() {
        Monster monster = Monster.createIfValid(makeValidData());
        assertEquals("Zombie", monster.getName());
        assertTrue(monster.getAffectsTarget());
        assertTrue(monster.getAffectsPlayer());
        assertTrue(monster.getActive());
        assertTrue(monster.getCanAttack());
        assertEquals("loses HP", monster.getEffect());
        assertEquals(-10, monster.getDamage());
        assertEquals("Graveyard", monster.getTarget());
        assertEquals("use torch", monster.getSolution());
        assertEquals(50, monster.getValue());
        assertEquals("Undead horror", monster.getDescription());
        assertEquals("swipe", monster.getAttack());
        assertEquals("zombie.png", monster.getPicture());
    }

    /** Test setActive updates the active state. */
    @Test
    public void testSetActive() {
        Monster monster = Monster.createIfValid(makeValidData());
        assertTrue(monster.getActive());
        monster.setActive(false);
        assertFalse(monster.getActive());
    }

    /** Test setCanAttack updates the canAttack state. */
    @Test
    public void testSetCanAttack() {
        Monster monster = Monster.createIfValid(makeValidData());
        assertTrue(monster.getCanAttack());
        monster.setCanAttack(false);
        assertFalse(monster.getCanAttack());
    }
}
