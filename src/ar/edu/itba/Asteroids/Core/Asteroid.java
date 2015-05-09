package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
	ShapeRenderer shape = new ShapeRenderer();
	float x;
	float y;
	float radius = 15;
	int mass = 1;
	Vector2 vel = new Vector2(2,3);
	Vector2 acel = new Vector2(0,0);
	public Asteroid(float a, float b,float velx, float vely,float mass){
		vel.x = velx;
		vel.y = vely;
		x = a;
		y = b;
		this.mass = 1; 
	}
	
	public void update(){
		x+= vel.x;
		y+= vel.y;
		vel.x += acel.x;
		vel.y += acel.y;
		if(x + radius > Gdx.graphics.getWidth() || x - radius < 0){
			if(x -radius < 0){
				x = radius + 1;
			}else{
				x = Gdx.graphics.getWidth() - radius;
			}
			
			vel.x*=-1;
			acel.x*=-1;
		}
		if(y + radius > Gdx.graphics.getHeight() || y - radius < 0){
			if(y -radius < 0){
				y = radius + 1;
			}else{
				y = Gdx.graphics.getHeight() - radius;
			}
			
			vel.y*=-1;
			acel.y*=-1;
		}
		
		draw();
	}
	
	public void draw(){
		shape.begin(ShapeType.Filled);
		shape.setColor(0, 0, 1, 1);
		shape.circle(x, y, radius);
		shape.end();

	}
	public void collision(Asteroid o){
		float dist = (float)(Math.pow(o.x - x,2) + Math.pow(o.y - y, 2));
		if(dist <= 4 * radius * radius){
			//acel.x = acel.y = vel.y = vel.x = 0;
			checkNew(o);
		}
	}
	public void checkNew(Asteroid o){
		float collisionPointX = ((x * o.radius) + (o.x * radius)) / (radius + o.radius);		 
		float collisionPointY = ((y * o.radius) + (o.y * radius)) / (radius + o.radius);
		float newVelX =  (vel.x * (mass - o.mass) + (2 * o.mass * o.vel.x)) / (mass + o.mass);
		float newVelY1 = (vel.y * (mass - o.mass) + (2 * o.mass * o.vel.y)) / (mass + o.mass);

		float newVelX2 =  (o.vel.x * (o.mass - mass) + (2 * mass * vel.x)) / (o.mass + mass);
		float newVelY2 = (o.vel.y * (o.mass - mass) + (2 * mass * vel.y)) / (o.mass + mass);
		vel.x = newVelX;
		vel.y = newVelY1;
		x+= vel.x;
		y+= vel.y;
		o.vel.x = newVelX2;
		o.vel.y = newVelY2;
		o.x+= o.vel.x;
		o.y+= o.vel.y;
		
		
		shape.begin(ShapeType.Filled);
		shape.setColor(0,1,0,1);
		shape.circle(collisionPointX, collisionPointY, 10);
		shape.end();
	}
	
	
}

