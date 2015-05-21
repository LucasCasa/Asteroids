package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class HUDManager implements Drawable{
	
	public HUDManager(){
		
	}
	
	public abstract void draw(SpriteBatch batch);
	
	private void drawShipPlayer(SpriteBatch batch,Vector2 pos ,Player p){
		Assets.SMALL_FONT.draw(batch,p.getName() +":" , pos.x, pos.y);
		batch.draw(Assets.HEART, (float)(pos.x),(float)(pos.y - 50), 35,35);
		Assets.SMALL_FONT.draw(batch, "x " + p.getSpaceShip().getLives(), pos.x + 40 , pos.y - 25);
		if(p.getSpaceShip().getInvincible()){
			float heightPercent =  p.getSpaceShip().getInviTimer().getTime() / p.getSpaceShip().getInvincibleTotalTime();
			batch.draw(Assets.INVIICON, (float)(pos.x) + 80,(float)(pos.y - 25), 35, (int)(Assets.INVIICON.getHeight() *(1 - heightPercent)), 0, 0, 35, (int)(Assets.INVIICON.getHeight() *(1 - heightPercent)), false, true);
		}
	}
	
	private void drawAsteroidPlayer(SpriteBatch batch, Vector2 pos, String name, float cooldown, int reserve){
		Assets.SMALL_FONT.draw(batch, name + ":", pos.x, pos.y);
		batch.draw(Assets.COOLDOWN,pos.x,pos.y - 40,(int)(Assets.COOLDOWN.getWidth() * cooldown),15,(int)(Assets.COOLDOWN.getWidth() * cooldown),Assets.COOLDOWN.getHeight());
		Assets.SMALL_FONT.draw(batch, "x " + reserve, pos.x + Assets.COOLDOWN.getWidth() + 5, pos.y - 28);
	}
	public void drawSector1(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(0,Assets.VIRTUAL_HEIGHT - 15);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	public void drawSector2(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(Assets.VIRTUAL_WIDTH - 120,Assets.VIRTUAL_HEIGHT - 15);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	public void drawSector3(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(Assets.VIRTUAL_WIDTH - 120,50);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	
}
