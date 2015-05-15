package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidThrower;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class WorldManager {
	private static WorldManager self;
	String[] imagenes = {"nose.png","capsule.png","death.png","rosca.png","ufo.png"};
	int spaceshipAmount = 2;
	ArrayList<SpaceShip> naves = new ArrayList<SpaceShip>();
	List<SpaceShipUI> naveui = new ArrayList<SpaceShipUI>();
	public ArrayList<Asteroid> e;
	public ArrayList<AsteroidUI> eUI;
	Asteroid thrown;
	AsteroidPlayer third;
	SpaceShip first;
	SpaceShip second;
	private WorldManager(){
		
		for(int i=0; i<spaceshipAmount; i++){
			float x = 100.0f;
			float y = 100.0f;
			SpaceShip aux = new SpaceShip(x+x*i, y, 30, 1, 500, 20, 3);
			naves.add(aux);
			naveui.add(new SpaceShipUI(aux, new Texture(imagenes[(int)(Math.random()*5)])));
			
		}
		first = naves.get(0);
		second = naves.get(1);
		third = new AsteroidPlayer();
		e = new ArrayList<Asteroid>();
		eUI = new ArrayList<AsteroidUI>();
	}
	public static WorldManager getInstance(){
		if(self == null){
			self = new WorldManager();
		}
		return self;
	}

	public void update(){
		
		third.update();
		
		for(SpaceShip s: naves){
			s.update();
		}
		
		first.shipCollision(second);
		
		for( Asteroid a: e){
			a.update();
		}
		
		
		for(int i = 0; i<e.size();i++){
			Asteroid aux = e.get(i);
			for(int j = i+1; j < e.size();j++){
				aux.collision(e.get(j));
			}
			if(aux.outOfScreen()){
				e.remove(i);
			}
			if(first.shipCollision(aux)){
				e.remove(i);
			}
		}
	}

	public ArrayList<SpaceShip> getNaves(){
		return naves;
	}
	
	public void keyDown(int keyCode) {	
		switch (keyCode) {
		case Keys.DOWN:
			second.acelDown(true);
			break;
		case Keys.UP:
			second.acelUp(true);
			break;
		case Keys.LEFT:
			second.acelLeft(true);
			break;
		case Keys.RIGHT:
			second.acelRight(true);
			break;
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
		default:
			Asteroid d;
			if((d = third.keyPressed(keyCode,naves.get(0))) != null){
				e.add(d);
				eUI.add(new AsteroidUI(d));
			}
		}
	}
	public void keyUp(int keyCode) {
		switch (keyCode) {
		case Keys.DOWN:
			second.acelDown(false);
			break;
		case Keys.UP:
			second.acelUp(false);
			break;
		case Keys.LEFT:
			second.acelLeft(false);
			break;
		case Keys.RIGHT:
			second.acelRight(false);
			break;
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
	public AsteroidPlayer getAsteroidPlayer(){
		return third;
	}
}
