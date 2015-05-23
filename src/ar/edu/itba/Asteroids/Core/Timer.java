package ar.edu.itba.Asteroids.Core;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;

public class Timer {
	private float time;
	private float last;
	public Timer(){
		time = 0;
	}
	
	public void update(){
		time+= Gdx.graphics.getDeltaTime();
	}
	
	public void reset(){
		time = 0;
		last = 0;
	}
	public float getTime(){
		return round(time,2); 
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
	public float getDeltaTime(){
		float aux =  getTime() - last;
		last = getTime();
		return aux;
	}
	
}
