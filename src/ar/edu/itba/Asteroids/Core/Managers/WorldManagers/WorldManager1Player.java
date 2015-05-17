package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager1Player extends WorldManager{
	private AIPlayer AI;
	
	public WorldManager1Player(Connector<SpaceShip,SpaceShipUI> a) {
		super();
		getAll().put(a.getBack(), a.getFront());
		first = a.getBack();
		firstUI = a.getFront();
		AI = new AIPlayer();
	}
	
	public void update(){
		super.update();
		AI.update();
		if(!(super.getSpaceShips().get(0).getLives() <= 0)){
			timer.update();	
			gameOver = true;
		}
	}
}
