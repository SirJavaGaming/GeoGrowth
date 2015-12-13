package de.sirjavagaming.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiOver extends Gui {
	
	private Texture background;
	
	private int totalDistance;
	
	private int difficultyBonus;
	
	private int totalScore;
	
	@Override
	public void create() {
		background = new Texture(Gdx.files.internal("dead.png"));
		totalDistance = 500-((MathUtil.GAME_LENGTH - Game.getGame().getPixels()) * 500 / MathUtil.GAME_LENGTH );
		switch (Game.getGame().getDifficulty()) {
		case EASY:
			difficultyBonus = 0;
			break;
		case MEDIUM:
			difficultyBonus = 30;
			break;
		case HARD:
			difficultyBonus = 60;
			break;
		default:
			break;
		}
		
		totalScore = MathUtil.calcTotalPoints();
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(background, 440, 60, 400, 600);
		BitmapFont font = Game.getGame().getFont();
		font.getData().setScale(0.67f);
		int lives = Game.getGame().getLives();
		if(lives > 0) {
			font.setColor(0, 1, 0, 1);
			font.draw(batch, "YOU WON", 510, 600);
		} else {
			font.setColor(1, 0, 0, 1);
			font.draw(batch, "YOU DIED", 510, 600);
		}
		font.getData().setScale(0.25f);
		font.setColor(0.8f, 1f, 1f, 1);
		font.draw(batch, "________________", 480, 530);
		font.draw(batch, "Total distance: " + totalDistance, 480, 490);
		font.draw(batch, "Coins: " + Game.getGame().getCoins(), 480, 450);
		font.draw(batch, "Lives: " + (lives == 0 ? "None" : lives+""), 480, 410);
		font.draw(batch, "Difficulty bonus: " + difficultyBonus, 480, 370);
		font.draw(batch, "________________", 480, 350);
		font.getData().setScale(0.4f);
		font.draw(batch, "Total score:", 480, 300);
		font.draw(batch, totalScore + " out of 1000", 480, 250);
		font.getData().setScale(0.25f);
		font.draw(batch, "________________", 480, 215);
		font.getData().setScale(0.3f);
		font.draw(batch, "SPACE to play again", 480, 170);
		if(totalScore > 950) {
			font.getData().setScale(0.05f);
			font.draw(batch, "v", 1000, 10);
		}
		
		
	}

	@Override
	public void delete() {
		background.dispose();
		
	}

}
