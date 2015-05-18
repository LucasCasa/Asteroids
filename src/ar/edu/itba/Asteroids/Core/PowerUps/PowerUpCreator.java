package ar.edu.itba.Asteroids.Core.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;

public final class PowerUpCreator {
	
	private PowerUpCreator(){
		
	}
	
	/**
	 * Creates a random PowerUp in a random position
	 * @return Connector with the PowerUp and its UI
	 */
	public static Connector<PowerUp, PowerUpUI> create(){
		PowerUp p;
		PowerUpUI pUI;
		Vector2 pos = randomPosition();
		int r = generateRandInt(0, 3);
		switch(r){
			case 0: p = new ExtraLifePowerUp(pos);
					pUI = new PowerUpUI(p, Assets.EXTRALIVESIMG);
					break;
			case 1: p = new ExtraMassPowerUp(pos);
					pUI = new PowerUpUI(p, Assets.EXTRAMASSIMG);
					break;
			case 2: p = new InvincibilityPowerUp(pos);
					pUI = new PowerUpUI(p, Assets.INVENCIBILITYIMG);
					break;
			default:p = null;
					pUI = null;
					break;
		}
		return new Connector<PowerUp, PowerUpUI>(p, pUI);	
	}
	
	/**
	 * Returns a random position within the game screen
	 * @return Vector with the random x and y positions
	 */
	private static Vector2 randomPosition(){
		return new Vector2((float)(Gdx.graphics.getWidth() * Math.random()),(float)( Gdx.graphics.getHeight() * Math.random()));
	}
	
	/**
	 * Generates a random int from within the interval provided
	 * @param min; Minimum value INCLUSIVE
	 * @param max; Maximum value EXCLUSIVE
	 * @return Returns the random int
	 */
	private static int generateRandInt(int min, int max){
		int n = max;
		while(n == max)
			n = (int) Math.floor(Math.random()*max);
		return n;
	}
}
