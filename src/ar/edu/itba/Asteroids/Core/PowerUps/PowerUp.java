package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.Collisionable;
import ar.edu.itba.Asteroids.Core.Logical;

import com.badlogic.gdx.math.Vector2;

public abstract class PowerUp extends Collisionable implements Logical{

	private static final int radius = 20;

	/**
	 * 
	 * @param x; position in the x component
	 * @param y; position in the y component
	 * @param radius; radius of the powerup
	 */
	public PowerUp(Vector2 pos){
		super(pos, new Vector2(0,0), 0, radius);
	}
	
	public void update(float deltaTime){
	}
	
	public abstract void effect(SpaceShip s);
	
	public static int getPowerUpRadius(){
		return radius;
	}

}
