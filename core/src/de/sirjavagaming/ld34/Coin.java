package de.sirjavagaming.ld34;

import com.badlogic.gdx.graphics.Color;

public class Coin extends Obstacle {
	
	@Override
	public void create(int x, int y) {
		super.create(x, y);
		super.texture = 10;
		super.color = new Color(1, 1, 1, 1);
		super.calc = 70f;
	}

}
