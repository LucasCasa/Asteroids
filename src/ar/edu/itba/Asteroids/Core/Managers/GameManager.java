package ar.edu.itba.Asteroids.Core.Managers;

import java.util.List;

import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1Player;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager2Player;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

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
	public void newGame(GameMode gm,List<SpaceShip> a){
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
	public WorldManager getWorld(){
		return world; 
	}
	public boolean isInMenu(){
		return isMenu;
	}
}
