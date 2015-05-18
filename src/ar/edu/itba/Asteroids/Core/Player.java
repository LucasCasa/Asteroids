package ar.edu.itba.Asteroids.Core;

import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

public class Player implements Logical {
	private String name;
	private SpaceShip s;
	private AsteroidPlayer a;
	private int playerNumber;
	private float score = 0f;
	private Timer timer;
	
	/**
	 * 
	 * @param name; Name of the player
	 * @param s; the spaceShip the player is using
	 * @param AP; if the player is an asteroid Player or not
	 * @param PlayerNumber
	 */
	public Player(String name, SpaceShip s,int PlayerNumber) {
		this.name=name;
		this.s=s;
		this.playerNumber=PlayerNumber;
	}
	 
	/**
	 * 
	 * @return name of the player
	 */
	public String getName(){
		return name;
	}
	
	public int getPlayerNumber(){
		return playerNumber;
	}
	
	public SpaceShip getSpaceShip(){
		return s;
	}

	@Override
	public void update() {
		if(s.getLives()<=0){
			this.score += timer.getTime();
			timer.reset();
		}
	}
}
