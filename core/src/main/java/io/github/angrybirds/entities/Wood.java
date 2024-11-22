package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Wood extends Structures{
    //private Texture texture;
    private Vector2 position;
    private int durability;

    public Wood(Vector2 startPosition, int health) {
        super(startPosition,health);
        //this.texture = new Texture("");
        this.position = startPosition;
        this.durability = health;
    }

    public void render(SpriteBatch batch) {
        //batch.draw(texture, position.x, position.y);
    }

    public void takeHit() {
        durability--;
        if (durability <= 0) {

        }
    }

    public void dispose() {
        //texture.dispose();
    }
}
