package ar.edu.itba.Asteroids.Core.HUDs;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD2PlayersVs extends HUDManager{
	SpaceShip player1;
	//AsteroidPlayer player2;
	
	public HUD2PlayersVs(SpaceShip p) {
		player1 = p;
	}
	@Override
	public void draw(SpriteBatch batch) {
		drawSector1(batch, player1);
		
	}

}
