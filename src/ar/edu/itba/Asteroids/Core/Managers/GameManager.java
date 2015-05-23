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
/**
 * The core of the game. it calls other object based on the state of the game
 * @author ME
 *
 */
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
	/**
	 * Define el worldManager a usar dependiendo del tipo de juego.
	 * @param gm
	 */
	public void newGame(GameMode gm){
		switch(gm){
		case OnePlayer:
			world = new WorldManager1Player(players);
			break;
		case TwoPlayersA:
			world = new WorldManager2Player(players);
			break;
		case TwoPlayersB:
			world = new WorldManager2PlayersVs(players);
			break;
		case ThreePlayersA:
			world = new WorldManager3Player(players);
			break;
		case ThreePlayersB:
			world = new WorldManager3Players2vs1(players);
		}
		isMenu = false;
	}
	/**
	 * Se encarga de llamar a las demas clases para que se updateen
	 */
	public void update(){
		if(isMenu){
			MenuManager.getInstance().update();
		}else{
			if(!world.gameEnded()){//hay que preguntar si termino el juego porque sino el world se sique updatando y agrega infitos jugadores iguales al highscore
				world.update();	
			}
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
	/**
	 * añade una spaceship para manejar en el juego
	 * @param player numero de jugador
	 * @param s la nave y su naveUI
	 * @param createAsteroidPlayer si tambien maneja asteroide
	 * @param statsAsteroid si en el primer juego es el que maneja los asteroides
	 * @param name nombre DUH
	 */
	public void addSpaceShip(int player, Connector<SpaceShip,SpaceShipUI> s, boolean createAsteroidPlayer,boolean statsAsteroid, String name){
		this.s.put(player, s);
		if(createAsteroidPlayer){
			players.add(new Player(name, new AsteroidPlayer(), s.getBack(), player,statsAsteroid));
		}else{
			players.add(new Player(name, s.getBack(), player));
		}
	}
	/**
	 * returns the player in the desire position
	 * @param i
	 * @return the player if exists, null if outOfBounds
	 */
	public Player getPlayer(int i) {
		if(i<players.size()){
			return players.get(i);
		}
		return null;
	}
	public ArrayList<SpaceShipUI> getShipsUI(){
		ArrayList<SpaceShipUI> ships = new ArrayList<SpaceShipUI>();
		for(Connector<SpaceShip,SpaceShipUI> aux : s.values()){
			ships.add(aux.getFront());
		}
		return ships;
	}
}
