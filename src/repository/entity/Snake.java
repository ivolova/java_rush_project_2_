package repository.entity;

import repository.entity.enums.EntityType;


public class Snake extends Entity {
    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    public Snake(EntityType type, float weigth) {
        super(type, weigth);
    }
}
