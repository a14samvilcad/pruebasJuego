package m08.uf3.drops.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;
    public GameScreen(Batch prevBatch, Viewport prevViewport, Drops game) {
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

        map = new TmxMapLoader().load("Maps/map.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        batch.begin();
        batch.end();
        tmr.render();
        stage.act(delta);
        stage.draw();
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
