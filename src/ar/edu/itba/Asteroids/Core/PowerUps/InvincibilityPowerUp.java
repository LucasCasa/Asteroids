package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.math.Vector2;

public class InvincibilityPowerUp extends PowerUp{

	private final float time = 5.0f;
	
	public InvincibilityPowerUp(Vector2 pos) {
		super(pos);
	}

	public void effect(SpaceShip s) {
		s.setInvincible(time);
	}

}

