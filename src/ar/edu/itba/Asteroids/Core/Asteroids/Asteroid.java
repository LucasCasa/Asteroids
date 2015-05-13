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
	
	/**
	 * 
	 * @param a; position in the x component
	 * @param b; position in the y component
	 * @param velx; velocity in the x component
	 * @param vely; velocity in the y component
	 * @param mass; mass of the asteroid
	 */
	public Asteroid(float a, float b,float velx, float vely,float mass){
		super(new Vector2(a,b), new Vector2(velx,vely), mass,15); 
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
}

