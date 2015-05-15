package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

public class InvincibilityPowerUp extends PowerUp{

	private final float time = 5.0f;
	
	public InvincibilityPowerUp(float x, float y, float radius) {
		super(x, y, radius);
	}

	public void effect(SpaceShip s) {
		s.setInvincible(time);
	}

}
