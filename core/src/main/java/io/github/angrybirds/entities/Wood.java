package io.github.angrybirds.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static io.github.angrybirds.GameScreens.Level1.METERS_TO_PIXELS;
import static io.github.angrybirds.GameScreens.Level1.PIXELS_TO_METERS;

public class Wood extends Structures{
    public Wood(World world, Vector2 startPosition, int health, int radius) {
        super(world,startPosition,health,radius,new TextureRegion(new Texture("Blocks16x16(2).png"), 64, 192, 32, 32),0);
    }
}
