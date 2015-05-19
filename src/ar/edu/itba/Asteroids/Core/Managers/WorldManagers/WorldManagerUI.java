package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUDManager;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldManagerUI implements Drawable{
	HUDManager h;
	WorldManager wm;
	List<AsteroidUI> aUI;
	
	public WorldManagerUI(WorldManager w, HUDManager h){
		wm = w;
		this.h = h;
		aUI = new ArrayList<AsteroidUI>();
	}
	
	public void draw(SpriteBatch batch){
	    if ( wm.getGameOver() ) {// esto hay que cambiarlo, por ahora esta asi para probarse
	        String highScore = Assets.getHighScore() + "";
	        String score = wm.score + "";
	        
	        Assets.FONT.draw(batch, "Game Over", Gdx.graphics.getWidth()/2 - 120, Gdx.graphics.getHeight()/2 + 100);
	        Assets.FONT.draw(batch,"Ganador :" + wm.getWinner().getName(),Gdx.graphics.getWidth()/2 - 120, Gdx.graphics.getHeight()/2 + 60);
	        Assets.FONT.draw(batch, "High Score:", Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight()/2 + 10);
	        Assets.FONT.draw(batch, highScore, Gdx.graphics.getWidth()/2 + 80, Gdx.graphics.getHeight()/2 + 10);
	        
	        Assets.FONT.draw(batch, "Scored:", Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 100);
	        Assets.FONT.draw(batch, score, Gdx.graphics.getWidth()/2 + 50, Gdx.graphics.getHeight()/2 - 100);
	    }else{
		for(SpaceShipUI s: wm.getShipsUI()){
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
