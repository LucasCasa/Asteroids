package ar.edu.itba.Asteroids.Core.PowerUps;

import ar.edu.itba.Asteroids.Core.Drawable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUpUI implements Drawable{

	private PowerUp backend;
	private Texture texture;
	
	/**
	 * 
	 * @param back; PowerUp
	 * @param texture; the texture the powerUp is going to have
	 */
	public PowerUpUI(PowerUp back, Texture texture){
		this.backend = back;
		this.texture = texture;
	}
	
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, backend.getPosition().x, backend.getPosition().y, backend.getRadius()*2, backend.getRadius()*2);
	}
}
