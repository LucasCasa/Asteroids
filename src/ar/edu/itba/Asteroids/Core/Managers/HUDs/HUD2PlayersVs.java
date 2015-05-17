package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD2PlayersVs extends HUDManager{
	SpaceShip player1;
	AsteroidPlayer player2;
	
	public HUD2PlayersVs(SpaceShip p,AsteroidPlayer a) {
		player1 = p;
		player2 = a;
	}
	@Override
	public void draw(SpriteBatch batch) {
		drawSector1(batch, player1);
		drawSector2(batch,player2);
	}

}
