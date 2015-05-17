package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager2Player extends WorldManager{
	
	public WorldManager2Player(int spaceshipAmount,List<Texture> textures) {
		super();
	}

	@Override
	public AsteroidPlayer getAsteroidPlayer() {
		return null;
	}

	@Override
	public List<SpaceShipUI> getShipsUI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpaceShip> getSpaceShips() {
		// TODO Auto-generated method stub
		return null;
	}

}
