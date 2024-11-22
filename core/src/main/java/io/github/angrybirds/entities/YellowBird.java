package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static io.github.angrybirds.GameScreens.Level1.METERS_TO_PIXELS;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;

public class YellowBird extends Bird {
    private Texture texture;
    private int radius;
    private Body body;


    public YellowBird(World world, Vector2 startPosition, int radius) {
        super(world, startPosition, radius);
        this.texture = new Texture("YellowBird(1).png");
        this.radius = radius;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Vector2 scaledPosition = new Vector2(startPosition.x * PIXELS_TO_METERS, startPosition.y * PIXELS_TO_METERS);
        //bodyDef.position.set(new Vector2(startPosition.x+32,startPosition.y+32));
        bodyDef.position.set(scaledPosition);

        body = world.createBody(bodyDef);

        // Create a circular shape for the bird
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        // Define fixture properties
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f; // Adjust as needed
        fixtureDef.restitution = 0f; // Bounciness
        fixtureDef.friction = 0.4f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        // Draw the texture at the position of the body in the physics world
        Vector2 physicsPosition = body.getPosition();
        float x = physicsPosition.x * METERS_TO_PIXELS;
        float y = physicsPosition.y * METERS_TO_PIXELS;
        //float radius = radius; // Add a getter for radius in `Bird` if needed
        batch.draw(texture, x - 32, y - 32);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Vector2 getPosition(){
        return body.getPosition();
    }
    @Override
    public void applyForce(Vector2 force) {
        // Add any specific YellowBird logic, if needed
        body.applyLinearImpulse(force,body.getWorldCenter(),true); // Use the parent's logic
    }

    @Override
    public Body getBody(){
        //System.out.println("Check2");
        return this.body;
    }

    @Override
    public void changeType(){
        body.setType(BodyDef.BodyType.DynamicBody);
    }

    @Override
    public void setPosition(Vector2 newPosition) {
        body.setTransform(new Vector2((newPosition.x+32)*PIXELS_TO_METERS,(newPosition.y+32)*PIXELS_TO_METERS),body.getAngle());
    }
}
