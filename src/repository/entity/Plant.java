package repository.entity;

import repository.entity.enums.EntityType;

public class Plant extends Entity {

    public Plant(EntityType type, float weigth) {
        super(type, weigth);
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canEat() {
        return false;
    }
}
