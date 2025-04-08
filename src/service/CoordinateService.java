package service;

import repository.Cell;
import repository.Coordinate;
import repository.config.util.ConfigUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoordinateService {

    public Optional<Cell> getCellByCoordinate(Coordinate coordinate, List<Cell> cellList) {
        Optional<Cell> targetCell = Optional.ofNullable(cellList.stream().filter(cell -> cell.getCoordinate().equals(coordinate)).findFirst().orElse(null));
        return targetCell;
    }

    public List<Cell> getCellsWithCoordinate(int x, List<Cell> cellList) {
    List<Cell> cellListByX = new ArrayList<>();
        for (int y = 0; y < ConfigUtil.getSizeY(); y++) {
            Coordinate coordinate = new Coordinate(x, y);
            Optional<Cell> optionalCell = getCellByCoordinate(coordinate, cellList);
            optionalCell.ifPresent(cellListByX::add);
        }
        return cellListByX;
    }
}
