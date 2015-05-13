package ar.edu.itba.Asteroids.Core.Asteroids;

import ar.edu.itba.Asteroids.Core.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsteroidUI {
	private Asteroid back;
	
	/**
	 * 
	 * @param a; asteroid
	 */
	public AsteroidUI(Asteroid a){
		back = a;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(Assets.ASTEROID, back.getPosition().x, back.getPosition().y,back.getRadius() *2, back.getRadius() *2);
	}
}
