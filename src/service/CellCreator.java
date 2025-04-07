package service;

import repository.Cell;
import repository.Coordinate;
import repository.config.util.ConfigUtil;
import repository.entity.Entity;
import repository.entity.enums.EntityType;

import java.util.List;

public class CellCreator {
    private final EntityCreator entityCreator = new EntityCreator();

    public Cell getCell(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);

        List<Entity> entityList = entityCreator.getListEntitiesByAllType();

        Cell cell = new Cell(coordinate, entityList);
 //       System.out.println("Начало: Клетка x =" + x + " y =" + y );

//        getStatistic(entityList);

        return cell;
    }

//    public void getStatistic(List<Entity> entityList) {
//        EntityType[] values = EntityType.values();
//
//        for (EntityType entityType : values) {
//            long count = entityList.stream()
//                    .filter(e -> e.getType().equals(entityType)) // Фильтруем по типу
//                    .count();            // Преобразуем в числовые значения
//
//            System.out.println(" тип = " + entityType.toString() + " кол-во = " + count+ " максимальное кол-во = "+ ConfigUtil.getMaxCountEntityOnCeil(entityType));
//        }
//    }

};

