package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.ArrayMap;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Timer;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUp;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpCreator;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Input.Keys;

public abstract class WorldManager {
	public ArrayMap<Asteroid,AsteroidUI> asteroids;
	private ArrayMap<PowerUp, PowerUpUI> powerUps;
	protected Timer timer;
	protected float score;
	protected boolean gameOver;
	private Timer powerUpTimer;
	private final float powerUpCooldown = 5f;
	protected ArrayList<Player> players;
	protected boolean impasse;
	private boolean pause;
	private boolean gotoMenu;
	/**
	 * 
	 * @param spaceshipAmount; amount of spaceShips in the game
	 * @param textures; textures of the spaceships. the first one is of the first player, second one of the second player
	 * and third one of the third player, depending on the amount of spaceShips that there is
	 */
	public WorldManager(ArrayList<Player> players){
		 asteroids = new ArrayMap<Asteroid,AsteroidUI>();
		 timer = new Timer();
		 powerUpTimer = new Timer();
		 powerUps = new ArrayMap<PowerUp, PowerUpUI>();
		 this.players = players;
	}

	public void update(){
		if(!gameOver){
			if(impasse || pause){
				if(impasse){
					powerUps = new ArrayMap<PowerUp, PowerUpUI>();
				}
			}else{
				updatePowerUps();
				updateSpaceships();
				for( Asteroid a: asteroids.getKeys()){
					a.update();
				}
				updateAsteroidCollision();
				updatePowerUpCollision();
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
		for(PowerUp p: powerUps.getKeys()){
			p.update();
		}
	}
	
	public void updatePowerUpCollision(){
		PowerUp con;
		for(int i=0;i<powerUps.size();i++){
			con = powerUps.getKeyAt(i);

			for(Asteroid a: asteroids.getKeys()){
				if(con.collision(a)){
					powerUps.remove(con);
				}
			}
			
			for(Player p: players){
				if(p.isSpaceShipPlayer()){
					if(con.collision(p.getSpaceShip()) && powerUps.contains(con)){
						con.effect(p.getSpaceShip());
						powerUps.remove(con);
					}
				}
			}
		}
	}
	public void updateSpaceships(){
		for(Player p: players){
			p.update();
		}
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

	public void keyDown(int keyCode) {	
		keyDown(keyCode, 0);
	}
	public void keyUp(int keyCode) {
		keyUp(keyCode,0);
	}

	public void addAsteroid(Asteroid thrown, AsteroidUI asteroidUI) {
		asteroids.put(thrown, asteroidUI);
		
	}

	public void keyDown(int keyCode, int activeSpaceShip) {
		if(impasse){
			if(keyCode == Keys.ENTER){
				impasse = false;
			}
		}else if(pause){
			if(keyCode == Keys.ENTER){
				pause = false;
			}else if(keyCode == Keys.ESCAPE){
				gameOver = true;
				pause = false;
			}
		}else if(gameOver && keyCode == Keys.ESCAPE){
			gotoMenu = true;
		}else{
			switch (keyCode) {
			case Keys.W:
				players.get(activeSpaceShip).getSpaceShip().acelUp(true);
				break;
			case Keys.S:
				players.get(activeSpaceShip).getSpaceShip().acelDown(true);
				break;
			case Keys.A:
				players.get(activeSpaceShip).getSpaceShip().acelLeft(true);
				break;
			case Keys.D:
				players.get(activeSpaceShip).getSpaceShip().acelRight(true);
				break;
			case Keys.ESCAPE:
				pause = true;
				break;
			}
		}
	}

	public void keyUp(int keyCode, int activeSpaceShip) {
		switch (keyCode) {
		case Keys.W:
			players.get(activeSpaceShip).getSpaceShip().acelUp(false);
			break;
		case Keys.S:
			players.get(activeSpaceShip).getSpaceShip().acelDown(false);
			break;
		case Keys.A:
			players.get(activeSpaceShip).getSpaceShip().acelLeft(false);
			break;
		case Keys.D:
			players.get(activeSpaceShip).getSpaceShip().acelRight(false);
			break;
		}	
		
	}
	public ArrayList<SpaceShip> getSpaceShips(){
		ArrayList<SpaceShip> aux = new ArrayList<SpaceShip>();
		for(Player p: players){
			if(p.isSpaceShipPlayer()){
				aux.add(p.getSpaceShip());
			}
		}
		return aux;
	}
	/**
	 * 
	 * @return null if there is no asteroid player in that mode (there is an AI Asteroid player)
	 *  or returns the player which is controlling the asteroids
	*/
	public AsteroidPlayer getAsteroidPlayer() {
		for(Player p: players){
			if(!p.isSpaceShipPlayer()){
				return p.getAsteroidPlayer();
			}
		}
		return null;
	}
	public ArrayList<AsteroidUI> getAsteroidsUI() {
		return asteroids.getValues();
	}
	public ArrayList<PowerUpUI> getPowerUpUI(){
		return powerUps.getValues();
	}
	public float getTime(){
		return timer.getTime();
	}
	public Player getPlayer(int index){
		return players.get(index);
	}
	public boolean isImpasse(){
		return impasse;
	}
	public void continueGame(){
		impasse = false;
	}
	public int getNumberOfPlayers(){
		return players.size();
	}
	public abstract Player getWinner();

	public boolean isPaused() {
		return pause;
	}
	public boolean isOver(){
		return gotoMenu;
	}
	public boolean gameEnded(){
		return gameOver;
	}
}

