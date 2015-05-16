package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;

public class WorldManager3Player extends WorldManager{
	public WorldManager3Player(int spaceshipAmount,List<Texture> textures) {
		super(3,textures);
	}
	
	@Override
	public AsteroidPlayer getAsteroidPlayer() {
		return null;
	}
}
