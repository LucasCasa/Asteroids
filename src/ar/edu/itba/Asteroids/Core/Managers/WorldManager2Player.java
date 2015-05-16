package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;

public class WorldManager2Player extends WorldManager{
	
	public WorldManager2Player(int spaceshipAmount,List<Texture> textures) {
		super(2,textures);
	}

	@Override
	public AsteroidPlayer getAsteroidPlayer() {
		return null;
	}

}
