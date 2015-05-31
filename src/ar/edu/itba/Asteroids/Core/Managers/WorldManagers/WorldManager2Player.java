package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;


import com.badlogic.gdx.Input.Keys;

public class WorldManager2Player extends WorldManager{
	private AIPlayer AI;
	public WorldManager2Player(ArrayList<Player> players) {
		super(players);
		AI = new AIPlayer();
	}


	public void update(float deltaTime){
		if(!isPaused() && !impasse){
			AI.update(deltaTime);
		}
		checkGameOver();
		
		if(!gameOver){
			super.update(deltaTime);
		}
		
	}
	public void checkGameOver(){
		for(int i = 0; i<players.size() && gameOver == false ;i++){
			if(players.get(i).shipHasLost()){
				gameOver = true;
			}
		}
	}
	public void keyDown(int keyCode){
		switch (keyCode) {
		case Keys.DOWN:
			getSpaceShips().get(1).acelDown(true);
			break;
		case Keys.UP:
			getSpaceShips().get(1).acelUp(true);
			break;
		case Keys.LEFT:
			getSpaceShips().get(1).acelLeft(true);
			break;
		case Keys.RIGHT:
			getSpaceShips().get(1).acelRight(true);
			break;
		default:
			super.keyDown(keyCode);
			break;
		}
	}
	
	public void keyUp(int keyCode){
		switch (keyCode) {
		case Keys.DOWN:
			getSpaceShips().get(1).acelDown(false);
			break;
		case Keys.UP:
			getSpaceShips().get(1).acelUp(false);
			break;
		case Keys.LEFT:
			getSpaceShips().get(1).acelLeft(false);
			break;
		case Keys.RIGHT:
			getSpaceShips().get(1).acelRight(false);
			break;
		default:
			super.keyUp(keyCode);
			break;
		}
	}
	public Player getWinner(){
		for(int i = 0; i<players.size();i++){
			if(!super.getPlayer(i).shipHasLost()){
				return getPlayer(i);
			}
		}
		return null;
	}

}
