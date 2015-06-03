package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;

import ar.edu.itba.Asteroids.Core.Assets;
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
/**
 * the Core UI of the game, it calls other object based of the state of the game
 * @author ME
 *
 */
public class GameManagerUI implements Drawable{
	private static GameManagerUI self = null;
	private WorldManagerUI worldUI;
	private boolean lastState = true;
	private GameManagerUI(){
		Assets.SOUND_MENU.play();
	}
	public static GameManagerUI getInstance(){
		if(self == null){
			self = new GameManagerUI();
		}
		return self;
	}
	/**
	 * it creates a new WorldManagerUI based of the gameMode
	 * @param gm the gameMode to play
	 */
	public void newGame(GameModeTypes gm){
		Assets.SOUND_GAME.play();
		Assets.SOUND_MENU.stop();
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
	/**
	 * calls the MenuManagerUI or WorldManagerUI depending on the state of the game
	 */
	public void draw(SpriteBatch batch){
		changeState();
		lastState = GameManager.getInstance().isInMenu();
		if(lastState){
			MenuManagerUI.getInstance().draw(batch);
		}else{
			worldUI.draw(batch);
		}
	}
	private void changeState(){
		if(GameManager.getInstance().isInMenu() != lastState){
			if(GameManager.getInstance().isInMenu()){
				Assets.SOUND_MENU.loop(Assets.menuVolume);
				Assets.SOUND_GAME.stop();
			}else{
				Assets.SOUND_GAME.loop(Assets.gameVolume);
				Assets.SOUND_MENU.stop();
			}
		}
	}
	public WorldManagerUI getWorldUI() {
		return worldUI;
	}
}
