package repository;

import lombok.Getter;
import repository.config.util.ConfigUtil;
import repository.entity.Entity;
import service.EatingService;
import service.MoveService;
import service.ReproductionService;
import service.StatisticService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Cell {

    private final static int MAX_ATTEMPTS = 10;
    private final static long TIMEOUT = 1000;

    private final Coordinate coordinate;
    private final ReentrantLock lock;

    private List<Entity> entityList;

    private final StatisticService statisticService = new StatisticService();

    private final ReproductionService reproductionService = new ReproductionService();

    private final EatingService eatingService = new EatingService();

    private final MoveService moveService = new MoveService();

    public ReentrantLock getLock() {
        return lock;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Cell(Coordinate coordinate, List<Entity> entityList) {
        this.coordinate = coordinate;
        this.entityList = entityList;
        this.lock = new ReentrantLock();
    }

    public List<Entity> getEntityList() {
        return new CopyOnWriteArrayList<>(entityList);
    }

    @Override
    public String toString() {
        return "Клетка " + coordinate.getX() + " x " + coordinate.getY();
    }

    public synchronized void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public synchronized void removeEntity(Entity entity) {
        entityList.remove(entity);
    }


    public void runReproduction() {
        List<Entity> newbornList = reproductionService.getNewbornEntityList(entityList);
        entityList.addAll(newbornList);
    }


    public void runEating() {
        List<Entity> eatenList = eatingService.getEatenList(entityList);
        entityList.removeAll(eatenList);
    }


    public void runEntitiesMoving(List<Cell> targetCellList) {

        Map<Entity, Cell> movingEntityMap = moveService.getEntitiesMovingMap(this, targetCellList);

        for (Map.Entry<Entity, Cell> entityCellEntry : movingEntityMap.entrySet()) {

            Cell targetCell = entityCellEntry.getValue();
            int countMaxEntityTypeOnCell = ConfigUtil.getMaxCountEntityOnCeil(entityCellEntry.getKey().getType());

            for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
                try {
                    if (targetCell.getLock().tryLock(TIMEOUT, TimeUnit.MILLISECONDS)) {

                        long countEntityTypeOnCeil = targetCell.getEntityList().stream().filter(e -> e.getType().equals(entityCellEntry.getKey().getType())).count();
                        if (countEntityTypeOnCeil < countMaxEntityTypeOnCell) {
                            entityCellEntry.getValue().addEntity(entityCellEntry.getKey());
                            this.removeEntity(entityCellEntry.getKey());
                        }
                        targetCell.getLock().unlock();
                        break;
                    } else {
                        System.out.println("Не удалось залочить " + targetCell.toString() + "Попытка = " + attempt);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

