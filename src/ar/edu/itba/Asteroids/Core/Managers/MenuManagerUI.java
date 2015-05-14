package ar.edu.itba.Asteroids.Core.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.Asteroids.Core.Drawable;

public class MenuManagerUI implements Drawable{
	MenuManager mm;
	BitmapFont font=new BitmapFont(Gdx.files.internal("arcade.fnt"));;
	public MenuManagerUI(MenuManager menu) {
		this.mm=menu;
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
			font.draw(batch, "Asteroids", 300, 500);
			font.draw(batch, "1- New Game", 100, 300);
			font.draw(batch, "2- Help", 100, 250);
			font.draw(batch, "3- Exit", 100, 200);
			break;
		case NumberOfPlayers:
			font.draw(batch, "Number of Players in the Game ", 50, 500);
			font.draw(batch, "1- 1 Players", 100,300);
			font.draw(batch, "2- 2 Players", 100,250);
			font.draw(batch, "3- 3 Players", 100,200);
			break;
		case GameMode: 
			font.draw(batch, "Game Mode", 300,500);
			font.draw(batch, "1 - 2 SpaceShips", 50, 300);
			font.draw(batch, "Asteroids controlled by AI",50,250);
			font.draw(batch, "2 - 1 SpaceShip", 50, 200);
			font.draw(batch, "Asteroids controlled",40,150);
			font.draw(batch, "by Another Player",40,100);
			break;
		case ChooseSpaceShip:
			font.draw(batch, "Choose SpaceShip", 50, 500);
			printSpaceShips(batch);
		}
	}
}
