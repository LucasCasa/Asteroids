package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD3Player extends HUDManager{
	private SpaceShip player1;
	private SpaceShip player2;
	private SpaceShip player3;
	
	public HUD3Player(SpaceShip p1, SpaceShip p2,SpaceShip p3){
		player1 = p1;
		player2 = p2;
		player3 = p3;
	}
	public void draw(SpriteBatch batch){
		drawSector1(batch, player1);
		drawSector2(batch, player2);
		drawSector3(batch, player3);
	}

}
