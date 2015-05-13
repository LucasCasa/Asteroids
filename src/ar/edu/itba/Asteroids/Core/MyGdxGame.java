package ar.edu.itba.Asteroids.Core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	public SpriteBatch batch;
	Texture img;
	Asteroid a;
	Asteroid b;
	WorldManagerUI wmUI;
	BitmapFont font;
	BitmapFont standardFont;
	float time;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//a = new Asteroid(50,50,2,2,50);
		//b = new Asteroid(200,100,5,0,2);
		wmUI = new WorldManagerUI(WorldManager.getInstance());
		img = new Texture("background.png");
		font = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		standardFont = new BitmapFont();
		MyInputProcessor MYP = new MyInputProcessor();
		Gdx.input.setInputProcessor(MYP); // el que se encarga del manejo del input
	}

	@Override
	public void render () {
		time+= Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0,2048,1536);
		standardFont.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(),1,600);
		// font.draw(batch, "Asteroides", 50, 50); queda como ejemplo
		WorldManager.getInstance().update();
		wmUI.draw(batch);
		batch.end();

	}
}
