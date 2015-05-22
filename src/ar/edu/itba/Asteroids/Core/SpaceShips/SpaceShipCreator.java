package ar.edu.itba.Asteroids.Core.SpaceShips;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Constants;

import com.badlogic.gdx.math.Vector2;

public final class SpaceShipCreator {
	
	private static final float offset = 50; //Starting position distance from edge of screen
	
	private SpaceShipCreator(){	
	}

	public static Connector<SpaceShip, SpaceShipUI> create(int type, int playerNumber){
		SpaceShip s;
		SpaceShipUI sUI;
		Vector2 pos = startPos(playerNumber);
		int radius = Constants.SHIPS_RADIUS[type];
		int mass = Constants.SHIPS_MASS[type];
		int accel = Constants.SHIPS_ACCEL[type];
		int speed = Constants.SHIPS_MAX_VEL[type];
		int lives = Constants.SHIPS_LIVES[type];
		s = new SpaceShip(pos.x, pos.y,radius, mass, speed, accel, lives);
		sUI = new SpaceShipUI(s, Assets.SHIPS[type]);
		return new Connector<SpaceShip, SpaceShipUI>(s,sUI);

	}
	
	private static Vector2 startPos(int playerNumber){
		switch(playerNumber){
		case 1: return new Vector2(offset,Constants.VIRTUAL_HEIGHT - offset);
		case 2: return new Vector2(Constants.VIRTUAL_WIDTH - offset, Constants.VIRTUAL_HEIGHT - offset);
		case 3: return new Vector2(offset, offset);
		default: return null;
		}
	}
}
