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
        //рандомно получили индекс  животного, которое хотим съесть
        int indexPairEntity;
        do {
            indexPairEntity = new Random().nextInt(entityList.size() - 1);
        }
        while
                (entityList.get(indexPairEntity).equals(who) || eatenEntityList.contains(entityList.get(indexPairEntity)) || ( who.getType() == entityList.get(indexPairEntity).getType()));
        return entityList.get(indexPairEntity);
    }

    private int getProbabilityEating() {
        return new Random().nextInt(100);
    }

    private boolean needEating(int indexWho, int indexWhos, List<Entity> entityList) {
        if (!entityList.get(indexWho).canEat()) {
            return false;
        }
        int randomEatenProbability = getProbabilityEating(indexWho, indexWhos);
        int requeredEatenProbability = ConfigUtil.getEatingsProbability(entityList.get(indexWho).getType(), entityList.get(indexWhos).getType());

        if (randomEatenProbability >= requeredEatenProbability) {
            return true;
        }
        return false;
    }

    private int getIndexPairEntityForEating(int indexWho, List<Entity> entityList, List<Integer> eatenEntityIndexList) {
        //рандомно получили индекс  животного, которое хотим съесть
        int indexPairEntity;
        do {
            indexPairEntity = new Random().nextInt(entityList.size() - 1);
        }
        while (
                (indexPairEntity == indexWho) || eatenEntityIndexList.contains(indexPairEntity) || (entityList.get(indexWho).getType() == entityList.get(indexPairEntity).getType()));
        return indexPairEntity;
    }

    private int getProbabilityEating(int indexEntity, int indexPairEntity) {
        return new Random().nextInt(100);
    }

}
