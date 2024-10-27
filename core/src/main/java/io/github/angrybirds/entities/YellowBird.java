package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class YellowBird extends Bird{
    private Texture texture;
    private Vector2 position;
    private float speed;  // Basic speed property

    public YellowBird(Vector2 startPosition) {
        super(startPosition);
        //this.texture = new Texture("YellowBird(1).png");
        this.position = startPosition;
        this.speed = 0; // Speed can be updated on launch
    }

    public void render(SpriteBatch batch) {
        //batch.draw(texture, position.x, position.y);
    }

    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
    }

    public void updatePosition(float deltaTime) {
        // Placeholder for moving the bird based on physics
        position.add(speed * deltaTime, speed * deltaTime);
    }

    public void dispose() {
        texture.dispose();
    }
}
