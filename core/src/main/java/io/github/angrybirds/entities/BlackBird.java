package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static io.github.angrybirds.GameScreens.Level1.METERS_TO_PIXELS;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;

public class BlackBird extends Bird {
    private Texture texture;
    private int radius;
    private Body body;


    public BlackBird(World world, Vector2 startPosition, int radius) {
        super(world, startPosition, radius);
        this.texture = new Texture("Bomb(4).png");
        this.radius = radius;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Vector2 scaledPosition = new Vector2((startPosition.x+radius) * PIXELS_TO_METERS, (startPosition.y+radius) * PIXELS_TO_METERS);
        bodyDef.position.set(scaledPosition);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius*PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.8f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.4f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    public void render(SpriteBatch batch){
        Vector2 physicsPosition = body.getPosition();
        float x = physicsPosition.x * METERS_TO_PIXELS;
        float y = physicsPosition.y * METERS_TO_PIXELS;
        batch.draw(texture, x - radius, y - radius);

    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Vector2 getPosition(){
        Vector2 ans = body.getPosition();
        return new Vector2(ans.x*METERS_TO_PIXELS-radius,ans.y*METERS_TO_PIXELS-radius);
    }
    @Override
    public void applyForce(Vector2 force) {
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
        body.setTransform(new Vector2((newPosition.x)*PIXELS_TO_METERS,(newPosition.y)*PIXELS_TO_METERS),body.getAngle());
    }
}
