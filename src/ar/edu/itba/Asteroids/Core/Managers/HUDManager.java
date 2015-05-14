package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUDManager implements Drawable{

	private static HUDManager instance = null;
	
	public static HUDManager getInstance(){
		if(instance==null){
			instance = new HUDManager();
		}
		return instance;
	}
	
	private HUDManager(){
		
	}
	
	public void draw(SpriteBatch batch) {
		drawLives(batch);
	}
	
	public void drawLives(SpriteBatch batch){
		ArrayList<SpaceShip> ships = WorldManager.getInstance().getNaves();
		for(int i=0; i<ships.size();i++){
			Assets.FONT.draw(batch, "Player " + (i+1) + ":" , 250*i, 60);
			for(int j=0; j<ships.get(i).getLives(); j++){
				batch.draw(Assets.HEART, 250*i+50*j, 5, 35,35);
			}
		}
	}
}
