package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * the singlePlayer HUD
 *
 */
public class HUD1Player extends HUDManager{
	private Player player;
	
	public HUD1Player(Player p){
		player = p;
	}
	
	public void draw(SpriteBatch batch){
		drawSector1(batch, player);
		Assets.SMALL_FONT.draw(batch,"Tiempo:"+ player.getStringScore() ,0, 15);
	}

}
