package model.objects;

import java.util.List;

/**
 * interface of the game objects.
 */
public interface IGameObjects {
  /**
   * room list getter.
   * @return list of the room objects in the game.
   */
  List<IRoom> getRoomList();

  /**
   * puzzle getter.
   * @return list of the puzzles in the game.
   */
  List<IPuzzle> getPuzzleList();

  /**
   * fixture getter.
   * @return list of the fixtures in the game.
   */
  List<IFixture> getFixtureList();

  /**
   * items getter.
   * @return list of the items in all room.
   */
  List<IItem> getItemList();

  /**
   * monster getter.
   * @return list of all the monsters in the game.
   */
  List<IMonster> getMonsterList();

  /**
   * Get name of the game.
   */
  String getGameName();
}
