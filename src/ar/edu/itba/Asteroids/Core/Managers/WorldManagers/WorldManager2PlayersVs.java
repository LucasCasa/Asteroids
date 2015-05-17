package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager2PlayersVs extends WorldManager{
	private int activeSpaceShip = 0;
	
	public WorldManager2PlayersVs(List<Connector<SpaceShip,SpaceShipUI>> s) {
		super();
		s.get(1).getBack().setActive(false);
		super.getAll().put(s.get(0).getBack(), s.get(0).getFront());
		super.getAll().put(s.get(1).getBack(), s.get(1).getFront());
		asteroidP = new AsteroidPlayer();
	}
	
	public void update(){
		super.update();
		if(getSpaceShips().get(0).getLives()<= 0){
			getSpaceShips().get(0).setActive(false);
			getSpaceShips().get(1).setActive(true);
			activeSpaceShip = 1;
		}
	}
	
	public void keyDown(int keyCode){
		switch(keyCode){
		case Keys.W:
		case Keys.A:
		case Keys.S:
		case Keys.D:
			super.keyDown(keyCode,activeSpaceShip);
			break;
		default:
			asteroidP.keyPressed(keyCode, super.getSpaceShips());
			break;
		}
	}
	public void keyUp(int keyCode){
		super.keyUp(keyCode,activeSpaceShip);
	}
}
