package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * The HUD when there are 2 player both controlling spaceShips
 * @author ME
 *
 */
public class HUD2Player extends HUDManager{
	private Player player1;
	private Player player2;
	
	public HUD2Player(Player p1, Player p2){
		player1 = p1;
		player2 = p2;
	}
	public void draw(SpriteBatch batch){
		drawSector1(batch, player1);
		drawSector2(batch, player2);
	}

}
