package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldManagerUI implements Drawable{

	WorldManager wm;
	public WorldManagerUI(WorldManager w){
		wm = w;
	}
	
	public void draw(SpriteBatch batch){
		wm.firstUI.draw(batch);
		for(Asteroid a : wm.e){
			a.draw(batch);
		}
	}
	
}
