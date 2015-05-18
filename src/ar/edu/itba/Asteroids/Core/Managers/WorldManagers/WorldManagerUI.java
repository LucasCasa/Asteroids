package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUDManager;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

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
