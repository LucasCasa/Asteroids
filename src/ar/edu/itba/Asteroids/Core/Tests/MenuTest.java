package ar.edu.itba.Asteroids.Core.Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.Asteroids.Core.Managers.MenuManager;
import ar.edu.itba.Asteroids.Core.Managers.MenuTypes;

import com.badlogic.gdx.Input.Keys;

public class MenuTest {
	
	@Before
	public void init(){
		MenuManager.getInstance().reset();
	}
	
	
	@Test
	public void ChangeState(){
		Assert.assertTrue(MenuManager.getInstance().getState()==MenuTypes.Main);
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		Assert.assertFalse(MenuManager.getInstance().getState() == MenuTypes.Main);
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		MenuManager.getInstance().keyDown(Keys.ESCAPE);
		MenuManager.getInstance().keyDown(Keys.NUM_3);
		Assert.assertTrue(MenuManager.getInstance().getState() == MenuTypes.GameMode3Players);
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		Assert.assertTrue(MenuManager.getInstance().getState()== MenuTypes.GetPlayerName);	
	}
	
	@Test
	public void GetPlayers(){
		Assert.assertTrue(MenuManager.getInstance().getPlayers()==0); //when the game starts the amount of players is 0
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		Assert.assertFalse(MenuManager.getInstance().getPlayers()==0);
		MenuManager.getInstance().keyDown(Keys.ESCAPE);
		MenuManager.getInstance().keyDown(Keys.NUM_3);
		Assert.assertTrue(MenuManager.getInstance().getPlayers()==3);
	}
	
	@Test
	public void GetName(){
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		MenuManager.getInstance().keyDown(Keys.NUM_3);
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		MenuManager.getInstance().keyDown(Keys.NUM_1);
		MenuManager.getInstance().keyDown(Keys.ENTER);
		MenuManager.getInstance().keyDown(Keys.A);
		Assert.assertFalse(MenuManager.getInstance().getName(0).equals("1A"));
		Assert.assertTrue(MenuManager.getInstance().getName(1).equals("A"));
	}
}
