package repository.entity;

import repository.entity.enums.EntityType;

public class Wolf extends Entity {

    public Wolf(EntityType type, float weigth) {
        super(type, weigth);
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return true;
    }
}
