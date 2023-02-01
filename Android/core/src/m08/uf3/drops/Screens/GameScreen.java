package m08.uf3.drops.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import m08.uf3.drops.Drops;
import m08.uf3.drops.Objects.Bucket;
import m08.uf3.drops.Utils.Settings;
import m08.uf3.drops.helper.AssetManager;

public class GameScreen implements Screen {
    Stage stage;
    Batch batch;
    Bucket bucket;
    final Drops game;
    ShapeRenderer shapeRenderer;
    Label vidas;

    //Map
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    private int tileWidth, tileHeight,
            mapWidthInTiles, mapHeightInTiles,
            mapWidthInPixels, mapHeightInPixels;

    public GameScreen(Batch prevBatch, Viewport prevViewport, Drops game) {

        map = AssetManager.map;

        MapProperties properties = map.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        prevViewport.setCamera(camera);

        tmr = new OrthogonalTiledMapRenderer(map);

        this.game = game;
        Settings.LIVES = 3;
        crearLabels();


        // Creem el ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        // Creem l'stage i assginem el viewport
        stage = new Stage(prevViewport, prevBatch);

        batch = stage.getBatch();

        // Creem la nau i la resta d'objectes
        bucket = new Bucket(368, 20, 64, 64);

        // Afegim els actors a l'stage
        stage.addActor(bucket);
        stage.addActor(vidas);
        // Donem nom a l'Actor
        bucket.setName("bucket");

    }

    private void crearLabels(){
        BitmapFont bitmapfont = new BitmapFont();

        vidas = new Label("Vidas: "+ Settings.LIVES, new Label.LabelStyle(bitmapfont, Color.WHITE));
        vidas.setPosition((Settings.GAME_WIDTH - (vidas.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, (Settings.GAME_HEIGHT - vidas.getHeight()) / 2);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cameraPosition();
        camera.update();
        tmr.setView(camera);
        tmr.render();
        stage.draw();
        stage.act(delta);
        vidas.setText("Vidas: "+ Settings.LIVES);

        if(Settings.LIVES < 0){
            stage.dispose();
            game.setScreen(new MainMenuScreen(game));
        }

        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cameraPosition() {
        if (Gdx.app.getType() == Application.ApplicationType.Android){
            if(bucket.getCollisionRectBucket().x < (mapWidthInPixels - (Gdx.graphics.getWidth() / 2) / 2) && bucket.getCollisionRectBucket().x > (Gdx.graphics.getWidth() / 2) / 2){
                camera.position.x = bucket.getCollisionRectBucket().x;
            }
            if(bucket.getCollisionRectBucket().y < (mapHeightInPixels - (Gdx.graphics.getHeight() / 2) / 2) && bucket.getCollisionRectBucket().y > (Gdx.graphics.getHeight() / 2) / 2) {
                camera.position.y = bucket.getCollisionRectBucket().y;
            }
        } else {
            if(bucket.getCollisionRectBucket().x < (mapWidthInPixels - (Gdx.graphics.getWidth() / 2)) && bucket.getCollisionRectBucket().x > (Gdx.graphics.getWidth() / 2)){
                camera.position.x = bucket.getCollisionRectBucket().x;
            }
            if(bucket.getCollisionRectBucket().y < (mapHeightInPixels - (Gdx.graphics.getHeight() / 2)) && bucket.getCollisionRectBucket().y > (Gdx.graphics.getHeight() / 2)) {
                camera.position.y = bucket.getCollisionRectBucket().y;
            }
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        AssetManager.load();


    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        AssetManager.dispose();
        tmr.dispose();
        map.dispose();
    }

}
