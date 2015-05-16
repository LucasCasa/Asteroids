package ar.edu.itba.Asteroids.Core;

import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Managers.GameManager;
import ar.edu.itba.Asteroids.Core.Managers.GameManagerUI;
import ar.edu.itba.Asteroids.Core.Managers.MenuManager;
import ar.edu.itba.Asteroids.Core.Managers.MenuManagerUI;

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
	MenuManagerUI mmUI;
	BitmapFont font;
	BitmapFont standardFont;
	float time;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
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
		standardFont.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(),1,50);
		// font.draw(batch, "Asteroides", 50, 50); queda como ejemplo
		GameManager.getInstance().update();
		GameManagerUI.getInstance().draw(batch);
		batch.end();

	}
}
