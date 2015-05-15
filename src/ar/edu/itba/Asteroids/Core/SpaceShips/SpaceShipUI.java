package ar.edu.itba.Asteroids.Core.SpaceShips;

import ar.edu.itba.Asteroids.Core.Drawable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceShipUI implements Drawable{
	private SpaceShip s;
	private Texture texture;
	
	/**
	 * 
	 * @param s; the SpaceShip
	 * @param texture; the texture the SpaceShip is going to have
	 */
	public SpaceShipUI(SpaceShip s, Texture texture) {
		this.s=s;
		this.texture=texture;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(texture,s.getPosition().x , s.getPosition().y, s.getRadius()*2, s.getRadius()*2);
	}

	

	
}
