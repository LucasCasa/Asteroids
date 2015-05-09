package ar.edu.itba.Asteroids.Core;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Asteroid a;
	Asteroid b;
	ArrayList<Asteroid> e;
	@Override
	public void create () {
		batch = new SpriteBatch();
		//a = new Asteroid(50,50,2,2,50);
		//b = new Asteroid(200,100,5,0,2);
		e = new ArrayList<Asteroid>();
		for(int i = 0; i< 30; i++){
			float width = (float)Math.random() * Gdx.graphics.getWidth();
			float height = (float)Math.random() * Gdx.graphics.getHeight();
			float mass = (float)Math.random() * 1 +1;
			float velx = (float)Math.random() * 5 ;
			float vely = (float)Math.random() * 5 ;
			e.add(new Asteroid(width,height,velx,vely,(int)mass));
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(Asteroid as: e){
			as.update();
		}
		for(int i = 0; i<e.size();i++){
			Asteroid aux = e.get(i);
			for(int j = i+1; j < e.size();j++){
				aux.collision(e.get(j));
			}
		}
		batch.end();
	}
}
