package ar.edu.itba.Asteroids.Core.Asteroids;

import ar.edu.itba.Asteroids.Core.Collisionable;
import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.Logical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Asteroid extends Collisionable implements Logical{
	
	private static final float minRadius = 15.0f;
	private static final float radiusOffset = 10.0f;
	
	
	/**
	 * 
	 * @param a; position in the x component
	 * @param b; position in the y component
	 * @param velx; velocity in the x component
	 * @param vely; velocity in the y component
	 * @param mass; mass of the asteroid
	 */
	public Asteroid(float a, float b,float velx, float vely, float mass){
		super(new Vector2(a,b), new Vector2(velx,vely), mass, (int)(minRadius + radiusOffset * (mass - 1))); 
	}
	
	public void update(){
		getCPos().x+= getSpeed().x * Gdx.graphics.getDeltaTime();
		getCPos().y+= getSpeed().y * Gdx.graphics.getDeltaTime();
	}
	public boolean asteroidCollision(Asteroid o){
		boolean b =collision(o); 
		if(b){
			setCollision(b);
			newVel(o);
		}
		return b; 
	}
	public boolean outOfScreen(){
		if(this.getCPos().x + super.getRadius() < 0 || this.getCPos().x - super.getRadius() > Constants.VIRTUAL_WIDTH){
			return true;
		}
		if(this.getCPos().y + super.getRadius() < 0 || this.getCPos().y - super.getRadius() > Constants.VIRTUAL_HEIGHT){
			return true;
		}
		return false;
	}

}

