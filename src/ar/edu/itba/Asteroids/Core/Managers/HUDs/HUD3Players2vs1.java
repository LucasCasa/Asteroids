package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * this is the HUD for the 3 player mod where one controlls the asteroids.
 * @author ME
 *
 */
public class HUD3Players2vs1 extends HUDManager {
	private Player player1;
	private Player player2;
	private Player player3;
	
	public HUD3Players2vs1(Player p1, Player p2, Player p3) {
		player1 = p1;
		player2 = p2;
		player3 = p3;
	}
	@Override
	/**
	 * it draws all the informacion of the 3 players plus the score they have
	 */
	public void draw(SpriteBatch batch) {
		drawSector1(batch, player1);
		drawSector2(batch, player2);
		drawSector3(batch, player3);
		Assets.SMALL_FONT.draw(batch,"Tiempo:"+ GameManager.getInstance().getTime() ,0, 15);
		Assets.SMALL_FONT.draw(batch, "Score "+ player1.getName() + ":" + player1.getStringScore(), 0, 30);
		Assets.SMALL_FONT.draw(batch, "Score "+ player2.getName() + ":" + player2.getStringScore(), 0, 45);
		Assets.SMALL_FONT.draw(batch, "Score "+ player3.getName() + ":" + player3.getStringScore(), 0, 60);
	}

}
