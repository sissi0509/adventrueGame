package model.dataClasses;

import model.objects.IPuzzle;
import com.google.gson.annotations.SerializedName;

/**
 * create a dataClass for puzzle.
 */
public class PuzzleData {
  private final String name;
  private final Boolean active;

  @SerializedName("affects_target")
  private final Boolean affectsTarget;

  @SerializedName("affects_player")
  private final Boolean affectsPlayer;

  private final String solution;
  private final Integer value;
  private final String description;
  private final String effects;
  private final String target;
  private final String picture;

  /**
   * constructor of puzzleData class.
   * create a puzzleData object based on a puzzle object.
   * @param puzzle a puzzle object.
   */
  public PuzzleData(IPuzzle puzzle) {
    this.name = puzzle.getName();
    this.active = puzzle.getActive();
    this.affectsPlayer = puzzle.getAffectsPlayer();
    this.affectsTarget = puzzle.getAffectsTarget();
    this.solution = puzzle.getSolution();;
    this.value = puzzle.getValue();
    this.description = puzzle.getDescription();
    this.effects = puzzle.getEffect();
    this.target = puzzle.getTarget();
    this.picture = puzzle.getPicture();
  }

  /**
   * Constructor for input parameters.
   */
  public PuzzleData(String name, Boolean active, Boolean affectsTarget, Boolean affectsPlayer, String solution,
                    Integer value, String description, String effects, String target, String picture) {
    this.name = name;
    this.active = active;
    this.affectsTarget = affectsTarget;
    this.affectsPlayer = affectsPlayer;
    this.solution = solution;
    this.value = value;
    this.description = description;
    this.effects = effects;
    this.target = target;
    this.picture = picture;
  }

  /**
   * getter of name of the puzzle.
   * @return name of the puzzle.
   */
  public String getName() {
    return name;
  }

  /**
   * getter of isActive of the puzzle.
   * @return puzzle is active or not.
   */
  public Boolean isActive() {
    return active;
  }

  /**
   * getter of isAffectsTarget of the puzzle.
   * @return whether the puzzle can affect the target.
   */
  public Boolean isAffectsTarget() {
    return affectsTarget;
  }

  /**
   * getter of isAffectsPlayer of the puzzle.
   * @return whether the puzzle can affect the player.
   */
  public Boolean isAffectsPlayer() {
    return affectsPlayer;
  }

  /**
   * getter of the solution of the puzzle.
   * @return solution of the puzzle.
   */
  public String getSolution() {
    return this.solution;
  }

  /**
   * getter of value of the puzzle.
   * @return value of the puzzle.
   */
  public Integer getValue() {
    return value;
  }

  /**
   * getter of description of the puzzle.
   * @return description of the puzzle.
   */
  public String getDescription() {
    return description;
  }

  /**
   * getter of effects of the puzzle.
   * @return effects of the puzzle.
   */
  public String getEffects() {
    return effects;
  }

  /**
   * getter of target of the puzzle.
   * @return target of the puzzle.
   */
  public String getTarget() {
    return target;
  }

  /**
   * getter of picture of the puzzle.
   * @return picture of the puzzle.
   */
  public String getPicture() {
    return picture;
  }
}