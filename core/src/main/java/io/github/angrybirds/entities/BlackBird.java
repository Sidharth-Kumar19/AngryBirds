package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BlackBird extends Bird{
    private Texture texture;
    private Vector2 position;
    private float speed;  // Basic speed property
    private Body body;

    public BlackBird(World world, Vector2 startPosition, int radius) {
        super(world,startPosition,radius);
        this.texture = new Texture("Bomb(4).png");
        this.position = startPosition;
        this.speed = 0; // Speed can be updated on launch
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startPosition);

        body = world.createBody(bodyDef);

        // Create a circular shape for the bird
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        // Define fixture properties
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f; // Adjust as needed
        fixtureDef.restitution = 0; // Bounciness
        fixtureDef.friction = 0.4f;

        body.createFixture(fixtureDef);
        shape.dispose();

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
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
    @Override
    public void applyForce(Vector2 force) {
        // Customize behavior for BlackBird, if needed
        super.applyForce(force); // Call parent implementation
    }
}
