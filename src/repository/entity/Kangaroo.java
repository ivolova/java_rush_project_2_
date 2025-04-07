package repository.entity;

import repository.entity.enums.EntityType;


public class Kangaroo extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Kangaroo(EntityType type, float weigth) {
        super(type, weigth);
    }
}
