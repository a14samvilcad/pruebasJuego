package m08.uf3.drops.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import m08.uf3.drops.helper.AssetManager;
import m08.uf3.drops.Screens.GameScreen;
import  m08.uf3.drops.Utils.Settings;


public class Drop extends Actor {

    public static final int WALLET_STANDING = 0;
    public static final int WALLET_RIGHT = 1;
    public static final int WALLET_LEFT = 2;

    private Vector2 position;
    private int width, height;
    private int direction;

    private Rectangle collisionRect;

    public Drop(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        direction = WALLET_STANDING;

        collisionRect = new Rectangle();
        collisionRect.x = x;
        collisionRect.y = y;
        collisionRect.width = this.width;
        collisionRect.height = this.height;

        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
    }

    public void act(float delta){
        super.act(delta);
        this.position.y -= Settings.DROP_VELOCITY * Gdx.graphics.getDeltaTime();

        if (this.position.y + width < 0){
            this.remove();
            Settings.LIVES--;
        }

        collisionRect.x = this.position.x;
        collisionRect.y = this.position.y;
        collisionRect.width = this.width;
        collisionRect.height = this.height;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.dropImage, this.position.x, this.position.y, width, height);
    }

    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getCollisionRectDrop() {
        return collisionRect;
    }

}
