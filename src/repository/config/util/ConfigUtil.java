package repository.config.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import repository.entity.enums.EntityType;
import repository.config.InitConfig;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class ConfigUtil {

    private static Map<EntityType, Integer> initMaxCountEntityOnCeil;
    private static Map<EntityType, Integer> weight;
    private static Map<EntityType, Integer> countSteps;
    private static Map<EntityType, Map<EntityType, Integer>> eatingsProbability;

    private static int sizeX;
    private static int sizeY;

    public static void saveConfig(String pathname) throws IOException {
        ObjectMapper readMapper = new ObjectMapper();
        InitConfig initConfig = readMapper.readValue(new File(pathname), InitConfig.class);
        save(initConfig);
    }

    private static void save(InitConfig initConfig) {
        initMaxCountEntityOnCeil = initConfig.getInitMaxCountEntityOnCeil();
        weight = initConfig.getWeight();
        countSteps = initConfig.getCountSteps();
        eatingsProbability = initConfig.getEatingsProbability();
        sizeX = initConfig.getSizeX();
        sizeY = initConfig.getSizeY();

    }

    public static float getWeight(EntityType entityType) {
        return weight.entrySet().stream()
                .filter(entry -> entry.getKey().equals(entityType))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0);
    }

    public static int getMaxCountEntityOnCeil(EntityType entityType) {
        return initMaxCountEntityOnCeil.entrySet().stream()
                .filter(entry -> entry.getKey().equals(entityType))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0);
    }

    public static int getCountSteps(EntityType entityType) {
        return countSteps.entrySet().stream()
                .filter(entry -> entry.getKey().equals(entityType))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0);
    }

    public static int getEatingsProbability(EntityType who, EntityType whom) {
        return Optional.ofNullable(eatingsProbability.get(who))
                .map(innerMap -> innerMap.entrySet().stream()
                        .filter(entry -> entry.getKey().equals(whom))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .orElse(0))
                .orElse(0);
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

}
