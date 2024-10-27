package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pig {
    private Vector2 position;
    private int hp;

    public Pig(Vector2 startPosition, int hp) {
        this.position = startPosition;
        this.hp = hp;
    }

    public void render(SpriteBatch batch) {

    }

    public void dispose() {

    }
}
