package repository.entity;

import repository.entity.enums.EntityType;


public class Deer extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Deer(EntityType type, float weigth) {
        super(type, weigth);
    }
}
