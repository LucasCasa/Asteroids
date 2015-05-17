package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager2Player extends WorldManager{
	private SpaceShip second;
	private SpaceShipUI secondUI;
	private AIPlayer AI;
	
	public WorldManager2Player(List<Connector<SpaceShip,SpaceShipUI>> s) {
		super();
		super.getAll().put(s.get(0).getBack(), s.get(0).getFront());
		super.getAll().put(s.get(1).getBack(), s.get(1).getFront());
		AI = new AIPlayer();
	}


	public void update(){
		super.update();
		AI.update();
		
	}

}
