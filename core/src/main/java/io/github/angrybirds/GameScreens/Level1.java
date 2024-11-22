package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.angrybirds.AngryBirds;
import io.github.angrybirds.entities.*;


import java.util.ArrayList;

public class Level1 implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ArrayList<Bird> birds;
    private ArrayList<Pig> pigs;
    private ArrayList<Structures> blocks;
    private OrthographicCamera camera;
    private Texture winpage;
    private Texture losspage;
    private int done;

    public Level1(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("Level1InProgress.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //loadEntitiesFromMap();
        winpage = new Texture("win_.png");
        losspage = new Texture("loss_[1](1).png");
        done = -1;

    }

    private void loadEntitiesFromMap() {
        for (MapObject object : map.getLayers().get("Objects").getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                String type = (String) object.getProperties().get("type");

                switch (type) {
                    case "redBird":
                        birds.add(new RedBird(new Vector2(rect.x, rect.y)));
                        break;
                    case "blackBird":
                        birds.add(new BlackBird(new Vector2(rect.x, rect.y)));
                        break;
                    case "yellowBird":
                        birds.add(new YellowBird(new Vector2(rect.x, rect.y)));
                        break;
                    case "minionPig":
                        pigs.add(new MinionPig(new Vector2(rect.x, rect.y), 2));
                        break;
                    case "corporalPig":
                        pigs.add(new CorporalPig(new Vector2(rect.x, rect.y), 5));
                        break;
                    case "kingPig":
                        pigs.add(new KingPig(new Vector2(rect.x, rect.y), 10));
                        break;
                    case "dirtBlock":
                        blocks.add(new Dirt(new Vector2(rect.x, rect.y), 2));
                        break;
                    case "woodBlock":
                        blocks.add(new Wood(new Vector2(rect.x, rect.y), 5));
                        break;
                    case "steelBlock":

                        blocks.add(new Steel(new Vector2(rect.x, rect.y), 10));
                        break;
                }
            }
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.begin();
        if(done==-1){
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                batch.draw(winpage,640-winpage.getWidth()/2,0,winpage.getWidth(),720);
                done = 1;
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                batch.draw(losspage,640-losspage.getWidth()/2,0,losspage.getWidth(),720);
                done = 0;
            }
        }
        else if(done==0){
            batch.draw(losspage,640-losspage.getWidth()/2,0,losspage.getWidth(),720);
        }
        else if (done == 1) {
            batch.draw(winpage,640-winpage.getWidth()/2,0,winpage.getWidth(),720);
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
