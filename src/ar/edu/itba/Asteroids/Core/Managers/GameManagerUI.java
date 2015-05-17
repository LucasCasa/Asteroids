package ar.edu.itba.Asteroids.Core.Managers;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Asteroids.AsteroidPlayer;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD1Player;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD2Player;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD2PlayersVs;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD3Player;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManagerUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShip;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManagerUI implements Drawable{
	private static GameManagerUI self = null;
	private WorldManagerUI worldUI;
	private GameManagerUI(){
		
	}
	public static GameManagerUI getInstance(){
		if(self == null){
			self = new GameManagerUI();
		}
		return self;
	}
	public void newGame(GameMode gm){
		SpaceShip aux1;
		SpaceShip aux2;
		SpaceShip aux3;
		
		switch(gm){
		case OnePlayer:
			aux1 = GameManager.getInstance().getWorld().getSpaceShips().get(0);
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(),new HUD1Player(aux1));
			break;
		case TwoPlayersA:
			aux1 = GameManager.getInstance().getWorld().getSpaceShips().get(0);
			aux2 = GameManager.getInstance().getWorld().getSpaceShips().get(1);
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(), new HUD2Player(aux1, aux2));
			break;
		case TwoPlayersB:
			aux1 = GameManager.getInstance().getWorld().getSpaceShips().get(0);
			AsteroidPlayer a = GameManager.getInstance().getWorld().getAsteroidPlayer();
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(), new HUD2PlayersVs(aux1,a));
			break;
		case ThreePlayersA:
			aux1 = GameManager.getInstance().getWorld().getSpaceShips().get(0);
			aux2 = GameManager.getInstance().getWorld().getSpaceShips().get(1);
			aux3 = GameManager.getInstance().getWorld().getSpaceShips().get(2);
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(), new HUD3Player(aux1, aux2,aux3));
			break;
		}
	}
	
	public void draw(SpriteBatch batch){
		if(GameManager.getInstance().isInMenu()){
			MenuManagerUI.getInstance().draw(batch);
		}else{
			worldUI.draw(batch);
		}
	}
}
