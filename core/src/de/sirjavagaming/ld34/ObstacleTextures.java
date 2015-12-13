package de.sirjavagaming.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;

public class ObstacleTextures {
	
	public static void init() {
		
		TextureLoader.TextureParameter params = new TextureLoader.TextureParameter();
		params.minFilter = Texture.TextureFilter.MipMapLinearLinear;
		params.magFilter = Texture.TextureFilter.Linear;
		
		
		
		circle = new Texture(Gdx.files.internal("circle.png"), true);
		hexagon = new Texture(Gdx.files.internal("hexagon.png"), true);
		cube = new Texture(Gdx.files.internal("cube_test.png"), true);
		coin = new Texture(Gdx.files.internal("coin.png"));
	}
	
	public static Texture circle;
	public static Texture hexagon;
	public static Texture cube;
	public static Texture coin;
	
	public static Texture getTexture(int id) {
		switch (id) {
		case 0:
			return circle;
		case 1:
			return hexagon;
		case 2:
			return cube;
		case 10:
			return coin;
		default:
			return null;
		}
	}
}
