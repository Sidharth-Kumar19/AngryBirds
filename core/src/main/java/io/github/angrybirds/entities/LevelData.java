package io.github.angrybirds.entities;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;
import java.util.ArrayList;

public class LevelData implements Serializable {
    public ArrayList<BirdData> birdDataList = new ArrayList<>();
    public ArrayList<PigData> pigDataList = new ArrayList<>();
    public ArrayList<StructData> structDataList = new ArrayList<>();
}
