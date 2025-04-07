package repository.entity;

import repository.entity.enums.EntityType;

public class Cow extends Entity {

    public Cow(EntityType type, float weigth) {
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
