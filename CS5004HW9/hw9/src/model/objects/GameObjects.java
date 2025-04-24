package model.objects;

import java.util.List;

/**
 * create a concrete class of GameObjects.
 * handle all room, item, fixture, monster, puzzle in the rooms.
 */
public class GameObjects implements IGameObjects {
  private List<IRoom> roomList;
  private List<IItem> itemList;
  private List<IFixture> fixtureList;
  private List<IMonster> monsterList;
  private List<IPuzzle> puzzleList;
  private String name;

  /**
   * constructor of the GameObjects.
   * @param roomList list of room objects.
   * @param itemList list of item objects.
   * @param fixtureList list of fixtures.
   * @param monsterList list of monsters.
   * @param puzzleList list of puzzles.
   */
  public GameObjects(List<IRoom> roomList,
                   List<IItem> itemList,
                   List<IFixture> fixtureList,
                   List<IMonster> monsterList,
                   List<IPuzzle> puzzleList, String gameName) {
    this.roomList = roomList;
    this.itemList = itemList;
    this.fixtureList = fixtureList;
    this.monsterList = monsterList;
    this.puzzleList = puzzleList;
    this.name = gameName;
  }
  @Override
  public List<IRoom> getRoomList() {
    return roomList;
  }

  @Override
  public List<IPuzzle> getPuzzleList() {
    return puzzleList;
  }

  @Override
  public List<IFixture> getFixtureList() {
    return fixtureList;
  }

  @Override
  public List<IItem> getItemList() {
    return itemList;
  }

  @Override
  public List<IMonster> getMonsterList() {
    return monsterList;
  }

  @Override
  public String getGameName() {
    return name;
  }
}
