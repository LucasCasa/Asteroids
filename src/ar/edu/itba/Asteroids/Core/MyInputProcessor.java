package ar.edu.itba.Asteroids.Core;

import ar.edu.itba.Asteroids.Core.Managers.WorldManager;

import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

	public MyInputProcessor(){

	}
	@Override
	public boolean keyDown (int keyCode) {
		WorldManager.getInstance().keyDown(keyCode);// esto despues tiene que llamar a gameManager
		return false;
	}

	@Override
	public boolean keyUp (int keyCode) {
		WorldManager.getInstance().keyUp(keyCode); // esto tambien
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}
}
