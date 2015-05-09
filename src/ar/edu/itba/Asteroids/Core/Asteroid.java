package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
	ShapeRenderer shape = new ShapeRenderer();
	float x;
	float y;
	float radius = 30;
	int mass = 1;
	Vector2 vel = new Vector2(2,3);
	Vector2 acel = new Vector2(0,0);
	public Asteroid(float a, float b,float velx, float vely,float mass){
		vel.x = velx;
		vel.y = vely;
		x = a;
		y = b;
		this.mass = (int)mass; 
	}
	
	public void update(){
		x+= vel.x;
		y+= vel.y;
		vel.x += acel.x;
		vel.y += acel.y;
		if(x + 30 > Gdx.graphics.getWidth() || x - 30 < 0){
			if(x -30 < 0){
				x = 31;
			}else{
				x = Gdx.graphics.getWidth() - 30;
			}
			
			vel.x*=-1;
			acel.x*=-1;
		}
		if(y + 30 > Gdx.graphics.getHeight() || y - 30 < 0){
			if(y -30 < 0){
				y = 31;
			}else{
				y = Gdx.graphics.getHeight() - 30;
			}
			
			vel.y*=-1;
			acel.y*=-1;
		}
		
		draw();
	}
	
	public void draw(){
		shape.begin(ShapeType.Filled);
		shape.setColor(0, 0, 1, 1);
		shape.circle(x, y, radius,500);
		shape.end();

	}
	public void collision(Asteroid o){
		float dist = (float)(Math.pow(o.x - x,2) + Math.pow(o.y - y, 2));
		if(dist <= 3600){
			//acel.x = acel.y = vel.y = vel.x = 0;
			System.out.println("BINGO");
			checkNew(o);
		}
		System.out.println(dist);
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

