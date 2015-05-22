package ar.edu.itba.Asteroids.Core.Managers;

import java.util.Map.Entry;
import java.util.TreeMap;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.Drawable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
				int sp = Constants.SHIPS_MAX_VEL[i];
				int accel = Constants.SHIPS_ACCEL[i];
				int mass = Constants.SHIPS_MASS[i];
				int lives = Constants.SHIPS_LIVES[i];
				printSpaceShip(batch,Assets.SHIPS[i],i+1,(i / 3) * (Constants.VIRTUAL_WIDTH / 2) + 25,(Constants.VIRTUAL_HEIGHT/ 4) * (i % 3) + 25,sp,accel,mass,lives);
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
		Assets.FONT.draw(batch, "Players Names", Constants.VIRTUAL_WIDTH/16, Constants.VIRTUAL_HEIGHT*7/8);
		for(int i=0;i<mm.getCompleteName()+1;i++){
			Assets.SMALL_FONT.draw(batch, "Player " + (i+1) + " write your name then press enter to continue", Constants.VIRTUAL_WIDTH/16, Constants.VIRTUAL_HEIGHT*3/4 -100*i);
			Assets.SMALL_FONT.draw(batch, mm.getName(i), Constants.VIRTUAL_WIDTH/16 + 50, Constants.VIRTUAL_HEIGHT*3/4 -100*i -50 );
		}
	}
	
	private void printHighScores(SpriteBatch batch){
		HighScoreManager.getInstance().loadScores();
		TreeMap<Float, String> highscores = HighScoreManager.getInstance().getHighScores();
		Assets.FONT.draw(batch, "Name", Constants.VIRTUAL_WIDTH*2/8, Constants.VIRTUAL_HEIGHT*2/3);
		Assets.FONT.draw(batch, "Score", Constants.VIRTUAL_WIDTH*4/8,  Constants.VIRTUAL_HEIGHT*2/3);
				
		int count = 0;
		for (Entry<Float, String> each : highscores.entrySet()) {
			count++;
			String score = each.getKey().toString();
			String name = each.getValue();

			Assets.FONT.draw(batch, name,  Constants.VIRTUAL_WIDTH*2/8, Constants.VIRTUAL_HEIGHT*8/13 - count*30);
			Assets.FONT.draw(batch, score, Constants.VIRTUAL_WIDTH*4/8, Constants.VIRTUAL_HEIGHT*8/13 - count*30);
			if(count == 10) break;
		}
	}
	
	
	private void drawMenu(SpriteBatch batch){
		Menu state= mm.getState();
		switch(state){
		case Main:
			Assets.FONT.draw(batch, "Asteroids", Constants.VIRTUAL_WIDTH*3/8, Constants.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch, "1- New Game", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch, "2- Settings", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch, "3- Help", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*3/8);
			Assets.FONT.draw(batch, "4- Get High Scores", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*2/8);
			Assets.FONT.draw(batch, "Escape- Exit", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*1/8);
			break;
		case Help:
			Assets.FONT.draw(batch,"Help", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			break;
		case Settings:
			Assets.FONT.draw(batch,"Change Resolution", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch,"1- 800x600", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch,"2- 1024x768", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch,"3- 1280x1024", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*3/8);
			break;
		case HighScore:
			Assets.FONT.draw(batch, "Recent HighScores",Constants.VIRTUAL_WIDTH*1/16,Constants.VIRTUAL_HEIGHT*7/8);
			printHighScores(batch);
			break;
		case NumberOfPlayers:
			Assets.FONT.draw(batch, "Number of Players in the Game ", Constants.VIRTUAL_WIDTH*1/16, Constants.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch, "1- One Player", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch, "2- Two Players", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch, "3- Three Players", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*3/8);
			break;
		case GameMode2Players: 
			Assets.FONT.draw(batch, "Game Mode",  Constants.VIRTUAL_WIDTH/4,Constants.VIRTUAL_HEIGHT*3/4);
			Assets.SMALL_FONT.draw(batch, "1 - 2 SpaceShips, Asteroids controlled by AI", Constants.VIRTUAL_WIDTH/16, Constants.VIRTUAL_HEIGHT*4/8);
			Assets.SMALL_FONT.draw(batch, "2 - 1 SpaceShip, Asteroids controlled by another player", Constants.VIRTUAL_WIDTH/16, Constants.VIRTUAL_HEIGHT*3/8);
			break;
		case GameMode3Players:
			Assets.FONT.draw(batch, "Game Mode",  Constants.VIRTUAL_WIDTH/4,Constants.VIRTUAL_HEIGHT*3/4);
			Assets.SMALL_FONT.draw(batch,"1 - 3 spaceShips, Asteroids controlled by AI", Constants.VIRTUAL_WIDTH/16, Constants.VIRTUAL_HEIGHT*4/8);
			Assets.SMALL_FONT.draw(batch, "2 - 2 SpaceShips, Asteroids controlled by the third player", Constants.VIRTUAL_WIDTH/16, Constants.VIRTUAL_HEIGHT*3/8);
			break;
		case GetPlayerName:
			printPlayersNames(batch);
			break;
		case ChooseSpaceShip:
			Assets.FONT.draw(batch, "Choose SpaceShip", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*7/8);
			if(mm.getPlayers()>mm.getSpaceShipsSelected())
				Assets.FONT.draw(batch, mm.getName(mm.getSpaceShipsSelected()) + " choose SpaceShip", Constants.VIRTUAL_WIDTH*3/16, Constants.VIRTUAL_HEIGHT*6/8);
			printSpaceShips(batch);
			break;
		default:break;
		}
	}	
}
