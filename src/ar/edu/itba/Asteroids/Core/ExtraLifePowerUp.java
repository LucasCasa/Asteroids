package ar.edu.itba.Asteroids.Core;

public class ExtraLifePowerUp extends PowerUp{

	public ExtraLifePowerUp(float x, float y, float radius) {
		super(x,y,radius);
	}

	public void effect(SpaceShip s) {
		s.addLives(1);
	}
}

