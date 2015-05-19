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
	private int startingLives;
	private boolean invincible;
	private float time; //time of invincibility
	private float cont;
	private boolean accelerating[] = {false,false,false,false};
	private boolean active = true;
	private Vector2 initialPos;
	
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
		initialPos = new Vector2(x,y);
		this.maxVel=vel;
		this.acelModifier=acel;
		this.lives=lives;
		startingLives = lives;
		this.invincible=false;
		this.cont=0;
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
			setCollision(b);
			if( o instanceof Asteroid){
				this.damage(1);
				newVel(o);
			}else{
				newVel(o);
			}
		}
		return b;
	}

	/**
	 *  Define si tiene que acelerar positivamente en el eje Y
	 * 
	 * @param b true si tiene que acelerar positiviamente 
	 */
	public void acelUp(boolean b){
		if(b != accelerating[0]){
			if(b){
				acel.y+= acelModifier;
				accelerating[0] = true;
			}else{
				acel.y-= acelModifier;
				accelerating[0] = false;
			}
		}
	}
	/**
	 * define si tiene que acelerar negativamente en el eje Y
	 * 
	 * @param b true si acelera negativamente
	 */
	public void acelDown(boolean b){
		if(b != accelerating[1]){
			if(!b){
				acel.y+= acelModifier;
				accelerating[1] = false;
			}else{
				accelerating[1] = true;
				acel.y-= acelModifier;
			}
		}
	}
	/**
	 * define si tiene que acelerar negativamente en el eje X
	 * 
	 * @param b true si acelera negativamente
	 */
	public void acelLeft(boolean b){
		if(b != accelerating[2]){
			if(b){
				accelerating[2] = true;
				acel.x-= acelModifier;
			}else{
				accelerating[2] = false;
				acel.x+= acelModifier;
			}
		}
	}
	/**
	 * define si tiene que acelerar positivamente en el eje X
	 * 
	 * @param b true si acelera positivamente
	 */
	public void acelRight(boolean b){
		if(b != accelerating[3]){
			if(!b){
				accelerating[3] = false;
				acel.x-= acelModifier;
			}else{
				accelerating[3] = true;
				acel.x+= acelModifier;
			}
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

	
	public boolean[] getAccelerating(){
		return accelerating;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean b){
		active = b;
	}

	public void reset() {
		lives = startingLives;
		getCPos().set(initialPos.x,initialPos.y);
		getSpeed().set(0, 0);
		accelerating[0] = false;
		accelerating[1] = false;
		accelerating[2] = false;
		accelerating[3] = false;
	
		acel.x = 0;
		acel.y = 0;
		invincible = false;
		active = true;
		
		
	}
}
