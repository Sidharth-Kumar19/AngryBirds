package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinScreen implements Screen {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture starTexture;
    private Texture buttonMenuTexture, buttonRestartTexture, buttonNextTexture;

    public WinScreen() {
        batch = new SpriteBatch();

        // Load textures
        backgroundTexture = new Texture("background.png"); // Replace with your background path
        starTexture = new Texture("star.png");             // Replace with your star texture
        buttonMenuTexture = new Texture("button_menu.png");
        buttonRestartTexture = new Texture("button_restart.png");
        buttonNextTexture = new Texture("button_next.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Draw background
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Calculate screen center
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        // Draw stars (centered above the buttons)
        float starWidth = 100;  // Adjust as needed
        float starHeight = 100; // Adjust as needed
        batch.draw(starTexture, centerX - starWidth * 1.5f, centerY + 50, starWidth, starHeight); // Left star
        batch.draw(starTexture, centerX - starWidth / 2f, centerY + 50, starWidth, starHeight);  // Center star
        batch.draw(starTexture, centerX + starWidth / 2f, centerY + 50, starWidth, starHeight);  // Right star

        // Draw buttons (centered horizontally below the stars)
        float buttonWidth = 80;  // Adjust as needed
        float buttonHeight = 80; // Adjust as needed
        batch.draw(buttonMenuTexture, centerX - 1.5f * buttonWidth, centerY - 100, buttonWidth, buttonHeight);
        batch.draw(buttonRestartTexture, centerX - buttonWidth / 2f, centerY - 100, buttonWidth, buttonHeight);
        batch.draw(buttonNextTexture, centerX + 0.5f * buttonWidth, centerY - 100, buttonWidth, buttonHeight);

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
        batch.dispose();
        backgroundTexture.dispose();
        starTexture.dispose();
        buttonMenuTexture.dispose();
        buttonRestartTexture.dispose();
        buttonNextTexture.dispose();
    }
}
