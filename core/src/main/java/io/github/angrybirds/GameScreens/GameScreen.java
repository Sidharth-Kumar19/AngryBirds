package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.angrybirds.AngryBirds;

public class GameScreen implements Screen {
    private AngryBirds game;
    private Stage stage;
    private Skin skin;
    private TextButton pauseButton;

    public GameScreen(AngryBirds game) {
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Pause Button
        pauseButton = new TextButton("Pause", skin);

        pauseButton.setPosition(700, 400);  // Example position

        pauseButton.setSize(100, 50);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Implement pause functionality here
            }
        });

        stage.addActor(pauseButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
        skin.dispose();
    }
}

