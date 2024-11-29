package io.github.angrybirds.entities;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.ArrayList;

public class BirdData implements Serializable {
    private float posx;
    private float posy;
    private int damage;
    private String type;
    private int radius;
    public BirdData(Bird b1){
        this.damage = b1.getDamage();
        if(damage==2){
            this.type = "yellowBird";
            this.radius = 32;
        }
        else if(damage==4){
            this.type = "redBird";
            this.radius = 32;
        }
        else if(damage==6){
            this.type = "blackBird";
            this.radius = 48;
        }
        posx = b1.getPosition().x*20;
        posy = b1.getPosition().y*20;
    }

    public Vector2 getPosition() {
        return new Vector2(posx,posy);
    }
    public int getDamage() {
        return damage;
    }
    public String getType() {
        return type;
    }
    public int getRadius() {
        return radius;
    }
}
