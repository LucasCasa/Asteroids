package ar.edu.itba.Asteroids.Core;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;



public class SpaceShip implements Logical {
	private Vector2 position= new Vector2();
	private float radius;
	private int mass;
	private Vector2 vel;
	private Vector2 acel;
	private int lives;
	private boolean invincible;
	private float time; //time of invincibility
	private float cont;

	/**
	 * @param position ; the initial position of the SpaceShip
	 * @param radius ; the initial radius of the SpaceShip
	 * @param mass ; the initial mass of the SpaceShip
	 * @param vel ; the initial velocity of the SpaceShip
	 * @param acel ; the initial acceleration of the SpaceShip
	 */
	public SpaceShip(float x, float y,float radius, int mass, Vector2 vel, Vector2 acel,int lives) throws IllegalArgumentException {
		if(radius<=0 || mass<=0 || lives<=0) {
			throw new IllegalArgumentException();
		}
		this.radius=radius;
		this.mass=mass;
		this.vel=vel;
		this.acel=acel;
		this.position.x=x;
		this.position.y=y;
		this.lives=lives;
		this.invincible=false;
		this.cont=0;
	}

	public void changeAcceleration(float x, float y){
		acel.x=x;
		acel.y=y;
	}

	public void changeVelocity(float x, float y){
		this.vel.x=x;
		this.vel.y=y;
	}

	public void damage(int amount){
		if(!this.invincible){
			this.lives-=amount;
		}
	}

	/**
	 * Increases the amount of lives that the SpaceShip is going to have
	 * @param amount
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
	
	public float getRadius(){
		return this.radius;
	}
	
	public float getPosX(){
		return this.position.x;
	}
	
	public float getPosY(){
		return this.position.y;
	}
	
	public Vector2 getVel(){
		return this.vel;
	}
	
	public Vector2 getacel(){
		return this.vel;
	}
	
	@Override
	public void update() {
		cont+=Gdx.graphics.getDeltaTime();
		if(this.invincible == true && time<cont){
			this.invincible=false;
		}
		position.x+= vel.x;
		position.y+= vel.y;
		vel.x += acel.x;
		vel.y += acel.y;
		if(position.x + radius > Gdx.graphics.getWidth() || position.x - radius < 0){
			if(position.x -radius < 0){
				position.x = radius + 1;
			}else{
				position.x = Gdx.graphics.getWidth() - radius;
			}
			
			vel.x*=-1;
		}
	}
}
