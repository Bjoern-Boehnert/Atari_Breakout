package com.bboehnert.atari_breakout.entites;

/**
 * Class for representing the game entities
 */
class GameEntity {
    float x, y;
    final float width, height;

    /**
     * Constructor
     *
     * @param x      of origin X-coordinate
     * @param y      of origin Y-coordinate
     * @param width  of the entity
     * @param height of the entity
     */
    public GameEntity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for the X-Coordinate
     *
     * @return the X-Coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Getter for the Y-Coordinate
     *
     * @return the Y-Coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Getter for the width
     *
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Getter for the height
     *
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Check on collision with another GameEntity.
     *
     * @param other GameEntity to check on collision
     * @return a value about the collision
     */
    public boolean isIntersecting(GameEntity other) {
        return x < other.x + other.width && other.x < width + x
                && y < other.height + other.y && other.y < height + y;
    }

}
