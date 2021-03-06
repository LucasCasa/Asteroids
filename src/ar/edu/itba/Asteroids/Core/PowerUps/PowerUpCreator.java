package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.RandFunctions;

import com.badlogic.gdx.math.Vector2;

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
		int r = RandFunctions.generateRandInt(0, 4);
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
			case 3:	p = new ExtraAcelPowerUp(pos);
					pUI = new PowerUpUI(p, Assets.EXTRAACELIMG);
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
		return new Vector2((float)(((Constants.VIRTUAL_WIDTH - 2*PowerUp.getPowerUpRadius())) * Math.random())+PowerUp.getPowerUpRadius(),(float)( (Constants.VIRTUAL_HEIGHT-2*PowerUp.getPowerUpRadius()) * Math.random())+PowerUp.getPowerUpRadius());
	}
	
}
