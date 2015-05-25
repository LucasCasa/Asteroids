package ar.edu.itba.Asteroids.Core.Tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.PowerUps.PowerUpCreator;
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
		Assert.assertFalse(b.asteroidCollision(a));
	}
	@Test
	/**
	 * Se fija si los asteroides colisionan correctamente
	 */
	public void YesCollisionTest(){
		Asteroid b = new Asteroid(15,15,0,0,1);
		Assert.assertTrue(b.asteroidCollision(a));
	}
	@Test
	/**
	 * Se fija si los asteroides pueden sacarle vida a la nave en
	 * el caso en que tengan que hacerlo
	 */
	public void AsteroidTakeLifeTest(){
		SpaceShip b = new SpaceShip(15, 15, 15, 1, 0, 0, 3);
		b.shipCollision(a);
		Assert.assertEquals(2, b.getLives());
		
	}
	@Test
	/**
	 * Se fija que los asteroides no saquen vida cuando no colisionan
	 */
	public void AsteroidDontTakeLife(){
		SpaceShip b = new SpaceShip(150, 150, 15, 1, 0, 0, 3);
		b.shipCollision(a);
		Assert.assertEquals(3, b.getLives());
		
	}
	@Test
	public void EliminatePowerUp(){
		PowerUpCreator.create();
	}
/*	Falta implementar que si el asteroide se va de la pantalla desparece
 * 	@Test
	public void AsteroidOutOfScreen(){
		
	}
*/
/* No se como hacerlo porque no tengo el tama�o de la pantalla
 * Probablemente el backend no tenga que llamar a Gdx.grapichs.getWidth()/height()
 * Hay que charlarlo.
 * 	@Test
	public void ShipOutOfScreen(){
		
	}
*/
}
