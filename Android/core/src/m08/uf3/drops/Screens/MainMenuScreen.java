package m08.uf3.drops.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import m08.uf3.drops.Drops;
import m08.uf3.drops.Utils.Settings;

public class MainMenuScreen implements Screen {

    final Drops game;
    GameScreen gameScreen;
    OrthographicCamera camera;
    Stage stage;
    int lives;
    Label title;
    Label message;

    TextButton button;


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
        gameScreen = new GameScreen(stage.getBatch(), stage.getViewport(), game);

        crearLabels();
        //stage.addActor(title);
        //stage.addActor(message);
        stage.addActor(button);
    }

    private void crearLabels(){
        Gdx.input.setInputProcessor(stage);
        BitmapFont font = new BitmapFont();
        title = new Label("PLAY", new Label.LabelStyle(font, Color.WHITE));
        title.setFontScale(Settings.TITLE_RESCALE_SIZE);
        /*title.setPosition((Settings.GAME_WIDTH - (title.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, ((Settings.GAME_HEIGHT - title.getHeight()) / 2) + 50);
        message = new Label("Pulsa en la pantalla para empezar", new Label.LabelStyle(font, Color.WHITE));
        message.setPosition((Settings.GAME_WIDTH - (message.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, (Settings.GAME_HEIGHT - message.getHeight()) / 2);*/

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.GREEN;
        button = new TextButton("PLAY", textButtonStyle);
        button.setLabel(title);
        button.setPosition((Settings.GAME_WIDTH - (button.getWidth() * Settings.TITLE_RESCALE_SIZE)) / 2, (Settings.GAME_HEIGHT - button.getHeight()) / 1.1f);


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        stage.draw();
        stage.act(delta);
        if (button.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(gameScreen);
            }
        }));

        // Si es fa clic en la pantalla, canviem la pantalla
        /*if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport(), game));
            dispose();
        }*/
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
        game.dispose();
    }


    //...Rest of class omitted for succinctness.

}