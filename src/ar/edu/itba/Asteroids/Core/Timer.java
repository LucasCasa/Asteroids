package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.Gdx;

public class Timer {
	private float time;
	
	public Timer(){
		time = 0;
	}
	
	public void update(){
		time+= Gdx.graphics.getDeltaTime();
	}
	
	public void reset(){
		time = 0;
	}
	public float getTime(){
		return time;
	}
}
