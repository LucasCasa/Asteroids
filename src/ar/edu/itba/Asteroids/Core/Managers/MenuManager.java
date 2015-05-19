package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipCreator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MenuManager {
	private static MenuManager self;
	private Menu state=Menu.Main;
	private GameMode mode;
	private boolean[] selected = {false, false, false, false, false};
	private int numberOfShipsSelected = 0;
	private int numberofShips = 0;

	private MenuManager(){
		this.resetSelected();
	}


	public static MenuManager getInstance(){
		if(self == null){
			self = new MenuManager();
		}
		return self;
	}


	public void keyDown(int keyCode) {
		switch(keyCode){
		case Keys.NUM_0:
		case Keys.NUMPAD_0:
			Pressed0();
			break;
		case Keys.NUMPAD_1:
		case Keys.NUM_1:
			Pressed1();
			break;
		case Keys.NUMPAD_2:
		case Keys.NUM_2:
			Pressed2();
			break;
		case Keys.NUMPAD_3:
		case Keys.NUM_3:
			Pressed3();
			break;
		case Keys.NUMPAD_4:
		case Keys.NUM_4:
			Pressed4();
			break;
		case Keys.NUMPAD_5:
		case Keys.NUM_5:
			Pressed5();
			break;
		}
	}

	private void Pressed0() {
		switch(state){
		case NumberOfPlayers:
			this.state=Menu.Main;
			break;
		case GameMode2Players:
			this.state=Menu.NumberOfPlayers;
			break;
		case GameMode3Players:
			this.state=Menu.NumberOfPlayers;
			break;
		case ChooseSpaceShip:
			resetSelected();
			if(mode == GameMode.TwoPlayersA || mode == GameMode.TwoPlayersB){
				this.state=Menu.GameMode2Players;
			}else if(mode ==GameMode.ThreePlayersA || mode==GameMode.ThreePlayersB){
				this.state=Menu.GameMode3Players;
			}else
				this.state=Menu.NumberOfPlayers;
			break;
		default:
			break;
		}
	}

	private void Pressed1() {
		switch(state){
		case Main:
			this.state=Menu.NumberOfPlayers;
			break;
		case NumberOfPlayers:
			this.state=Menu.ChooseSpaceShip;
			this.mode=GameMode.OnePlayer;
			this.numberofShips=1;
			break;
		case GameMode2Players:
			this.state=Menu.ChooseSpaceShip;
			this.mode=GameMode.TwoPlayersA;
			break;
		case GameMode3Players:
			this.state=Menu.ChooseSpaceShip;
			this.mode=GameMode.ThreePlayersA;
			break;
		case ChooseSpaceShip:
			shipSelected(0);
			break;
		}
	}

	private void Pressed2() {
		switch(state){
		case Main:
			Help();
			break;
		case NumberOfPlayers:
			this.state=Menu.GameMode2Players;
			this.numberofShips=2;
			break;
		case GameMode2Players: 
			this.state=Menu.ChooseSpaceShip;
			this.mode=GameMode.TwoPlayersB;
			break;
		case GameMode3Players:
			this.state=Menu.ChooseSpaceShip;
			this.mode=GameMode.ThreePlayersB;
			break;
		case ChooseSpaceShip:
			shipSelected(1);
			break;
		}			
	}

	private void Pressed3() {
		switch(state){
		case Main: 
			Gdx.app.exit(); //the game ends
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

	private void Pressed4() {
		switch(state){
		case ChooseSpaceShip:
			shipSelected(3);
			break;
		default:break;
		}
	}

	private void Pressed5(){
		switch(state){
		case ChooseSpaceShip:
			shipSelected(4);
			break;
		default:break;
		}
	}

	/**
	 * resets the selected Ships so that the user can choose all of them
	 */
	private void resetSelected() {
		for(int i=0;i<Assets.SHIPS.length;i++){
			selected[i]=false;		
		}
		this.numberOfShipsSelected=0;
	}

	//lo que hace este metodo es dado el ship i lo saca de los posibles spaceShips a elegir si no fue seleccionado todavia
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
			GameManager.getInstance().addSpaceShip(numberOfShipsSelected, SpaceShipCreator.create(i, numberOfShipsSelected),createAsteroidPlayer,startsAsteroid,"hola");
			if(this.numberOfShipsSelected==this.numberofShips) //si ya se eligieron todas las naves lo que hace es crea el juego
				GenerateGame(this.mode);
		}
	}


	private void GenerateGame(GameMode mode) {
		
		GameManager.getInstance().newGame(mode);
		GameManagerUI.getInstance().newGame(mode);
	}

	private void Help() {
		//VER

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
	
	public void update() {	
	}




}
