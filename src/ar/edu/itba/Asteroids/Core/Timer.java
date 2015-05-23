package ar.edu.itba.Asteroids.Core;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;

public class Timer {
	private float time;
	private float last;
	public Timer(){
		time = 0;
	}
	/**
	 * Updates the time of the timer, this needs to be called every cicle of the program
	 */
	public void update(){
		time+= Gdx.graphics.getDeltaTime();
	}
	
	public void reset(){
		time = 0;
		last = 0;
	}
	/**
	 * 
	 * @return the time rounded by 2 decimals
	 */
	public float getTime(){
		return round(time,2); 
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
	/**
	 * gets the time since the last time this method was called.
	 */
	public float getDeltaTime(){
		float aux =  getTime() - last;
		last = getTime();
		return aux;
	}
	
}
