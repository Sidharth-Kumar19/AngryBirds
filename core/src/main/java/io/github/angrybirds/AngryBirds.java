package io.github.angrybirds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import io.github.angrybirds.GameScreens.HomePage;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new HomePage(this));
    }
    @Override
    public void render() {
        //ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        super.render();
    }
    @Override
    public void dispose() {
        batch.dispose();
    }
}
