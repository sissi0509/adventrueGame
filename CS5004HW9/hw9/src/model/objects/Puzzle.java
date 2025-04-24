package model.objects;

import model.dataClasses.PuzzleData;

/**
 * PuzzleImpl class that represents a puzzle. Puzzle has name,
 * active, effect, target, solution, value, affectsTarget, affectsPlayer,
 * description and picture.
 */
public class Puzzle implements IPuzzle {
  private String name;
  private Boolean active;
  private String effect;
  private String target;
  private String solution;
  private Integer value;
  private Boolean affectsTarget;
  private Boolean affectsPlayer;
  private String description;
  private String picture;

  /**
   * Constructor of PuzzleImpt class. PuzzleImpl class get
   * PuzzleData type data.
   * @param data PuzzleData type data which holds the data of puzzle.
   */
  public Puzzle(PuzzleData data) {
    this.name = data.getName();
    this.active = data.isActive();
    this.effect = data.getEffects();
    this.target = data.getTarget();
    this.solution = data.getSolution();
    this.value = data.getValue();
    this.affectsTarget = data.isAffectsTarget();
    this.affectsPlayer = data.isAffectsPlayer();
    this.description = data.getDescription();
    this.picture = data.getPicture();
  }

  /**
   * Method that create puzzle if data is valid.
   * @param data data of puzzle
   * @return Puzzle object if can create puzzle, otherwise, null.
   */
  public static Puzzle createIfValid(PuzzleData data) {
    if (data.getName() == null || data.isActive() == null || data.isAffectsPlayer() == null ||
            data.getSolution() == null || data.getValue() == null || data.getDescription() == null ||
            data.getEffects() == null || data.getTarget() == null) {
      return null;
    }

    return new Puzzle(data);
  }

  public String getPicture() {
    return picture;
  }

  /**
   * Gets the name of a puzzle.
   * @return name of a puzzle.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the active state of a puzzle.
   * @return active state of a puzzle.
   */
  @Override
  public Boolean getActive() {
    return this.active;
  }

  /**
   * Gets the effect of a puzzle.
   * @return effect of a puzzle.
   */
  @Override
  public String getEffect() {
    return this.effect;
  }

  /**
   * Gets a target (which puzzle is located) of a puzzle.
   * @return target of a puzzle.
   */
  @Override
  public String getTarget() {
    return this.target;
  }

  /**
   * Gets a solution of a puzzle.
   * @return solution of a puzzle.
   */
  @Override
  public String getSolution() {
    return this.solution;
  }

  /**
   * Gets the value of a puzzle.
   * @return value of a puzzle.
   */
  @Override
  public Integer getValue() {
    return this.value;
  }

  /**
   * Gets the state of a puzzle rather puzzle can affect
   * the target or not.
   * @return state represents puzzle can affect the target or not.
   */
  @Override
  public Boolean getAffectsTarget() {
    return this.affectsTarget;
  }

  /**
   * Gets the state of a puzzle rather puzzle can affect
   * the player or not.
   * @return state represents puzzle can affect the player or not.
   */
  @Override
  public boolean getAffectsPlayer() {
    return this.affectsPlayer;
  }

  /**
   * Gets the description of a puzzle.
   * @return description of a puzzle.
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Set the active state of a puzzle to false (deactivate)
   * when puzzle is solved.
   */
  @Override
  public void setActive() {
    this.active = false;
  }
}
