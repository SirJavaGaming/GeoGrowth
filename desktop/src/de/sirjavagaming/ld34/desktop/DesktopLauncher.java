package de.sirjavagaming.ld34.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.sirjavagaming.ld34.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.title = "GeoGrowth";
		config.foregroundFPS = 50;
		config.backgroundFPS = 50;
		config.vSyncEnabled = true; //Disabled. Causes flickering. If you have ideas why, send to sirjavagaming@gmail.com! Thanks <3!
		config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
