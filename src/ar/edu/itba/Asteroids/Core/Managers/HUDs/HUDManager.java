package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * the base class for all the HUDManagers
 * @author ME
 *
 */
public abstract class HUDManager implements Drawable{
	private final int HORIZONTAL_OFFSET = 120;
	public HUDManager(){

	}

	public abstract void draw(SpriteBatch batch);

	/**
	 * this method is responsible for drawing the names and lives of the player's ship that is being passed in a certain position .
	 * @param batch
	 * @param pos the position of the screen in which you draw
	 * @param p the player from which the information is pulled
	 */
	private void drawShipPlayer(SpriteBatch batch,Vector2 pos ,Player p){
		Assets.SMALL_FONT.draw(batch,p.getName() +":" , pos.x, pos.y);
		batch.draw(Assets.HEART, (float)(pos.x),(float)(pos.y - Constants.TEXT_SEPARATOR), Constants.ICON_SIZE,Constants.ICON_SIZE);
		Assets.SMALL_FONT.draw(batch, "x " + p.getSpaceShip().getLives(), pos.x + Constants.ICON_SIZE + Constants.HORIZONTAL_OFFSET , pos.y - 25);
		if(p.getSpaceShip().getInvincible()){
			float heightPercent =  p.getSpaceShip().getInviTimer().getTime() / p.getSpaceShip().getInvincibleTotalTime();
			batch.draw(Assets.INVIICON, (float)(pos.x) + 80,(float)(pos.y - 25), Constants.ICON_SIZE, (int)(Assets.INVIICON.getHeight() *(1 - heightPercent)), 0, 0, Constants.ICON_SIZE, (int)(Assets.INVIICON.getHeight() *(1 - heightPercent)), false, true);
		}
	}
	/**
	 * draws the name,the amount of asteroids and the time left until you can get a new asteroid in the player that is being passed
	 * @param batch
	 * @param pos; the position where it is drawn
	 * @param name; name of the player
	 * @param cooldown; the relation between the elapsed time and the time that must pass until a new asteroid is added
	 * @param reserve; the amount of asteroids available
	 */
	private void drawAsteroidPlayer(SpriteBatch batch, Vector2 pos, String name, float cooldown, int reserve){
		Assets.SMALL_FONT.draw(batch, name + ":", pos.x, pos.y);
		batch.draw(Assets.COOLDOWN,pos.x,pos.y - 40,(int)(Assets.COOLDOWN.getWidth() * cooldown),15,(int)(Assets.COOLDOWN.getWidth() * cooldown),Assets.COOLDOWN.getHeight());
		Assets.SMALL_FONT.draw(batch, "x " + reserve, pos.x + Assets.COOLDOWN.getWidth() + 5, pos.y - 28);
	}
	/**
	 *  draws the player in the first position(top left corner)
	 * @param batch
	 * @param p player
	 */
	public void drawSector1(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(0,Constants.VIRTUAL_HEIGHT - Constants.VERTICAL_OFFSET);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	/**
	 * draws the player in the second positon (top right corner)
	 * @param batch
	 * @param p;player
	 */
	public void drawSector2(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(Constants.VIRTUAL_WIDTH - HORIZONTAL_OFFSET,Constants.VIRTUAL_HEIGHT - Constants.VERTICAL_OFFSET);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	/** 
	 * draws the player in the third positon(bottom right corner)
	 * @param batch
	 * @param p;player
	 */
	public void drawSector3(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(Constants.VIRTUAL_WIDTH - HORIZONTAL_OFFSET,3 *Constants.VERTICAL_OFFSET);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}

}
