package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.badlogic.gdx.Input.Keys;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager3Players2vs1 extends WorldManager {
	public WorldManager3Players2vs1(List<Connector<SpaceShip,SpaceShipUI>> s,ArrayList<Player> players) {
		super(players);
		s.get(2).getBack().setActive(false);
		super.getAll().put(s.get(0).getBack(), s.get(0).getFront());
		super.getAll().put(s.get(1).getBack(), s.get(1).getFront());
		super.getAll().put(s.get(2).getBack(), s.get(2).getFront());
		asteroidP = new AsteroidPlayer();
	}
	

	public void keyDown(int keyCode){
		switch (keyCode) {
		case Keys.W:
		case Keys.A:
		case Keys.S:
		case Keys.D:
			super.keyDown(keyCode);
			break;
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
			asteroidP.keyPressed(keyCode, super.getSpaceShips());
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


	@Override
	public Player getWinner() {
		throw new NotImplementedException();
	}
}
