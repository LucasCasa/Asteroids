package ar.edu.itba.Asteroids.Core.Managers;

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
		batch.draw(Assets.HEART, (float)(pos.x),(float)(pos.y - 50), 35,35);
		Assets.SMALL_FONT.draw(batch, "x " + lives, pos.x + 35 + 5 , pos.y - 25);
	}
	
	public void drawAsteroidPlayer(SpriteBatch batch, Vector2 pos, int playernum, float cooldown, int reserve){
		Assets.SMALL_FONT.draw(batch, "Player " + playernum + ":" , pos.x, pos.y);
		// dibujo la barrita del cooldown
	}
	public void drawSector1(SpriteBatch batch, SpaceShip s){
		drawShipPlayer(batch, new Vector2(0,Gdx.graphics.getHeight() - 15), 1,s.getLives());
	}
	public void drawSector2(SpriteBatch batch, SpaceShip s){
		drawShipPlayer(batch, new Vector2(Gdx.graphics.getWidth() - 120,Gdx.graphics.getHeight() - 15), 2,s.getLives());
	}
	public void drawSector3(SpriteBatch batch, SpaceShip s){
		drawShipPlayer(batch, new Vector2(Gdx.graphics.getWidth() - 120,50), 3,s.getLives());
	}
	/* Falta el humanAsteroid que va a tener todo el control de los asteroides.
	 * public void drawSector1(HumanAsteroid  a){
		
	}
	public void drawSector2(HumanAsteroid a){
		
	}
	public void drawSector3(HumanAsteroid a){
		
	}*/
}
