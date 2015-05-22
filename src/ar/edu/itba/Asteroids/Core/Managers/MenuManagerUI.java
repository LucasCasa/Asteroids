package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.badlogic.gdx.Graphics.DisplayMode;
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
				printSpaceShip(batch,Assets.SHIPS[i],i+1,(i / 3) * (Assets.VIRTUAL_WIDTH / 2) + 25,(Assets.VIRTUAL_HEIGHT/ 4) * (i % 3) + 25,sp,accel,mass,lives);
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
		Assets.FONT.draw(batch, "Players Names", Assets.VIRTUAL_WIDTH/16, Assets.VIRTUAL_HEIGHT*7/8);
		for(int i=0;i<mm.getCompleteName()+1;i++){
			Assets.SMALL_FONT.draw(batch, "Player " + (i+1) + " write your name then press enter to continue", Assets.VIRTUAL_WIDTH/16, Assets.VIRTUAL_HEIGHT*3/4 -100*i);
			Assets.SMALL_FONT.draw(batch, mm.getName(i), Assets.VIRTUAL_WIDTH/16 + 50, Assets.VIRTUAL_HEIGHT*3/4 -100*i -50 );
		}
	}
	
	private void printHighScores(SpriteBatch batch){
		HighScoreManager.getInstance().loadScores();
		TreeMap<Float, String> highscores = HighScoreManager.getInstance().getHighScores();
		Assets.FONT.draw(batch, "Name", Assets.VIRTUAL_WIDTH*2/8, Assets.VIRTUAL_HEIGHT*2/3);
		Assets.FONT.draw(batch, "Score", Assets.VIRTUAL_WIDTH*4/8,  Assets.VIRTUAL_HEIGHT*2/3);
				
		int count = 0;
		for (Entry<Float, String> each : highscores.entrySet()) {
			count++;
			String score = each.getKey().toString();
			String name = each.getValue();

			Assets.FONT.draw(batch, name,  Assets.VIRTUAL_WIDTH*2/8, Assets.VIRTUAL_HEIGHT*8/13 - count*30);
			Assets.FONT.draw(batch, score, Assets.VIRTUAL_WIDTH*4/8, Assets.VIRTUAL_HEIGHT*8/13 - count*30);
			if(count == 10) break;
		}
	}

	private void printOptionsNormalScreen(SpriteBatch batch){
		ArrayList<DisplayMode> displays = ResolutionManager.getInstance().getNormalScreenRes();
		for(int i=0;i<displays.size();i++){
			Assets.FONT.draw(batch, (i+1) + "- " + displays.get(i).width + ":" + displays.get(i).height,Assets.VIRTUAL_WIDTH*2/8,Assets.VIRTUAL_HEIGHT*(12-2*i)/16);
		}
	}
	
	private void printOptionsWideScreen(SpriteBatch batch){
		ArrayList<DisplayMode> displays = ResolutionManager.getInstance().getWideScreenRes();
		for(int i=0;i<displays.size();i++){
			Assets.FONT.draw(batch, (i+1) + "- " + displays.get(i).width + ":" + displays.get(i).height,Assets.VIRTUAL_WIDTH*2/8,Assets.VIRTUAL_HEIGHT*(12-2*i)/16);
		}
	}
	
	private void printOptionsMacScreen(SpriteBatch batch){
		ArrayList<DisplayMode> displays = ResolutionManager.getInstance().getMacScreenRes();
		for(int i=0;i<displays.size();i++){
			Assets.FONT.draw(batch, (i+1) + "- " + displays.get(i).width + ":" + displays.get(i).height,Assets.VIRTUAL_WIDTH*2/8,Assets.VIRTUAL_HEIGHT*(12-2*i)/16);
		}
	}
	
	
	private void drawMenu(SpriteBatch batch){
		Menu state= mm.getState();
		switch(state){
		case Main:
			Assets.FONT.draw(batch, "Asteroids", Assets.VIRTUAL_WIDTH*3/8, Assets.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch, "1- New Game", Assets.VIRTUAL_WIDTH/8, Assets.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch, "2- Settings", Assets.VIRTUAL_WIDTH/8, Assets.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch, "3- Help", Assets.VIRTUAL_WIDTH/8, Assets.VIRTUAL_HEIGHT*3/8);
			Assets.FONT.draw(batch, "4- Get High Scores", Assets.VIRTUAL_WIDTH/8, Assets.VIRTUAL_HEIGHT*2/8);
			Assets.FONT.draw(batch, "Escape- Exit", Assets.VIRTUAL_WIDTH/8, Assets.VIRTUAL_HEIGHT*1/8);
			break;
		case Help:
			Assets.FONT.draw(batch,"Help", Assets.VIRTUAL_WIDTH*3/8,Assets.VIRTUAL_HEIGHT*7/8);
			break;
		case ChangeResolution:
			Assets.FONT.draw(batch,"Change Resolution", Assets.VIRTUAL_WIDTH*3/8,Assets.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch,"1- 16:9", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch,"2- 16:10", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch,"3- 4:3", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*3/8);
			Assets.FONT.draw(batch,"4- FullScreen", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*2/8);
			break;
		case WideScreen:
			Assets.FONT.draw(batch,"16:9", Assets.VIRTUAL_WIDTH*3/8,Assets.VIRTUAL_HEIGHT*7/8);
			printOptionsWideScreen(batch);
			break;
		case NormalScreen:
			Assets.FONT.draw(batch,"4:3", Assets.VIRTUAL_WIDTH*3/8,Assets.VIRTUAL_HEIGHT*7/8);
			printOptionsNormalScreen(batch);
			break;
		case MacScreen:
			Assets.FONT.draw(batch,"16:10", Assets.VIRTUAL_WIDTH*3/8,Assets.VIRTUAL_HEIGHT*7/8);
			printOptionsMacScreen(batch);
			break;
		case HighScore:
			Assets.FONT.draw(batch, "Recent HighScores",Assets.VIRTUAL_WIDTH*1/16,Assets.VIRTUAL_HEIGHT*7/8);
			printHighScores(batch);
			break;
		case NumberOfPlayers:
			Assets.FONT.draw(batch, "Number of Players in the Game ", Assets.VIRTUAL_WIDTH*1/16, Assets.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch, "1- One Player", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch, "2- Two Players", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch, "3- Three Players", Assets.VIRTUAL_WIDTH/8,Assets.VIRTUAL_HEIGHT*3/8);
			break;
		case GameMode2Players: 
			Assets.FONT.draw(batch, "Game Mode",  Assets.VIRTUAL_WIDTH/4,Assets.VIRTUAL_HEIGHT*3/4);
			Assets.SMALL_FONT.draw(batch, "1 - 2 SpaceShips, Asteroids controlled by AI", Assets.VIRTUAL_WIDTH/16, Assets.VIRTUAL_HEIGHT*4/8);
			Assets.SMALL_FONT.draw(batch, "2 - 1 SpaceShip, Asteroids controlled by another player", Assets.VIRTUAL_WIDTH/16, Assets.VIRTUAL_HEIGHT*3/8);
			break;
		case GameMode3Players:
			Assets.FONT.draw(batch, "Game Mode",  Assets.VIRTUAL_WIDTH/4,Assets.VIRTUAL_HEIGHT*3/4);
			Assets.SMALL_FONT.draw(batch,"1 - 3 spaceShips, Asteroids controlled by AI", Assets.VIRTUAL_WIDTH/16, Assets.VIRTUAL_HEIGHT*4/8);
			Assets.SMALL_FONT.draw(batch, "2 - 2 SpaceShips, Asteroids controlled by the third player", Assets.VIRTUAL_WIDTH/16, Assets.VIRTUAL_HEIGHT*3/8);
			break;
		case GetPlayerName:
			printPlayersNames(batch);
			break;
		case ChooseSpaceShip:
			Assets.FONT.draw(batch, "Choose SpaceShip", Assets.VIRTUAL_WIDTH/8, Assets.VIRTUAL_HEIGHT*7/8);
			if(mm.getPlayers()>mm.getSpaceShipsSelected())
				Assets.FONT.draw(batch, mm.getName(mm.getSpaceShipsSelected()) + " choose SpaceShip", Assets.VIRTUAL_WIDTH*3/16, Assets.VIRTUAL_HEIGHT*6/8);
			printSpaceShips(batch);
			break;
		default:break;
		}
	}	
}
