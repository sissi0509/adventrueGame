package model.objects;

import model.dataClasses.FixtureData;

/**
 * Class that represents a fixture. Fixture has name, weight and description.
 */
public class Fixture implements IFixture {
  private String name;
  private Integer weight;
  private String description;
  private String puzzle;
  private String states;
  private String picture;

  /**
   * Constructor of FixtureImpl. FixtureImpl gets data of fixture (Fixturedata)
   * and set the name, weight and description.
   * @param data data of a fixture.
   */
  public Fixture(FixtureData data) {
    this.name = data.getName();
    this.weight = data.getWeight();
    this.description = data.getDescription();
    this.puzzle = data.getPuzzle();
    this.states = data.getStates();
    this.picture = data.getPicture();
  }

  /**
   * check if a fixture can be created or not,
   * create if all information is correct.
   * @param data data of a fixture.
   * @return the fixture object if all information is correct.
   *          else null.
   */
  public static Fixture createIfValid(FixtureData data) {
    if (data.getName() == null || data.getWeight() == null || data.getDescription() == null
            || data.getName().isBlank()|| data.getWeight() <= 0 || data.getDescription().isBlank()) {
      return null;
    }
    return new Fixture(data);
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Integer getWeight() {
    return this.weight;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getPuzzle() {
    return this.puzzle;
  }

  @Override
  public String getStates() {
    return this.states;
  }

  @Override
  public String getPicture() {
    return picture;
  }
}
