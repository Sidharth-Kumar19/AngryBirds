package io.github.angrybirds.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;

public class Bounds {
    private Body body;

    public Bounds(World world, Vector2 start, Vector2 end) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);

        EdgeShape shape = new EdgeShape();
        shape.set(start.scl(PIXELS_TO_METERS), end.scl(PIXELS_TO_METERS));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getBody() {
        return body;
    }
}
