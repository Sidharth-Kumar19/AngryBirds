package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class RedBird extends Bird {
    private Texture texture;
    private int radius;
    private Body body;
    private float speedMultiplier;
    private int damage;


    public RedBird(World world, Vector2 startPosition, int radius) {
        super(world, startPosition, radius, new Texture("RedBird(2).png"));
        this.texture = new Texture("RedBird(2).png");
        this.radius = radius;
        this.damage = 4;
        this.speedMultiplier = 2;
    }

    @Override
    public int getDamage(){
        return damage;
    }

    @Override
    public float getSpeed(){
        return speedMultiplier;
    }
}
