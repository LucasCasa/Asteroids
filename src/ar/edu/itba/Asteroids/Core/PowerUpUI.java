package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUpUI implements Drawable{

	private PowerUp backend;
	private Texture texture;
	
	
	public PowerUpUI(PowerUp back, Texture texture){
		this.backend = back;
		this.texture = texture;
	}
	
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, backend.getPos().x, backend.getPos().y, backend.getRadius()*2, backend.getRadius()*2);
	}
}
