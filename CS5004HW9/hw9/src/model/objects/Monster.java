package model.objects;

import java.util.Objects;
import java.util.stream.Stream;

import model.dataClasses.MonsterData;

/**
 * MonsterImpl class that represents a Monster. Monster
 * has name, affectsTarget, affectsPlayer, active, canAttack,
 * effect, damage, target, solution, value, description, attack
 * and picture.
 */
public class Monster implements IMonster {
  private String name;
  private Boolean affectsTarget;
  private Boolean affectsPlayer;
  private Boolean active;
  private Boolean canAttack;
  private String effect;
  private Integer damage;
  private String target;
  private String solution;
  private Integer value;
  private String description;
  private String attack;
  private String picture;

  /**
   * Constructor of a MonsterImpl class. MonsterImpl gets
   * data of a monster (MonsterData data).
   * @param data data of a Moster.
   */
  public Monster(MonsterData data) {
    this.name = data.getName();
    this.affectsTarget = data.isAffectsTarget();
    this.affectsPlayer = data.isAffectPlayer();
    this.active = data.isActive();
    this.canAttack = data.isCanAttack();
    this.effect = data.getEffects();
    this.damage = data.getDamage();
    this.target = data.getTarget();
    this.solution = data.getSolution();
    this.value = data.getValue();
    this.description = data.getDescription();
    this.attack = data.getAttack();
    this.picture = data.getPicture();
  }

  /**
   * Method that create monster if the data is valid data.
   * @param data MonsterData from Json
   * @return Monster object if data is valid, otherwise null.
   */
  public static Monster createIfValid(MonsterData data) {
    if (Stream.of(data.getName(), data.isAffectPlayer(), data.isAffectsTarget(), data.isActive(),
                    data.isCanAttack(), data.getEffects(), data.getDamage(), data.getTarget(),
                    data.getSolution(), data.getValue(), data.getDescription(), data.getAttack())
            .anyMatch(Objects::isNull)) {
      return null;
    }

    if (data.getName().isBlank() || data.getEffects().isBlank() || data.getDamage() >= 0
            || data.getTarget().isBlank() || data.getSolution().isBlank() || data.getValue() <= 0
            || data.getDescription().isBlank()) {
      return null;
    }

    return new Monster(data);
  }

  /**
   * Gets the name of a monster.
   * @return name of a monster.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets rather a monster affects target or not.
   * @return value represents a monster can affects target or not.
   */
  @Override
  public boolean getAffectsTarget() {
    return this.affectsTarget;
  }

  /**
   * Gets rather a monster can affect player or not.
   * @return value represents a monster can affects player or not.
   */
  @Override
  public boolean getAffectsPlayer() {
    return this.affectsPlayer;
  }

  /**
   * Gets rather a monster is active or not.
   * @return value represents a monster is active or not.
   */
  @Override
  public boolean getActive() {
    return this.active;
  }

  /**
   * Gets rather monster can attack a player or not.
   * @return value represents a monster can attack a player or not.
   */
  @Override
  public boolean getCanAttack() {
    return this.canAttack;
  }

  /**
   * Gets an effect message of a monster.
   * @return effect message of a monster.
   */
  @Override
  public String getEffect() {
    return this.effect;
  }

  /**
   * Gets how much monster can damage a player.
   * @return integer value that a monster can damage a player.
   */
  @Override
  public Integer getDamage() {
    return this.damage;
  }

  /**
   * Gets the target (where a monster is located) of a monster.
   * @return location where a monster is located (target).
   */
  @Override
  public String getTarget() {
    return this.target;
  }

  /**
   * Gets the solution to defeat a monster.
   * @return solution to defeat a monster.
   */
  @Override
  public String getSolution() {
    return this.solution;
  }

  /**
   * Gets the value of a monster. This will be a value that player
   * will earn after defeating a monster.
   * @return value of a monster.
   */
  @Override
  public Integer getValue() {
    return this.value;
  }

  /**
   * Gets the description of a monster.
   * @return description of a monster.
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets description which explains how a monster attack a player.
   * @return description of a monster's attack.
   */
  @Override
  public String getAttack() {
    return this.attack;
  }

  /**
   * Set the active state of a monster. If monster is defeated,
   */
  @Override
  public void setActive(Boolean state) {
    this.active = state;
  }

  /**
   * Set the canAttack state of a monster. If monster is defeated,
   * monster can no longer attack a player, therefore, set
   * canAttack to false.
   */
  @Override
  public void setCanAttack(Boolean state) {
    this.canAttack = state;
  }

  @Override
  public String getPicture() {
    return picture;
  }
}