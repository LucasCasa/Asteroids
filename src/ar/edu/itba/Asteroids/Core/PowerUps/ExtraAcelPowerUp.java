package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.math.Vector2;

public class ExtraAcelPowerUp extends PowerUp{

	private static float extraAcelTime = 10;
	private static int addedAcel = 1000; 
	
	public ExtraAcelPowerUp(Vector2 pos) {
		super(pos);
	}

	@Override
	public void effect(SpaceShip s) {
		s.extraAcel(addedAcel, extraAcelTime);
	}

}
