package m08.uf3.drops.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import m08.uf3.drops.Objects.Drop;
import m08.uf3.drops.Utils.Settings;
import m08.uf3.drops.helper.AssetManager;

public class GameScreen implements Screen {
    Array<Rectangle> raindrops;
    long lastDropTime;
    Stage stage;
    Batch batch;
    Bucket bucket;
    final Drops game;
    Drop drop;
    ShapeRenderer shapeRenderer;
    Label vidas;

    public GameScreen(Batch prevBatch, Viewport prevViewport, Drops game) {
        this.game = game;
        Settings.LIVES = 3;
        crearLabels();
        // Iniciem la música
        AssetManager.rainMusic.play();

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
        ScreenUtils.clear(0, 0, 0.2f, 1);
        stage.act(delta);
        stage.draw();
        vidas.setText("Vidas: "+ Settings.LIVES);

        if(Settings.LIVES < 0){
            stage.dispose();
            game.setScreen(new MainMenuScreen(game));
        }

        if(TimeUtils.nanoTime() - lastDropTime > 2000000000){
            drop = new Drop(MathUtils.random(0, 800 - 64), 480, 64,64);
            stage.addActor(drop);
            lastDropTime = TimeUtils.nanoTime();
        }

        try {
            if (drop.getCollisionRectDrop().overlaps(bucket.getCollisionRectBucket())) {
                AssetManager.dropSound.play();
                drop.remove();
                drop.getCollisionRectDrop().setX(Settings.GAME_WIDTH + 200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown

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
    }

}
