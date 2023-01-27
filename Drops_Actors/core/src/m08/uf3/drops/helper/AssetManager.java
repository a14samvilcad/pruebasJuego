package m08.uf3.drops.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    public static Texture dropImage;
    public static Texture bucketImage;
    public static Sound dropSound;
    public static Music rainMusic;


    // Font
    public static BitmapFont font;


    //Load textures
    public static void load() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("pizza.png"));
        bucketImage = new Texture(Gdx.files.internal("mike.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("augh.mp3"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("monstersinc.mp3"));
        rainMusic.setLooping(true);
        rainMusic.play();
    }



    public static void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
