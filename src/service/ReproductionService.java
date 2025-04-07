package service;

import repository.config.util.ConfigUtil;
import repository.entity.Entity;
import repository.entity.enums.EntityType;

import java.util.*;

public class ReproductionService {

    private final static int COUNT_ENTITY_FOR_REPRODUCTION = 2;

    private final EntityCreator entityCreator = new EntityCreator();
    private final StatisticService statisticService = new StatisticService();

    Map<EntityType, Long> countEntitiesByEntityTypeOnCell;

    public List<Entity> getNewbornEntityList(List<Entity> entityList) {
        List<Entity> newbornList = new ArrayList<>();
         countEntitiesByEntityTypeOnCell = statisticService.getStatisticMapByEntityType(entityList);

        for (Entity entity : entityList) {
            if (needReproduction(entity.getType())) {
                newbornList.add(entityCreator.createEntity(entity.getType()));
                countEntitiesByEntityTypeOnCell.put(entity.getType(), countEntitiesByEntityTypeOnCell.get(entity.getType())+1);
            }
        }
        return newbornList;
    }

    private boolean needReproduction(EntityType entityType) {

        int maxCountEntityByType = ConfigUtil.getMaxCountEntityOnCeil(entityType);
        return ((countEntitiesByEntityTypeOnCell.get(entityType) >= COUNT_ENTITY_FOR_REPRODUCTION) && isTrueProbabilityToCreate() && maxCountEntityByType > countEntitiesByEntityTypeOnCell.get(entityType));
    }

    private boolean isTrueProbabilityToCreate() {
        return new Random().nextBoolean();
    }
}
