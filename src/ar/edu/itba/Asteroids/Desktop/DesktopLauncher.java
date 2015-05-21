package ar.edu.itba.Asteroids.Desktop;

import ar.edu.itba.Asteroids.Core.MyGdxGame;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;
		config.fullscreen = false;
		config.resizable = false;
		config.title = "Asteroids";
		config.addIcon("icon32.png", FileType.Internal);
		config.addIcon("icon16.png", FileType.Internal);
		new LwjglApplication(new MyGdxGame(), config);
	}
	
}
