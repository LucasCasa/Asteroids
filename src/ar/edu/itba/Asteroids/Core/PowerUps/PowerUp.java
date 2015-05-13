package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.math.Vector2;

public abstract class PowerUp{

	private Vector2 pos;
	private float radius;


	public PowerUp(float x, float y, float radius){
		pos = new Vector2(x,y);
		this.radius = radius;
	}
	
	public abstract void effect(SpaceShip s);
	
	public Vector2 getPos() {
		return pos;
	}

	public float getRadius() {
		return radius;
	}

}
