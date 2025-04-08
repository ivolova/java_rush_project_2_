package service;

import repository.Cell;
import repository.config.util.ConfigUtil;
import repository.entity.Entity;
import repository.entity.enums.EntityType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticService {

    Map<EntityType, Long> getStatisticMapByEntityType(List<Entity> entityList) {
        EntityType[] values = EntityType.values();
        Map<EntityType, Long> map = new HashMap<>();
        for (EntityType entityType : values) {
            long count = entityList.stream()
                    .filter(e -> e.getType().equals(entityType))
                    .count();
            map.put(entityType, count);
        }
        return map;
    }

    public void getStatisticByType(List<Cell> cellList, LocalTime localTime, String type) {
        System.out.println("========================================================");
        for (Cell cell : cellList) {
            Map<EntityType, Long> map = getStatisticMapByEntityType(cell.getEntityList());
            System.out.println(localTime.toString() + "  " + type + "  " + cell.toString());
            map.entrySet().forEach(e -> System.out.println("Тип: " + e.getKey().toString() + " Кол-во: " + e.getValue().toString() + " Максимально возможное " + ConfigUtil.getMaxCountEntityOnCeil(e.getKey())));
        }
    }

    public void getVerySmallStatisticByType(List<Cell> cellList, LocalTime localTime, String type) {
        System.out.println("========================================================");
        Long countEntity = cellList.stream().flatMap(cell -> cell.getEntityList().stream()).count();
        System.out.println(localTime.toString() +  " " + type + " Кол-во " + countEntity);
    }
}


