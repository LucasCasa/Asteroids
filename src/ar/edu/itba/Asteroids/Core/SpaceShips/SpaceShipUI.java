package ar.edu.itba.Asteroids.Core.SpaceShips;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.RandFunctions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceShipUI implements Drawable{
	private SpaceShip s;
	private Texture texture;
	private int explosionSize;
	/**
	 * 
	 * @param s; the SpaceShip
	 * @param texture; the texture the SpaceShip is going to have
	 */
	public SpaceShipUI(SpaceShip s, Texture texture) {
		this.s=s;
		this.texture=texture;
		explosionSize = s.getRadius();
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(s.isActive()){
		batch.draw(texture,s.getPosition().x , s.getPosition().y, s.getRadius()*2, s.getRadius()*2);
		if(s.getCollision()){
			batch.draw(Assets.EXPLOSION, s.getCollisionPoint().x - explosionSize / 2,s.getCollisionPoint().y - explosionSize / 2,explosionSize,explosionSize);
			s.setCollision(false);
			Assets.SOUND_CRASH[RandFunctions.generateRandInt(0, Assets.SOUND_CRASH.length)].play(Assets.crashVolume);
		}
		Vector2 acel = s.getacel();
		if(acel.y > 0){
			batch.draw(Assets.PROPULSORS[0], s.getCPos().x -Assets.PROPULSORS[0].getWidth() / 2, s.getPosition().y - Assets.PROPULSORS[0].getHeight());
		}
		if(acel.y < 0){
			batch.draw(Assets.PROPULSORS[1], s.getCPos().x - Assets.PROPULSORS[1].getWidth() / 2, s.getPosition().y + s.getRadius() * 2);
		}
		if(acel.x < 0){
			batch.draw(Assets.PROPULSORS[2],s.getPosition().x + 2*s.getRadius(), s.getCPos().y - Assets.PROPULSORS[2].getHeight() / 2);
		}
		
		if(acel.x > 0){
			batch.draw(Assets.PROPULSORS[3],s.getPosition().x - Assets.PROPULSORS[3].getWidth(), s.getCPos().y - Assets.PROPULSORS[3].getHeight() / 2);
		}
		}
	}
}
