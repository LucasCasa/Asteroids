import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.Asteroids.Core.Asteroids.Asteroid;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;


public class CollisionTest {
	Asteroid a;
	
	@Before
	public void init(){
		a = new Asteroid(0, 0, 5, 5, 1,15);
	}
	@Test
	public void NoCollisionTest() {
		Asteroid b = new Asteroid(500,500,0,0,1,15);
		Assert.assertFalse(b.asteroidCollision(a));
	}
	@Test
	public void YesCollisionTest(){
		Asteroid b = new Asteroid(15,15,0,0,1,15);
		Assert.assertTrue(b.asteroidCollision(a));
	}
	@Test
	public void AsteroidTakeLifeTest(){
		SpaceShip b = new SpaceShip(15, 15, 15, 1, 0, 0, 3);
		b.shipCollision(a);
		Assert.assertEquals(2, b.getLives());
		
	}
	@Test
	public void AsteroidDontTakeLife(){
		SpaceShip b = new SpaceShip(150, 150, 15, 1, 0, 0, 3);
		b.shipCollision(a);
		Assert.assertEquals(3, b.getLives());
		
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
