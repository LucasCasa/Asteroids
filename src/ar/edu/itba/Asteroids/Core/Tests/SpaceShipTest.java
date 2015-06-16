package ar.edu.itba.Asteroids.Core.Tests;

import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceShipTest {
	
	SpaceShip s;
	final float EPSILON = 0.01f;
	
	@Before
	public void init(){
		s = new SpaceShip(100, 100, 15, 30, 150, 100, 7);
	}
	
	@Test
	public void PositionTest(){
		Assert.assertTrue(s.getCPos().x == 100 && s.getCPos().y == 100);
		
		s.acelRight(true);
		s.update(1);
		System.out.println(s.getSpeed().x);
		Assert.assertEquals(100, s.getSpeed().x, EPSILON); // Checks for correct X speed
		Assert.assertTrue(s.getSpeed().y == 0); // Checks for correct Y speed
		Assert.assertTrue(Math.abs(s.getCPos().x - (100+100)) < EPSILON); // Checks position

		s.acelRight(false);
		s.update(1);
		Assert.assertTrue(Math.abs(s.getSpeed().x) - 100 < EPSILON); // Checks for no changes in speed when not accelerating
		
		s.acelRight(true);
		s.update(1);
		Assert.assertTrue(150 == s.getSpeed().x); // Doesn't surpass max speed
	}
	
	@Test
	public void LivesTest(){
		s.damage(1);
		Assert.assertTrue(s.getLives() == 6);
		s.addLives(8);
		Assert.assertTrue(s.getLives() == 14);
	}
	
	@Test
	public void InvencibilityTest(){
		s.setInvincible(5);
		s.damage(1);
		Assert.assertTrue(s.getLives() == 7);
		s.update(4);
		s.damage(1);
		Assert.assertTrue(s.getLives() == 7);
		s.update(1.1f);
		s.damage(1);
		Assert.assertTrue(s.getLives() == 6);
	}
	
}
