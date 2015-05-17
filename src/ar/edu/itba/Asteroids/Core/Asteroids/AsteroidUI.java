package ar.edu.itba.Asteroids.Core.Asteroids;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsteroidUI implements Drawable {
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
		if(back.getCollision()){
			batch.draw(Assets.EXPLOSION,back.getCollisionPoint().x - 10, back.getCollisionPoint().y -10 , 20,20);
			back.setCollision(false);
		}
	}
}
