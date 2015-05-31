package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;


import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.Managers.HighScoreManager;

/**
 * the world manager when in single player
 *
 */
public class WorldManager1Player extends WorldManager{
	private AIPlayer AI;
	int i = 0;
	public WorldManager1Player(ArrayList<Player> players) {
		super(players);
		HighScoreManager.getInstance().loadScores();
		AI = new AIPlayer();
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		if(!isPaused() && !impasse){
			AI.update(deltaTime);
		}
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
