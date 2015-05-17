package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ar.edu.itba.Asteroids.Core.ArrayMap;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

//deje las naves como un array list no importa que halla solo una para que se chequee muchos mas facil. 
public abstract class WorldManager {
	public ArrayList<Connector<Asteroid,AsteroidUI>> asteroids;
	protected SpaceShip first;
	protected SpaceShipUI firstUI;
	protected Timer timer;
	protected boolean gameOver;
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
	}

	public void update(){
		for(SpaceShip s: ships.getKeys()){
			s.update();
		}
		//this for checks if the spaceships are collisioning
		for(int i=0; i<ships.size();i++){
			SpaceShip aux = ships.getKeyAt(i); //you can always do this because you always have at least one spaceship
			for(int j=i+1; j<this.ships.size();j++){
				aux.shipCollision(ships.getKeyAt(j));
			}
		}
		
		for( Connector a: asteroids){
			a.getBack().update();
		}
		
		for(int i = 0; i<asteroids.size();i++){
			Asteroid aux = asteroids.get(i).getBack();
			for(int j = i+1; j < asteroids.size();j++){
				aux.asteroidCollision(asteroids.get(j).getBack());
			}
			if(aux.outOfScreen()){
				asteroids.remove(i);
			}
			for(SpaceShip s : ships.getKeys())
			if(s.shipCollision(aux)){
				asteroids.remove(i);
			}
		}
	}

	
	
	public ArrayList<AsteroidUI> getAsteroidsUI() {
		ArrayList<AsteroidUI> au = new ArrayList<AsteroidUI>();
		for(int i = 0; i<asteroids.size();i++){
			au.add(asteroids.get(i).getFront());
		}
		return au;
	}
	public float getTime(){
		return timer.getTime();
	}
	
	public ArrayList<SpaceShipUI> getShipsUI(){
		return ships.getValues(); 
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
		switch (keyCode) {
/*		case Keys.DOWN:
			second.acelDown(true);
			break;
		case Keys.UP:
			second.acelUp(true);
			break;
		case Keys.LEFT:
			second.acelLeft(true);
			break;
		case Keys.RIGHT:
			second.acelRight(true);
			break; */
		case Keys.W:
			ships.getKeyAt(0).acelUp(true);
			break;
		case Keys.S:
			ships.getKeyAt(0).acelDown(true);
			break;
		case Keys.A:
			ships.getKeyAt(0).acelLeft(true);
			break;
		case Keys.D:
			ships.getKeyAt(0).acelRight(true);
			break;
	/*	default:
			Asteroid d;
			if((d = third.keyPressed(keyCode,ships.get(0))) != null){
				asteroids.add(new Connector<Asteroid,AsteroidUI>(d,new AsteroidUI(d)));
			}*/
		}
	}
	public void keyUp(int keyCode) {
		switch (keyCode) {
/*		case Keys.DOWN:
			second.acelDown(false);
			break;
		case Keys.UP:
			second.acelUp(false);
			break;
		case Keys.LEFT:
			second.acelLeft(false);
			break;
		case Keys.RIGHT:
			second.acelRight(false);
			break; */
		case Keys.W:
			ships.getKeyAt(0).acelUp(false);
			break;
		case Keys.S:
			ships.getKeyAt(0).acelDown(false);
			break;
		case Keys.A:
			ships.getKeyAt(0).acelLeft(false);
			break;
		case Keys.D:
			ships.getKeyAt(0).acelRight(false);
			break;
		}	
	}

	public void addAsteroid(Asteroid thrown, AsteroidUI asteroidUI) {
		asteroids.add(new Connector<Asteroid,AsteroidUI>(thrown,asteroidUI));
		
	}
	public boolean gameOver(){
		return gameOver;
	}


}

