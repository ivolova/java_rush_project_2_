package repository.entity;

import repository.entity.enums.EntityType;


public class Caterpillar extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Caterpillar(EntityType type, float weigth) {
        super(type, weigth);
    }
}
