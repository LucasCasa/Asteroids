package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;
import ar.edu.itba.Asteroids.Core.Managers.HighScoreManager;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUDManager;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is in charge of printing (or calling other objects that print) while playing.
 *
 */
public class WorldManagerUI implements Drawable{
	HUDManager h;
	WorldManager wm;
	List<AsteroidUI> aUI;
	List<SpaceShipUI> shipsUI;
	public WorldManagerUI(WorldManager w,ArrayList<SpaceShipUI> ships, HUDManager h){
		wm = w;
		this.h = h;
		aUI = new ArrayList<AsteroidUI>();
		shipsUI = ships;
	}

	public void draw(SpriteBatch batch){
		if ( wm.gameEnded()) {
			drawEndScreen(batch);
		}else if(wm.isImpasse()){
			drawImpasseScreen(batch);
		}else if(wm.isPaused()){
			drawPauseScreen(batch);
		}else{

			for(SpaceShipUI s: shipsUI){
				s.draw(batch);
			}
			for(int i = 0; i<aUI.size() ; i++){
				if(aUI.get(i).isDestroyed()){
					aUI.remove(i);
				}else{
					aUI.get(i).draw(batch);
				}
			}
			for(PowerUpUI p: wm.getPowerUpUI()){
				p.draw(batch);
			}
			h.draw(batch);
		}
	}
	/**
	 * this is the screen at the end of the game, it tells who is the winner in case there are more than 1 player or the score
	 * and the highscore if there is only one player
	 * @param batch
	 */
	private void drawEndScreen(SpriteBatch batch){
		Assets.FONT.draw(batch, "Game Over", Constants.VIRTUAL_WIDTH/2 - 120, Constants.VIRTUAL_HEIGHT/2 + 100);
		if(wm.players.size() == 1){
			String highScore = HighScoreManager.getInstance().getHighScores().firstKey() + "";
			String score = GameManager.getInstance().getPlayer(0).getScore() + "";

			Assets.FONT.draw(batch, "High Score:", Constants.VIRTUAL_WIDTH/2 - 200, Constants.VIRTUAL_HEIGHT/2 + 10);
			Assets.FONT.draw(batch, highScore, Constants.VIRTUAL_WIDTH/2 + 80, Constants.VIRTUAL_HEIGHT/2 + 10);

			Assets.FONT.draw(batch, "Scored:", Constants.VIRTUAL_WIDTH/2 - 150, Constants.VIRTUAL_HEIGHT/2 - 100);
			Assets.FONT.draw(batch, score, Constants.VIRTUAL_WIDTH/2 + 50, Constants.VIRTUAL_HEIGHT/2 - 100);

		}else{
			Assets.FONT.draw(batch,"Ganador :" + wm.getWinner().getName(),Constants.VIRTUAL_WIDTH/2 - 120, Constants.VIRTUAL_HEIGHT/2 + 60);
		}
	}
	
	/**
	 * this is the screen when the game is paused
	 * @param batch
	 */
	private void drawPauseScreen(SpriteBatch batch){
		Assets.FONT.draw(batch, "Juego Pausado", 200, 400);
		Assets.SMALL_FONT.draw(batch, "Presione Enter para continuar", 100, 300);
	}
	/**
	 * this screen is drawn when in a Vs game the players have to change roles.
	 * @param batch
	 */
	private void drawImpasseScreen(SpriteBatch batch){
		Assets.FONT.draw(batch, "Cambio de posiciones", 200, 400);
		for(int i =0;i<wm.getNumberOfPlayers();i++){
			String aux = (wm.getPlayer(i).isSpaceShipPlayer())?"Nave":"Asteroides";
			String aux2 = "";
			if(wm.getPlayer(i).isAsteroidPlayer()){
				aux2 = "12346789";
			}else{
				if(wm.getAsteroidPlayer()!= null && wm.getNumberOfPlayers() == 2){
					aux2 = "WASD";
				}else{
					switch(i){
					case 0:
						aux2 = "WASD";
						break;
					case 1:
						aux2 = "Flechas";
						break;
					case 2:
						aux2 = "IJKL";
						break;

					}
				}
			}
			Assets.SMALL_FONT.draw(batch, wm.getPlayer(i).getName() + " Maneja " + aux + " Controles: " + aux2, 300, 50*i+ 50);
		}
	}
	public void addAsteroidUI(AsteroidUI a){
		aUI.add(a);
	}
}
