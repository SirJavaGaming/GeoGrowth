package de.sirjavagaming.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiMain extends Gui {
	
	private int selected = 1;
	
	private Texture background;
	
	private Sound beep;
	
	@Override
	public void create() {
		background = new Texture(Gdx.files.internal("dead.png"));
		
		beep = Gdx.audio.newSound(Gdx.files.internal("beep_low.ogg"));
		
	}
	
	private boolean b;
	private boolean b2 = false;
	
	private long startTime;
	
	@Override
	public void render(SpriteBatch batch) {
		BitmapFont font = Game.getGame().getFont();
		if(!b2) {
			renderTitle(batch, font);
			font.getData().setScale(0.4f);
			font.setColor(0.8f, 1, 1f, 1);
			font.draw(batch, "Select difficulty:", 480, 470);
			font.getData().setScale(0.32f);
			font.draw(batch, "EASY    MEDIUM      HARD", 480, 380);
			font.setColor(0.8f, 1, 1f, 1);
			if(selected == 0) {
				font.setColor(1,0,0,1);
			}
			font.draw(batch, "___", 478, 370);
			font.setColor(0.8f, 1, 1f, 1);
			if(selected == 1) {
				font.setColor(1,0,0,1);
			}
			font.draw(batch, "____", 580, 370);
			font.setColor(0.8f, 1, 1f, 1);
			if(selected == 2) {
				font.setColor(1,0,0,1);
			}
			font.draw(batch, "___", 722, 370);

			font.setColor(0.8f, 1, 1f, 1);
			font.getData().setScale(0.25f);
			font.draw(batch, "Press ENTER to start", 510, 280);
	
			boolean b1 = false;
			if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !b) {
				selected--;
				if(selected == -1) selected = 2;
				b1 = true;
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !b) {
				selected++;
				if(selected == 3) selected = 0;
				b1 = true;
			}
			if(!b1) {
				b = false;
			} else {
				b = true;
			}
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
				Difficulty d = Difficulty.MEDIUM;
				switch (selected) {
				case 0:
					d = Difficulty.EASY;
					break;
				case 1:
					d = Difficulty.MEDIUM;
					break;
				case 2:
					d = Difficulty.HARD;
				default:
					break;
				}
				Game.getGame().setDifficulty(d);
				startTime = System.currentTimeMillis();
				b2 = true;
			}
		} else {
			int i = (int) (3 - ((System.currentTimeMillis() - startTime) / 1000));
			if(i < 0) {
				Game.getGame().setUpNewGame();
			} else {
				font.setColor(0.8f, 1, 1f, 1);
				font.getData().setScale(1);
				if(i < lastBeep) {
					beep.play();
					lastBeep = i;
				}
				if(i != 0) {
					if(i == 1) {
						font.draw(batch, i+"", 615, 500);
					} else {
						font.draw(batch, i+"", 600, 500);
					}
				} else {
					font.draw(batch, "GO", 580, 500);
				}
			}
		}
		
	}
	
	private int lastBeep = 4;

	private void renderTitle(SpriteBatch batch, BitmapFont font) {
		font.getData().setScale(0.72f);
		font.setColor(0.8f, 0.8f, 0.9f, 1f);
		int coff = Math.abs((int) ((System.currentTimeMillis() / 500)) % 6);
		float woff = -20;
		font.setColor(getColor((coff + 0) % 6));
		woff += font.draw(batch, "G", 485 + woff, 600).width;
		font.setColor(getColor((coff + 1) % 6));
		woff += font.draw(batch, "e", 485 + woff, 600).width;
		font.setColor(getColor((coff + 2) % 6));
		woff += font.draw(batch, "o", 485 + woff, 600).width;
		font.setColor(getColor((coff + 3) % 6));
		woff += font.draw(batch, "G", 485 + woff, 600).width;
		font.setColor(getColor((coff + 4) % 6));
		woff += font.draw(batch, "r", 485 + woff, 600).width;
		font.setColor(getColor((coff + 5) % 6));
		woff += font.draw(batch, "o", 485 + woff, 600).width;
		font.setColor(getColor((coff + 6) % 6));
		woff += font.draw(batch, "w", 485 + woff, 600).width;
		font.setColor(getColor((coff + 7) % 6));
		woff += font.draw(batch, "t", 485 + woff, 600).width;
		font.setColor(getColor((coff + 8) % 6));
		woff += font.draw(batch, "h", 485 + woff, 600).width;
		font.setColor(1, 1, 1, 1);
	}
	
	private Color getColor(int i) {
		switch (i) {
		case 0:
			return new Color(1, 0, 0, 1);
		case 1:
			return new Color(1, 0, 1, 1);
		case 2:
			return new Color(0, 0, 1, 1);
		case 3:
			return new Color(0, 1, 1, 1);
		case 4:
			return new Color(0, 1, 0, 1);
		case 5:
			return new Color(1, 1, 0, 1);
		default:
			return new Color(1, 1, 1, 1);
		}
	}

	@Override
	public void delete() {
		background.dispose();
		beep.dispose();
	}

}
