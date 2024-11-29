package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Steel extends Structures{
    public Steel(World world, Vector2 startPosition, int health, int radius) {
        super(world,startPosition,health,radius,new TextureRegion(new Texture("Blocks16x16(2).png"), 128, 128, 32, 32),1);
    }
}
