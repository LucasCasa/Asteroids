package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Drawable;
import ar.edu.itba.Asteroids.Core.Player;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD1Player;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD2Player;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD2PlayersVs;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD3Player;
import ar.edu.itba.Asteroids.Core.Managers.HUDs.HUD3Players2vs1;
import ar.edu.itba.Asteroids.Core.Managers.WorldManagers.WorldManagerUI;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipUI;

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
		Player p1 = GameManager.getInstance().getPlayer(0);
		Player p2 = GameManager.getInstance().getPlayer(1);
		Player p3 = GameManager.getInstance().getPlayer(2);
		ArrayList<SpaceShipUI> aux = GameManager.getInstance().getShipsUI();
		switch(gm){
		case OnePlayer:
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(),aux,new HUD1Player(p1));
			break;
		case TwoPlayersA:
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(),aux,  new HUD2Player(p1,p2));
			break;
		case TwoPlayersB:
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(),aux, new HUD2PlayersVs(p1,p2));
			break;
		case ThreePlayersA:
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(),aux, new HUD3Player(p1, p2,p3));
			break;
		case ThreePlayersB:
			worldUI = new WorldManagerUI(GameManager.getInstance().getWorld(),aux, new HUD3Players2vs1(p1, p2, p3));
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
