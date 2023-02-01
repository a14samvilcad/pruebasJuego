package m08.uf3.drops.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import m08.uf3.drops.Drops;
import m08.uf3.drops.Screens.GameScreen;
import m08.uf3.drops.Utils.Settings;

public class MainMenuScreen implements Screen {

    final Drops game;
    OrthographicCamera camera;
    Stage stage;
    int lives;
    Label title;
    Label message;

    public MainMenuScreen(Drops game) {
        this.game = game;
        lives = 3;

        // Creem la càmera de les dimensions del joc
        camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);
        crearLabels();
        stage.addActor(title);
        stage.addActor(message);
    }

    private void crearLabels(){
        BitmapFont bitmapfont = new BitmapFont();
        title = new Label("Zombie Game", new Label.LabelStyle(bitmapfont, Color.WHITE));
        title.setFontScale(Settings.TITLE_RESCALE_SIZE);
        title.setPosition((Settings.GAME_WIDTH - (title.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, ((Settings.GAME_HEIGHT - title.getHeight()) / 2) + 50);
        message = new Label("Pulsa en la pantalla para empezar", new Label.LabelStyle(bitmapfont, Color.WHITE));
        message.setPosition((Settings.GAME_WIDTH - (message.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, (Settings.GAME_HEIGHT - message.getHeight()) / 2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        stage.draw();
        stage.act(delta);
        // Si es fa clic en la pantalla, canviem la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport(), game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    //...Rest of class omitted for succinctness.

}