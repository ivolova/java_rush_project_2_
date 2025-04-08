package service;

import repository.config.util.ConfigUtil;
import repository.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EatingService {

    public List<Entity> getEatenList(List<Entity> entityList) {
        List<Entity> eatenEntityList = new ArrayList<>();

        for (Entity entity : entityList) {
            Entity pairEntity = getPairEntityForEating(entity, entityList, eatenEntityList);
            boolean isNeedEaten = needEating(entity, pairEntity);
            if (isNeedEaten) {
                eatenEntityList.add(pairEntity);
            }
        }
        return eatenEntityList;
    }


    private int getProbabilityEating() {
        return new Random().nextInt(100);
    }


    private boolean needEating(Entity who, Entity whom) {
        if (!who.canEat()) {
            return false;
        }
        int randomEatenProbability = getProbabilityEating();
        int requeredEatenProbability = ConfigUtil.getEatingsProbability(who.getType(), whom.getType());

        if (randomEatenProbability <= requeredEatenProbability) {
            return true;
        }
        return false;
    }


    private Entity getPairEntityForEating(Entity who, List<Entity> entityList, List<Entity> eatenEntityList) {
        int indexPairEntity;
        do {
            indexPairEntity = new Random().nextInt(entityList.size() - 1);
        }
        while
        (entityList.get(indexPairEntity).equals(who) || eatenEntityList.contains(entityList.get(indexPairEntity)) || (who.getType() == entityList.get(indexPairEntity).getType()));
        return entityList.get(indexPairEntity);
    }
}
