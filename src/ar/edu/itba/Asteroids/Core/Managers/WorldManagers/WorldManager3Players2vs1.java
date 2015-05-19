package ar.edu.itba.Asteroids.Core.Managers.WorldManagers;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.badlogic.gdx.Input.Keys;

import ar.edu.itba.Asteroids.Core.Connector;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

public class WorldManager3Players2vs1 extends WorldManager {
	private int asteroidPlayerNumber = 3;
	private boolean changePlayers = true;
	public WorldManager3Players2vs1(List<Connector<SpaceShip,SpaceShipUI>> s,ArrayList<Player> players) {
		super(players);
		s.get(2).getBack().setActive(false);
		super.getAll().put(s.get(0).getBack(), s.get(0).getFront());
		super.getAll().put(s.get(1).getBack(), s.get(1).getFront());
		super.getAll().put(s.get(2).getBack(), s.get(2).getFront());
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
		switch (keyCode) {
		case Keys.W:
		case Keys.A:
		case Keys.S:
		case Keys.D:
			super.keyDown(keyCode);
			break;
		case Keys.DOWN:
			getSpaceShips().get(1).acelDown(true);
			break;
		case Keys.UP:
			getSpaceShips().get(1).acelUp(true);
			break;
		case Keys.LEFT:
			getSpaceShips().get(1).acelLeft(true);
			break;
		case Keys.RIGHT:
			getSpaceShips().get(1).acelRight(true);
			break;
		case Keys.K:
			getSpaceShips().get(2).acelDown(true);
			break;
		case Keys.I:
			getSpaceShips().get(2).acelUp(true);
			break;
		case Keys.J:
			getSpaceShips().get(2).acelLeft(true);
			break;
		case Keys.L:
			getSpaceShips().get(2).acelRight(true);
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
	
	public void keyUp(int keyCode){
		switch (keyCode) {
		case Keys.DOWN:
			getSpaceShips().get(1).acelDown(false);
			break;
		case Keys.UP:
			getSpaceShips().get(1).acelUp(false);
			break;
		case Keys.LEFT:
			getSpaceShips().get(1).acelLeft(false);
			break;
		case Keys.RIGHT:
			getSpaceShips().get(1).acelRight(false);
			break;
		case Keys.K:
			getSpaceShips().get(2).acelDown(false);
			break;
		case Keys.I:
			getSpaceShips().get(2).acelUp(false);
			break;
		case Keys.J:
			getSpaceShips().get(2).acelLeft(false);
			break;
		case Keys.L:
			getSpaceShips().get(2).acelRight(false);
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
