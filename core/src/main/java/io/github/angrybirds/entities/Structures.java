package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Structures {
    private Vector2 position;
    private int durability;

    public Structures(Vector2 startPosition, int durability) {
        this.position = startPosition;
        this.durability = durability;
    }

    public void render(SpriteBatch batch) {

    }

    public void takeHit() {
        durability--;
        if (durability <= 0) {

        }
    }

    public void dispose() {
    }
}
