package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CorporalPig extends Pig{
    private Texture texture;
    private Vector2 position;
    private int hp;

    public CorporalPig(Vector2 startPosition, int health) {
        super(startPosition,health);
        //this.texture = new Texture("CorporalPig(1).png");
        this.position = startPosition;
        this.hp = health;
    }

    public void render(SpriteBatch batch) {
        //batch.draw(texture, position.x, position.y);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {

        }
    }

    public void dispose() {
        texture.dispose();
    }
}
