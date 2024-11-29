package io.github.angrybirds.GameScreens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import io.github.angrybirds.AngryBirds;
import io.github.angrybirds.entities.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class Level3 implements Screen{
    private AngryBirds game;
    private SpriteBatch batch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private ArrayList<Bird> birds;
    private ArrayList<Pig> pigs;
    private ArrayList<Structures> blocks;
    private Texture winpage,losspage,pausePage,pauseButton;
    private Bird currentBird;
    private Vector2 slingAnchor;
    private boolean isDragging;
    private boolean birdloaded;
    private World world;
    private Slingshot slingshot;
    public static final float PIXELS_TO_METERS = 0.05f;
    public static final float METERS_TO_PIXELS = 20f; // 1 meter = 20 pixels
    private static int n = 0;
    private Box2DDebugRenderer debugRenderer;
    private Ground ground;
    private Bounds upperBound, leftBound, rightBound;
    private static final float velocityThreshold = 2f;
    private boolean launched;
    private boolean isPaused = false;
    private Circle pause,winMenu,winRestart,next,loseMenu,loseRestart,pauseRestart,pauseMenu,unPause;
    private boolean newGame;
    private LevelData lData;

    public Level3(AngryBirds game, boolean isNewGame, LevelData d){
        world = new World(new Vector2(0, -19.6f),true);
        this.game = game;
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("Level3.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 704);
        birdloaded = false;
        debugRenderer = new Box2DDebugRenderer();
        this.newGame = isNewGame;
        this.lData = d;
        if(this.newGame){
            loadEntitiesFromMap();
        }
        else{
            loadLevel();
        }
        this.slingAnchor = this.slingshot.getAnchorPoint();
        float screenWidth = 1280;
        float screenHeight = 704;
        upperBound = new Bounds(world, new Vector2(0, screenHeight), new Vector2(screenWidth+1, screenHeight));
        leftBound = new Bounds(world, new Vector2(0, 0),new Vector2(0, screenHeight));
        rightBound = new Bounds(world, new Vector2(screenWidth+1, 0),new Vector2(screenWidth+1, screenHeight));
        world.setContactListener(new GameContactListener());
        launched = false;
        winpage = new Texture("winPage.png");
        losspage = new Texture("losePage.png");
        pauseButton = new Texture("PauseButton.png");
        pause = new Circle(40,680,40);
        winMenu = new Circle(492,140,44);
        winRestart = new Circle(623,140,44);
        loseMenu = new Circle(565,144,50);
        loseRestart = new Circle(710,144,50);
        pauseMenu = new Circle(81,252,41);
        pauseRestart = new Circle(81,453,41);
        next = new Circle(754,140,44);
        pausePage = new Texture("pausePage.png");
        unPause = new Circle(175,354,27);
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
                        pigs.add(new CorporalPig(world,new Vector2(rect.x, rect.y), 5,32));
                        break;
                    case "kingPig":
                        pigs.add(new KingPig(world,new Vector2(rect.x, rect.y), 10,48));
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
                        blocks.add(new Dirt(world,new Vector2(rect.x, rect.y), 2,16));
                        break;
                    case "woodBlock":
                        blocks.add(new Wood(world,new Vector2(rect.x, rect.y), 5,16));
                        break;
                    case "steelBlock":
                        blocks.add(new Steel(world,new Vector2(rect.x, rect.y), 10,16));
                        break;
                }
            }
        }
    }
    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        world.step(1/60f,6,2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.begin();
        if(didWin()){
            batch.draw(winpage,391,0);
            batch.end();
            if(Gdx.input.justTouched()){
                Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                if(winMenu.contains(touchpoint)){
                    game.setScreen(new LevelSelection(game));
                }
                else if(winRestart.contains(touchpoint)){
                    game.setScreen(new Level3(game,true,new LevelData()));
                }
                else if(next.contains(touchpoint)){
                    game.setScreen(new Level1(game,true,new LevelData()));
                }
            }
            return;
        }
        else if(didLose()){
            batch.draw(losspage,407,0);
            batch.end();
            if(Gdx.input.justTouched()){
                Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                if(loseMenu.contains(touchpoint)){
                    game.setScreen(new LevelSelection(game));
                }
                else if(loseRestart.contains(touchpoint)){
                    game.setScreen(new Level3(game,true,new LevelData()));
                }
            }
            return;
        }
        if(isPaused){
            batch.draw(pausePage,10,10);
            batch.end();
            if(Gdx.input.justTouched()){
                Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                if(pauseMenu.contains(touchpoint)){
                    saveLevel();
                    game.setScreen(new LevelSelection(game));
                }
                else if(pauseRestart.contains(touchpoint)){
                    game.setScreen(new Level3(game,true,new LevelData()));
                }
                else if(unPause.contains(touchpoint)){
                    isPaused = false;
                }
            }
            return;
        }
        batch.draw(pauseButton,0,640);
        if(!birdloaded){
            birdloaded = true;
            this.currentBird = this.birds.get(0);
            birds.get(0).setPosition(this.slingAnchor);
        }
        else{
            if(currentBird != null && this.currentBird.getBody().getType() == BodyDef.BodyType.DynamicBody){
                if(this.currentBird.getBody().getLinearVelocity().len() < velocityThreshold){
                    world.destroyBody(this.currentBird.getBody());
                    birds.remove(currentBird);
                    currentBird = null;
                    birdloaded = false;
                    launched = false;
                }
            }
        }

        slingshot.render(batch);
        for(Bird bird : birds){
            bird.render(batch);
        }
        for(int i = 0; i < pigs.size(); i++){
            Pig pig = pigs.get(i);
            if(!pig.isAlive()){
                pigs.get(i).dispose();
                pigs.remove(i);
                i--;
            }
            else{
                pig.render(batch);
            }
        }

        for(int i = 0; i < blocks.size(); i++){
            Structures block = blocks.get(i);
            if(!block.isStanding()){
                blocks.get(i).dispose();
                blocks.remove(i);
                i--;
            }
            else{
                block.render(batch);
            }
        }

        if(Gdx.input.isTouched()){
            Vector2 touchpoint = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            if(pause.contains(touchpoint)){
                isPaused = true;
                batch.end();
                return;
            }
        }
        if(!launched && Gdx.input.isTouched()){
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
                launchForce.scl(currentBird.getSpeed());
                currentBird.changeType();
                currentBird.applyForce(launchForce);
                launched = true;
                //Gdx.app.log("Bird Launch", "Position: " + currentBird.getPosition() + ", Force: " + launchForce);
            }
        }
        batch.end();
        debugRenderer.render(world, camera.combined);
    }

    public boolean didWin(){
        return pigs.isEmpty();
    }

    public boolean didLose(){
        return birds.isEmpty();
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
    public void dispose(){
        batch.dispose();
        winpage.dispose();
        losspage.dispose();
        map.dispose();
        mapRenderer.dispose();
    }

    public void loadLevel(){
        for(BirdData b : lData.birdDataList){
            if(Objects.equals(b.getType(), "yellowBird")){
                birds.add(new YellowBird(world,b.getPosition(),b.getRadius()));
            }
            else if(Objects.equals(b.getType(), "redBird")){
                birds.add(new YellowBird(world,b.getPosition(),b.getRadius()));
            }
            else if(Objects.equals(b.getType(), "blackBird")){
                birds.add(new YellowBird(world,b.getPosition(),b.getRadius()));
            }
        }

        for(PigData p : lData.pigDataList){
            if(Objects.equals(p.getType(), "minionPig")){
                pigs.add(new MinionPig(world,p.getPosition(),p.getHP(),p.getRadius()));
            }
            else if(Objects.equals(p.getType(), "corporalPig")){
                pigs.add(new CorporalPig(world,p.getPosition(),p.getHP(),p.getRadius()));
            }
            else if(Objects.equals(p.getType(), "kingPig")){
                pigs.add(new KingPig(world,p.getPosition(),p.getHP(),p.getRadius()));
            }
        }

        for(StructData s : lData.structDataList){
            if(Objects.equals(s.getType(), "woodBlock")){
                blocks.add(new Wood(world,s.getPosition(),s.getDurability(),s.getRadius()));
            }
            else if(Objects.equals(s.getType(), "steelBlock")){
                blocks.add(new Steel(world,s.getPosition(),s.getDurability(),s.getRadius()));
            }
            else if(Objects.equals(s.getType(), "dirtBlock")){
                blocks.add(new Dirt(world, s.getPosition(), s.getDurability(), s.getRadius()));
            }
        }
    }

    public void saveLevel(){
        try (ObjectOutputStream o1 = new ObjectOutputStream(new FileOutputStream("DataStorage3.dat"))) {
            ArrayList<BirdData> SaveBird = new ArrayList<>();
            ArrayList<PigData> SavePigs = new ArrayList<>();
            ArrayList<StructData> SaveStruct = new ArrayList<>();

            for(Bird b : birds){
                SaveBird.add(new BirdData(b));
            }
            for(Pig p : pigs){
                SavePigs.add(new PigData(p));
            }
            for(Structures s : blocks){
                SaveStruct.add(new StructData(s));
            }

            lData.birdDataList = SaveBird;
            lData.pigDataList = SavePigs;
            lData.structDataList = SaveStruct;

            o1.writeObject(lData);
            System.out.println("Data saved successfully to " + "DataStorage3.dat");
        }
        catch(IOException e){
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}
