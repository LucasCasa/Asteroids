package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;

public class WorldManager2PlayersVs extends WorldManager{
	
	public WorldManager2PlayersVs(int spaceshipAmount,List<Texture> textures) {
		super(2,textures); //manda dos porque son dos spaceShips uno para el 1 y uno para el dos aunque se turnen en jugar. cada jugador
						//deberia poder elegir la nave que le toca lo mismo para 3 que es 2 contra 1
	}
	
	@Override
	public AsteroidPlayer getAsteroidPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
}
