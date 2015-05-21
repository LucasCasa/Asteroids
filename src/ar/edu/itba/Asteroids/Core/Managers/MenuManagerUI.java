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
			if(!mm.spaceShipSelected(i)){
				int sp = Assets.SHIPS_MAX_VEL[i];
				int accel = Assets.SHIPS_ACCEL[i];
				int mass = Assets.SHIPS_MASS[i];
				int lives = Assets.SHIPS_LIVES[i];
				printSpaceShip(batch,Assets.SHIPS[i],i+1,(i / 3) * (Gdx.graphics.getWidth() / 2) + 25,(Gdx.graphics.getHeight()/ 4) * (i % 3) + 25,sp,accel,mass,lives);
				// despues hay que hacer vectores con las velocidades de las naves			
			}
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
	
	private void printPlayersNames(SpriteBatch batch) {
		Assets.FONT.draw(batch, "Players Names", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*7/8);
		for(int i=0;i<mm.getCompleteName()+1;i++){
			Assets.SMALL_FONT.draw(batch, "Player " + (i+1) + " write your name then press enter to continue", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*3/4 -100*i);
			Assets.SMALL_FONT.draw(batch, mm.getName(i), Gdx.graphics.getWidth()/16 + 50, Gdx.graphics.getHeight()*3/4 -100*i -50 );
		}
	}
	
	private void printHighScores(SpriteBatch batch){
		
	}
	
	private void drawMenu(SpriteBatch batch){
		Menu state= mm.getState();
		switch(state){
		case Main:
			Assets.FONT.draw(batch, "Asteroids", Gdx.graphics.getWidth()*3/8, Gdx.graphics.getHeight()*7/8);
			Assets.FONT.draw(batch, "1- New Game", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()*5/8);
			Assets.FONT.draw(batch, "2- Settings", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()*4/8);
			Assets.FONT.draw(batch, "3- Help", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()*3/8);
			Assets.FONT.draw(batch, "4- Get High Scores", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()*2/8);
			Assets.FONT.draw(batch, "Escape- Exit", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()*1/8);
			break;
		case Help:
			Assets.FONT.draw(batch,"Help", Gdx.graphics.getWidth()*3/8,Gdx.graphics.getHeight()*7/8);
			break;
		case Settings:
			Assets.FONT.draw(batch,"Change Resolution", Gdx.graphics.getWidth()*3/8,Gdx.graphics.getHeight()*7/8);
			Assets.FONT.draw(batch,"1- 800x600", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*5/8);
			Assets.FONT.draw(batch,"2- 1024x768", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*4/8);
			Assets.FONT.draw(batch,"3- 1280x1024", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*3/8);
			break;
		case HighScore:
			Assets.FONT.draw(batch, "Recent HighScores",Gdx.graphics.getWidth()*1/16,Gdx.graphics.getHeight()*7/8);
			printHighScores(batch);
			break;
		case NumberOfPlayers:
			Assets.FONT.draw(batch, "Number of Players in the Game ", Gdx.graphics.getWidth()*1/16, Gdx.graphics.getHeight()*7/8);
			Assets.FONT.draw(batch, "1- One Player", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*5/8);
			Assets.FONT.draw(batch, "2- Two Players", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*4/8);
			Assets.FONT.draw(batch, "3- Three Players", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*3/8);
			break;
		case GameMode2Players: 
			Assets.FONT.draw(batch, "Game Mode",  Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()*3/4);
			Assets.SMALL_FONT.draw(batch, "1 - 2 SpaceShips, Asteroids controlled by AI", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*4/8);
			Assets.SMALL_FONT.draw(batch, "2 - 1 SpaceShip, Asteroids controlled by another player", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*3/8);
			break;
		case GameMode3Players:
			Assets.FONT.draw(batch, "Game Mode",  Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()*3/4);
			Assets.SMALL_FONT.draw(batch,"1 - 3 spaceShips, Asteroids controlled by AI", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*4/8);
			Assets.SMALL_FONT.draw(batch, "2 - 2 SpaceShips, Asteroids controlled by the third player", Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()*3/8);
			break;
		case GetPlayerName:
			printPlayersNames(batch);
			break;
		case ChooseSpaceShip:
			Assets.FONT.draw(batch, "Choose SpaceShip", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()*7/8);
			if(mm.getPlayers()>mm.getSpaceShipsSelected())
				Assets.FONT.draw(batch, mm.getName(mm.getSpaceShipsSelected()) + " choose SpaceShip", Gdx.graphics.getWidth()*3/16, Gdx.graphics.getHeight()*6/8);
			printSpaceShips(batch);
			break;
		default:break;
		}
	}	
}
