package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceShipUI implements Drawable{
	private SpaceShip s;
	private Texture texture;
	
	public SpaceShipUI(SpaceShip s, Texture texture) {
		this.s=s;
		this.texture=texture;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(texture,s.getPosX() - s.getRadius(), s.getPosY() - s.getRadius(), s.getRadius()*2, s.getRadius()*2);
	}

	

	
}
