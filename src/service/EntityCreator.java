package service;

import repository.config.util.ConfigUtil;
import repository.entity.*;
import lombok.NoArgsConstructor;
import repository.entity.enums.EntityType;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;


@NoArgsConstructor
public class EntityCreator {
    private final Map<EntityType, Supplier<Entity>> entity = Map.ofEntries(
            Map.entry(EntityType.WOLF, () -> (new Wolf(EntityType.WOLF, ConfigUtil.getWeight(EntityType.WOLF)))),
            Map.entry(EntityType.SNAKE, () -> (new Wolf(EntityType.SNAKE, ConfigUtil.getWeight(EntityType.SNAKE)))),
            Map.entry(EntityType.FOX, () -> (new Wolf(EntityType.FOX, ConfigUtil.getWeight(EntityType.FOX)))),
            Map.entry(EntityType.BEAR, () -> (new Bear(EntityType.BEAR, ConfigUtil.getWeight(EntityType.BEAR)))),
            Map.entry(EntityType.EAGLE, () -> (new Wolf(EntityType.EAGLE, ConfigUtil.getWeight(EntityType.EAGLE)))),
            Map.entry(EntityType.HORSE, () -> (new Wolf(EntityType.HORSE, ConfigUtil.getWeight(EntityType.HORSE)))),
            Map.entry(EntityType.DEER, () -> (new Wolf(EntityType.DEER, ConfigUtil.getWeight(EntityType.DEER)))),
            Map.entry(EntityType.HARE, () -> (new Wolf(EntityType.HARE, ConfigUtil.getWeight(EntityType.HARE)))),
            Map.entry(EntityType.HAMSTER, () -> (new Wolf(EntityType.HAMSTER, ConfigUtil.getWeight(EntityType.HAMSTER)))),
            Map.entry(EntityType.GOAT, () -> (new Wolf(EntityType.GOAT, ConfigUtil.getWeight(EntityType.GOAT)))),
            Map.entry(EntityType.SHEEP, () -> (new Wolf(EntityType.SHEEP, ConfigUtil.getWeight(EntityType.SHEEP)))),
            Map.entry(EntityType.KANGAROO, () -> (new Wolf(EntityType.KANGAROO, ConfigUtil.getWeight(EntityType.KANGAROO)))),
            Map.entry(EntityType.COW, () -> (new Wolf(EntityType.COW, ConfigUtil.getWeight(EntityType.COW)))),
            Map.entry(EntityType.DUCK, () -> (new Duck(EntityType.DUCK, ConfigUtil.getWeight(EntityType.DUCK)))),
            Map.entry(EntityType.CATERPILLAR, () -> (new Wolf(EntityType.CATERPILLAR, ConfigUtil.getWeight(EntityType.CATERPILLAR)))),
            Map.entry(EntityType.PLANT, () -> (new Plant(EntityType.PLANT, ConfigUtil.getWeight(EntityType.PLANT))))
    );


    private List<Entity> createEntities(EntityType entityType, Integer count) {

        List<Entity> entityList = new ArrayList<>();
        //System.out.println("тут у нас фабрика сразу нескольких объектов");
        for (int i = 0; i < count; i++) {
            Entity entity = createEntity(entityType);
            entityList.add(entity);
        }
        return entityList;
    }


    public Entity createEntity(EntityType entityType) {

        Supplier<Entity> supplier = entity.get(entityType);
        return supplier.get();
    }

    public List<Entity> getListEntitiesByAllType() {
        List<Entity> entitiesByAllType = new CopyOnWriteArrayList<>();

        for (EntityType entityType : EntityType.values()) {
            int countEventType = (int) (Math.random() * ConfigUtil.getMaxCountEntityOnCeil(entityType)) + 1;

            List<Entity> entityList = createEntities(entityType, countEventType);
            entitiesByAllType.addAll(entityList);
        }
        Collections.shuffle(entitiesByAllType);
        return entitiesByAllType;
    }


}
