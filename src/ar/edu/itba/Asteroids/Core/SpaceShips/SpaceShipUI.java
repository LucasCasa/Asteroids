package ar.edu.itba.Asteroids.Core.SpaceShips;

import ar.edu.itba.Asteroids.Core.Drawable;

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
		batch.draw(texture,s.getPosition().x , s.getPosition().y, s.getRadius()*2, s.getRadius()*2);
		System.out.println(s.getPosition().x + " --- " + s.getCPos().x);
	}

	

	
}
