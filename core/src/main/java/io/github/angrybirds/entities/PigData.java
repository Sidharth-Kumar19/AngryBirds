package io.github.angrybirds.entities;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class PigData implements Serializable {
    private float posx,posy;
    private int hp;
    private String type;
    private int radius;
    public PigData(Pig p1){
        if(p1.getType()==0){
            this.type = "minionPig";
            this.radius = 32;
        }
        else if(p1.getType()==1){
            this.type = "corporalPig";
            this.radius = 32;
        }
        else if(p1.getType()==2){
            this.type = "kingPig";
            this.radius = 48;
        }
        posx = p1.getPosition().x*20;
        posy = p1.getPosition().y*20;
    }

    public Vector2 getPosition() {
        return new Vector2(posx,posy);
    }
    public int getHP() {
        return hp;
    }
    public String getType() {
        return type;
    }
    public int getRadius() {
        return radius;
    }
}
