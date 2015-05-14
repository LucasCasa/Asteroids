package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class HUDManager implements Drawable{
	
	public HUDManager(){
		
	}
	
	public abstract void draw(SpriteBatch batch);
	
	public void drawShipPlayer(SpriteBatch batch,Vector2 pos ,int playernum,int lives){
		Assets.SMALL_FONT.draw(batch, "Player " + playernum + ":" , pos.x, pos.y);
		for(int j=0; j<lives; j++){
			batch.draw(Assets.HEART, (float)(pos.x + 50*j),(float)(pos.y - 50), 35,35);
		}
	}
	public void drawAsteroidPlayer(SpriteBatch batch, Vector2 pos, int playernum, float cooldown, int reserve){
		Assets.SMALL_FONT.draw(batch, "Player " + playernum + ":" , pos.x, pos.y);
		// dibujo la barrita del cooldown
	}
}
