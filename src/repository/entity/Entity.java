package repository.entity;

import repository.entity.enums.EntityType;


public abstract class Entity {

    private final EntityType type;

    private final float weigth;

    public Entity(EntityType type, float weigth) {
        this.type = type;
        this.weigth = weigth;
    }

    public abstract boolean canMove();

    public abstract boolean canEat();

    public EntityType getType() {
        return type;
    }

    public float getWeigth() {
        return weigth;
    }
}
