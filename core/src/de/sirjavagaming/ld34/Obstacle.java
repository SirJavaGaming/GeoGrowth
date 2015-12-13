package de.sirjavagaming.ld34;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacle {
	
	private int y;
	private int x;
	
	protected Color color;
	
	protected float calc;
	
	private static final float baseSize = 75f;
	
	protected int texture;
	
	public void create(int x, int y) {
		this.y = y;
		this.x = x;
		this.color = MathUtil.generateRandomColor(false);
		this.calc = baseSize * (MathUtil.rand.nextFloat() + 0.5f);
		this.texture = MathUtil.rand.nextInt(3);
	}
	
	public void updateAndRender(SpriteBatch batch, int pixels) {
		batch.setColor(color);
		float rotation = 0;
		batch.setColor(color);
		if(this instanceof Coin) {
			int off = Math.abs((int)(((Math.pow(Math.sin(Math.toRadians((System.currentTimeMillis() / 6) % 360)), 2)) * calc/2)));
			
			batch.draw(ObstacleTextures.getTexture(texture), Math.round(x - calc/2 + off), Math.round(y - pixels - calc/2), Math.round(calc/2 - off), Math.round(calc/2), Math.round(calc - 2*off), Math.round(calc), 1, 1, Math.round(rotation), 0, 0, 400, 400, false, false);
		} else {
			batch.draw(ObstacleTextures.getTexture(texture), Math.round(x - calc/2), Math.round(y - pixels - calc/2), Math.round(calc/2), Math.round(calc/2), Math.round(calc), Math.round(calc), 1, 1, Math.round(rotation), 0, 0, 400, 400, false, false);
		}
		batch.setColor(1, 1, 1, 1);
	}
	
	public boolean isOutOfScreen(int pixels) {
		return y + 100 < pixels;
	}
	
	public int getRadius() {
		return (int)calc/2;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
