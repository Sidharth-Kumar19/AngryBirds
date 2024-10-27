package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.angrybirds.AngryBirds;

public class Level2 implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private Texture levelselectionScreen;
    private Rectangle level1, level2;

    public Level2(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        levelselectionScreen = new Texture("LevelSelection.png");

        level1 = new Rectangle();
        level2 = new Rectangle();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(levelselectionScreen,0,0);
        batch.end();

        if(Gdx.input.justTouched()){
            Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            if(level1.contains(touchpoint)){
                game.setScreen(new Level1(game));
            }
            else if(level2.contains(touchpoint)){
                game.setScreen(new Level2(game));
            }
        }

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
