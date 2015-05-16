package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;

public class WorldManager1Player extends WorldManager{
	public WorldManager1Player(int spaceshipAmount,List<Texture> textures) {
		super(1,textures);
	}
	
	@Override
	public AsteroidPlayer getAsteroidPlayer() {
		return null;
	}
}
