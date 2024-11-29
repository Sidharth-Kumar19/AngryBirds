package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Dirt extends Structures{
    public Dirt(World world, Vector2 startPosition, int health, int radius) {
        super(world,startPosition,health,radius,new TextureRegion(new Texture("Blocks16x16(2).png"), 192, 192, 32, 32),2);
    }
}
