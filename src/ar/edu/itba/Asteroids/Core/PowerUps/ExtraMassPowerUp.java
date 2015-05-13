package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

public class ExtraMassPowerUp extends PowerUp{

	public ExtraMassPowerUp(float x, float y, float radius) {
		super(x, y, radius);
	}

	public void effect(SpaceShip s) {
		//s.addMass());
	}
	

}
