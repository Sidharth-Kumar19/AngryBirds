package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static io.github.angrybirds.GameScreens.Level1.METERS_TO_PIXELS;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;

public class MinionPig extends Pig{
    private Texture texture;
    private int hp;
    private int radius;
    private Body body;

    public MinionPig(World world, Vector2 startPosition, int health, int radius) {
        super(world,startPosition, health,radius,new Texture("MinionPig(1).png"),0);

    }

}
