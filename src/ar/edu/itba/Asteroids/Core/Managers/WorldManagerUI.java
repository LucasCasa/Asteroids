package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldManagerUI implements Drawable{

	WorldManager wm;
	public WorldManagerUI(WorldManager w){
		wm = w;
	}
	
	public void draw(SpriteBatch batch){
		wm.firstUI.draw(batch);
		for(AsteroidUI a : wm.eUI){
			a.draw(batch);
		}
	}
	
}
