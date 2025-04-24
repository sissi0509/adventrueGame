package model.utilityClass;

import model.objects.IMonster;
import model.objects.IPlayer;
import model.objects.IPuzzle;
import model.objects.IRoom;
import model.objects.IRoomManager;

/**
 * Utility class for handling monster attack logic in the game.
 */

public class CheckAttack {
    /**
     * Checks if the player is attacked by the monster in the current room.
     * If there is an active monster in the room and it can attack.
     * @param player  The player in the current room
     * @param roomManager The manager that keeps track of all rooms
     * @return  a string message describing the attack and player's damage, or an empty string.
     */
    public static String checkAttack(IPlayer player, IRoomManager roomManager) {
        IRoom room = roomManager.getRoomByNumber(player.getRoomNumber());
        IMonster monster = room.getMonster(); // should be null if there's no monster
        IPuzzle puzzle = room.getPuzzle(); // should be null if there's no monster
        String attackMessage = "";

        if (monster != null && monster.getActive()) {
            int damage = 0;
            if (monster.getCanAttack()) { // player get damaged when monster can attack
                damage = monster.getDamage();
                player.setHP(damage);
                attackMessage = getPlayerDamageStatus(player, monster);
                return attackMessage;
            }
        }
        return attackMessage;
    }

    /**
     * Return player HP in text mode.
     */
    private static String getPlayerDamageStatus(IPlayer player, IMonster monster) {
        if (monster != null && monster.getActive() && monster.getCanAttack()) {
            int damage = monster.getDamage();
            return "Player takes " + damage + " damage!\n";
        }
        return "\n";
    }
}