package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static io.github.angrybirds.GameScreens.Level1.METERS_TO_PIXELS;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;
import static java.lang.Math.sqrt;

public class Structures{
    private int durability;
    private TextureRegion texture;
    private int radius;
    private Body body;
    private World world;
    private int type;

    public Structures(World world, Vector2 startPosition, int health, int radius, TextureRegion texture, int type){
        this.world = world;
        this.durability = health;
        this.radius = radius;
        this.texture = texture;
        this.type = type;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Vector2 scaledPosition = new Vector2((startPosition.x+radius) * PIXELS_TO_METERS, (startPosition.y+radius) * PIXELS_TO_METERS);
        bodyDef.position.set(scaledPosition);

        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(radius * PIXELS_TO_METERS, radius * PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 4f;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        shape.dispose();
    }

    public void render(SpriteBatch batch){
        Vector2 physicsPosition = body.getPosition();
        float x = physicsPosition.x * METERS_TO_PIXELS;
        float y = physicsPosition.y * METERS_TO_PIXELS;
        batch.draw(texture, x-radius, y-radius);
    }

    public void takeHit(int damage){
        durability-=damage;
    }

    public boolean isStanding(){
        return this.durability>0;
    }

    public void dispose(){
        world.destroyBody(body);
    }

    public double getLinearVelocity(){
        Vector2 vel = body.getLinearVelocity();
        return sqrt(vel.x*vel.x + vel.y*vel.y);
    }
    public int getType(){
        return type;
    }
    public Vector2 getPosition() {
        Vector2 ans = body.getPosition();
        return new Vector2(ans.x*METERS_TO_PIXELS-radius,ans.y*METERS_TO_PIXELS-radius);
    }
    public int getDurability() {
        return durability;
    }
}
