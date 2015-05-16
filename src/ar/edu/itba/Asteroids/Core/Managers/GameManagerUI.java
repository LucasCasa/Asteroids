package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD1Player;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1PlayerUI;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManagerUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManagerUI implements Drawable{
	private static GameManagerUI self = null;
	private WorldManagerUI worldUI;
	private GameManagerUI(){
		
	}
	public static GameManagerUI getInstance(){
		if(self == null){
			self = new GameManagerUI();
		}
		return self;
	}
	public void newGame(GameMode gm){
		switch(gm){
		case OnePlayer:
			SpaceShip aux = GameManager.getInstance().getWorld().getSpaceShips().get(0);
			worldUI = new WorldManager1PlayerUI(GameManager.getInstance().getWorld(),new HUD1Player(aux));
			break;
		}
	}
	
	public void draw(SpriteBatch batch){
		if(GameManager.getInstance().isInMenu()){
			MenuManagerUI.getInstance().draw(batch);
		}else{
			worldUI.draw(batch);
		}
	}
}
