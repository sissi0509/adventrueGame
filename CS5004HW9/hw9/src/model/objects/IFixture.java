package model.objects;

/**
 * Interface that represents a fixture.
 */
public interface IFixture {

  /**
   * Gets the name of a fixture.
   * @return name of a fixture
   */
  public String getName();

  /**
   * Gets the weight of a fixture.
   * @return weight of a fixture.
   */
  public Integer getWeight();

  /**
   * Get the description of a fixture.
   * @return description of a fixture.
   */
  public String getDescription();

  /**
   * get puzzle related to the fixture.
   * @return puzzle related to the fixture.
   */
  public String getPuzzle();

  /**
   * get status related to the fixture.
   * @return status related to the fixture.
   */
  public String getStates();

  /**
   * get picture name related to the fixture.
   * @return picture name related to the fixture.
   */
  public String getPicture();

}
