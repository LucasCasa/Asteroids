package ar.edu.itba.Asteroids.Core.Asteroids;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * the drawable part of the Asteroid Class
 *
 */
public class AsteroidUI implements Drawable {
	private Asteroid back;
	
	/**
	 * 
	 * @param a; asteroid
	 */
	public AsteroidUI(Asteroid a){
		back = a;
	}
	/**
	 * draws the asteroid and a spark if there has been a collision
	 */
	public void draw(SpriteBatch batch){
		batch.draw(Assets.ASTEROID, back.getPosition().x, back.getPosition().y,back.getRadius() *2, back.getRadius() *2);
		if(back.getCollision()){
			batch.draw(Assets.EXPLOSION,back.getCollisionPoint().x - 10, back.getCollisionPoint().y -10 , 20,20);
			back.setCollision(false);
		}
	}
	public boolean isDestroyed(){
		return back.isDestroyed();
	}
}
