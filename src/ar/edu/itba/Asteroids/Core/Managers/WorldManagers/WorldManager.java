package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

//deje las naves como un array list no importa que halla solo una para que se chequee muchos mas facil. 
public abstract class WorldManager {
	private int spaceshipAmount;
	ArrayList<SpaceShip> ships = new ArrayList<SpaceShip>(); //cambie el nombre de el array naves a ships para que este todo en ingles
	List<SpaceShipUI> shipsui = new ArrayList<SpaceShipUI>();
	public ArrayList<Connector<Asteroid,AsteroidUI>> asteroids;
	Asteroid thrown;
	AsteroidPlayer third;
	SpaceShip first;
	SpaceShip second;
	
	/**
	 * 
	 * @param spaceshipAmount; amount of spaceShips in the game
	 * @param textures; textures of the spaceships. the first one is of the first player, second one of the second player
	 * and third one of the third player, depending on the amount of spaceShips that there is
	 */
	public WorldManager(){
		 asteroids = new ArrayList<Connector<Asteroid,AsteroidUI>>();
	}

	public void update(){
		
		for(SpaceShip s: ships){
			s.update();
		}
		//this for checks if the spaceships are collisioning
		for(int i=0; i<this.spaceshipAmount;i++){
			SpaceShip aux = ships.get(i); //you can always do this because you always have at least one spaceship
			for(int j=i+1; j<this.spaceshipAmount;j++){
				aux.collision(ships.get(j));
			}
		}
		
		for( Connector a: asteroids){
			a.getBack().update();
		}
		
		for(int i = 0; i<asteroids.size();i++){
			Asteroid aux = asteroids.get(i).getBack();
			for(int j = i+1; j < asteroids.size();j++){
				aux.collision(asteroids.get(i).getBack());
			}
			if(aux.outOfScreen()){
				asteroids.remove(i);
			}
			if(first.shipCollision(aux)){
				asteroids.remove(i);
			}
		}
	}

	public ArrayList<SpaceShip> getSpaceShips(){
		return ships;
	}
	
	public ArrayList<AsteroidUI> getAsteroidsUI() {
		ArrayList<AsteroidUI> au = new ArrayList<AsteroidUI>();
		for(int i = 0; i<asteroids.size();i++){
			au.add(asteroids.get(i).getFront());
		}
		return au;
	}
	
	public List<SpaceShipUI> getShipsUI() {
		return shipsui;
	}
	
	/**
	 * 
	 * @return null if there is no asteroid player in that mode (there is an AI Asteroid player) or returns the player which is controlling the asteroids
	 */
	public abstract AsteroidPlayer getAsteroidPlayer();
	
	public void keyDown(int keyCode) {	
		switch (keyCode) {
		case Keys.DOWN:
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
			break;
		case Keys.W:
			first.acelUp(true);
			break;
		case Keys.S:
			first.acelDown(true);
			break;
		case Keys.A:
			first.acelLeft(true);
			break;
		case Keys.D:
			first.acelRight(true);
			break;
		default:
			Asteroid d;
			if((d = third.keyPressed(keyCode,ships.get(0))) != null){
				asteroids.add(new Connector<Asteroid,AsteroidUI>(d,new AsteroidUI(d)));
			}
		}
	}
	public void keyUp(int keyCode) {
		switch (keyCode) {
		case Keys.DOWN:
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
			break;
		case Keys.W:
			first.acelUp(false);
			break;
		case Keys.S:
			first.acelDown(false);
			break;
		case Keys.A:
			first.acelLeft(false);
			break;
		case Keys.D:
			first.acelRight(false);
			break;
		}	
	}





}
