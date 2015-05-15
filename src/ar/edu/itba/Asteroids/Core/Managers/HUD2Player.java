package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD2Player extends HUDManager{
	private SpaceShip player1;
	private SpaceShip player2;
	
	public HUD2Player(SpaceShip p1, SpaceShip p2){
		player1 = p1;
		player2 = p2;
	}
	public void draw(SpriteBatch batch){
		drawSector1(batch, player1);
		drawSector2(batch, player2);
	}

}
