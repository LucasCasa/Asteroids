package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

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

}
