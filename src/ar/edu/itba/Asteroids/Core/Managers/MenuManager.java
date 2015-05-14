package ar.edu.itba.Asteroids.Core.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MenuManager {
	private static MenuManager self;
	private Menu state=Menu.Main;
	private GameMode mode;


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
		}
	}

	private void Pressed0(boolean b) {
		if(b && state!=Menu.Main){
			switch(state){
			case NumberOfPlayers:
				this.state=Menu.Main;
				break;
			case GameMode:
				this.state=Menu.NumberOfPlayers;
				break;
			case ChooseSpaceShip:
				if(mode == GameMode.TwoPlayersA || mode == GameMode.TwoPlayersB){
					this.state=Menu.GameMode;
				}else{
					this.state=Menu.NumberOfPlayers;
				}
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
				this.state=Menu.GameMode;
				this.mode=GameMode.OnePlayer;
				break;
			case GameMode:
				this.state=Menu.ChooseSpaceShip;
				this.mode=GameMode.TwoPlayersA;
				break;
			case ChooseSpaceShip:
				GenerateGame(mode);
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
				this.state=Menu.GameMode;
				break;
			case GameMode: 
				this.state=Menu.ChooseSpaceShip;
				this.mode=GameMode.TwoPlayersB;
				break;
			case ChooseSpaceShip: 
				GenerateGame(mode);
				break;
			}			
		}


	}

	private void Pressed3(boolean b) {
		if(b){
			switch(state){
			case Main: 
				Gdx.app.exit(); //deberia terminar el juego
				break;
			case NumberOfPlayers:
				this.state=Menu.ChooseSpaceShip;
				this.mode=GameMode.ThreePlayers;
				break;
			case ChooseSpaceShip: 
				GenerateGame(mode);
				break;	
			default:break;
			}
		}

	}

	private void GenerateGame(GameMode mode2) {
		//se crearia el juego y lo asignas al Game Manager 
	}

	private void Help() {
		//VER

	}

	public Menu getState(){
		return this.state;
	}

	public void update() {	
	}


}
