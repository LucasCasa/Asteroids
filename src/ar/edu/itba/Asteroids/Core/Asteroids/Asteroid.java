package ar.edu.itba.Asteroids.Core.Asteroids;

import ar.edu.itba.Asteroids.Core.Collisionable;
import ar.edu.itba.Asteroids.Core.Logical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Asteroid extends Collisionable implements Logical{
	ShapeRenderer shape = new ShapeRenderer();
	Texture t = new Texture("aster.png");
	private static final int minimumRadius = 10;
	private static final int maxRadiusVar = 40;
	
	/**
	 * www
	 * @param a; position in the x component
	 * @param b; position in the y component
	 * @param velx; velocity in the x component
	 * @param vely; velocity in the y component
	 * @param mass; mass of the asteroid
	 */
	public Asteroid(float a, float b,float velx, float vely,float mass){
		super(new Vector2(a,b), new Vector2(velx,vely), mass,(int)((minimumRadius+maxRadiusVar*Math.random()))); 
	}
	
	public void update(){
		getCPos().x+= getSpeed().x * Gdx.graphics.getDeltaTime();
		getCPos().y+= getSpeed().y * Gdx.graphics.getDeltaTime();
	}
	public void collision(Asteroid o){
		if(Collision(o)){
			newVel(o);
		}
	}
	public boolean outOfScreen(){
		if(this.getCPos().x + super.getRadius() < 0 || this.getCPos().x - super.getRadius() > Gdx.graphics.getWidth()){
			return true;
		}
		if(this.getCPos().y + super.getRadius() < 0 || this.getCPos().y - super.getRadius() > Gdx.graphics.getHeight()){
			return true;
		}
		return false;
	}
}

