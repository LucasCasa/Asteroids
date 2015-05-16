package ar.edu.itba.Asteroids.Core.SpaceShips;


import ar.edu.itba.Asteroids.Core.Collisionable;
import ar.edu.itba.Asteroids.Core.Logical;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;



public class SpaceShip extends Collisionable implements Logical {
	private int maxVel;
	private Vector2 acel = new Vector2(0,0);
	private int acelModifier;
	private int lives;
	private boolean invincible;
	private float time; //time of invincibility
	private float cont;

	/**
	 * 
	 * @param x; initial position, in the x component
	 * @param y; initial position, in the y component
	 * @param radius; radius of the SpaceShip
	 * @param mass; mass of the spaceShip
	 * @param vel; maximum velocity of the spaceShip
	 * @param acel; initial acceleration of the spaceShip
	 * @param lives; initial amount of lives that the spaceShip has
	 */
	public SpaceShip(float x, float y,int radius, int mass, int vel, int acel,int lives){
		super(new Vector2(x,y), new Vector2(0,0),mass, radius);
		this.maxVel=vel;
		this.acelModifier=acel;
		this.lives=lives;
		this.invincible=false;
		this.cont=0;
	}

	/**
	 *  Define si tiene que acelerar positivamente en el eje Y
	 * 
	 * @param b true si tiene que acelerar positiviamente 
	 */
	public void acelUp(boolean b){
		if(b){
			acel.y+= acelModifier;
		}else{
			acel.y-= acelModifier;
		}
	}
	/**
	 * define si tiene que acelerar negativamente en el eje Y
	 * 
	 * @param b true si acelera negativamente
	 */
	public void acelDown(boolean b){
		if(!b){
			acel.y+= acelModifier;
		}else{
			acel.y-= acelModifier;
		}
	}
	/**
	 * define si tiene que acelerar negativamente en el eje X
	 * 
	 * @param b true si acelera negativamente
	 */
	public void acelLeft(boolean b){
		if(b){
			acel.x-= acelModifier;
		}else{
			acel.x+= acelModifier;
		}
	}
	/**
	 * define si tiene que acelerar positivamente en el eje X
	 * 
	 * @param b true si acelera positivamente
	 */
	public void acelRight(boolean b){
		if(!b){
			acel.x-= acelModifier;
		}else{
			acel.x+= acelModifier;
		}
	}

	private void updateVelocity(){
		if(Math.abs(getSpeed().x) < maxVel || getSpeed().x * acel.x <= 0){
			getSpeed().x += acel.x;
		}	
		if(Math.abs(getSpeed().y) < maxVel || getSpeed().y * acel.y <= 0){
			getSpeed().y += acel.y;
		}
	}

	public void damage(int amount){
		if(!this.invincible){
			this.lives-=amount;
		}
	}

	/**
	 * Increases the amount of lives that the SpaceShip is going to have
	 * @param amount the amount to increase
	 */
	public void addLives(int amount){
		this.lives+=amount;
	}

	/**
	 * Sets the time for which the SpaceShip is going to be invincible
	 * @param time
	 */
	public void setInvincible(float time){
		this.invincible=true;
		this.time=cont+time;	
	}

	public int getLives() {
		return this.lives;
	}

	public boolean getInvincible(){
		return this.invincible;
	}

	public Vector2 getacel(){
		return this.acel;
	}
	/**
	 * check whether the two object collide with each other.
	 * if the other object is a ship then it bounces.
	 * if the other object is an asteroid then it takes one life.
	 * 
	 * @param o the other object
	 * @return true if collision, false if not
	 * 
	 */
	public boolean shipCollision(Collisionable o){
		boolean b = collision(o); 
		if(b){
			if( o instanceof Asteroid){
				this.damage(1);
			}else{
				newVel(o);
			}
		}
		return b;
	}
	/**
	 * do all the logic that the ship has to do in every cycle.
	 */
	@Override
	public void update() {
		cont+=Gdx.graphics.getDeltaTime();
		if(this.invincible == true && time<cont){
			this.invincible=false;
		}
		getCPos().x+= getSpeed().x * Gdx.graphics.getDeltaTime();
		getCPos().y+= getSpeed().y * Gdx.graphics.getDeltaTime();
		updateVelocity();
		checkOutOfScreen();
	}
}
