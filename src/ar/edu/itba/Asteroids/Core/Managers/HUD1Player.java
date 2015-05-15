package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HUD1Player extends HUDManager{
	private SpaceShip player;
	
	public HUD1Player(SpaceShip p){
		player = p;
	}
	
	public void draw(SpriteBatch batch){
		drawShipPlayer(batch, new Vector2(0,Gdx.graphics.getWidth() - 50), 1, player.getLives());
	}

}
