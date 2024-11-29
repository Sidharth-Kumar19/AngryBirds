package io.github.angrybirds.entities;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class StructData implements Serializable {
    private float posx,posy;
    private int durability;
    private String type;
    private int radius;
    public StructData(Structures s1){
        if(s1.getType()==0){
            this.type = "woodBlock";
            this.radius = 32;
        }
        else if(s1.getType()==1){
            this.type = "steelBlock";
            this.radius = 32;
        }
        else if(s1.getType()==2){
            this.type = "dirtBlock";
            this.radius = 32;
        }
        this.durability = s1.getDurability();
        posx = s1.getPosition().x*20;
        posy = s1.getPosition().y*20;
    }

    public Vector2 getPosition() {
        return new Vector2(posx,posy);
    }
    public int getDurability() {
        return durability;
    }
    public String getType() {
        return type;
    }
    public int getRadius() {
        return radius;
    }
}
