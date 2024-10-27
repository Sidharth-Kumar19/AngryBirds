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

public class LevelSelection implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private Texture levelselectionScreen;
    private Rectangle level1, level2;
    //ShapeRenderer shapeRenderer;

    public LevelSelection(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        levelselectionScreen = new Texture("LevelSelection.png");

        level1 = new Rectangle(84,300,184,202);
        level2 = new Rectangle(384,300,184,202);
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

//        shapeRenderer = new ShapeRenderer();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.rect(84,300,184,202);
//        shapeRenderer.rect(384,300,184,202);
//        shapeRenderer.end();

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
