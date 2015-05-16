package ar.edu.itba.Asteroids.Core.SpaceShips;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Connector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SpaceShipCreator {
	
	private static SpaceShipCreator self = null;
	private final float offset = 50; //Starting position distance from edge of screen
	
	private SpaceShipCreator(){	
	}
	
	public SpaceShipCreator getInstance(){
		if(self == null){
			self = new SpaceShipCreator();
		}
		return self;
	}

	public Connector<SpaceShip, SpaceShipUI> create(SpaceShipType type, int playerNumber){
		SpaceShip s;
		SpaceShipUI sUI;
		Vector2 pos = startPos(playerNumber);
		switch(type){
			case Deathstar: s = new DeathStarShip(pos.x,pos.y);
							sUI = new SpaceShipUI(s, Assets.SHIPS[0]);
							break;
			case Capsule:	s = new CapsuleShip(pos.x,pos.y);
							sUI = new SpaceShipUI(s, Assets.SHIPS[1]);
							break;
			case Attackball:s = new AttackballShip(pos.x,pos.y);
							sUI = new SpaceShipUI(s, Assets.SHIPS[2]);
							break;
			case Donut:		s = new DonutShip(pos.x,pos.y);
							sUI = new SpaceShipUI(s, Assets.SHIPS[3]);
							break;
			case Ufo: 		s = new UFOShip(pos.x,pos.y);
							sUI = new SpaceShipUI(s, Assets.SHIPS[4]);
							break;
			default:		s=null;
							sUI=null;
							break;
		}
		return new Connector<SpaceShip, SpaceShipUI>(s,sUI);

	}
	
	private Vector2 startPos(int playerNumber){
		switch(playerNumber){
		case 1: return new Vector2(offset,Gdx.graphics.getHeight() - offset);
		case 2: return new Vector2(Gdx.graphics.getWidth() - offset, Gdx.graphics.getHeight() - offset);
		case 3: return new Vector2(offset, offset);
		default: return null;
		}
	}
}
