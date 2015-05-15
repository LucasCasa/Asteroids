package ar.edu.itba.Asteroids.Core.HUDs;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD3Players2vs1 extends HUDManager {
	SpaceShip player1;
	SpaceShip player2;
	AsteroidPlayer player3;
	public HUD3Players2vs1(SpaceShip p1, SpaceShip p2, AsteroidPlayer p3) {
		player1 = p1;
		player2 = p2;
		player3 = p3;
	}
	@Override
	public void draw(SpriteBatch batch) {
		drawSector1(batch, player1);
		drawSector2(batch, player2);
		drawSector3(batch, player3);

	}

}
