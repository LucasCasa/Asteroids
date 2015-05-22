package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;


import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.Managers.HighScoreManager;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager1Player extends WorldManager{
	private AIPlayer AI;
	int i = 0;
	public WorldManager1Player(Connector<SpaceShip,SpaceShipUI> a,ArrayList<Player> players) {
		super(players);
		HighScoreManager.getInstance().loadScores();
		AI = new AIPlayer();
		getAll().put(a.getBack(), a.getFront());
		first = a.getBack();
		firstUI = a.getFront();
	}
	
	public void update(){
		super.update();
		AI.update();
		if(super.getPlayer(0).shipHasLost() ){
			i++;
			gameOver = true;
			System.out.println();
			HighScoreManager.getInstance().add(players.get(0));
			HighScoreManager.getInstance().writeScores();
		}
	}
	
	public Player getWinner(){
		return null;
	}
}
