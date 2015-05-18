package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.itba.Asteroids.Core.ArrayMap;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUp;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpCreator;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

public abstract class WorldManager {
	public ArrayList<Connector<Asteroid,AsteroidUI>> asteroids;
	private ArrayList<Connector<PowerUp, PowerUpUI>> powerUps;
	protected SpaceShip first;
	protected AsteroidPlayer asteroidP;
	protected SpaceShipUI firstUI;
	protected Timer timer;
	protected boolean gameOver;
	private Timer powerUpTimer;
	private final float powerUpCooldown = 5.0f;
	private ArrayMap<SpaceShip,SpaceShipUI> ships;
	/**
	 * 
	 * @param spaceshipAmount; amount of spaceShips in the game
	 * @param textures; textures of the spaceships. the first one is of the first player, second one of the second player
	 * and third one of the third player, depending on the amount of spaceShips that there is
	 */
	public WorldManager(){
		 asteroids = new ArrayList<Connector<Asteroid,AsteroidUI>>();
		 ships = new ArrayMap<SpaceShip,SpaceShipUI>();
		 timer = new Timer();
		 powerUpTimer = new Timer();
		 powerUps = new ArrayList<Connector<PowerUp, PowerUpUI>>();
	}

	public void update(){
		updatePowerUps();
		updateSpaceships();
		asteroidP.update();
		for( Connector<Asteroid,AsteroidUI> a: asteroids){
			a.getBack().update();
		}
		updateAsteroidCollision();
		updatePowerUpCollision();
	}

	
	
	public ArrayList<AsteroidUI> getAsteroidsUI() {
		ArrayList<AsteroidUI> au = new ArrayList<AsteroidUI>();
		for(int i = 0; i<asteroids.size();i++){
			au.add(asteroids.get(i).getFront());
		}
		return au;
	}
	public ArrayList<PowerUpUI> getPowerUpUI(){
		ArrayList<PowerUpUI> pui = new ArrayList<PowerUpUI>();
		for(int i = 0; i<powerUps.size();i++){
			pui.add(powerUps.get(i).getFront());
		}
		return pui;
	}
	public ArrayList<SpaceShipUI> getShipsUI(){
		return ships.getValues(); 
	}
	public float getTime(){
		return timer.getTime();
	}
	

	public ArrayList<SpaceShip> getSpaceShips(){
		return ships.getKeys();
	}
	
	public ArrayMap<SpaceShip, SpaceShipUI> getAll(){
		return ships;
	}
	
	/**
	 * 
	 * @return null if there is no asteroid player in that mode (there is an AI Asteroid player) or returns the player which is controlling the asteroids
	 
	public abstract AsteroidPlayer getAsteroidPlayer();
	*/
	public void keyDown(int keyCode) {	
		keyDown(keyCode, 0);
	}
	public void keyUp(int keyCode) {
		keyUp(keyCode,0);
	}

	public void addAsteroid(Asteroid thrown, AsteroidUI asteroidUI) {
		asteroids.add(new Connector<Asteroid,AsteroidUI>(thrown,asteroidUI));
		
	}
	public boolean gameOver(){
		return gameOver;
	}

	public void keyDown(int keyCode, int activeSpaceShip) {
		switch (keyCode) {
		case Keys.W:
			ships.getKeyAt(activeSpaceShip).acelUp(true);
			break;
		case Keys.S:
			ships.getKeyAt(activeSpaceShip).acelDown(true);
			break;
		case Keys.A:
			ships.getKeyAt(activeSpaceShip).acelLeft(true);
			break;
		case Keys.D:
			ships.getKeyAt(activeSpaceShip).acelRight(true);
			break;
		}
		
	}

	public void keyUp(int keyCode, int activeSpaceShip) {
		switch (keyCode) {
		case Keys.W:
			ships.getKeyAt(activeSpaceShip).acelUp(false);
			break;
		case Keys.S:
			ships.getKeyAt(activeSpaceShip).acelDown(false);
			break;
		case Keys.A:
			ships.getKeyAt(activeSpaceShip).acelLeft(false);
			break;
		case Keys.D:
			ships.getKeyAt(activeSpaceShip).acelRight(false);
			break;
		}	
		
	}

	public AsteroidPlayer getAsteroidPlayer() {
		return asteroidP;
	}

	public void updateSpaceships(){
		for(SpaceShip s: ships.getKeys()){
			if(s.isActive()){
			s.update();
			}
		}
		//this for checks if the spaceships are collisioning
		for(int i=0; i<ships.size();i++){
			SpaceShip aux = ships.getKeyAt(i); //you can always do this because you always have at least one spaceship
			for(int j=i+1; j<this.ships.size();j++){
				if(aux.isActive() && ships.getKeyAt(j).isActive()){
					aux.shipCollision(ships.getKeyAt(j));
				}
			}
		}
	}
	
	public void updateAsteroidCollision(){
		for(int i = 0; i<asteroids.size();i++){
			Asteroid aux = asteroids.get(i).getBack();
			for(int j = i+1; j < asteroids.size();j++){
				aux.asteroidCollision(asteroids.get(j).getBack());
			}
			if(aux.outOfScreen()){
				asteroids.remove(i);
			}
			for(SpaceShip s : ships.getKeys())
			if(s.isActive() && s.shipCollision(aux)){
				asteroids.remove(i);
			}
		}
	}
	
	public void updatePowerUps(){
		powerUpTimer.update();
		if(powerUpTimer.getTime() > powerUpCooldown){
			powerUps.add(PowerUpCreator.create());
			powerUpTimer.reset();
		}
		for(Connector<PowerUp, PowerUpUI> p: powerUps){
			p.getBack().update();
		}
	}
	
	public void updatePowerUpCollision(){
		Iterator<Connector<PowerUp, PowerUpUI>> iter = powerUps.iterator();
		Connector<PowerUp, PowerUpUI> con;
		while(iter.hasNext()){
			con = iter.next();
			for(Connector<Asteroid, AsteroidUI> a: asteroids){
				if(con.getBack().collision(a.getBack()))
					iter.remove();
			}
			for(SpaceShip s: ships.getKeys()){
				if(con.getBack().collision(s)){
					con.getBack().effect(s);
					iter.remove();
				}
			}
		}
	}
}

