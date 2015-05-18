package ar.edu.itba.Asteroids.Core.Asteroids;

import java.util.ArrayList;

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
	 * @param x2, the x-component of the spaceship
	 * @param y2, the y-component of the spaceship
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
	
	public ArrayList<Asteroid> throwBottomRight(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			if (Math.random() > 0.5){
				x = (float) (Gdx.graphics.getWidth() -  Math.random()*(Gdx.graphics.getWidth()/4));
				y = 0;
			}else{
				x = Gdx.graphics.getWidth();
				y = (float) (Math.random()*(Gdx.graphics.getHeight()/4));;
			}	

			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;


	}

	public ArrayList<Asteroid> throwBottomMiddle(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			x = (float) (Gdx.graphics.getWidth()/4 + Math.random()*(Gdx.graphics.getWidth()/2));
			y = 0;
			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}
	public ArrayList<Asteroid> throwBottomLeft(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			if (Math.random() > 0.5){
				x = (float) (Math.random()*(Gdx.graphics.getWidth()/4));
				y = 0;
			}else{
				x = 0;
				y = (float) (Math.random()*(Gdx.graphics.getHeight()/4));;
			}
			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}

	public ArrayList<Asteroid> throwUpperRight(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			if (Math.random() > 0.5){
				x = (float) (Gdx.graphics.getWidth() -  Math.random()*(Gdx.graphics.getWidth()/4));
				y = Gdx.graphics.getHeight();
			}else{
				x = Gdx.graphics.getWidth();
				y = (float) (Gdx.graphics.getHeight() - Math.random()*(Gdx.graphics.getHeight()/4));
			}
			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}

	public ArrayList<Asteroid> throwUpperMiddle(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			x = (float) (Gdx.graphics.getWidth()/4 + Math.random()*(Gdx.graphics.getWidth()/2));
			y = Gdx.graphics.getHeight();

			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}

	public ArrayList<Asteroid> throwUpperLeft(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			if (Math.random() > 0.5){
				x = (float) (Math.random()*(Gdx.graphics.getWidth()/4));
				y = Gdx.graphics.getHeight();
			}else{
				x = 0;
				y = (float) (Gdx.graphics.getHeight() - Math.random()*(Gdx.graphics.getHeight()/4));
			}
			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}

	public ArrayList<Asteroid> throwLeft(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			x = 0;
			y = (float) (Gdx.graphics.getHeight()/4 + Math.random()*(Gdx.graphics.getHeight()/2));

			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}

	public ArrayList<Asteroid> throwRight(ArrayList<Vector2> pos){
		float x;
		float y;
		ArrayList<Asteroid> aster = new ArrayList<>();
		for(Vector2 ShipPos : pos){
			x = Gdx.graphics.getWidth();
			y = (float) (Gdx.graphics.getHeight()/4 + Math.random()*(Gdx.graphics.getHeight()/2));
			
			aster.add(throwAsteroid(x, y, ShipPos.x, ShipPos.y));
		}
		return aster;
	}	
}