package ar.edu.itba.Asteroids.Core.Managers.HUDs;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class HUDManager implements Drawable{
	private final int HORIZONTAL_OFFSET = 120;
	public HUDManager(){
		
	}
	
	public abstract void draw(SpriteBatch batch);
	/**
	 * este meotodo se encarga de dibujar el nombre y vidas de la nave del jugador que se le pasa en una
	 * determinada posicion.
	 * @param batch HOLA MAGGIE
	 * @param pos la posicion de la pantalla en la cual dibuja
	 * @param p el jugador del que se saca la informacion
	 */
	private void drawShipPlayer(SpriteBatch batch,Vector2 pos ,Player p){
		Assets.SMALL_FONT.draw(batch,p.getName() +":" , pos.x, pos.y);
		batch.draw(Assets.HEART, (float)(pos.x),(float)(pos.y - Assets.TEXT_SEPARATOR), Assets.ICON_SIZE,Assets.ICON_SIZE);
		Assets.SMALL_FONT.draw(batch, "x " + p.getSpaceShip().getLives(), pos.x + Assets.ICON_SIZE + Assets.HORIZONTAL_OFFSET , pos.y - 25);
		if(p.getSpaceShip().getInvincible()){
			float heightPercent =  p.getSpaceShip().getInviTimer().getTime() / p.getSpaceShip().getInvincibleTotalTime();
			batch.draw(Assets.INVIICON, (float)(pos.x) + 80,(float)(pos.y - 25), Assets.ICON_SIZE, (int)(Assets.INVIICON.getHeight() *(1 - heightPercent)), 0, 0, Assets.ICON_SIZE, (int)(Assets.INVIICON.getHeight() *(1 - heightPercent)), false, true);
		}
	}
	/**
	 * dibuja el nombre la cantidad de asteroides y el tiempo que falta hasta que consiga
	 * un asteroide del jugador que se le pasa
	 * @param batch
	 * @param pos la posicion donde se imprime
	 * @param name Nombre del jugador
	 * @param cooldown relacion entre el tiempo trascurrido y el tiempo que tiene que transcurrir hasta que se 
	 * agregue un nuevo asteroides
	 * @param reserve cantidad de asteroides disponibles
	 */
	private void drawAsteroidPlayer(SpriteBatch batch, Vector2 pos, String name, float cooldown, int reserve){
		Assets.SMALL_FONT.draw(batch, name + ":", pos.x, pos.y);
		batch.draw(Assets.COOLDOWN,pos.x,pos.y - 40,(int)(Assets.COOLDOWN.getWidth() * cooldown),15,(int)(Assets.COOLDOWN.getWidth() * cooldown),Assets.COOLDOWN.getHeight());
		Assets.SMALL_FONT.draw(batch, "x " + reserve, pos.x + Assets.COOLDOWN.getWidth() + 5, pos.y - 28);
	}
	/**
	 *  dibuja el player en la posicion 1 ( arriba a la izquierda)
	 * @param batch
	 * @param p player
	 */
	public void drawSector1(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(0,Assets.VIRTUAL_HEIGHT - Assets.VERTICAL_OFFSET);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	/**
	 * dibuja el player en la posicion 2 ( arriba a la derecha)
	 * @param batch
	 * @param p
	 */
	public void drawSector2(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(Assets.VIRTUAL_WIDTH - HORIZONTAL_OFFSET,Assets.VIRTUAL_HEIGHT - Assets.VERTICAL_OFFSET);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	/** 
	 * dibuja el player en la posicion 3 (abajo a la derecha)
	 * @param batch
	 * @param p
	 */
	public void drawSector3(SpriteBatch batch, Player p){
		Vector2 pos = new Vector2(Assets.VIRTUAL_WIDTH - HORIZONTAL_OFFSET,3 *Assets.VERTICAL_OFFSET);
		if(p.isSpaceShipPlayer()){
			if(!p.shipHasLost()){
				drawShipPlayer(batch, pos, p);
			}
		} else if(p.isAsteroidPlayer()){
			drawAsteroidPlayer(batch, pos, p.getName(), p.getAsteroidPlayer().getTimePercentage(), p.getAsteroidPlayer().getReserve());
		}
	}
	
}
