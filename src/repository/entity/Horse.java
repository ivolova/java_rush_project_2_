package repository.entity;

import repository.entity.enums.EntityType;


public class Horse extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Horse(EntityType type, float weigth) {
        super(type, weigth);
    }
}
