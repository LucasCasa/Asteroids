package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.HUDs.HUDManager;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class WorldManagerUI implements Drawable{
	HUDManager h;
	WorldManager wm;
	
	public WorldManagerUI(WorldManager w){
		wm = w;
	}
	
	public void draw(SpriteBatch batch){
		for(SpaceShipUI s: wm.getShipsUI()){
			s.draw(batch);
		}
		for(AsteroidUI a : wm.getAsteroidsUI()){
			a.draw(batch);
		}
		h.draw(batch);
	}
	
}
