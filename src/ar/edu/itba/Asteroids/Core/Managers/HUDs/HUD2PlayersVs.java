package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD2PlayersVs extends HUDManager{
	Player player1;
	Player player2;
	
	public HUD2PlayersVs(Player a, Player b) {
		player1 = a;
		player2 = b;
	}
	@Override
	public void draw(SpriteBatch batch) {
		drawSector1(batch, player1);
		drawSector2(batch, player2);
	}

}
