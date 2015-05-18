package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.math.Vector2;

public class ExtraLifePowerUp extends PowerUp{

	public ExtraLifePowerUp(Vector2 pos) {
		super(pos);
	}

	public void effect(SpaceShip s) {
		s.addLives(1);
	}
}

