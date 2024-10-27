package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import io.github.angrybirds.AngryBirds;

public class HomePage implements Screen {
    private AngryBirds game;
    private Texture Homepagescreen;

    public HomePage(AngryBirds game) {
        this.game = game;
    }

    @Override
    public void show() {
        Homepagescreen = new Texture("HomePageScreen.jpg");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new StartPage(game));
            }
        }, 3);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Homepagescreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  // Draw the background
        game.batch.end();
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
        Homepagescreen.dispose();
    }
}
