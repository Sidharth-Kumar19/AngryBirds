package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BlackBird extends Bird {
    private Texture texture;
    private int radius;
    private Body body;
    private float speedMultiplier;
    private int damage;

    public BlackBird(World world, Vector2 startPosition, int radius) {
        super(world, startPosition, radius, new Texture("Bomb(4).png"));
        this.texture = new Texture("Bomb(4).png");
        this.radius = radius;
        this.damage = 6;
        this.speedMultiplier = 1.75f;
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
