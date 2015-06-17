package ar.edu.itba.Asteroids.Core.SpaceShips;


import ar.edu.itba.Asteroids.Core.Collisionable;
import ar.edu.itba.Asteroids.Core.Logical;
import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;

import com.badlogic.gdx.math.Vector2;
 /**
  * the SpaceShip class. it handles all the logic of the spaceShip
  *
  */


public class SpaceShip extends Collisionable implements Logical {
	private int maxVel;
	private Vector2 acel = new Vector2(0,0);
	private int acelModifier;
	private int lives;
	private int startingLives;
	private boolean extraAcel;
	private boolean invincible;
	private boolean active = true;
	private Vector2 initialPos;
	private Timer inviTimer = new Timer();
	private Timer extraAcelTimer = new Timer();
	private float invincibleTotalTime;
	private float extraAcelTotalTime;
	private int originalAcel;
	
	private boolean acelup = false;
	private boolean aceldown = false;
	private boolean acelright = false;
	private boolean acelleft = false;
	
	/**
	 * 
	 * @param x; initial position, in the x component
	 * @param y; initial position, in the y component
	 * @param radius; radius of the SpaceShip
	 * @param mass; mass of the spaceShip
	 * @param maxvel; maximum velocity of the spaceShip
	 * @param acel; initial acceleration of the spaceShip
	 * @param lives; initial amount of lives that the spaceShip has
	 */
	public SpaceShip(float x, float y,int radius, int mass, int maxvel, int acel,int lives){
		super(new Vector2(x,y), new Vector2(0,0),mass, radius);
		initialPos = new Vector2(x,y);
		this.maxVel=maxvel;
		this.acelModifier=acel;
		this.originalAcel=acel;
		this.lives=lives;
		startingLives = lives;
		this.invincible=false;

	}

	/**
	 * does all the logic that the ship has to do in every cycle.
	 */
	@Override
	public void update(float deltaTime) {
		updateAcel();
		updateVelocity(deltaTime);
		getCPos().x+= getSpeed().x * deltaTime;
		getCPos().y+= getSpeed().y * deltaTime;
		checkOutOfScreen();
		updateInvincibility(deltaTime);
		updateExtraAcel(deltaTime);
	}
	
	public Timer getInviTimer() {
		return inviTimer;
	}
	
	public void updateAcel(){
		if(acelup == true){
			if(aceldown == true)
				acel.y = 0;
			else
				acel.y = acelModifier;
		}
		else{
			if(aceldown == true)
				acel.y = -acelModifier;
			else
				acel.y = 0;
		}
		
		if(acelright == true){
			if(acelleft == true)
				acel.x = 0;
			else
				acel.x = acelModifier;
		}
		else{
			if(acelleft == true)
				acel.x = -acelModifier;
			else
				acel.x = 0;
		}
	}

	public float getInvincibleTotalTime() {
		return invincibleTotalTime;
	}
	/**
	 * updates the invisibility timer and checks whether the invisibility time is over or not.
	 */
	private void updateInvincibility(float deltaTime) {
		if(this.invincible == true){
			inviTimer.update(deltaTime);
			if(inviTimer.getTime()>invincibleTotalTime){
				this.invincible = false;
			}
		}
	}
	private void updateExtraAcel(float deltaTime){
		if(this.extraAcel == true){
			extraAcelTimer.update(deltaTime);
			if(extraAcelTimer.getTime()>extraAcelTotalTime){
				this.extraAcel = false;
				this.acelModifier = this.originalAcel;
			}
		}
	}
	/**
	 * checks if the two object collide with each other.
	 * this method is called when the "o" is not an Asteroid.
	 * 
	 * @param o the other object
	 * @param deltaTime time since last frame
	 * 
	 * @return true if there is collision, false if not
	 * 
	 */
	public boolean shipCollision(Collisionable o,float deltaTime){
		boolean b = collision(o); 
		if(b){
			setCollision(b);
				newVel(o,deltaTime);
		}
		return b;
	}
	/**
	 * checks if the two objects collide with each other.
	 * if there is a collision then the spaceship is damaged.
	 * 
	 * @param o an Asteroid
	 * @param deltaTime time since last frame
	 * 
	 * @return true if collision, false if not
	 */
	public boolean shipCollision(Asteroid o, float deltaTime){
		boolean b = collision(o);
		if(b){
			setCollision(b);
			this.damage(Asteroid.damageToShips);
			newVel(o,deltaTime);
		}
		return b;
	}
	
	/**
	 *  Defines if there should be positive acceleration in the Y axis
	 * 
	 * @param b true if there is positive acceleration in the Y axis.
	 */
	public void acelUp(boolean b){
		acelup = b;
	}
	/**
	 * Defines if there should be negative acceleration in the Y axis
	 * 
	 * @param b true if there is negative acceleration in the Y axis.
	 */
	public void acelDown(boolean b){
		aceldown = b;
	}
	/**
	 * Defines if there should be negative acceleration in the X axis
	 * 
	 * @param b true if there is negative acceleration in the X axis.
	 */
	public void acelLeft(boolean b){
		acelleft = b;
	}
	/**
	 * Defines if there should be positive acceleration in the X axis
	 * 
	 * @param b true if there is positive acceleration in the X axis.
	 */
	public void acelRight(boolean b){
		acelright = b;
	}
	/**
	 * updates the velocity checking if it can
	 */
	private void updateVelocity(float deltaTime){
		
		addSpeedX(acel.x * deltaTime);
		addSpeedY(acel.y * deltaTime);
	}
	protected void addSpeedX(float amount){
		if(Math.abs(getSpeed().x) < maxVel || getSpeed().x * acel.x <= 0){
			if(Math.abs(getSpeed().x + amount) < maxVel) // If it doesn't reach maximum speed, it just adds
				super.addSpeedX(amount);
			else  // It adds whatever is left until maximum speed is reached
				super.addSpeedX(Math.signum(getSpeed().x)*(maxVel - Math.abs(getSpeed().x)));
		}	
	}
	protected void addSpeedY(float amount){
		if(Math.abs(getSpeed().y) < maxVel || getSpeed().y * acel.y <= 0){
			if(Math.abs(getSpeed().y + amount) < maxVel)
				super.addSpeedY(amount);
			else
				super.addSpeedY(Math.signum(getSpeed().y)*(maxVel - Math.abs(getSpeed().y)));
		}
	}
	public void damage(int amount){
		if(!this.invincible){
			this.lives-=amount;
		}
	}
	
	public void extraAcel(int amount, float time){
		this.extraAcel = true;
		this.extraAcelTimer.reset();
		this.extraAcelTotalTime = time;
		this.acelModifier = amount;
	}
	
	public void addMass(float amount){
		super.setMass(super.getMass() + amount);
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
		this.inviTimer.reset();
		this.invincibleTotalTime=time;	
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
	
	public int getOriginalAcel(){
		return this.originalAcel;
	}
	
	public Timer getExtraAcelTimer() {
		return extraAcelTimer;
	}

	public float getExtraAcelTotalTime() {
		return extraAcelTotalTime;
	}
	
	public Vector2 getAcel(){
		return acel;
	}
	
	public boolean getExtraAcel(){
		return extraAcel;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean b){
		active = b;
	}
	/**
	 * resets all the values of the class
	 */
	public void reset() {
		lives = startingLives;
		getCPos().set(initialPos.x,initialPos.y);
		resetSpeed();
	
		acel.x = 0;
		acel.y = 0;
		invincible = false;
		active = true;
	}
}
