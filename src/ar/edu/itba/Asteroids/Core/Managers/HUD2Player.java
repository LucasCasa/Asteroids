package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HUD2Player extends HUDManager{
	private SpaceShip player1;
	private SpaceShip player2;
	
	public HUD2Player(SpaceShip p1, SpaceShip p2){
		player1 = p1;
		player2 = p2;
	}
	public void draw(SpriteBatch batch){
		drawShipPlayer(batch, new Vector2(15,Gdx.graphics.getHeight() -15), 1, player1.getLives());
		drawShipPlayer(batch, new Vector2(Gdx.graphics.getWidth() - 150,Gdx.graphics.getHeight() - 15), 2, player2.getLives());
	}

}
