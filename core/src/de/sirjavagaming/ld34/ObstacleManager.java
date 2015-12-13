package de.sirjavagaming.ld34;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObstacleManager {
	
	private List<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	private Random random;
	
	private int coins_planted = 0;
	
	public void create() {
		ObstacleTextures.init();
		random = new Random();
	}
	
	public void updateAndRender(SpriteBatch batch, int pixels) {
		if(random.nextInt(Game.getGame().getDifficulty().getI()) == 0 && Game.getGame().isInGame() && pixels < MathUtil.GAME_LENGTH) {
			Obstacle o = new Obstacle();
			int y = pixels + 800;
			int x = MathUtil.rand.nextInt(1180) + 50;
			if(clearPosition(x, y)) {
				o.create(x, y);
				obstacles.add(o);
			}
		}
		
		if(MathUtil.GAME_LENGTH / 27 * coins_planted < pixels) {
			Obstacle coin = new Coin();
			
			int y = pixels + 800;
			int x = MathUtil.rand.nextInt(1180) + 50;
			
			if(clearPosition(x, y)) {
				coin.create(x, y);
				obstacles.add(coin);
				coins_planted++;
			}
			
		}
		
		for(Obstacle obstacle : obstacles) {
			if(obstacle.isOutOfScreen(pixels)) {
				obstacles.remove(obstacle);
				break;
			}
		}

		for(Obstacle obstacle : obstacles) {
			obstacle.updateAndRender(batch, pixels);
			
		}
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	private boolean clearPosition(int x, int y) {
		for(Obstacle o : obstacles) {
			if(Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2)) < 200) {
				return false;
			}
		}
		return true;
	}
}
