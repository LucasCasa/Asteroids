package ar.edu.itba.Asteroids.Core.Asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class AsteroidThrower {
	private static AsteroidThrower self;
	private float velFactor = 1;
	
	private AsteroidThrower(){
		
	}
	
	public static AsteroidThrower getInstance(){
		if(self == null){
			self = new AsteroidThrower();
		}
		return self;
	}
	/**
	 * This method calculates the velocity and the mass of the new asteroid
	 * @param x1, the x-component of the asteroid being thrown
	 * @param y1, the y-component of the asteroid being thrown
	 * @param x1, the x-component of the spaceship
	 * @param y1, the y-component of the spaceship
	 * @return a new asteroid aiming to the spaceship
	 */
	private Asteroid throwAsteroid(float x1, float y1, float x2, float y2){

		float ady = x2 - x1;
		float op = y2 - y1;
		float hip = Vector2.dst(x1,y1,x2,y2);
		float cos_alfa = ady/hip;
		float sin_alfa = op/hip;
		float vel = 200*velFactor;
		
		float mass = (float)Math.random() * 1 + 1;
		float velx = vel*cos_alfa;
		float vely = vel*sin_alfa;
		return new Asteroid(x1, y1, velx, vely, mass);
	}
	
	public void setVelFactor(float factor){
		this.velFactor = factor;
	}
	
	public Asteroid throwBottomRight(Vector2 pos){
		float x;
		float y;
		
		if (Math.random() > 0.5){
			x = (float) (Gdx.graphics.getWidth() -  Math.random()*(Gdx.graphics.getWidth()/4));
			y = 0;
		}else{
			x = Gdx.graphics.getWidth();
			y = (float) (Math.random()*(Gdx.graphics.getHeight()/4));;
		}
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwBottomMiddle(Vector2 pos){
		float x;
		float y;

		x = (float) (Gdx.graphics.getWidth()/4 + Math.random()*(Gdx.graphics.getWidth()/2));
		y = 0;
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwBottomLeft(Vector2 pos){
		float x;
		float y;
		
		if (Math.random() > 0.5){
			x = (float) (Math.random()*(Gdx.graphics.getWidth()/4));
			y = 0;
		}else{
			x = 0;
			y = (float) (Math.random()*(Gdx.graphics.getHeight()/4));;
		}
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwUpperRight(Vector2 pos){
		float x;
		float y;

		if (Math.random() > 0.5){
			x = (float) (Gdx.graphics.getWidth() -  Math.random()*(Gdx.graphics.getWidth()/4));
			y = Gdx.graphics.getHeight();
		}else{
			x = Gdx.graphics.getWidth();
			y = (float) (Gdx.graphics.getHeight() - Math.random()*(Gdx.graphics.getHeight()/4));
		}
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwUpperMiddle(Vector2 pos){
		float x;
		float y;

		x = (float) (Gdx.graphics.getWidth()/4 + Math.random()*(Gdx.graphics.getWidth()/2));
		y = Gdx.graphics.getHeight();
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwUpperLeft(Vector2 pos){
		float x;
		float y;
		
		if (Math.random() > 0.5){
			x = (float) (Math.random()*(Gdx.graphics.getWidth()/4));
			y = Gdx.graphics.getHeight();
		}else{
			x = 0;
			y = (float) (Gdx.graphics.getHeight() - Math.random()*(Gdx.graphics.getHeight()/4));
		}
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwLeft(Vector2 pos){
		float x;
		float y;

		x = 0;
		y = (float) (Gdx.graphics.getHeight()/4 + Math.random()*(Gdx.graphics.getHeight()/2));
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}
	
	public Asteroid throwRight(Vector2 pos){
		float x;
		float y;

		x = Gdx.graphics.getWidth();
		y = (float) (Gdx.graphics.getHeight()/4 + Math.random()*(Gdx.graphics.getHeight()/2));
		
		return throwAsteroid(x, y, pos.x, pos.y);
	}	
}