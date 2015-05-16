package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;

public class WorldManager3Players2vs1 extends WorldManager {
	public WorldManager3Players2vs1(int spaceshipAmount,List<Texture> textures) {
		super(3,textures);
	}
	
	@Override
	public AsteroidPlayer getAsteroidPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
}
