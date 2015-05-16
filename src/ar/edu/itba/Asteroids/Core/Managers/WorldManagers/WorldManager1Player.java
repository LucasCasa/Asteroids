package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager1Player extends WorldManager{
	boolean gameOver = false;
	public WorldManager1Player(Connector<SpaceShip,SpaceShipUI> a) {
		super();
		first = a.getBack();
		firstUI = a.getFront();
	}
	
	public void update(){
		super.update();
		first.update();
		//IA.update();
		if(!(first.getLives() <= 0)){
			timer.update();	
			gameOver = true;
		}
	}
	@Override
	public List<SpaceShipUI> getShipsUI() {
		List<SpaceShipUI> aux = new ArrayList<SpaceShipUI>();
		aux.add(firstUI);
		return aux;
	}
	@Override
	public List<SpaceShip> getSpaceShips() {
		List<SpaceShip> aux = new ArrayList<SpaceShip>();
		aux.add(first);
		return aux;

	}
}
