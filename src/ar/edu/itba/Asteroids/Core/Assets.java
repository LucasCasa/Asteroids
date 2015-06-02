package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
/**
 * a Static class that has all the textures
 * @author ME
 *
 */
public class Assets {

	//Asteroides
	public static final Texture ASTEROID = new Texture("aster.png");
	
	//Naves
	public static final Texture[] SHIPS = {new Texture("death.png"),new Texture("capsule.png"),new Texture("nose.png"),new Texture("rosca.png"),new Texture("ufo.png")};
	
	
	//Sounds
	public static final Sound SOUND_MENU = Gdx.audio.newSound(Gdx.files.internal("MenuMusic.mp3"));
	public static final Sound SOUND_GAME = Gdx.audio.newSound(Gdx.files.internal("GameMusic.mp3"));
	//PowerUps
	public static final Texture EXTRALIVESIMG = new Texture("extravida.png");
	public static final Texture EXTRAMASSIMG = new Texture("extramasa.png");
	public static final Texture INVENCIBILITYIMG = new Texture("invi35.png");
	public static final Texture EXTRAACELIMG = new Texture("acel.png");
	
	//Miscellaneous 
	public static final Texture HEART = new Texture("heart.png");
	public static final Texture INVIICON = new Texture("invi35invertida.png");
	public static final Texture ACELICON = new Texture("acel35invertida.png");
	public static final Texture COOLDOWN = new Texture("cooldown.png");
	public static final Texture BAR_BACK = new Texture("backBar.png");
	public static final Texture BAR_FRONT = new Texture("frontBar.png");
	public static final Texture EXPLOSION = new Texture("explosion.png");
	public static final Texture[] PROPULSORS = {new Texture("propulsorUp.png"),new Texture("propulsorDown.png"),
												new Texture("propulsorLeft.png"), new Texture("propulsorRight.png")};
	//Fonts
	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
	public static final BitmapFont SMALL_FONT = new BitmapFont(Gdx.files.internal("little.fnt"));
	public static final BitmapFont TITLE_FONT = new BitmapFont(Gdx.files.internal("title.fnt"));
	
	//Help
	public static final Texture HELP = new Texture("help.png");
	
	@Deprecated
	//HighScore
	private static Preferences prefs = Gdx.app.getPreferences("Asteroids");
	@Deprecated
	public static void setHighScore(float val) {
	    prefs.putFloat("highScore", val);
	    prefs.flush();
	}
	@Deprecated
	public static float getHighScore() {
	    return prefs.getFloat("highScore");
	}	
}