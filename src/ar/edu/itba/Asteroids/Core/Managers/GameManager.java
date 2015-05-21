package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1Player;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager2Player;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager2PlayersVs;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager3Player;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager3Players2vs1;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class GameManager {
	private static GameManager self = null;
	private WorldManager world;
	private boolean isMenu = true;
	private Map<Integer,Connector<SpaceShip,SpaceShipUI>> s;
	private ArrayList<Player> players = new ArrayList<Player>();
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
			world = new WorldManager1Player(s.get(1),players);
			break;
		case TwoPlayersA:
			world = new WorldManager2Player(new ArrayList<Connector<SpaceShip, SpaceShipUI>>(s.values()),players);
			break;
		case TwoPlayersB:
			world = new WorldManager2PlayersVs(new ArrayList<Connector<SpaceShip, SpaceShipUI>>(s.values()),players);
			break;
		case ThreePlayersA:
			world = new WorldManager3Player(new ArrayList<Connector<SpaceShip, SpaceShipUI>>(s.values()),players);
			break;
		case ThreePlayersB:
			world = new WorldManager3Players2vs1(new ArrayList<Connector<SpaceShip, SpaceShipUI>>(s.values()),players);
		}
		isMenu = false;
	}
	public void update(){
		if(isMenu){
			MenuManager.getInstance().update();
		}else{
			world.update();	
			if(world.isOver()){
				isMenu = true;
				MenuManager.getInstance().reset();
				players = new ArrayList<Player>();
			}
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
	public void addSpaceShip(int player, Connector<SpaceShip,SpaceShipUI> s, boolean createAsteroidPlayer,boolean statsAsteroid, String name){
		this.s.put(player, s);
		if(createAsteroidPlayer){
			players.add(new Player(name, new AsteroidPlayer(), s.getBack(), player,statsAsteroid));
		}else{
			players.add(new Player(name, s.getBack(), player));
		}
	}
	public Player getPlayer(int i) {
		return players.get(i);
	}
}
