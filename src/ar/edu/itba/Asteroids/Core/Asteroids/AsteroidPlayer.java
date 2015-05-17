package ar.edu.itba.Asteroids.Core.Asteroids;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class AsteroidPlayer {

	private int reserve;
	private int maxAsteroids = 2;
	private float cooldown = 1;
	private Timer timer;
	
	public AsteroidPlayer(){
		timer = new Timer();
	}
	/**
	 * Se fija si se puede incrementar la cantidad de asteroides,
	 * si se puede updatea el timer, y si ya se cumplio el tiempo
	 * añade uno a la reserva
	 */
	public void update(){
		if(reserve < maxAsteroids){
			timer.update();
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
	 * @return la relacion entre el tiempo que paso y el tiempo que tiene
	 * que pasar hasta que se cree un asteroide.
	 * Si ya paso mas que el tiempo necesario devuelve 1
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
	 * Se encarga de crear el Asteroide
	 * 
	 * @param keyCode la tecla que se presiono
	 * @param t la nave objetivo
	 * @return un nuevo asteroide si se puede, null si no se puede crear o no se presiono
	 * una tecla valida
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
				GameManager.getInstance().getWorld().addAsteroid(a, new AsteroidUI(a));
			}
		}
		return thrown;
	}
}
