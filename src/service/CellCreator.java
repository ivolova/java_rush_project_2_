package service;

import repository.Cell;
import repository.Coordinate;
import repository.config.util.ConfigUtil;
import repository.entity.Entity;
import repository.entity.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class CellCreator {
    private final EntityCreator entityCreator = new EntityCreator();


    public Cell getCell(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);

        List<Entity> entityList = entityCreator.getListEntitiesByAllType();

        Cell cell = new Cell(coordinate, entityList);
        return cell;
    }


    public List<Cell> initCellList(int countX, int sizeY) {
        List<Cell> cells = new ArrayList<>();
        for (int x = 0; x < countX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Cell cell = getCell(x, y);
                cells.add(cell);
            }
        }
        return cells;
    }
};

