package io.github.angrybirds.entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bird{
    private Vector2 position;
    private float speed;  // Basic speed property
    private float radius;
    private Body body;

    public Bird(World world, Vector2 startPosition, int radius) {
        this.position = startPosition;
        this.speed = 0; // Speed can be updated on launch
        this.radius = radius;
    }

    public void render(SpriteBatch batch){

    }

    public void changeType(){

    }
    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
    }

    public void updatePosition(float deltaTime) {
        // Placeholder for moving the bird based on physics
        position.add(speed * deltaTime, speed * deltaTime);
    }

    public void dispose() {

    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void applyForce(Vector2 force) {
        //body.applyLinearImpulse(force, body.getWorldCenter(), true);
    }

    public Body getBody(){
        //System.out.println("Check1");
        return body;
    }

}
