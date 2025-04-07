package repository.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import repository.entity.enums.EntityType;

import java.io.Serializable;
import java.util.Map;


@Getter
@Setter
@ToString
public class InitConfig implements Serializable {

    @JsonProperty
    private Map<EntityType, Integer> initMaxCountEntityOnCeil;

    @JsonProperty
    private Map<EntityType, Integer> weight;

    @JsonProperty
    private Map<EntityType, Integer> countSteps;

    @JsonProperty
    private  Map<EntityType, Map<EntityType, Integer>> eatingsProbability;

    @JsonProperty
    private int sizeX;

    @JsonProperty
    private int sizeY;

    public Map<EntityType, Integer> getInitMaxCountEntityOnCeil() {
        return initMaxCountEntityOnCeil;
    }

    public Map<EntityType, Integer> getWeight() {
        return weight;
    }

    public Map<EntityType, Integer> getCountSteps() {
        return countSteps;
    }

    public Map<EntityType, Map<EntityType, Integer>> getEatingsProbability() {
        return eatingsProbability;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public InitConfig(Map<EntityType, Integer> initMaxCountEntityOnCeil, Map<EntityType, Integer> weight, Map<EntityType, Integer> countSteps, Map<EntityType, Map<EntityType, Integer>> eatingsProbability, int sizeX, int sizeY) {
        this.initMaxCountEntityOnCeil = initMaxCountEntityOnCeil;
        this.weight = weight;
        this.countSteps = countSteps;
        this.eatingsProbability = eatingsProbability;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public InitConfig() {
    }
}

