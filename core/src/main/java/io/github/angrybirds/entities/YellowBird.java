package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

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
        bodyDef.position.set(new Vector2(startPosition.x+32,startPosition.y+32));

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

    @Override
    public void render(SpriteBatch batch) {
        // Draw the texture at the position of the body in the physics world
        Vector2 physicsPosition = body.getPosition();
        //float radius = radius; // Add a getter for radius in `Bird` if needed
        batch.draw(texture, physicsPosition.x - radius, physicsPosition.y - radius);
    }

    @Override
    public void dispose() {
        texture.dispose();
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
    public void setPosition(Vector2 newPosition) {
        body.setTransform(newPosition,body.getAngle());
    }
}
