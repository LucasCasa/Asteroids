package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class Collisionable {
	private Vector2 cPosition;
	private Vector2 Position;
	private Vector2 speed;
	private int radius;
	private float mass;
	
	public Collisionable(Vector2 cPos,Vector2 speed,float mass,int radius){
		cPosition = cPos;
		this.speed = speed;
		this.mass = mass;
		this.radius = radius;
		Position = new Vector2(cPos.x - radius, cPos.y - radius);
	}
	public boolean Collision(Collisionable o){
		float dist = (float)(Math.pow(o.cPosition.x - cPosition.x,2) + Math.pow(o.cPosition.y - cPosition.y, 2));
		if(dist <= 4 * radius * radius){
			return true;
		}else{
			return false;
		}
	}
	public void newVel(Collisionable o){
		/*
		float collisionPointX = ((cPosition.x * o.radius) + (o.cPosition.x * radius)) / (radius + o.radius);		 
		float collisionPointY = ((cPosition.y * o.radius) + (o.cPosition.y * radius)) / (radius + o.radius);
		*/
		
		float newVelX =  (speed.x * (mass - o.mass) + (2 * o.mass * o.speed.x)) / (mass + o.mass);
		float newVelY1 = (speed.y * (mass - o.mass) + (2 * o.mass * o.speed.y)) / (mass + o.mass);
		float newVelX2 =  (o.speed.x * (o.mass - mass) + (2 * mass * speed.x)) / (o.mass + mass);
		float newVelY2 = (o.speed.y * (o.mass - mass) + (2 * mass * speed.y)) / (o.mass + mass);
		speed.x = newVelX;
		speed.y = newVelY1;
		cPosition.x+= speed.x;
		cPosition.y+= speed.y;
		o.speed.x = newVelX2;
		o.speed.y = newVelY2;
		o.cPosition.x+= o.speed.x;
		o.cPosition.y+= o.speed.y;
	}
	public void checkOutOfScreen(){
		if(cPosition.x + radius > Gdx.graphics.getWidth() || cPosition.x - radius < 0){
			if(cPosition.x -radius < 0){
				cPosition.x = radius + 1;
			}else{
				cPosition.x = Gdx.graphics.getWidth() - radius;
			}
			
			speed.x*= -0.5f;
		}
		if(cPosition.y + radius > Gdx.graphics.getHeight() || cPosition.y - radius < 0){
			if(cPosition.y -radius < 0){
				cPosition.y = radius + 1;
			}else{
				cPosition.y = Gdx.graphics.getHeight() - radius - 1;
			}
			
			speed.y*= -0.5f;
		}
		
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
}
