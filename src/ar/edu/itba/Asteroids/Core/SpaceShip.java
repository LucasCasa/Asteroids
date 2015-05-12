package ar.edu.itba.Asteroids.Core;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;



public class SpaceShip implements Logical {
	private Vector2 position= new Vector2();
	private float radius;
	private int mass;
	private Vector2 vel = new Vector2(0,0);
	private int maxVel;
	private Vector2 acel = new Vector2(0,0);
	private int acelModifier;
	private int lives;
	private boolean invincible;
	private float time; //time of invincibility
	private float cont;

	/**
	 * @param position ; the initial position of the SpaceShip
	 * @param radius ; the initial radius of the SpaceShip
	 * @param mass ; the initial mass of the SpaceShip
	 * @param vel ; the max velocity of the SpaceShip
	 * @param acel ; the acceleration of the SpaceShip when a key is press
	 */
	public SpaceShip(float x, float y,float radius, int mass, int vel, int acel,int lives) throws IllegalArgumentException {
		if(radius<=0 || mass<=0 || lives<=0) {
			throw new IllegalArgumentException();
		}
		this.radius=radius;
		this.mass=mass;
		this.maxVel=vel;
		this.acelModifier=acel;
		this.position.x=x;
		this.position.y=y;
		this.lives=lives;
		this.invincible=false;
		this.cont=0;
	}

	/* Esto deja muy desprotegido a acel, ademas de que asi no tendria
	 * que funcionar 
	 * public void changeAcceleration(float x, float y){
		acel.x=x;
		acel.y=y;
	}
	 */
	/* Desde afuera no se cambia la velocidad, lo hace la nave misma
	 *  public void changeVelocity(float x, float y){
		this.vel.x=x;
		this.vel.y=y;
	}
	 */
	
		public void acelUp(boolean b){
			if(b){
				acel.y+= acelModifier;
			}else{
				acel.y-= acelModifier;
			}
		}
		public void acelDown(boolean b){
			if(!b){
				acel.y+= acelModifier;
			}else{
				acel.y-= acelModifier;
			}
		}
		public void acelLeft(boolean b){
			if(b){
				acel.x-= acelModifier;
			}else{
				acel.x+= acelModifier;
			}
		}
		public void acelRight(boolean b){
			if(!b){
				acel.x-= acelModifier;
			}else{
				acel.x+= acelModifier;
			}
		}
		
		private void updateVelocity(){
			if(Math.abs(vel.x) < maxVel || vel.x * acel.x <= 0){
			vel.x += acel.x;
			}
			if(Math.abs(vel.y) < maxVel || vel.y * acel.y <= 0){
			vel.y += acel.y;
			}
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
		position.x+= vel.x * Gdx.graphics.getDeltaTime();
		position.y+= vel.y * Gdx.graphics.getDeltaTime();
		updateVelocity();
		if(position.x + radius > Gdx.graphics.getWidth() || position.x - radius < 0){
			if(position.x -radius < 0){
				position.x = radius + 1;
			}else{
				position.x = Gdx.graphics.getWidth() - radius;
			}
			
			vel.x*=-0.5; // choque inelastico;
		}
		if(position.y + radius > Gdx.graphics.getHeight() || position.y - radius < 0){
			if(position.y -radius < 0){
				position.y = radius + 1;
			}else{
				position.y = Gdx.graphics.getHeight() - radius;
			}
			vel.y*=-0.5; // choque inelastico;
		}
	}
}
