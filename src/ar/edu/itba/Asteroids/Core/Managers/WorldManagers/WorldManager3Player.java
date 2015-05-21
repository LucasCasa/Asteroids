package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

public class WorldManager3Player extends WorldManager2Player{
	
	public WorldManager3Player(List<Connector<SpaceShip,SpaceShipUI>> s,ArrayList<Player> players) {
		super(s,players);
		super.getAll().put(s.get(2).getBack(), s.get(2).getFront());
	}
	public void checkGameOver(){
		int aux =0;
		for(Player p : players){
			if(p.shipHasLost()){
				aux++;
			}
		}
		if(aux == 2){
			gameOver = true;
		}
	}
	public void keyDown(int keyCode){
		switch (keyCode) {
		case Keys.K:
			getSpaceShips().get(2).acelDown(true);
			break;
		case Keys.I:
			getSpaceShips().get(2).acelUp(true);
			break;
		case Keys.J:
			getSpaceShips().get(2).acelLeft(true);
			break;
		case Keys.L:
			getSpaceShips().get(2).acelRight(true);
			break;
		default:
			super.keyDown(keyCode);
			break;
		}
	}
	
	public void keyUp(int keyCode){
		switch (keyCode) {
		case Keys.K:
			getSpaceShips().get(2).acelDown(false);
			break;
		case Keys.I:
			getSpaceShips().get(2).acelUp(false);
			break;
		case Keys.J:
			getSpaceShips().get(2).acelLeft(false);
			break;
		case Keys.L:
			getSpaceShips().get(2).acelRight(false);
			break;
		default:
			super.keyUp(keyCode);
			break;
		}
	}
}
