package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.math.Vector2;

public class ExtraAcelPowerUp extends PowerUp{

	private static float extraAcelTime = 10;
	private static int acelModifier = 2; 
	
	public ExtraAcelPowerUp(Vector2 pos) {
		super(pos);
	}

	@Override
	public void effect(SpaceShip s) {
		s.extraAcel(acelModifier*s.getOriginalAcel(), extraAcelTime);
	}

}
