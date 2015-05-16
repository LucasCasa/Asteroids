package ar.edu.itba.Asteroids.Core.Managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1Player;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class GameManager {
	private static GameManager self = null;
	private WorldManager world;
	private boolean isMenu = true;
	private Map<Integer,Connector<SpaceShip,SpaceShipUI>> s;
	
	private GameManager(){
		s = new HashMap<Integer,Connector<SpaceShip,SpaceShipUI>>();
	}
	public static GameManager getInstance(){
		if(self == null){
			self = new GameManager();
		}
		return self;
	}
	public void newGame(GameMode gm){
		switch(gm){
		case OnePlayer:
			world = new WorldManager1Player(s.get(1));
			break;
		case TwoPlayersA:
			//world = new WorldManager2Player(spaceshipAmount, textures);
			break;
		}
		isMenu = false;
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
	public void addSpaceShip(int player, Connector<SpaceShip,SpaceShipUI> s){
		this.s.put(player, s);
	}
}
