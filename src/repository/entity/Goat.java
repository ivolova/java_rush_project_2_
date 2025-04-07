package repository.entity;

import repository.entity.enums.EntityType;


public class Goat extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Goat(EntityType type, float weigth) {
        super(type, weigth);
    }
}
