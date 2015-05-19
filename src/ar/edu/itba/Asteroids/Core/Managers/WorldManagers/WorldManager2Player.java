package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

public class WorldManager2Player extends WorldManager{
	private AIPlayer AI;
	public WorldManager2Player(List<Connector<SpaceShip,SpaceShipUI>> s,ArrayList<Player> players) {
		super(players);
		super.getAll().put(s.get(0).getBack(), s.get(0).getFront());
		super.getAll().put(s.get(1).getBack(), s.get(1).getFront());
		AI = new AIPlayer();
	}


	public void update(){
		AI.update();
		for(int i = 0; i<players.size() && gameOver == false;i++){
			if(players.get(i).shipHasLost()){
				gameOver = true;
			}
		}
		if(!gameOver){
			super.update();
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
		for(int i = 0; i<2;i++){
			if(!super.getPlayer(i).shipHasLost()){
				return getPlayer(i);
			}
		}
		return null;
	}

}
