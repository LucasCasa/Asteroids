package ar.edu.itba.Asteroids.Core.SpaceShips;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public final class SpaceShipCreator {
	
	private static final float offset = 50; //Starting position distance from edge of screen
	
	private SpaceShipCreator(){	
	}

	public static Connector<SpaceShip, SpaceShipUI> create(int type, int playerNumber){
		SpaceShip s;
		SpaceShipUI sUI;
		Vector2 pos = startPos(playerNumber);
		/*switch(type){
		case 0:
			s = new DeathStarShip(pos.x,pos.y);
			sUI = new SpaceShipUI(s, Assets.SHIPS[0]);
			break;
		case 1:
			s = new CapsuleShip(pos.x,pos.y);
			sUI = new SpaceShipUI(s, Assets.SHIPS[1]);
			break;
		case 2:
			s = new AttackballShip(pos.x,pos.y);
			sUI = new SpaceShipUI(s, Assets.SHIPS[2]);
			break;
		case 3:
			s = new DonutShip(pos.x,pos.y);
			sUI = new SpaceShipUI(s, Assets.SHIPS[3]);
			break;
		case 4: 
			s = new UFOShip(pos.x,pos.y);
			sUI = new SpaceShipUI(s, Assets.SHIPS[4]);
			break;
		default:
			s=null;
			sUI=null;
			break;
		}*/
		int radius = Assets.SHIPS_RADIUS[type];
		int mass = Assets.SHIPS_MASS[type];
		int accel = Assets.SHIPS_ACCEL[type];
		int speed = Assets.SHIPS_MAX_VEL[type];
		int lives = Assets.SHIPS_LIVES[type];
		s = new SpaceShip(pos.x, pos.y,radius, mass, speed, accel, lives);
		sUI = new SpaceShipUI(s, Assets.SHIPS[type]);
		return new Connector<SpaceShip, SpaceShipUI>(s,sUI);

	}
	
	private static Vector2 startPos(int playerNumber){
		switch(playerNumber){
		case 1: return new Vector2(offset,Gdx.graphics.getHeight() - offset);
		case 2: return new Vector2(Gdx.graphics.getWidth() - offset, Gdx.graphics.getHeight() - offset);
		case 3: return new Vector2(offset, offset);
		default: return null;
		}
	}
}
