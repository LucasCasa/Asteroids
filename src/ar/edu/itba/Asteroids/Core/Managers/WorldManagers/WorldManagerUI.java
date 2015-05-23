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
		}else if(wm.isImpasse()){
			Assets.FONT.draw(batch, "Cambio de posiciones", 200, 400);
			for(int i =0;i<wm.getNumberOfPlayers();i++){
				String aux = (wm.getPlayer(i).isSpaceShipPlayer())?"Nave":"Asteroides";
				String aux2 = "";
				if(wm.getPlayer(i).isAsteroidPlayer()){
					aux2 = "12346789";
				}else{
					if(wm instanceof WorldManager2PlayersVs){ // FEO FEO
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
		}else if(wm.isPaused()){
			Assets.FONT.draw(batch, "Juego Pausado", 200, 400);
			Assets.SMALL_FONT.draw(batch, "Presione Enter para continuar", 100, 300);
		}else{

			for(SpaceShipUI s: shipsUI){
				s.draw(batch);
			}
			for(AsteroidUI a : wm.getAsteroidsUI()){
				a.draw(batch);
			}
			for(PowerUpUI p: wm.getPowerUpUI()){
				p.draw(batch);
			}
			h.draw(batch);
		}
	}

}
