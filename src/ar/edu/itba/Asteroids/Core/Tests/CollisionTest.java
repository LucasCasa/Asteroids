package ar.edu.itba.Asteroids.Core.Tests;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManager1Player;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;


public class CollisionTest {
	Asteroid a;
	
	@Before
	public void init(){
		a = new Asteroid(0, 0, 5, 5, 1);
	}
	@Test
	/**
	 * Se fija que los asteroides no colisiones si no tienen
	 * que.
	 */
	public void NoCollisionTest() {
		Asteroid b = new Asteroid(500,500,0,0,1);
		Assert.assertFalse(b.asteroidCollision(a,1/60f));
	}
	@Test
	/**
	 * Se fija si los asteroides colisionan correctamente
	 */
	public void YesCollisionTest(){
		Asteroid b = new Asteroid(0,0,0,0,1);
		Assert.assertTrue(b.asteroidCollision(a,1/60f));
	}
	@Test
	/**
	 * Se fija si los asteroides pueden sacarle vida a la nave en
	 * el caso en que tengan que hacerlo
	 */
	public void AsteroidTakeLifeTest(){
		SpaceShip b = new SpaceShip(0, 0, 15, 1, 0, 0, 3);
		b.shipCollision(a,1/60f);
		Assert.assertEquals(2, b.getLives());
		
	}
	@Test
	/**
	 * Se fija que los asteroides no saquen vida cuando no colisionan
	 */
	public void AsteroidDontTakeLife(){
		SpaceShip b = new SpaceShip(150, 150, 15, 1, 0, 0, 3);
		b.shipCollision(a,1/60f);
		Assert.assertEquals(3, b.getLives());
		
	}
/*	No entiendo que debería hacer este test 
	@Test
	public void EliminatePowerUp(){
		PowerUpCreator.create();
	}
*/
 	@Test
	public void AsteroidOutOfScreen(){
 		ArrayList<Player> p = new ArrayList<Player>();
 		p.add(new Player("Prueba",new SpaceShip(200, 200, 10, 1, 20, 20, 5), 1));
		WorldManager aux = new WorldManager1Player(p);
		Asteroid a1,a2,a3,a4,a5;
		a1 = new Asteroid(0, 60, -1000, 0, 1); // este se va a ir por la derecha
		a2 = new Asteroid(Constants.VIRTUAL_WIDTH, 60, 1000, 0, 1); // este se va a ir por la izquierda	
		a3 = new Asteroid(200,0,0,-1000,1); // este se va por abajo
		a4 = new Asteroid(200,Constants.VIRTUAL_HEIGHT ,0,1000,1); // este se va por arriba
		a5 = new Asteroid(100,100,0,0,1); // este no se va
		 
		aux.addAsteroid(a1);
		aux.addAsteroid(a2);
		aux.addAsteroid(a3);
		aux.addAsteroid(a4);
		aux.addAsteroid(a5);
		a1.update(1/60f);
		a2.update(1/60f);
		a3.update(1/60f);
		a4.update(1/60f);
		a5.update(1/60f);
		
		Assert.assertTrue(a1.outOfScreen());
		Assert.assertTrue(a2.outOfScreen());
		Assert.assertTrue(a3.outOfScreen());
		Assert.assertTrue(a4.outOfScreen());
		Assert.assertFalse(a5.outOfScreen());
 	}


 	@Test
	public void ShipOutOfScreen(){
 		SpaceShip p = new SpaceShip(Constants.VIRTUAL_WIDTH - 20, Constants.VIRTUAL_HEIGHT - 20, 20, 1, 100, 100, 2);
 		p.acelUp(true);
 		p.acelRight(true);
 		p.update(1/60f);
 		Assert.assertTrue(p.getCPos().x + p.getRadius() <= Constants.VIRTUAL_WIDTH);
 		Assert.assertTrue(p.getCPos().y + p.getRadius() <= Constants.VIRTUAL_HEIGHT);
	}
}
