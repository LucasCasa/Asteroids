package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.AIPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class WorldManager3Player extends WorldManager2Player{
	
	public WorldManager3Player(List<Connector<SpaceShip,SpaceShipUI>> s) {
		super(s);
		super.getAll().put(s.get(2).getBack(), s.get(2).getFront());
		asteroidP = new AIPlayer();
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
