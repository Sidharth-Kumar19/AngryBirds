package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class KingPig extends Pig{
    private Texture texture;
    private Vector2 position;
    private int hp;

    public KingPig(World world, Vector2 startPosition, int health, int radius) {
        super(world,startPosition, health,radius,new Texture("KingPig(3).png"));
    }

}
