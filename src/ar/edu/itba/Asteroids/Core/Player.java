package ar.edu.itba.Asteroids.Core;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
/**
 * This class has the information of the player and its spaceship and asteroidPlayer(in Vs)
 * @author ME
 *
 */
public class Player implements Logical {
	private String name;
	private SpaceShip spaceShip;
	private AsteroidPlayer asteroidP;
	private int playerNumber;
	private float score = 0f;
	private Timer timer;
	private boolean shipActive = true; // hay que ver como se da cuenta que es ap;
	private boolean asteroidActive = false; 
	
	/**
	 * This constructor is used when a new player that uses a ship is created 
	 * @param name; Name of the player
	 * @param s; the spaceShip the player is using
	 * @param PlayerNumber
	 */
	public Player(String name, SpaceShip s,int playerNumber) {
		this(name,playerNumber);
		spaceShip=s;
		asteroidP = null;
		shipActive = true;
	}
	/**
	 * This constructor is used when a new player that uses the asteroids is created
	 * @param name; Name of the player
	 * @param ap; AsteroidPayer the player is going to manage
	 * @param s; the SpaceShip the player is going to use in his turn
	 * @param playerNumber
	 */
	public Player(String name, AsteroidPlayer ap,SpaceShip s, int playerNumber, boolean startsAsteroid){
		this(name,playerNumber);
		asteroidP = ap;
		spaceShip = s;
		if(startsAsteroid){
			asteroidActive = true;
			shipActive = false;
			spaceShip.setActive(false);
		}
		
		
	}
	
	private Player(String name, int playerNumber){
		this.name = name;
		this.playerNumber = playerNumber;
		timer = new Timer();
	}
	/**
	 * 
	 * @return name of the player DUH
	 */
	public String getName(){
		return name;
	}

	public boolean isSpaceShipPlayer(){
		return shipActive;
	}
	public int getPlayerNumber(){
		return playerNumber;
	}
	
	public SpaceShip getSpaceShip(){
		return spaceShip;
	}
	public boolean isAsteroidPlayer(){
		return asteroidActive;
	}
	public boolean shipHasLost(){
		return shipActive && spaceShip.getLives() <= 0;
	}
	public AsteroidPlayer getAsteroidPlayer(){
		return asteroidP;
	}

	@Override
	public void update() {
		if(spaceShip == null){
			updateAsteroidPlayer();
		}else{
			updateSpaceShip();
		}
	}
	/**
	 * this method neeeds to be call when a player changes what is he controlling
	 */
	public void changeState(){
		shipActive = !shipActive;
		asteroidActive = !asteroidActive;
		if(shipActive){
			spaceShip.reset();
		}else{
			spaceShip.setActive(false);
		}
	}
	private void updateSpaceShip(){
		if(shipActive && spaceShip.isActive()){
			score += timer.getDeltaTime();
			timer.update();
			if(spaceShip.getLives()<=0){
				timer.reset();
				spaceShip.setActive(false);
			}else{
				spaceShip.update();
			}
		}
	}
	private void updateAsteroidPlayer(){
		asteroidP.update();
	}
	public float getScore() {
		return score;
	}
	
	/**
	 * Returs the score rounded by 2 decimals and in a String
	 * @return the Score
	 */
	public String getStringScore(){
		String score = String.valueOf(this.score);
		String aux[] = score.split("\\.");
		if(aux.length > 1){
		if(aux[1].length() > 2){
			aux[1] = aux[1].substring(0, 2);
		}
		return aux[0] + "."+ aux[1];
		}else{
			return score;
		}
	}
	public float getTime() {
		return timer.getTime();
	}
	/**
	 * it reset al the spaceShip logic ( called when playing 2vs1 and a spaceship plays 2 times)
	 */
	public void reset() {
		spaceShip.reset();
		
	}
}
