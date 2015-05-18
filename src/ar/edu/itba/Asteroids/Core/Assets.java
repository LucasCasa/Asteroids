package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {

	//Asteroides
	public static final Texture ASTEROID = new Texture("aster.png");
	
	//Naves
	public static final Texture[] SHIPS = {new Texture("death.png"),new Texture("capsule.png"),new Texture("nose.png"),new Texture("rosca.png"),new Texture("ufo.png")};
	
	//Valores de las naves
	public static final int[] SHIPS_RADIUS = {25,20,10,30,20};
	public static final int[] SHIPS_MASS = {5,3,1,2,3};
	public static final int[] SHIPS_MAX_VEL = {300,325,500,400,350};
	public static final int[] SHIPS_ACCEL = {15,20,20,20,20};
	public static final int[] SHIPS_LIVES = {6,5,3,4,5};
	
	//PowerUps
	public static final Texture EXTRALIVESIMG = new Texture("extravida.png");
	public static final Texture EXTRAMASSIMG = new Texture("extramasa.png");
	public static final Texture INVENCIBILITYIMG = new Texture("invencibilidad.png");
	
	//Miscellaneous 
	public static final Texture HEART = new Texture("heart.png");
	public static final Texture COOLDOWN = new Texture("cooldown.png");
	public static final Texture EXPLOSION = new Texture("explosion.png");
	public static final Texture[] PROPULSORS = {new Texture("propulsorUp.png"),new Texture("propulsorDown.png"),
												new Texture("propulsorLeft.png"), new Texture("propulsorRight.png")};
	//Fonts
	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
	public static final BitmapFont SMALL_FONT = new BitmapFont(Gdx.files.internal("little.fnt"));
	
	//HighScore
	private static Preferences prefs = Gdx.app.getPreferences("Asteroids"); 
	public static void setHighScore(float val) {
	    prefs.putFloat("highScore", val);
	    prefs.flush();
	}
	public static float getHighScore() {
	    return prefs.getFloat("highScore");
	}	
}