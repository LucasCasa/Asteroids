package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldManagerUI implements Drawable{

	WorldManager wm;
	public WorldManagerUI(WorldManager w){
		wm = w;
	}
	
	public void draw(SpriteBatch batch){
		for(SpaceShipUI s: wm.naveui){
			s.draw(batch);
		}
		for(AsteroidUI a : wm.eUI){
			a.draw(batch);
		}
	}
	
}
