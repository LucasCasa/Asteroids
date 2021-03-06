package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * the HUD Manager when the game is a one vs one(one controlls the space ship. the other the asteroids)
 */
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
		if(player1.isSpaceShipPlayer()){
			Assets.SMALL_FONT.draw(batch,"Tiempo:"+ player1.getTime() ,0, 15);
		}else{
			Assets.SMALL_FONT.draw(batch,"Tiempo:"+ player2.getTime() ,0, 15);
		}
	}

}
