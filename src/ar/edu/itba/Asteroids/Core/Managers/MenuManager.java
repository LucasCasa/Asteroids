package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.Asteroids.Core.Assets;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MenuManager {
	private static MenuManager self;
	private Menu state=Menu.Main;
	private GameMode mode;
	private List<SpaceShip> s = new ArrayList<SpaceShip>();
	
	private List<Boolean> selected = new ArrayList<Boolean>();
	private int numberOfShipsSelected = 0;
	private int numberofShips = 0;
	
	private MenuManager(){
		resetSelected();
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
			Pressed0(true);
			break;
		case Keys.NUMPAD_1:
		case Keys.NUM_1:
			Pressed1(true);
			break;
		case Keys.NUMPAD_2:
		case Keys.NUM_2:
			Pressed2(true);
			break;
		case Keys.NUMPAD_3:
		case Keys.NUM_3:
			Pressed3(true);
			break;
		case Keys.NUMPAD_4:
		case Keys.NUM_4:
			Pressed4(true);
			break;
		case Keys.NUMPAD_5:
		case Keys.NUM_5:
			Pressed5(true);
			break;
		}
	}

	public void keyUp(int keyCode) {
		switch(keyCode){
		case Keys.NUM_0:
		case Keys.NUMPAD_0:
			Pressed0(false);
			break;
		case Keys.NUMPAD_1:
		case Keys.NUM_1:
			Pressed1(false);
			break;
		case Keys.NUMPAD_2:
		case Keys.NUM_2:
			Pressed2(false);
			break;
		case Keys.NUMPAD_3:
		case Keys.NUM_3:
			Pressed3(false);
			break;
		case Keys.NUMPAD_4:
		case Keys.NUM_4:
			Pressed4(false);
			break;
		case Keys.NUMPAD_5:
		case Keys.NUM_5:
			Pressed5(false);
			break;
		}
	}

	private void Pressed0(boolean b) {
		if(b && state!=Menu.Main){
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
			default:
				break;
			}
		}

	}

	private void Pressed1(boolean b) {
		if(b){
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
			case ChooseSpaceShip:
				shipSelected(1);
				break;
			}
		}
	}

	private void Pressed2(boolean b) {
		if(b){
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
			case ChooseSpaceShip:
				shipSelected(2);
				break;
			}			
		}


	}

	private void Pressed3(boolean b) {
		if(b){
			switch(state){
			case Main: 
				Gdx.app.exit(); //the game ends
				break;
			case NumberOfPlayers:
				this.state=Menu.GameMode3Players;
				this.numberofShips=3;
				break;
			case ChooseSpaceShip:
				shipSelected(3);
				break;	
			default:break;
			}
		}

	}
	
	private void Pressed4(boolean b) {
		if(b){
			switch(state){
			case ChooseSpaceShip:
				shipSelected(4);
			default:
				break;
			}
		}
	}
	
	private void Pressed5(boolean b){
		if(b){
			switch(state){
			case ChooseSpaceShip:
				shipSelected(5);
			default:
				break;
			}
		}
	}
	
	/**
	 * resets the selected Ships so that the user can choose all of them
	 */
	private void resetSelected() {
		for(int i=0;i<Assets.SHIPS.length;i++)
			this.selected.add(i, false);
		this.numberOfShipsSelected=0;
	}
	
	//lo que hace este metodo es dado el ship i lo saca de los posibles spaceShips a elegir 
	private void shipSelected(int i) {
		if(this.numberOfShipsSelected<this.numberofShips){
			this.selected.add(i-1, true);
			numberOfShipsSelected++;
			GenerateGame(mode);
		}
	}
	
	private void GenerateGame(GameMode mode2) {
		for(int i=0;i<Assets.SHIPS.length;i++){
			if(this.selected.get(i)){
				//entonces lo agrega a los spaceShips
			}
		}
		//se crearia el juego y lo asignas al Game Manager y se crean las spaceships necesarias
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

	public void update() {	
	}

	/**
	 * 
	 * @param s
	 * @return if the spaceShip s has been seleted or not by the user
	 */
	public boolean spaceShipSelected(int s) {
		return this.selected.get(s);
	}



}
