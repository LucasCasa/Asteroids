package ar.edu.itba.Asteroids.Core.Managers;

import java.util.HashMap;
import java.util.Map;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipCreator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class MenuManager {
	private static MenuManager self;
	private Menu state=Menu.Main;
	private GameMode mode;
	private Map<Integer,String> playerNames=new HashMap<Integer,String>();
	private String name="";
	private boolean[] selected = {false, false, false, false, false};
	private int numberOfShipsSelected = 0;
	private int numberofShips = 0;
	private int numberofNamesSaved = 0;

	public static MenuManager getInstance(){
		if(self == null){
			self = new MenuManager();
		}
		return self;
	}


	public void keyDown(int keyCode) {
		if(state == Menu.GetPlayerName ){
			if(keyCode == Keys.ESCAPE){ //if the user wants to go back to another in the menu
				pressedEscape();
				resetNames();
			}
			if(Generate(keyCode))
				state=Menu.ChooseSpaceShip;
		}else{
			switch(keyCode){
			case Keys.ESCAPE:
				pressedEscape();
				break;
			case Keys.NUM_1:
				pressed1();
				break;
			case Keys.NUM_2:
				pressed2();
				break;
			case Keys.NUM_3:
				pressed3();
				break;
			case Keys.NUM_4:
				pressed4();
				break;
			case Keys.NUM_5:
				pressed5();
				break;
			default:
				break;
			}
		}
	}

	private void pressedEscape() {
		switch(state){
		case Main:
			Gdx.app.exit(); //the game ends
		case HighScore:
		case Settings:
		case Help:
		case NumberOfPlayers:
			this.state=Menu.Main;
			break;
		case GameMode2Players:
			this.state=Menu.NumberOfPlayers;
			break;
		case GameMode3Players:
			this.state=Menu.NumberOfPlayers;
			break;
		case GetPlayerName:
			if(mode == GameMode.TwoPlayersA || mode == GameMode.TwoPlayersB){
				this.state=Menu.GameMode2Players;
			}else if(mode ==GameMode.ThreePlayersA || mode==GameMode.ThreePlayersB){
				this.state=Menu.GameMode3Players;
			}else
				this.state=Menu.NumberOfPlayers;
			break;
		case ChooseSpaceShip:
			resetSelected();
			resetNames();
			this.state=Menu.GetPlayerName;
		default:break;
		}
	}

	private void pressed1() {
		switch(state){
		case Main:
			this.state=Menu.NumberOfPlayers;
			break;
		case Settings:
			changeResolution(800,600);
			break;
		case NumberOfPlayers:
			this.state=Menu.GetPlayerName;
			this.mode=GameMode.OnePlayer;
			this.numberofShips=1;
			break;
		case GameMode2Players:
			this.state=Menu.GetPlayerName;
			this.mode=GameMode.TwoPlayersA;
			break;
		case GameMode3Players:
			this.state=Menu.GetPlayerName;
			this.mode=GameMode.ThreePlayersA;
			break;
		case ChooseSpaceShip:
			shipSelected(0);
			break;
		default:break;
		}
	}

	private void pressed2() {
		switch(state){
		case Main:
			this.state=Menu.Settings;
			Settings();
			break;
		case Settings:
			changeResolution(1024,768);
			break;
		case NumberOfPlayers:
			this.state=Menu.GameMode2Players;
			this.numberofShips=2;
			break;
		case GameMode2Players: 
			this.state=Menu.GetPlayerName;
			this.mode=GameMode.TwoPlayersB;
			break;
		case GameMode3Players:
			this.state=Menu.GetPlayerName;
			this.mode=GameMode.ThreePlayersB;
			break;
		case ChooseSpaceShip:
			shipSelected(1);
			break;
		default:break;
		}			
	}

	private void pressed3() {
		switch(state){
		case Main: 
			this.state=Menu.Help;
			Help();
			break;
		case Settings:
			changeResolution(1280,1024);
			break;
		case NumberOfPlayers:
			this.state=Menu.GameMode3Players;
			this.numberofShips=3;
			break;
		case ChooseSpaceShip:
			shipSelected(2);
			break;	
		default:break;
		}
	}

	private void pressed4() {
		switch(state){
		case Main:
			this.state=Menu.HighScore;
			//aparece el highscore
			break;
		case Settings:
			changeResolution(true); //cambiar esto
			break;
		case ChooseSpaceShip:
			shipSelected(3);
			break;
		default:break;
		}
	}

	private void pressed5(){
		switch(state){
		case ChooseSpaceShip:
			shipSelected(4);
			break;
		default:break;
		}
	}
	
	private void changeResolution(boolean fullscreen){
		Gdx.graphics.setDisplayMode(0,0,fullscreen); //cambio
		
	}
	private void changeResolution(int width, int height){
		for(DisplayMode d: Gdx.graphics.getDisplayModes()){
			System.out.println(d.width + "x" +d.height);
		}
		Gdx.graphics.setDisplayMode(width, height, false);
	}

	private boolean Generate(int KeyCode){
		String key = Input.Keys.toString(KeyCode);
		if(key.length()==1 && name.length()<11)
			this.name=name.concat(key);
		else if(KeyCode == Keys.ENTER){
			return setplayerName();
		}else if(KeyCode == Keys.DEL){
			if(!(name.length()==0))
				this.name=name.substring(0, name.length()-1);
		}
		return false;
	}

	/**
	 * This method sets the name of the player 
	 * @return if all the names have been saved
	 */
	private boolean setplayerName(){
		playerNames.put(numberofNamesSaved, name);
		numberofNamesSaved++;
		name="";
		if(this.numberofShips <= this.numberofNamesSaved)
			return true;
		else
			return false;

	}

	/**
	 * 
	 * @param player; the player who is typing their name
	 * @return the name of the player
	 */
	public String getName(int player){
		if(playerNames.get(player) == null){
			return name;			
		}
		return playerNames.get(player);
	}
	
	/**
	 * @return the number of names that have already been saved
	 */
	public int getCompleteName(){
		return this.numberofNamesSaved;
	}

	private void resetNames(){
		for(int i=0;i<this.numberofNamesSaved;i++)
			this.playerNames.put(i, null);
		this.numberofNamesSaved=0;
		this.name="";
	}

	private void resetSelected() {
		for(int i=0;i<Assets.SHIPS.length;i++){
			selected[i]=false;		
		}
		this.numberOfShipsSelected=0;
	}

	/**
	 * Given the spaceShip i this method removes it from the posible spaceShips to choose if it wasn't already selected.
	 * If the spaceShip was already selected the method does nothing.
	 * If the number of spaceShips is equal to the number of spaceShips that have been selected, the game is ready to begin and the game 
	 * is created
	 * @param i; the index of the spaceShip in the arry of spaceShips in assets
	 */
	private void shipSelected(int i) {
		if(this.numberOfShipsSelected<this.numberofShips && !spaceShipSelected(i)){
			selected[i]=true;
			this.numberOfShipsSelected++;
			boolean startsAsteroid = false;
			boolean createAsteroidPlayer = false;
			if(mode == GameMode.ThreePlayersB || mode == GameMode.TwoPlayersB){
				createAsteroidPlayer = true;
				if((GameMode.ThreePlayersB == mode && numberOfShipsSelected == 3 )||( mode == GameMode.TwoPlayersB && numberOfShipsSelected == 2)){
					startsAsteroid = true;
				}

			}
			GameManager.getInstance().addSpaceShip(numberOfShipsSelected, SpaceShipCreator.create(i, numberOfShipsSelected),createAsteroidPlayer,startsAsteroid,getName(numberOfShipsSelected - 1));
			if(this.numberOfShipsSelected==this.numberofShips) //si ya se eligieron todas las naves lo que hace es crea el juego
				generateGame(this.mode);
		}
	}

	/**
	 * Creats the game by generating a new Game in the gameManager and gameManagerUI
	 * @param mode; the game mode
	 */
	private void generateGame(GameMode mode) {

		GameManager.getInstance().newGame(mode);
		GameManagerUI.getInstance().newGame(mode);
	}

	/**
	 * @return the state the menu is in
	 */
	public Menu getState(){
		return this.state;
	}

	/**
	 * 
	 * @return the amount of Ships that are going to be used, the number of players in the game
	 */
	public int getPlayers() {
		return this.numberofShips;
	}

	/**
	 * @return the amount of ships that have already been selected
	 */
	public int getSpaceShipsSelected(){
		return this.numberOfShipsSelected;
	}

	/**
	 * 
	 * @param s; the index of the spaceShip in the menu
	 * @return if the spaceShip s has been seleted or not by the user
	 */
	public boolean spaceShipSelected(int s) {
		return selected[s];
	}

	private void Help() {
		//VER

	}

	private void Settings(){

	}

	public void update() {	

	}
}
