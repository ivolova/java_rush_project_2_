import lombok.RequiredArgsConstructor;
import repository.Cell;
import repository.config.util.ConfigUtil;
import service.CellCreator;
import service.ConfigService;
import service.LifeCycleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class GameMap {
    private final CellCreator cellCreator = new CellCreator();
    private final ConfigService configService = new ConfigService();
    private final LifeCycleService lifeCycleService = new LifeCycleService();
    private List<Cell> cellList = new ArrayList<>();


    public List<Cell> getCellList() {
        return new ArrayList<>(cellList);
    }


    public void init(String pathname) throws Exception {
        System.out.println("Читаем конфигурацию, создаем доску");
        configService.initConfig(pathname);
        addCellList(cellCreator.initCellList(ConfigUtil.getSizeX(), ConfigUtil.getSizeY()));
     }


    public void start() throws InterruptedException {
        System.out.println("Стартуем");
        lifeCycleService.startLife(getCellList());
    }


    private void addCellList(List<Cell> addCells) {
        cellList.addAll(addCells);
    }
}
