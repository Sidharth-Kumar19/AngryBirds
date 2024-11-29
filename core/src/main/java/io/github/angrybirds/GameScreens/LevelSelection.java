package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.angrybirds.AngryBirds;
import io.github.angrybirds.entities.LevelData;

import java.io.*;

public class LevelSelection implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private Texture levelselectionScreen,loadSelect;
    private Rectangle level1, level2, level3,back,newGame, loadGame;
    private Boolean selected;
    private int levelChosen;

    public LevelSelection(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        loadSelect = new Texture("SelectionScreen.jpg");
        levelselectionScreen = new Texture("LevelSelection.png");

        level1 = new Rectangle(84,300,184,202);
        level2 = new Rectangle(384,300,184,202);
        level3 = new Rectangle(684,300,184,202);
        back = new Rectangle(0,0,50,50);
        newGame = new Rectangle(500,290,90,50);
        loadGame = new Rectangle(700,290,90,50);
        selected = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(levelselectionScreen,0,0);

        if(selected) batch.draw(loadSelect,440,240);
        if(Gdx.input.justTouched()){
            Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            if(selected){
                if(Gdx.input.justTouched()){
                    if(newGame.contains(touchpoint)){
                        if(levelChosen==1){
                            game.setScreen(new Level1(game, true, new LevelData()));
                        }
                        else if(levelChosen==2){
                            game.setScreen(new Level2(game, true, new LevelData()));
                        }
                        else{
                            game.setScreen(new Level3(game, true, new LevelData()));
                        }
                    }
                    else if(loadGame.contains(touchpoint)){
                        if(levelChosen==1){
                            try (ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("C:/Users/Dell/Desktop/Programming/Projects/AngryBirdsGame/d1.dat"))){
                                LevelData lData = (LevelData) o1.readObject();
                                game.setScreen(new Level1(game, false, lData));
                            }
                            catch(IOException | ClassNotFoundException e){
                                System.err.println("Error loading data: " + e.getMessage());
                            }
                        }
                        else if(levelChosen==2){
                            try (ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("C:/Users/Dell/Desktop/Programming/Projects/AngryBirdsGame/d2.dat"))){
                                LevelData lData = (LevelData) o1.readObject();
                                game.setScreen(new Level2(game, false, lData));
                            }
                            catch(IOException | ClassNotFoundException e){
                                System.err.println("Error loading data: " + e.getMessage());
                            }
                        }
                        else{
                            try (ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("C:/Users/Dell/Desktop/Programming/Projects/AngryBirdsGame/d3.dat"))){
                                LevelData lData = (LevelData) o1.readObject();
                                game.setScreen(new Level3(game, false, lData));
                            }
                            catch(IOException | ClassNotFoundException e){
                                System.err.println("Error loading data: " + e.getMessage());
                            }
                        }
                    }
                }
                batch.end();
                return;
            }
            if(level1.contains(touchpoint)){
                levelChosen = 1;
                selected = true;
            }
            else if(level2.contains(touchpoint)){
                levelChosen = 2;
                selected = true;
            }
            else if(level3.contains(touchpoint)){
                levelChosen = 3;
                selected = true;
            }
            else if(back.contains(touchpoint)){
                game.setScreen(new StartPage(game));
            }
        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
    }
}
