package repository.entity;

import repository.entity.enums.EntityType;

public class Bear extends Entity{

    public Bear(EntityType type, float weigth) {
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
