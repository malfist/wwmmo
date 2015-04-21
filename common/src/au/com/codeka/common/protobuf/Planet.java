// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ./messages.proto
package au.com.codeka.common.protobuf;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoEnum;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.ENUM;
import static com.squareup.wire.Message.Datatype.INT32;

/**
 *
 * Represents a planet in the star system. A planet orbits a \c Star which in turn belongs
 * to a \c Sector.
 */
public final class Planet extends Message {
  private static final long serialVersionUID = 0L;

  public static final Integer DEFAULT_INDEX = 0;
  public static final PLANET_TYPE DEFAULT_PLANET_TYPE = PLANET_TYPE.GASGIANT;
  public static final Integer DEFAULT_SIZE = 0;
  public static final Integer DEFAULT_POPULATION_CONGENIALITY = 0;
  public static final Integer DEFAULT_FARMING_CONGENIALITY = 0;
  public static final Integer DEFAULT_MINING_CONGENIALITY = 0;

  /**
   * The "index" of the planet represents how far it is from the sun. Planets are always
   * in monotonically increasing order of distance (0, 1, 2, 3, etc)
   */
  @ProtoField(tag = 2, type = INT32)
  public final Integer index;

  @ProtoField(tag = 3, type = ENUM)
  public final PLANET_TYPE planet_type;

  /**
   * The size of the planet. Certain planet_type's are predisposed to be certain sizes
   * (e.g. GASGIANT is predisposed to being big, etc) but planets will still vary in exact size.
   */
  @ProtoField(tag = 4, type = INT32)
  public final Integer size;

  /**
   * How "congenial" is this planet to supporting population
   */
  @ProtoField(tag = 5, type = INT32)
  public final Integer population_congeniality;

  /**
   * How "congenial" is this planet to supporting farming
   */
  @ProtoField(tag = 6, type = INT32)
  public final Integer farming_congeniality;

  /**
   * How "congenial" is this planet to supporting mining
   */
  @ProtoField(tag = 7, type = INT32)
  public final Integer mining_congeniality;

  public Planet(Integer index, PLANET_TYPE planet_type, Integer size, Integer population_congeniality, Integer farming_congeniality, Integer mining_congeniality) {
    this.index = index;
    this.planet_type = planet_type;
    this.size = size;
    this.population_congeniality = population_congeniality;
    this.farming_congeniality = farming_congeniality;
    this.mining_congeniality = mining_congeniality;
  }

  private Planet(Builder builder) {
    this(builder.index, builder.planet_type, builder.size, builder.population_congeniality, builder.farming_congeniality, builder.mining_congeniality);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Planet)) return false;
    Planet o = (Planet) other;
    return equals(index, o.index)
        && equals(planet_type, o.planet_type)
        && equals(size, o.size)
        && equals(population_congeniality, o.population_congeniality)
        && equals(farming_congeniality, o.farming_congeniality)
        && equals(mining_congeniality, o.mining_congeniality);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = index != null ? index.hashCode() : 0;
      result = result * 37 + (planet_type != null ? planet_type.hashCode() : 0);
      result = result * 37 + (size != null ? size.hashCode() : 0);
      result = result * 37 + (population_congeniality != null ? population_congeniality.hashCode() : 0);
      result = result * 37 + (farming_congeniality != null ? farming_congeniality.hashCode() : 0);
      result = result * 37 + (mining_congeniality != null ? mining_congeniality.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<Planet> {

    public Integer index;
    public PLANET_TYPE planet_type;
    public Integer size;
    public Integer population_congeniality;
    public Integer farming_congeniality;
    public Integer mining_congeniality;

    public Builder() {
    }

    public Builder(Planet message) {
      super(message);
      if (message == null) return;
      this.index = message.index;
      this.planet_type = message.planet_type;
      this.size = message.size;
      this.population_congeniality = message.population_congeniality;
      this.farming_congeniality = message.farming_congeniality;
      this.mining_congeniality = message.mining_congeniality;
    }

    /**
     * The "index" of the planet represents how far it is from the sun. Planets are always
     * in monotonically increasing order of distance (0, 1, 2, 3, etc)
     */
    public Builder index(Integer index) {
      this.index = index;
      return this;
    }

    public Builder planet_type(PLANET_TYPE planet_type) {
      this.planet_type = planet_type;
      return this;
    }

    /**
     * The size of the planet. Certain planet_type's are predisposed to be certain sizes
     * (e.g. GASGIANT is predisposed to being big, etc) but planets will still vary in exact size.
     */
    public Builder size(Integer size) {
      this.size = size;
      return this;
    }

    /**
     * How "congenial" is this planet to supporting population
     */
    public Builder population_congeniality(Integer population_congeniality) {
      this.population_congeniality = population_congeniality;
      return this;
    }

    /**
     * How "congenial" is this planet to supporting farming
     */
    public Builder farming_congeniality(Integer farming_congeniality) {
      this.farming_congeniality = farming_congeniality;
      return this;
    }

    /**
     * How "congenial" is this planet to supporting mining
     */
    public Builder mining_congeniality(Integer mining_congeniality) {
      this.mining_congeniality = mining_congeniality;
      return this;
    }

    @Override
    public Planet build() {
      return new Planet(this);
    }
  }

  public enum PLANET_TYPE
      implements ProtoEnum {
    GASGIANT(1),
    RADIATED(2),
    INFERNO(3),
    ASTEROIDS(4),
    WATER(5),
    TOXIC(6),
    DESERT(7),
    SWAMP(8),
    TERRAN(9);

    private final int value;

    private PLANET_TYPE(int value) {
      this.value = value;
    }

    @Override
    public int getValue() {
      return value;
    }
  }
}