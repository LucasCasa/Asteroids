package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1Player;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class GameManager {
	private static GameManager self = null;
	private WorldManager world;
	private boolean isMenu = true;
	private GameManager(){
		
	}
	public static GameManager getInstance(){
		if(self == null){
			self = new GameManager();
		}
		return self;
	}
	public void newGame(GameMode gm,List<Connector<SpaceShip,SpaceShipUI>> a){
		switch(gm){
		case OnePlayer:
			world = new WorldManager1Player(a.get(0));
			break;
		case TwoPlayersA:
			//world = new WorldManager2Player(spaceshipAmount, textures);
			break;
		}
	}
	public void update(){
		if(isMenu){
			MenuManager.getInstance().update();
		}else{
			world.update();
		}
	}
	public void keyDown(int keyCode){
		if(isMenu){
			MenuManager.getInstance().keyDown(keyCode);
		}else{
			world.keyDown(keyCode);
		}
	}
	public void keyUp(int keyCode){
		if(!isMenu){
			world.keyUp(keyCode);
		}
	}
	public WorldManager getWorld(){
		return world; 
	}
	public boolean isInMenu(){
		return isMenu;
	}
	public float getTime(){
		return world.getTime();
	}
}
