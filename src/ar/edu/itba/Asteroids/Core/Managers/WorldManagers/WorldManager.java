package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.ArrayMap;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUp;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpCreator;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;

public abstract class WorldManager {
	public ArrayList<Connector<Asteroid,AsteroidUI>> asteroids;
	private ArrayList<Connector<PowerUp, PowerUpUI>> powerUps;
	protected SpaceShip first;
	protected SpaceShipUI firstUI;
	protected Timer timer;
	protected float score;
	protected boolean gameOver;
	private Timer powerUpTimer;
	private final float powerUpCooldown = 5f;
	private ArrayMap<SpaceShip,SpaceShipUI> ships;
	protected ArrayList<Player> players;
	/**
	 * 
	 * @param spaceshipAmount; amount of spaceShips in the game
	 * @param textures; textures of the spaceships. the first one is of the first player, second one of the second player
	 * and third one of the third player, depending on the amount of spaceShips that there is
	 */
	public WorldManager(ArrayList<Player> players){
		 asteroids = new ArrayList<Connector<Asteroid,AsteroidUI>>();
		 ships = new ArrayMap<SpaceShip,SpaceShipUI>();
		 timer = new Timer();
		 powerUpTimer = new Timer();
		 powerUps = new ArrayList<Connector<PowerUp, PowerUpUI>>();
		 this.players = players;
	}

	public void update(){
		updatePowerUps();
		updateSpaceships();
		for( Connector<Asteroid,AsteroidUI> a: asteroids){
			a.getBack().update();
		}
		updateAsteroidCollision();
		updatePowerUpCollision();
	}

	
	
	public ArrayList<AsteroidUI> getAsteroidsUI() {
		ArrayList<AsteroidUI> au = new ArrayList<AsteroidUI>();
		for(int i = 0; i<asteroids.size();i++){
			au.add(asteroids.get(i).getFront());
		}
		return au;
	}
	public ArrayList<PowerUpUI> getPowerUpUI(){
		ArrayList<PowerUpUI> pui = new ArrayList<PowerUpUI>();
		for(int i = 0; i<powerUps.size();i++){
			pui.add(powerUps.get(i).getFront());
		}
		return pui;
	}
	public ArrayList<SpaceShipUI> getShipsUI(){
		return ships.getValues(); 
	}
	public float getTime(){
		return timer.getTime();
	}
	

	public ArrayList<SpaceShip> getSpaceShips(){
		return ships.getKeys();
	}
	
	public ArrayMap<SpaceShip, SpaceShipUI> getAll(){
		return ships;
	}
	
	/**
	 * 
	 * @return null if there is no asteroid player in that mode (there is an AI Asteroid player) or returns the player which is controlling the asteroids
	 
	public abstract AsteroidPlayer getAsteroidPlayer();
	*/
	public void keyDown(int keyCode) {	
		keyDown(keyCode, 0);
	}
	public void keyUp(int keyCode) {
		keyUp(keyCode,0);
	}

	public void addAsteroid(Asteroid thrown, AsteroidUI asteroidUI) {
		asteroids.add(new Connector<Asteroid,AsteroidUI>(thrown,asteroidUI));
		
	}
	public boolean getGameOver(){
		return gameOver;
	}
	// CHECKEAR ESTO CON EL TEMA DE LOS PLAYERS
	public void keyDown(int keyCode, int activeSpaceShip) {
		switch (keyCode) {
		case Keys.W:
			ships.getKeyAt(activeSpaceShip).acelUp(true);
			break;
		case Keys.S:
			ships.getKeyAt(activeSpaceShip).acelDown(true);
			break;
		case Keys.A:
			ships.getKeyAt(activeSpaceShip).acelLeft(true);
			break;
		case Keys.D:
			ships.getKeyAt(activeSpaceShip).acelRight(true);
			break;
		}
		
	}

	public void keyUp(int keyCode, int activeSpaceShip) {
		switch (keyCode) {
		case Keys.W:
			ships.getKeyAt(activeSpaceShip).acelUp(false);
			break;
		case Keys.S:
			ships.getKeyAt(activeSpaceShip).acelDown(false);
			break;
		case Keys.A:
			ships.getKeyAt(activeSpaceShip).acelLeft(false);
			break;
		case Keys.D:
			ships.getKeyAt(activeSpaceShip).acelRight(false);
			break;
		}	
		
	}

	public AsteroidPlayer getAsteroidPlayer() {
		for(Player p: players){
			if(!p.isSpaceShipPlayer()){
				return p.getAsteroidPlayer();
			}
		}
		return null;
	}

	public void updateSpaceships(){
		for(Player p: players){
			p.update();
		}
		//this for checks if the spaceships are collisioning
		/*for(int i=0; i<ships.size();i++){
			SpaceShip aux = ships.getKeyAt(i); //you can always do this because you always have at least one spaceship
			for(int j=i+1; j<this.ships.size();j++){
				if(aux.isActive() && ships.getKeyAt(j).isActive()){
					aux.shipCollision(ships.getKeyAt(j));
				}
			}
		}*/
		for(int i = 0; i<players.size();i++){
			if(players.get(i).isSpaceShipPlayer() && !players.get(i).shipHasLost()){
				SpaceShip aux = players.get(i).getSpaceShip();
				for(int j = i+1;j<players.size();j++){
					if(players.get(j).isSpaceShipPlayer() && !players.get(j).shipHasLost()){
						aux.shipCollision(players.get(j).getSpaceShip());
					}
				}
			}
		}
	}
	
	public void updateAsteroidCollision(){
		for(int i = 0; i<asteroids.size();i++){
			Asteroid aux = asteroids.get(i).getBack();
			for(int j = i+1; j < asteroids.size();j++){
				aux.asteroidCollision(asteroids.get(j).getBack());
			}
			if(aux.outOfScreen()){
				asteroids.remove(i);
			}
			for(Player p : players){
				if(p.isSpaceShipPlayer() && !p.shipHasLost() && p.getSpaceShip().shipCollision(aux)){
					asteroids.remove(i);
				}
			}
		}
	}
	
	public void updatePowerUps(){
		powerUpTimer.update();
		if(powerUpTimer.getTime() > powerUpCooldown){
			powerUps.add(PowerUpCreator.create());
			powerUpTimer.reset();
		}
		for(Connector<PowerUp, PowerUpUI> p: powerUps){
			p.getBack().update();
		}
	}
	
	public void updatePowerUpCollision(){
		Connector<PowerUp, PowerUpUI> con;
		for(int i=0;i<powerUps.size();i++){
			con = powerUps.get(i);

			for(Connector<Asteroid, AsteroidUI> a: asteroids){
				if(con.getBack().collision(a.getBack()))
					powerUps.remove(con);

			}
			
			for(Player p: players){
				if(p.isSpaceShipPlayer()){
					if(con.getBack().collision(p.getSpaceShip()) && powerUps.contains(con)){
						con.getBack().effect(p.getSpaceShip());
						powerUps.remove(con);
					}
				}
			}
		}
	}
	public Player getPlayer(int index){
		return players.get(index);
	}
	public abstract Player getWinner();
}

