package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static io.github.angrybirds.GameScreens.Level1.METERS_TO_PIXELS;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;

public class Ground {
    private Body body;
    private float width;
    private float height;

    public Ground(World world, Vector2 position, float width, float height) {
        this.width = width;
        this.height = height;

        // Define the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position.x * PIXELS_TO_METERS, position.y * PIXELS_TO_METERS);

        body = world.createBody(bodyDef);

        // Define the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width) * PIXELS_TO_METERS, (height) * PIXELS_TO_METERS);

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f; // Ground should not move
        fixtureDef.friction = 0.4f; // Adjust friction for sliding
        fixtureDef.restitution = 0f; // No bounce

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {

    }

    public void dispose() {
    }
}
