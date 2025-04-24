package model.dataClasses;

import model.objects.IFixture;

/**
 * Data class that holds serializable information about a fixture in the game world.
 * This class is used to transfer fixture data between the model and Json input/output.
 *
 */
public class FixtureData {
  private final String name;
  private final Integer weight;
  private final String puzzle;
  private final String states;
  private final String description;
  private final String picture;

  /**
   * Constructs a fixture data class based on the existing IFixture object.
   *
   * @param fixture The IFixture object to extract data from.
   */
  public FixtureData(IFixture fixture) {
    this.name = fixture.getName();
    this.weight = fixture.getWeight();
    this.puzzle = fixture.getPuzzle();
    this.states = fixture.getStates();
    this.description = fixture.getDescription();
    this.picture = fixture.getPicture();
  }

  /**
   * Constructor based on input parameters.
   */
  public FixtureData(String name, Integer weight, String description,
                     String puzzle, String states, String picture) {
    this.name = name;
    this.weight = weight;
    this.description = description;
    this.puzzle = puzzle;
    this.states = states;
    this.picture = picture;
  }

  /**
   * Get the name of the fixture.
   *
   * @return the name of the fixture.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the weight of the fixture.
   *
   * @return the weight of the fixture.
   */

  public Integer getWeight() {
    return weight;
  }

  /**
   * Get the puzzle of the fixture.
   *
   * @return the puzzle of the fixture.
   */

  public String getPuzzle() {
    return puzzle;
  }

  /**
   * Get the state of the fixture.
   *
   * @return the state of the fixture
   */

  public String getStates() {
    return states;
  }

  /**
   * Get the description of the fixture.
   *
   * @return the description of the fixture.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the picture of the fixture.
   *
   * @return the picture of the fixture.
   */

  public String getPicture() {
    return picture;
  }
}