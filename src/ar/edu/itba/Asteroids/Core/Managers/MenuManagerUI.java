package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.Drawable;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class manages the user interface of the menu
 *
 */

public class MenuManagerUI implements Drawable{
	private static MenuManagerUI self = null;
	MenuManager mm;
	private static final int offSet = 270;
	
	
	private MenuManagerUI() {
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
	
	/**
	 * The method prints the spaceShips in the screen so that the players can choose which spaceShip they are going to use
	 * It only prints the spaceShips thate have not been selected
	 * @param batch
	 */
	private void printSpaceShips(SpriteBatch batch){
		for(int i = 0;i<Assets.SHIPS.length; i++){
			if(!mm.spaceShipSelected(i)){
				int sp = Constants.SHIPS_MAX_VEL[i];
				int accel = Constants.SHIPS_ACCEL[i];
				int mass = Constants.SHIPS_MASS[i];
				int lives = Constants.SHIPS_LIVES[i];
				printSpaceShip(batch,Assets.SHIPS[i],i+1,(i / 3) * (Constants.VIRTUAL_WIDTH / 2) + 25,(Constants.VIRTUAL_HEIGHT/ 4) * (i % 3) + 25,sp,accel,mass,lives);
			}
		}
	}
	
	private void drawBar(SpriteBatch batch, float x,float y, float cooldown){		
		batch.draw(Assets.BAR_BACK,x,y);
		batch.draw(Assets.BAR_FRONT,x,y,(int)(Assets.BAR_FRONT.getWidth() * cooldown),Assets.BAR_FRONT.getHeight());
	}
	
	private void printSpaceShip(SpriteBatch batch, Texture t,int key,int x, int y, float speed, float acel, float mass, float lives){
		batch.draw(t,x + 35,y,80,80);
		Assets.SMALL_FONT.draw(batch,"(" + key +")",x,y + 50);
		Assets.SMALL_FONT.draw(batch,"Speed: ", x + 120,y + 80);
		drawBar(batch,x+offSet,y+67,speed/Constants.MAX_MAX_VEL);
		Assets.SMALL_FONT.draw(batch,"Aceleration: ", x + 120, y +60);
		drawBar(batch,x+offSet, y+47,acel/Constants.MAX_ACCEL);
		Assets.SMALL_FONT.draw(batch,"Mass: ", x + 120, y +40);
		drawBar(batch,x+offSet, y+27,mass/Constants.MAX_MASS);
		Assets.SMALL_FONT.draw(batch,"Lives: ", x + 120, y +20);
		drawBar(batch,x+offSet,y+7,lives/Constants.MAX_LIVES);
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
		Assets.FONT.draw(batch, "Score", Constants.VIRTUAL_WIDTH*5/8,  Constants.VIRTUAL_HEIGHT*2/3);
				
		int count = 0;
		for (Entry<Float, String> each : highscores.entrySet()) {
			count++;
			String score = each.getKey().toString();
			String name = each.getValue();

			Assets.FONT.draw(batch, name,  Constants.VIRTUAL_WIDTH*2/8, Constants.VIRTUAL_HEIGHT*8/13 - count*30);
			Assets.FONT.draw(batch, score, Constants.VIRTUAL_WIDTH*5/8, Constants.VIRTUAL_HEIGHT*8/13 - count*30);
			if(count == 10) break;
		}
	}
	
	private void printOptionsNormalScreen(SpriteBatch batch){
		ArrayList<DisplayMode> displays = ResolutionManager.getInstance().getNormalScreenRes();
		for(int i=0;i<displays.size();i++){
			Assets.FONT.draw(batch, (i+1) + "- " + displays.get(i).width + ":" + displays.get(i).height,Constants.VIRTUAL_WIDTH*2/8,Constants.VIRTUAL_HEIGHT*(12-2*i)/16);
		}
	}
	
	private void printOptionsWideScreen(SpriteBatch batch){
		ArrayList<DisplayMode> displays = ResolutionManager.getInstance().getWideScreenRes();
		for(int i=0;i<displays.size();i++){
			Assets.FONT.draw(batch, (i+1) + "- " + displays.get(i).width + ":" + displays.get(i).height,Constants.VIRTUAL_WIDTH*2/8,Constants.VIRTUAL_HEIGHT*(12-2*i)/16);
		}
	}
	
	private void printOptionsMacScreen(SpriteBatch batch){
		ArrayList<DisplayMode> displays = ResolutionManager.getInstance().getMacScreenRes();
		for(int i=0;i<displays.size();i++){
			Assets.FONT.draw(batch, (i+1) + "- " + displays.get(i).width + ":" + displays.get(i).height,Constants.VIRTUAL_WIDTH*2/8,Constants.VIRTUAL_HEIGHT*(12-2*i)/16);
		}
	}
	
	/**
	 * This method draws the menu, depending on the state of the menu you are in, it is going to print different options.
	 * @param batch
	 */
	private void drawMenu(SpriteBatch batch){
		MenuTypes state= mm.getState();
		switch(state){
		case Main:
			Assets.TITLE_FONT.draw(batch, "Asteroids", Constants.VIRTUAL_WIDTH*2/8, Constants.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch, "1- New Game", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch, "2- Settings", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch, "3- Help", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*3/8);
			Assets.FONT.draw(batch, "4- Get High Scores", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*2/8);
			Assets.FONT.draw(batch, "Escape- Exit", Constants.VIRTUAL_WIDTH/8, Constants.VIRTUAL_HEIGHT*1/8);
			break;
		case Help:
			Assets.FONT.draw(batch,"Help", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			break;
		case ChangeResolution:
			Assets.FONT.draw(batch,"Change Resolution", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			Assets.FONT.draw(batch,"1- 16:9", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*5/8);
			Assets.FONT.draw(batch,"2- 16:10", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*4/8);
			Assets.FONT.draw(batch,"3- 4:3", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*3/8);
			Assets.FONT.draw(batch,"4- FullScreen", Constants.VIRTUAL_WIDTH/8,Constants.VIRTUAL_HEIGHT*2/8);
			break;
		case WideScreen:
			Assets.FONT.draw(batch,"16:9", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			printOptionsWideScreen(batch);
			break;
		case NormalScreen:
			Assets.FONT.draw(batch,"4:3", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			printOptionsNormalScreen(batch);
			break;
		case MacScreen:
			Assets.FONT.draw(batch,"16:10", Constants.VIRTUAL_WIDTH*3/8,Constants.VIRTUAL_HEIGHT*7/8);
			printOptionsMacScreen(batch);
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
