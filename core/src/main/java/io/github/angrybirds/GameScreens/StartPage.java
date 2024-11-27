package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angrybirds.AngryBirds;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class StartPage implements Screen{
    private AngryBirds game;
    private Texture Startpagescreen;
    private Rectangle startButton;
    private SpriteBatch batch;
    private final int quitX = 0;
    private final int quitY = 0;
    private final int quitWidth = 100;
    private final int quitHeight = 100;

    public StartPage(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        Startpagescreen = new Texture("StartPage.png");

        startButton = new Rectangle(530,240,215,105);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Startpagescreen,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        if(Gdx.input.justTouched()){
           float touchX = Gdx.input.getX();
           float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            if(startButton.contains(touchpoint)){
                game.setScreen(new LevelSelection(game));

            }
            if (touchX >= quitX && touchX <= quitX + quitWidth &&
                touchY >= quitY && touchY <= quitY + quitHeight) {
                Gdx.app.exit();
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
        batch.dispose();
        Startpagescreen.dispose();
    }
}

