package ar.edu.itba.Asteroids.Core.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Drawable;

public class MenuManagerUI implements Drawable{
	private static MenuManagerUI self = null;
	MenuManager mm;
	public MenuManagerUI() {
		mm = MenuManager.getInstance();
	}
	
	public static MenuManagerUI getInstance(){
		if(self == null){
			self = new MenuManagerUI();
		}
		return self;
	}
	@Override
	public void draw(SpriteBatch batch) {
		drawMenu(batch);
		
	}
	
	private void printSpaceShips(SpriteBatch batch){
		
	}
	
	private void drawMenu(SpriteBatch batch){
		Menu state= mm.getState();
		switch(state){
		case Main:
			Assets.FONT.draw(batch, "Asteroids", 300, 500);
			Assets.FONT.draw(batch, "1- New Game", 100, 300);
			Assets.FONT.draw(batch, "2- Help", 100, 250);
			Assets.FONT.draw(batch, "3- Exit", 100, 200);
			break;
		case NumberOfPlayers:
			Assets.FONT.draw(batch, "Number of Players in the Game ", 50, 500);
			Assets.FONT.draw(batch, "1- 1 Players", 100,300);
			Assets.FONT.draw(batch, "2- 2 Players", 100,250);
			Assets.FONT.draw(batch, "3- 3 Players", 100,200);
			break;
		case GameMode: 
			Assets.FONT.draw(batch, "Game Mode", 300,500);
			Assets.SMALL_FONT.draw(batch, "1 - 2 SpaceShips, Asteroids controlled by AI", 50, 300);
			Assets.SMALL_FONT.draw(batch, "2 - 1 SpaceShip, Asteroids controlled by another player", 50, 200);
			break;
		case ChooseSpaceShip:
			Assets.FONT.draw(batch, "Choose SpaceShip", 50, 500);
			printSpaceShips(batch);
		}
	}
}
