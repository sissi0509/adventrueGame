package model.dataClasses;

import com.google.gson.annotations.SerializedName;

import model.objects.IMonster;

/**
 * create a dataClass for monster.
 */
public class MonsterData {
  private String name;
  private Integer damage;

  @SerializedName("can_attack")
  private Boolean canAttack;

  @SerializedName("affects_target")
  private Boolean affectsTarget;

  @SerializedName("affects_player")
  private Boolean affectsPlayer;

  private String attack;
  private String description;
  private String picture;
  private Boolean active;
  private String effects;
  private String target;
  private String solution;
  private Integer value;

  /**
   * constructor of monsterData.
   * create a monsterData object based on a monster object.
   * @param monster monster object.
   */
  public MonsterData(IMonster monster) {
    this.name = monster.getName();
    this.damage = monster.getDamage();
    this.canAttack = monster.getCanAttack();
    this.affectsPlayer = monster.getAffectsPlayer();
    this.affectsTarget = monster.getAffectsTarget();
    this.attack = monster.getAttack();
    this.description = monster.getDescription();
    this.picture = monster.getPicture();
    this.active = monster.getActive();
    this.effects = monster.getEffect();
    this.target = monster.getTarget();
    this.solution = monster.getSolution();
    this.value = monster.getValue();
  }

  /**
   * Constructor based on input parameters.
   */
  public MonsterData(String name, Integer damage, Boolean canAttack, Boolean affectsTarget,
                     Boolean affectsPlayer, String attack, String description, String picture, Boolean active,
                     String effects, String target, String solution, Integer value) {
    this.name = name;
    this.damage = damage;
    this.canAttack = canAttack;
    this.affectsTarget = affectsTarget;
    this.affectsPlayer = affectsPlayer;
    this.attack = attack;
    this.description = description;
    this.picture = picture;
    this.active = active;
    this.effects = effects;
    this.target = target;
    this.solution = solution;
    this.value = value;
  }

  /**
   * getter of active.
   * @return whether the monster is active.
   */
  public Boolean isActive() {
    return active;
  }

  /**
   * getter of effects of the monster.
   * @return effect of the monster will be shown in the description
   *        when enter a room with active monster.
   */
  public String getEffects() {
    return effects;
  }

  /**
   * getter of name of the monster.
   * @return name of the monster.
   */
  public String getName() {
    return name;
  }

  /**
   * getter of target of the monster.
   * @return target of the monster.
   */
  public String getTarget() {
    return target;
  }

  /**
   * getter of solution of the monster.
   * @return solution of the monster.
   */
  public String getSolution() {
    return solution;
  }

  /**
   * getter of value of the monster.
   * @return value of the monster.
   */
  public Integer getValue() {
    return value;
  }

  /**
   * getter of the damage due to the monster.
   * @return damage points to the player hp.
   */
  public Integer getDamage() {
    return damage;
  }

  /**
   * getter of the canAttack of the monster.
   * @return whether the monster can attack or not.
   */
  public Boolean isCanAttack() {
    return canAttack;
  }

  /**
   * getter of the affectTarget of the monster.
   * @return whether the monster affect target or not.
   */
  public Boolean isAffectsTarget() {
    return affectsTarget;
  }

  /**
   * getter of the affectPlayer of the monster.
   * @return whether the monster can affect the player.
   */
  public Boolean isAffectPlayer() {
    return affectsPlayer;
  }

  /**
   * getter of attack message of the monster.
   * @return attack message when the monster attack player.
   */
  public String getAttack() {
    return attack;
  }

  /**
   * getter of the description of the monster.
   * @return description of the monster.
   */
  public String getDescription() {
    return description;
  }

  /**
   * getter of picture of the monster.
   * @return picture name of the monster.
   */
  public String getPicture() {
    return picture;
  }

}