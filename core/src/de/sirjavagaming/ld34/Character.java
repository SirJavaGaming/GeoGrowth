package de.sirjavagaming.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character {
	
	private static final float baseSize = 50;
	
	private Texture playerTexture;
	
	private Color generatedColor;
	
	private int x = 640;
	
	private float rotation = 0;
	
	private long lastCollision;
	
	private Sound collisionSound;
	
	public void create() {
		playerTexture = new Texture(Gdx.files.internal("player.png"));
		generatedColor = MathUtil.generateRandomColor(true);
		collisionSound = Gdx.audio.newSound(Gdx.files.internal("hit2.ogg"));
		
	}
	
	public void updateAndRender(SpriteBatch batch, int pixels) {	
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.A) ) {
		}
		
		if(pixels > MathUtil.GAME_LENGTH + 1000) {
			Game.getGame().setGameState(GameState.DEAD);
			Game.getGame().setGui(new GuiOver());
		}
		
		float calc = baseSize * MathUtil.pixelsToScale(pixels);
		//TODO: Staggering movement
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Game.getGame().isInGame()) {
			x-=10;
			if(x < calc / 2 + 5)
				x = (int) (calc / 2 + 5);
			rotation += 20f;
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Game.getGame().isInGame()) {
			x+=10;
			if(x > 1280 - calc / 2 - 5)
				x = (int) (1280 - calc / 2 - 5);
			rotation -= 25f;
		} else if(Game.getGame().isInGame()){
			rotation = (float)Math.toDegrees(Math.cos(Math.toRadians((System.currentTimeMillis() %1440)/4)))*4;
		}

		checkCollision(pixels);
		if(lastCollision + 1000 > System.currentTimeMillis()) {
			if((System.currentTimeMillis() % 400) < 200) {
				batch.setColor(generatedColor);
			} else {
				batch.setColor(Color.RED);
			}
		} else {
			batch.setColor(generatedColor);
		}
		batch.draw(playerTexture, x - calc/2, 50, calc/2, calc/2, calc, calc, 1, 1, rotation, 0, 0, 400, 400, false, false);
		batch.setColor(1, 1, 1, 1);
	}

	private void checkCollision(int pixels) {
		
		if(lastCollision + 600 > System.currentTimeMillis()) return;
		
		int myRadius = (int)(baseSize * MathUtil.pixelsToScale(pixels) / 2);
		
		for(Obstacle o : Game.getGame().getObstacleManager().getObstacles()) {
			int distance = (int)(Math.sqrt(Math.pow(o.getX() - x, 2) + Math.pow(o.getY() - pixels - 50 - myRadius, 2))+0.5); 
			if(myRadius + o.getRadius() > distance) {
				if(o instanceof Coin) {
					Game.getGame().getObstacleManager().getObstacles().remove(o);
					Game.getGame().addCoin();
					return;
				} else {
					if(Game.getGame().isInGame()) {
						collisionSound.play(1f);
						Game.getGame().hit();
					}
					lastCollision = System.currentTimeMillis();
				}
			}
		}
		
	}
	

}
