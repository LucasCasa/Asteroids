package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {

	//Asteroides
	public static final Texture ASTEROID = new Texture("aster.png");
	
	//Naves
	public static final Texture[] SHIPS = {new Texture("death.png"),new Texture("capsule.png"),new Texture("nose.png"),new Texture("rosca.png"),new Texture("ufo.png")};
	
	//PowerUps
	public static final Texture EXTRALIVESIMG = new Texture("extravida.png");
	public static final Texture EXTRAMASSIMG = new Texture("extramasa.png");
	public static final Texture INVENCIBILITYIMG = new Texture("invencibilidad.png");
	
	//Miscellaneous 
	public static final Texture HEART = new Texture("heart.png");
	public static final Texture COOLDOWN = new Texture("cooldown.png");
	public static final Texture EXPLOSION = new Texture("explosion.png");
	
	//Fonts
	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
	public static final BitmapFont SMALL_FONT = new BitmapFont(Gdx.files.internal("little.fnt"));
}