package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.angrybirds.AngryBirds;

public class LevelSelection implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private Texture levelselectionScreen;
    private Rectangle level1, level2, backButton;

    public LevelSelection(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        levelselectionScreen = new Texture("LevelSelection.png");

        // Define button regions
        level1 = new Rectangle(84, 300, 184, 202);
        level2 = new Rectangle(384, 300, 184, 202);

        // Back button region (adjust coordinates to match your layout)
        backButton = new Rectangle(20, 20, 50, 50);
    }

    private boolean isButtonClicked(float touchX, float touchY, float x, float y, float width, float height) {
        if (Gdx.input.justTouched()) {
            touchX = Gdx.input.getX();
            touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            return touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height;
        }
        return false;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(levelselectionScreen, 0, 0);
        batch.end();

        float touchX = 0, touchY = 0;

        // Check if Level 1 button is clicked
        if (isButtonClicked(touchX, touchY, level1.x, level1.y, level1.width, level1.height)) {
            game.setScreen(new Level1(game));
        }
        // Check if Level 2 button is clicked
        else if (isButtonClicked(touchX, touchY, level2.x, level2.y, level2.width, level2.height)) {
            game.setScreen(new Level2(game));
        }
        // Check if Back button is clicked
        else if (isButtonClicked(touchX, touchY, backButton.x, backButton.y, backButton.width, backButton.height)) {
            game.setScreen(new HomePage(game)); // Replace `HomeScreen` with your actual home screen class
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
        levelselectionScreen.dispose();
    }
}
