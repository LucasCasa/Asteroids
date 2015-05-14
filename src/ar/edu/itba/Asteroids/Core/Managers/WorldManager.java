package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidThrower;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

import com.badlogic.gdx.Gdx;
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
		
		e = new ArrayList<Asteroid>();
		eUI = new ArrayList<AsteroidUI>();
		/*for(int i = 0; i< 20; i++){
			float width = (float)Math.random() * Gdx.graphics.getWidth();
			float height = (float)Math.random() * Gdx.graphics.getHeight();
			float mass = (float)Math.random() * 1 + 1;
			float velx = (float)Math.random() * 100 - 50;
			velx += Math.signum(velx)*50;
			float vely = (float)Math.random() * 200 - 100;
			vely += Math.signum(vely)*50;
			Asteroid aux = new Asteroid(width,height,velx,vely,(int)mass);
			AsteroidUI aux2 = new AsteroidUI(aux);
			e.add(aux);
			eUI.add(aux2);
		}*/
	}
	public static WorldManager getInstance(){
		if(self == null){
			self = new WorldManager();
		}
		return self;
	}
	public void update(){
			
		for(SpaceShip s: naves){
			s.update();
		}
		
		if(first.Collision(second)){
			first.newVel(second);
		}
		
		for( Asteroid a: e){
			a.update();
		}
		
		for(int i = 0; i<e.size();i++){
			Asteroid aux = e.get(i);
			if(first.Collision(e.get(i))){
				first.newVel(e.get(i));
				first.damage(1);
			}
			for(int j = i+1; j < e.size();j++){
				aux.collision(e.get(j));
			}
			if(e.get(i).outOfScreen()){
				e.remove(i);
			}
		}
		
		System.out.println(e.size());
		
		/*Aca habria que manejar que tiempo paso desde que se lanzo
		el ultimo asteroide para saber si no debe lanzarlo denuevo*/
		if(thrown != null){ //esto hay que cambiarloo
			e.add(thrown);
			eUI.add(new AsteroidUI(thrown));
			thrown = null;
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
		case Keys.NUMPAD_1:
		case Keys.NUM_1:
			thrown = AsteroidThrower.getInstance().throwBottomLeft(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_2:
		case Keys.NUM_2:
			thrown = AsteroidThrower.getInstance().throwBottomMiddle(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_3:
		case Keys.NUM_3:
			thrown = AsteroidThrower.getInstance().throwBottomRight(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_6:
		case Keys.NUM_6:
			thrown = AsteroidThrower.getInstance().throwRight(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_9:
		case Keys.NUM_9:
			thrown = AsteroidThrower.getInstance().throwUpperRight(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_8:
		case Keys.NUM_8:
			thrown = AsteroidThrower.getInstance().throwUpperMiddle(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_7:
		case Keys.NUM_7:
			thrown = AsteroidThrower.getInstance().throwUpperLeft(naves.get(1).getCPos());
			break;
		case Keys.NUMPAD_4:
		case Keys.NUM_4:
			thrown = AsteroidThrower.getInstance().throwLeft(naves.get(1).getCPos());
			break;
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
}
