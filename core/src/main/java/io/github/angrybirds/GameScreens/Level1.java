package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import io.github.angrybirds.AngryBirds;
import io.github.angrybirds.entities.*;

import java.util.ArrayList;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class Level1 implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private ArrayList<Bird> birds;
    private ArrayList<Pig> pigs;
    private ArrayList<Structures> blocks;
    private Texture winpage;
    private Texture losspage;
    private Bird currentBird; // The bird currently on the slingshot
    private Vector2 slingAnchor; // Position of the slingshot anchor
    private boolean isDragging; // Whether the user is dragging the bird
    private boolean birdloaded;
    private World world;
    private Slingshot slingshot;
    public static final float PIXELS_TO_METERS = 0.05f;
    public static final float METERS_TO_PIXELS = 20f; // 1 meter = 20 pixels
    private static int n = 0;
    private Box2DDebugRenderer debugRenderer;
    private Ground ground;
    private Bounds upperBound, leftBound, rightBound;
    private static final float VELOCITY_THRESHOLD = 2f;

    public Level1(AngryBirds game) {
        world = new World(new Vector2(0, -196f/METERS_TO_PIXELS),true);
        this.game = game;
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("Level1.1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 708);
        birdloaded = false;
        debugRenderer = new Box2DDebugRenderer();
        loadEntitiesFromMap();
        this.slingAnchor = this.slingshot.getAnchorPoint();
        float screenWidth = 1280;
        float screenHeight = 720;
        upperBound = new Bounds(world, new Vector2(0, screenHeight), new Vector2(screenWidth, screenHeight));
        leftBound = new Bounds(world, new Vector2(0, 0),new Vector2(0, screenHeight));
        rightBound = new Bounds(world, new Vector2(screenWidth, 0),new Vector2(screenWidth, screenHeight));
    }

    private void loadEntitiesFromMap() {
        for (MapObject object : map.getLayers().get("Ground").getObjects()) {
            if(object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                this.ground = new Ground(world,new Vector2(rect.x, rect.y),1280,96);
            }
        }
        for (MapObject object : map.getLayers().get("Entities").getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                String type = (String) object.getProperties().get("type");

                switch(type){
                    case "redBird":
                        birds.add(new RedBird(world,new Vector2(rect.x, rect.y),32));
                        break;
                    case "blackBird":
                        birds.add(new BlackBird(world,new Vector2(rect.x, rect.y),48));
                        break;
                    case "yellowBird":
                        birds.add(new YellowBird(world,new Vector2(rect.x, rect.y),32));
                        break;
                    case "minionPig":
                        pigs.add(new MinionPig(world,new Vector2(rect.x, rect.y), 2,32));
                        break;
                    case "corporalPig":
                        pigs.add(new CorporalPig(new Vector2(rect.x, rect.y), 5));
                        break;
                    case "kingPig":
                        pigs.add(new KingPig(new Vector2(rect.x, rect.y), 10));
                        break;
                    case "slingshot":
                        this.slingshot = new Slingshot(new Vector2(rect.x,rect.y));
                        break;
                }
            }
        }
        for (MapObject object : map.getLayers().get("Blocks").getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                String type = (String) object.getProperties().get("type");
                switch (type) {
                    case "dirtBlock":
                        blocks.add(new Dirt(new Vector2(rect.x, rect.y), 2));
                        break;
                    case "woodBlock":
                        blocks.add(new Wood(world,new Vector2(rect.x, rect.y), 5,16));
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
    public void render(float delta){
        world.step(1/60f,6,2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.begin();
        if(!birdloaded){
            birdloaded = true;
            this.currentBird = this.birds.get(0);
            birds.get(0).setPosition(this.slingAnchor);
        }
        else{
            if (currentBird != null && this.currentBird.getBody().getType() == BodyDef.BodyType.DynamicBody) {
                if (this.currentBird.getBody().getLinearVelocity().len() < VELOCITY_THRESHOLD){
                    world.destroyBody(this.currentBird.getBody());
                    birds.remove(currentBird);
                    currentBird = null;
                    birdloaded = false;
                }
            }
        }

        slingshot.render(batch);
        for(Bird bird : birds){
            bird.render(batch);
        }
        for(Pig pig : pigs){
            pig.render(batch);
        }
        for(Structures block : blocks){
            block.render(batch);
        }

        if(Gdx.input.isTouched()){
            Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            Rectangle dragbox = new Rectangle(slingAnchor.x-32,slingAnchor.y-32,64,64);
            if(!isDragging && currentBird != null && dragbox.contains(touchpoint)){
                isDragging = true;
            }
            if(isDragging){
                Vector2 direction = touchpoint.cpy().sub(slingAnchor);
                float maxDistance = 75; // Maximum stretch
                if(direction.len() > maxDistance){
                    direction.setLength(maxDistance);
                }
                currentBird.setPosition(slingAnchor.cpy().add(direction));
            }
        }
        else{
            if(isDragging){
                isDragging = false;
                Vector2 releasePosition = currentBird.getPosition();
                Vector2 launchForce = slingAnchor.cpy().sub(releasePosition).scl(3f);
                currentBird.changeType();
                currentBird.applyForce(launchForce);
                Gdx.app.log("Bird Launch", "Position: " + currentBird.getPosition() + ", Force: " + launchForce);
            }
        }
        batch.end();
        debugRenderer.render(world, camera.combined);
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
        winpage.dispose();
        losspage.dispose();
        map.dispose();
        mapRenderer.dispose();
    }
}
