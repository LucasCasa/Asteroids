package ar.edu.itba.Asteroids.Core.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
		for(int i = 0;i<Assets.SHIPS.length; i++){
			if(!mm.spaceShipSelected(i))
				printSpaceShip(batch,Assets.SHIPS[i],i+1,(i / 3) * (Gdx.graphics.getWidth() / 2) + 25,(Gdx.graphics.getHeight()/ 4) * (i % 3) + 25,100,20,2,3);
			// despues hay que hacer vectores con las velocidades de las naves
		}
		
	}
	
	private void printSpaceShip(SpriteBatch batch, Texture t,int key,int x, int y, int speed, int acel, int mass, int lives){
		batch.draw(t,x + 35,y,80,80);
		Assets.SMALL_FONT.draw(batch,"(" + key +")",x,y + 50);
		Assets.SMALL_FONT.draw(batch,"Speed: " + speed, x + 120,y + 80);
		Assets.SMALL_FONT.draw(batch,"Aceleration: " + acel, x + 120, y +60);
		Assets.SMALL_FONT.draw(batch,"Mass: " + mass, x + 120, y +40);
		Assets.SMALL_FONT.draw(batch,"Lives: " + lives, x + 120, y +20);
	}
	
	private void drawMenu(SpriteBatch batch){
		Menu state= mm.getState();
		switch(state){
		case Main:
			Assets.FONT.draw(batch, "Asteroids", Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()*3/4);
			Assets.FONT.draw(batch, "1- New Game", Gdx.graphics.getWidth()/8, 300);
			Assets.FONT.draw(batch, "2- Help", Gdx.graphics.getWidth()/8, 250);
			Assets.FONT.draw(batch, "3- Exit", Gdx.graphics.getWidth()/8, 200);
			break;
		case NumberOfPlayers:
			Assets.FONT.draw(batch, "Number of Players in the Game ", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*3/4);
			Assets.FONT.draw(batch, "1- 1 Players", Gdx.graphics.getWidth()/8,300);
			Assets.FONT.draw(batch, "2- 2 Players", Gdx.graphics.getWidth()/8,250);
			Assets.FONT.draw(batch, "3- 3 Players", Gdx.graphics.getWidth()/8,200);
			break;
		case GameMode2Players: 
			Assets.FONT.draw(batch, "Game Mode",  Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()*3/4);
			Assets.SMALL_FONT.draw(batch, "1 - 2 SpaceShips, Asteroids controlled by AI", Gdx.graphics.getWidth()/16, 300);
			Assets.SMALL_FONT.draw(batch, "2 - 1 SpaceShip, Asteroids controlled by another player", Gdx.graphics.getWidth()/16, 200);
			break;
		case GameMode3Players:
			Assets.FONT.draw(batch, "Game Mode",  Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()*3/4);
			Assets.SMALL_FONT.draw(batch,"1 - 3 spaceShips, Asteroids controlled by AI", Gdx.graphics.getWidth()/16,300);
			Assets.SMALL_FONT.draw(batch, "2 - 2 SpaceShips, Asteroids controlled by the third player", Gdx.graphics.getWidth()/16,200);
			break;
		case ChooseSpaceShip:
			Assets.FONT.draw(batch, "Choose SpaceShip", Gdx.graphics.getHeight()/16, 500);
			Assets.SMALL_FONT.draw(batch, "Ships Selected=" + mm.getSpaceShipsSelected(), Gdx.graphics.getHeight()*10/16, 300);
			Assets.SMALL_FONT.draw(batch, "Number of Players=" + mm.getPlayers(), Gdx.graphics.getHeight()*10/16, 320);
			printSpaceShips(batch);
			break;
		}
	}
}
