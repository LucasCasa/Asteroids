package ar.edu.itba.Asteroids.Core;

import ar.edu.itba.Asteroids.Core.Managers.GameManager;

import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

	public MyInputProcessor(){

	}
	@Override
	public boolean keyDown (int keyCode) {
		GameManager.getInstance().keyDown(keyCode);
		return false;
	}

	@Override
	public boolean keyUp (int keyCode) {
		GameManager.getInstance().keyUp(keyCode);
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
