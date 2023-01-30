package m08.uf3.drops.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetManager {
    public static Texture bucketImage;
    public static Sound dropSound;
    public static Texture fondo;

    // Font
    public static BitmapFont font;






    //Load textures
    public static void load() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        bucketImage = new Texture(Gdx.files.internal("soldier.png"));
        fondo = new Texture(Gdx.files.internal("map.png"));



        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("augh.mp3"));


    }


    public static void dispose() {
        bucketImage.dispose();
        dropSound.dispose();
        fondo.dispose();

    }
}
