package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;
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
		drawSector1(batch, player);
		Assets.SMALL_FONT.draw(batch,"Tiempo:"+ GameManager.getInstance().getTime() ,0, 15);
	}

}
