package repository.entity;

import repository.entity.enums.EntityType;


public class Hamster extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Hamster(EntityType type, float weigth) {
        super(type, weigth);
    }
}
