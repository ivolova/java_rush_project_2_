import lombok.RequiredArgsConstructor;
import repository.Cell;
import repository.config.util.ConfigUtil;
import service.CellCreator;
import service.LifeCycleService;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
public class GameMap {
    private final CellCreator cellCreator = new CellCreator();

    private final LifeCycleService lifeCycleService = new LifeCycleService();

    private List<Cell> cellList = new ArrayList<>();

    public void init(String pathname) throws Exception {

        System.out.println("Читаем конфигурацию, создаем доску");
        initConfig(pathname);
        initCellList(ConfigUtil.getSizeX(), ConfigUtil.getSizeY());
    }

    public List<Cell> getCellList() {
        return new ArrayList<>(cellList);
      }

    public void start() throws InterruptedException {

        System.out.println("Стартуем");
        lifeCycleService.startLife(getCellList());
    }

    private void initConfig(String pathname) throws IOException {
        ConfigUtil.saveConfig(pathname);
    }

    private void initCellList(int countX, int sizeY) {

        for (int x = 0; x < countX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Cell cell = cellCreator.getCell(x, y);
                cellList.add(cell);
            }
        }
    }


}
