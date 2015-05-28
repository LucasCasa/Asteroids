package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;

import ar.edu.itba.Asteroids.Core.Player;
/**
 * the world Manager in a 3 players match, where one controlls the asteroids
 *
 */
public class WorldManager3Players2vs1 extends WorldManager {
	private int asteroidPlayerNumber = 3;
	private boolean changePlayers = true;
	public WorldManager3Players2vs1(ArrayList<Player> players) {
		super(players);
	}
	
	public void update(){
		super.update();
		changePlayers = true;
		for(int i = 0; i<players.size();i++){
				changePlayers = changePlayers && (players.get(i).shipHasLost() || players.get(i).isAsteroidPlayer());
			}
		if(changePlayers && asteroidPlayerNumber == 1){
			gameOver = true;
		}
		if(changePlayers){
			impasse = true;
			for(int i = 0; i<players.size(); i++){
				if( i == asteroidPlayerNumber -1){
					getPlayer(i).changeState();
				}else if( i == asteroidPlayerNumber - 2){
					getPlayer(i).changeState();
				} else {
					getPlayer(i).reset();
				}
			
			}
			asteroidPlayerNumber--;
		}
		
	}

	public void keyDown(int keyCode){
		if(isPaused() || isImpasse()){
			super.keyDown(keyCode);
		}else{
			switch (keyCode) {
			case Keys.W:
			case Keys.A:
			case Keys.S:
			case Keys.D:
			case Keys.ESCAPE:
				super.keyDown(keyCode);
				break;
			case Keys.DOWN:
				players.get(1).getSpaceShip().acelDown(true);
				break;
			case Keys.UP:
				players.get(1).getSpaceShip().acelUp(true);
				break;
			case Keys.LEFT:
				players.get(1).getSpaceShip().acelLeft(true);
				break;
			case Keys.RIGHT:
				players.get(1).getSpaceShip().acelRight(true);
				break;
			case Keys.K:
				players.get(2).getSpaceShip().acelDown(true);
				break;
			case Keys.I:
				players.get(2).getSpaceShip().acelUp(true);
				break;
			case Keys.J:
				players.get(2).getSpaceShip().acelLeft(true);
				break;
			case Keys.L:
				players.get(2).getSpaceShip().acelRight(true);
				break;
			default:
				for(Player p :players){
					if(p.isAsteroidPlayer()){
						p.getAsteroidPlayer().keyPressed(keyCode, super.getSpaceShips());
					}
				}
				break;
			}
		}
	}
	
	public void keyUp(int keyCode){
		switch (keyCode) {
		case Keys.DOWN:
			players.get(1).getSpaceShip().acelDown(false);
			break;
		case Keys.UP:
			players.get(1).getSpaceShip().acelUp(false);
			break;
		case Keys.LEFT:
			players.get(1).getSpaceShip().acelLeft(false);
			break;
		case Keys.RIGHT:
			players.get(1).getSpaceShip().acelRight(false);
			break;
		case Keys.K:
			players.get(2).getSpaceShip().acelDown(false);
			break;
		case Keys.I:
			players.get(2).getSpaceShip().acelUp(false);
			break;
		case Keys.J:
			players.get(2).getSpaceShip().acelLeft(false);
			break;
		case Keys.L:
			players.get(2).getSpaceShip().acelRight(false);
			break;
		default:
			super.keyUp(keyCode);
			break;
		}
	}


	@Override
	public Player getWinner() {
		Player winner = players.get(0);
		for(int i =1; i<players.size();i++){
			if(players.get(i).getScore() > winner.getScore()){
				winner = players.get(i);
			}
		}
		return winner;
	}
}
