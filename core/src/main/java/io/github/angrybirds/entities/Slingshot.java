package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Slingshot {
    private Texture texture;
    private Vector2 position;

    public Slingshot(Vector2 position) {
        this.texture = new Texture("Slingshot(1)(1).png"); // Replace with your texture path
        this.position = position;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Vector2 getAnchorPoint() {
        // Return the anchor point where birds are positioned for the launch
        return position.cpy().add(15, 100); // Adjust based on texture alignment
    }

    public void dispose() {
        texture.dispose();
    }
}
