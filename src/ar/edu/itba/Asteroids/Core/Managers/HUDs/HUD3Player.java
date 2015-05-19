package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD3Player extends HUDManager{
	private Player player1;
	private Player player2;
	private Player player3;
	
	public HUD3Player(Player p1, Player p2,Player p3){
		player1 = p1;
		player2 = p2;
		player3 = p3;
	}
	public void draw(SpriteBatch batch){
		drawSector1(batch, player1);
		drawSector2(batch, player2);
		drawSector3(batch, player3);
	}

}
