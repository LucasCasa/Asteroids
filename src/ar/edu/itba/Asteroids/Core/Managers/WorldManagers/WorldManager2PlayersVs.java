package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager2PlayersVs extends WorldManager{
	private int activeSpaceShip = 0;
	
	public WorldManager2PlayersVs(ArrayList<Player> players) {
		super(players);
		
	}
	
	public void update(){
		super.update();
		if(players.get(0).shipHasLost()){
			players.get(0).changeState();
			players.get(1).changeState();
			activeSpaceShip = 1;
			impasse = true;
		}
		if(getPlayer(0).shipHasLost()){
			super.impasse = true;
			getPlayer(0).changeState();
			getPlayer(1).changeState();
		}
		if(getPlayer(1).isSpaceShipPlayer() && getPlayer(1).shipHasLost()){
			gameOver = true;
		}
	}
	
	public void keyDown(int keyCode){
		if(isPaused() || isImpasse()){
			super.keyDown(keyCode);
		}else{
			switch(keyCode){
			case Keys.W:
			case Keys.A:
			case Keys.S:
			case Keys.D:
			case Keys.ESCAPE:
				super.keyDown(keyCode,activeSpaceShip);
				break;
			default:
				for(Player p :players){
					if(p.isAsteroidPlayer()){
						p.getAsteroidPlayer().keyPressed(keyCode, super.getSpaceShips());
					}
				}
				break;
			}
		}
	}
	public void keyUp(int keyCode){
		super.keyUp(keyCode,activeSpaceShip);
	}

	@Override
	public Player getWinner() {
		if(getPlayer(0).getScore() > getPlayer(1).getScore()){
			return getPlayer(0);
		}else{
			return getPlayer(1);
		}
	}
}
