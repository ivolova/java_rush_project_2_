package repository.entity;

import repository.entity.enums.EntityType;


public class Fox extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Fox(EntityType type, float weigth) {
        super(type, weigth);
    }
}
