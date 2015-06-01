package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.math.Vector2;
/**
 * Abstract class. superclass of all the object that collide in the game
 *
 */
public abstract class Collisionable {
	private Vector2 cPosition;
	private Vector2 Position;
	private Vector2 speed;
	private int radius;
	private float mass;
	private Vector2 collisionPoint;
	private boolean collision = false;
	
	public Collisionable(Vector2 cPos,Vector2 speed,float mass,int radius){
		cPosition = cPos;
		this.speed = speed;
		this.mass = mass;
		this.radius = (int)(radius);
		Position = new Vector2(cPos.x - radius, cPos.y - radius);
		collisionPoint = new Vector2();
		
	}
	/**
	 * Checks if there is a collision between this and the other
	 * @return true if collision
	 */
	public boolean collision(Collisionable o){
		float dist = (float)(Math.pow(o.cPosition.x - cPosition.x,2) + Math.pow(o.cPosition.y - cPosition.y, 2));
		if(dist <= Math.pow(radius + o.getRadius(),2)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * sets the new velocity of this and the other object after a collision
	 * @param o; the other object that collide
	 */
	public void newVel(Collisionable o,float deltaTime){
		collisionPoint.x = ((cPosition.x * o.radius) + (o.cPosition.x * radius)) / (radius + o.radius);		 
		collisionPoint.y = ((cPosition.y * o.radius) + (o.cPosition.y * radius)) / (radius + o.radius);
		float xDist = getCPos().x - o.getCPos().x;
		float yDist = getCPos().y - o.getCPos().y;
		float xVel = o.speed.x - speed.x;
		float yVel = o.speed.y - speed.y;
		float dotProduct = xDist*xVel + yDist*yVel;
		if(dotProduct > 0){ // si los objetos se estan alejando no colisiona !!! 
		float newVelX =  (speed.x * (mass - o.mass) + (2 * o.mass * o.speed.x)) / (mass + o.mass);
		float newVelY1 = (speed.y * (mass - o.mass) + (2 * o.mass * o.speed.y)) / (mass + o.mass);
		float newVelX2 =  (o.speed.x * (o.mass - mass) + (2 * mass * speed.x)) / (o.mass + mass);
		float newVelY2 = (o.speed.y * (o.mass - mass) + (2 * mass * speed.y)) / (o.mass + mass);
		speed.x = newVelX;
		speed.y = newVelY1;
		o.speed.x = newVelX2;
		o.speed.y = newVelY2;
		cPosition.x+= speed.x * deltaTime;
		cPosition.y+= speed.y * deltaTime;
		o.cPosition.x+= o.speed.x* deltaTime;
		o.cPosition.y+= o.speed.y* deltaTime;
		}
	}
	/**
	 * checks if the object is going out of screen
	 */
	public void checkOutOfScreen(){
		if(cPosition.x + radius > Constants.VIRTUAL_WIDTH || cPosition.x - radius < 0){
			if(cPosition.x -radius < 0){
				cPosition.x = radius + 1;
			}else{
				cPosition.x = Constants.VIRTUAL_WIDTH - radius;
			}
			
			speed.x*= -0.5f;
		}
		if(cPosition.y + radius > Constants.VIRTUAL_HEIGHT || cPosition.y - radius < 0){
			if(cPosition.y -radius < 0){
				cPosition.y = radius + 1;
			}else{
				cPosition.y = Constants.VIRTUAL_HEIGHT - radius - 1;
			}
			
			speed.y*= -0.5f;
		}
		
	}
	public float getMass(){
		return mass;
	}
	public void setMass(float mass){
		this.mass = mass;
	}
	public Vector2 getCPos(){
		return cPosition;
	}
	protected Vector2 getSpeed(){
		return speed;
	}
	public Vector2 getPosition(){
		return Position.set(cPosition.x - radius, cPosition.y - radius);
	}
	public int getRadius() {
		return radius;
	}
	public Vector2 getCollisionPoint(){
		return collisionPoint;
	}
	public boolean getCollision(){
		return collision;
	}
	public void setCollision(boolean b){
		collision = b;
	}
}
