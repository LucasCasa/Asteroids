package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;


import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager1Player extends WorldManager{
	private AIPlayer AI;
	public WorldManager1Player(Connector<SpaceShip,SpaceShipUI> a,ArrayList<Player> players) {
		super(players);
		AI = new AIPlayer();
		getAll().put(a.getBack(), a.getFront());
		first = a.getBack();
		firstUI = a.getFront();
	}
	
	public void update(){
		super.update();
		timer.update();	
		AI.update();
		score = timer.getTime(); //provisional
        if (score > Assets.getHighScore() && !getGameOver()) {
            Assets.setHighScore(score);
        }
		if(super.getPlayer(0).shipHasLost()){
			gameOver = true;
		}
	}
	public Player getWinner(){
		return null;
	}
}
