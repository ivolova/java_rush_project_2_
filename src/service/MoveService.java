package service;

import repository.Cell;
import repository.Coordinate;
import repository.Direction;
import repository.config.util.ConfigUtil;
import repository.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class MoveService {

    private final CoordinateService coordinateService = new CoordinateService();

    public Map<Entity, Cell> getEntitiesMovingMap(Cell fromCell, List<Cell> targetCellList) {

        Map<Entity, Cell> targetCellMap = new HashMap<>();
        List<Entity> entityList = fromCell.getEntityList();

        for (Entity entity : entityList) {
            int countStep = ConfigUtil.getCountSteps(entity.getType());

            if (countStep > 0 && entity.canMove()) {
                Optional<Cell> targetCell = getTargetCell(fromCell, countStep, targetCellList);

                if (targetCell.get() != null) {
                    targetCellMap.put(entity, targetCell.get());
                }
            }
        }
        return targetCellMap;
    }


    private Optional<Cell> getTargetCell(Cell fromCeil, int countStep, List<Cell> cellList) {
        return getRandomCell(fromCeil, countStep, cellList);
    }



    private Optional<Cell> getRandomCell(Cell from, int countStep, List<Cell> cellList) {

        List<Coordinate> coordinateList = getCoordinates(from, countStep);
        List<Cell> existCellList = getExistTargetCells(coordinateList, cellList);

        if (existCellList.isEmpty()){
            return Optional.empty();
        }
        Collections.shuffle(existCellList);

        return Optional.ofNullable(existCellList.get(0));
}


    private List<Coordinate> getCoordinates(Cell from, int countStep) {

        List<Coordinate> targetCoordinate = new ArrayList<>();

        int x = from.getCoordinate().getX();
        int y = from.getCoordinate().getY();

        for (Direction value : Direction.values()) {
            switch (value) {
                case LEFT  -> targetCoordinate.add(new Coordinate(x - countStep, y));
                case RIGHT -> targetCoordinate.add(new Coordinate(x + countStep, y));
                case UP    -> targetCoordinate.add(new Coordinate(x, y - countStep));
                case DOWN  -> targetCoordinate.add(new Coordinate(x, y + countStep));
            }
        }
        return targetCoordinate;
    }



    private List<Cell> getExistTargetCells(List<Coordinate> coordinateList, List<Cell> cellList) {

        List<Cell> targetCellList = new ArrayList<>();
        for (Coordinate coordinate : coordinateList) {
            Optional<Cell> optionalCell = coordinateService.getCellByCoordinate(coordinate, cellList);
            optionalCell.ifPresent(targetCellList::add);
        }
        return targetCellList;
    }
}


