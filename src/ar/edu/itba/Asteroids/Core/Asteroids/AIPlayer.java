package ar.edu.itba.Asteroids.Core.Asteroids;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Input.Keys;

public class AIPlayer extends AsteroidPlayer{
	private int keys[] = {Keys.NUMPAD_1,Keys.NUMPAD_2,Keys.NUMPAD_3,Keys.NUMPAD_6,Keys.NUMPAD_9,Keys.NUMPAD_8,Keys.NUMPAD_7,Keys.NUMPAD_4};
	private Timer elapsedTime;
	
	
	/**
	 * This method calculates the position where the asteroid will be thrown
	 * 
	 * @param t, the aimed spaceship
	 * @return a new asteroid if it can, null if it cannot be created or didn't pressed
	 * a valid key
	 */
	public ArrayList<Asteroid> calculateThrow(ArrayList<SpaceShip> t){
		int KeyCode = keys[(int)(Math.random()*8)];
		return keyPressed(KeyCode,t);
	}
	public AIPlayer(int spaceshipAmount){
		super(spaceshipAmount);
		elapsedTime = new Timer();
	}
	
	
	public void update(float deltaTime){
		elapsedTime.update(deltaTime);
		
		//the throwing velocity and the rate of fire keep increasing till it completes the first 2 minutes of game
		if(elapsedTime.getTime() < 120){
			super.setCooldown((float)(1.5 - elapsedTime.getTime()/100));
			
			AsteroidThrower.getInstance().setVelFactor((float)Math.pow(elapsedTime.getTime(),0.125));//the throwing velocity updates exponentially
		}

		if(getReserve() >= 1){
			calculateThrow(GameManager.getInstance().getWorld().getSpaceShips());
		}
		super.update(deltaTime);
	}
	
}
