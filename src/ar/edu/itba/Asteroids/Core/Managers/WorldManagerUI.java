package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldManagerUI implements Drawable{
	HUDManager h;
	WorldManager wm;
	public WorldManagerUI(WorldManager w){
		wm = w;
		h = new HUD3Player(w.getNaves().get(0), w.getNaves().get(1),w.getNaves().get(2));
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
