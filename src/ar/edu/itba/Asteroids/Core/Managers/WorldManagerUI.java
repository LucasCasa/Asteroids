package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.HUDs.HUD3Player;
import ar.edu.itba.Asteroids.Core.HUDs.HUD3Players2vs1;
import ar.edu.itba.Asteroids.Core.HUDs.HUDManager;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldManagerUI implements Drawable{
	HUDManager h;
	WorldManager wm;
	public WorldManagerUI(WorldManager w){
		wm = w;
		h = new HUD3Players2vs1(wm.getNaves().get(0),wm.getNaves().get(1),wm.getAsteroidPlayer());
	}
	
	public void draw(SpriteBatch batch){
		for(SpaceShipUI s: wm.naveui){
			s.draw(batch);
		}
		for(AsteroidUI a : wm.eUI){
			a.draw(batch);
		}
		h.draw(batch);
	}
	
}
