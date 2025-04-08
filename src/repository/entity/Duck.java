package repository.entity;

import repository.entity.enums.EntityType;

public class Duck extends Entity{

    public Duck(EntityType type, float weigth) {
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
