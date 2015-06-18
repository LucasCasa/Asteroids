package ar.edu.itba.Asteroids.Core.Asteroids;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;
import ar.edu.itba.Asteroids.Core.Managers.GameManagerUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class AsteroidPlayer {

	private int reserve;
	private int maxAsteroids = 2;
	private static final float cooldownModifier = 1f;
	private float baseCooldown;
	private float cooldown;
	private Timer timer;
	
	public AsteroidPlayer(int spaceshipAmount){
		timer = new Timer();
		this.cooldown = baseCooldown + spaceshipAmount * cooldownModifier;
	}
	/**
	 * This method validates if you can increase the amount of asteroids
	 * if you can, then it updates the timer and if the time has already passed it ads another one to the reserve
	 */
	public void update(float deltaTime){
		if(reserve < maxAsteroids){
			timer.update(deltaTime);
			if(timer.getTime() >= cooldown){
				reserve++;
				timer.reset();
			}
		}
	}
	protected void setCooldown(float cooldown){
		this.cooldown = cooldown;
	}
	protected void setMaxAsteroids(int max){
		this.maxAsteroids = max;
	}
	/**
	 * 
	 * @return the relationship between the time that has already passed and the time that has to pass until a new asteroid is created
	 * If more time has passed than necesary it returns 1
	 */
	public float getTimePercentage(){
		if(timer.getTime() > cooldown){
			return 1;
		}
		else{
			return timer.getTime() / cooldown;
		}
	}
	public int getReserve() {
		return reserve;
	}
	/**
	 * This method creats the asteroid 
	 * 
	 * @param keyCode; the key that has been pressed
	 * @param t; the spaceShip that it is going to try and destroy
	 * @return a new asteroid if you can, null if it can't be created,because there are no asteroids in reserve or if you pressed a key that is not a correct one
	 * 
	 */
	public ArrayList<Asteroid> keyPressed(int keyCode,ArrayList<SpaceShip> t) {
		if(reserve <= 0){
			return null;
		}
		reserve--;
		ArrayList<Asteroid> thrown = new ArrayList<Asteroid>();
		ArrayList<Vector2> targets = new ArrayList<Vector2>();
		
		for(SpaceShip target : t){
			if(target.isActive()){
				targets.add(target.getCPos());
			}
		}
		switch(keyCode){
		case Keys.NUMPAD_1:
		case Keys.NUM_1:
			thrown = AsteroidThrower.getInstance().throwBottomLeft(targets);
			break;
		case Keys.NUMPAD_2:
		case Keys.NUM_2:
			thrown = AsteroidThrower.getInstance().throwBottomMiddle(targets);
			break;
		case Keys.NUMPAD_3:
		case Keys.NUM_3:
			thrown = AsteroidThrower.getInstance().throwBottomRight(targets);
			break;
		case Keys.NUMPAD_6:
		case Keys.NUM_6:
			thrown = AsteroidThrower.getInstance().throwRight(targets);
			break;
		case Keys.NUMPAD_9:
		case Keys.NUM_9:
			thrown = AsteroidThrower.getInstance().throwUpperRight(targets);
			break;
		case Keys.NUMPAD_8:
		case Keys.NUM_8:
			thrown = AsteroidThrower.getInstance().throwUpperMiddle(targets);
			break;
		case Keys.NUMPAD_7:
		case Keys.NUM_7:
			thrown = AsteroidThrower.getInstance().throwUpperLeft(targets);
			break;
		case Keys.NUMPAD_4:
		case Keys.NUM_4:
			thrown = AsteroidThrower.getInstance().throwLeft(targets);
			break;
		default:
			reserve++;
			break;
		}
		if(thrown.size() > 0){
			for(Asteroid a : thrown){
				GameManager.getInstance().getWorld().addAsteroid(a);
				GameManagerUI.getInstance().getWorldUI().addAsteroidUI(new AsteroidUI(a));
			}
		}
		return thrown;
	}
}
