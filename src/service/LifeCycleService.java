package service;

import repository.Cell;
import repository.config.util.ConfigUtil;
import repository.entity.enums.EntityType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LifeCycleService {

    private final static String START = "START";
    private final static String SCHEDULER = "SCHEDULER";
    private final static int INITIAL_DELAY = 0;
    private final static int PERIOD = 5;

    private final StatisticService statisticService = new StatisticService();
    private final CoordinateService coordinateService = new CoordinateService();
    private final EntityCreator entityCreator = new EntityCreator();


    public void startLife(List<Cell> cellList) throws InterruptedException {

        ScheduledExecutorService growPlantsScheduled = getGrowPlantsScheduled(cellList);

        ExecutorService lifeCycleExecutorService = getLifeCycleExecutorService(cellList, ConfigUtil.getSizeX());

        lifeCycleExecutorService.shutdown();

        while (!lifeCycleExecutorService.isTerminated()){
            lifeCycleExecutorService.awaitTermination(5, TimeUnit.SECONDS);
        };
        growPlantsScheduled.shutdown();
    }

    private ScheduledExecutorService getGrowPlantsScheduled(List<Cell> cellList) {

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            cellList.forEach(cell -> cell.addEntity(entityCreator.createEntity(EntityType.PLANT)));
            statisticService.getVerySmallStatisticByType(cellList, LocalTime.now(), SCHEDULER);

        }, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);

        return scheduledExecutorService;
    }

    private ExecutorService getLifeCycleExecutorService(List<Cell> cellList, int size) {
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<List<Cell>> threadsCellList = new ArrayList<>();

        for (int x = 0; x < size; x++) {
            threadsCellList.add(coordinateService.getCellsWithCoordinate(x, cellList));
        }

        for (List<Cell> cells : threadsCellList) {
            executorService.execute(() -> {
                cells.forEach(Cell::runEating);
                cells.forEach(Cell::runReproduction);
                cells.forEach(cell -> cell.runEntitiesMoving(cellList));
            });
        }
        return executorService;
    }
}


