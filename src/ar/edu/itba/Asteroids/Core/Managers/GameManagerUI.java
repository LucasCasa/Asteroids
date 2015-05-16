package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1PlayerUI;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManagerUI;

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
			worldUI = new WorldManager1PlayerUI(GameManager.getInstance().getWorld());
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
