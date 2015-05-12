package ar.edu.itba.Asteroids.Core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class WorldManager {
	private static WorldManager self;
	SpaceShip first = new SpaceShip(50, 50, 30, 1, 100, 5, 3);
	SpaceShipUI firstUI = new SpaceShipUI(first,new Texture("capsule.png"));
	public ArrayList<Asteroid> e;
	private WorldManager(){
		
		e = new ArrayList<Asteroid>();
		for(int i = 0; i< 5; i++){
			float width = (float)Math.random() * Gdx.graphics.getWidth();
			float height = (float)Math.random() * Gdx.graphics.getHeight();
			float mass = (float)Math.random() * 1 + 1;
			float velx = (float)Math.random() * 5 ;
			float vely = (float)Math.random() * 5 ;
			e.add(new Asteroid(width,height,velx,vely,(int)mass));
		}
	}
	public static WorldManager getInstance(){
		if(self == null){
			self = new WorldManager();
		}
		return self;
	}
	public void update(){
		
		first.update();
		for( Asteroid a: e){
			a.update();
		}
		
		for(int i = 0; i<e.size();i++){
			Asteroid aux = e.get(i);
			for(int j = i+1; j < e.size();j++){
				aux.collision(e.get(j));
			}
		}
	}
	public void keyDown(int keyCode) {
		switch (keyCode) {
		case Keys.W:
			first.acelUp(true);
			break;
		case Keys.S:
			first.acelDown(true);
			break;
		case Keys.A:
			first.acelLeft(true);
			break;
		case Keys.D:
			first.acelRight(true);
			break;
		}
	}
	public void keyUp(int keyCode) {
		switch (keyCode) {
		case Keys.W:
			first.acelUp(false);
			break;
		case Keys.S:
			first.acelDown(false);
			break;
		case Keys.A:
			first.acelLeft(false);
			break;
		case Keys.D:
			first.acelRight(false);
			break;
		}	
	}
}
