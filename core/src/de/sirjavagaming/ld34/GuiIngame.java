package de.sirjavagaming.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiIngame extends Gui {

	private Texture heart;
	private Texture heart_kaputt;
	
	@Override
	public void create() {
		heart = new Texture(Gdx.files.internal("heart.png"));
		heart_kaputt = new Texture(Gdx.files.internal("heart_kaputt.png"));
		
	}

	@Override
	public void render(SpriteBatch batch) {
		for(int i = 0; i < 3; i++) {
			batch.draw(Game.getGame().getLives()-1 < i ? heart_kaputt : heart, 30 + 65 * i, 720 - 80, 50, 50);
			
			//TODO: Textur Überarbeiten
			//TODO: Herz könnte blinken, wenn es verloren geht
		}
		Game.getGame().getFont().setColor(0.8f, 1, 1f, 1);
		int meters = ((MathUtil.GAME_LENGTH - Game.getGame().getPixels()) * 500 / MathUtil.GAME_LENGTH);
		if(meters < 0) meters = 0;
		Game.getGame().getFont().getData().setScale(0.3f);
		Game.getGame().getFont().draw(batch, meters + "m  left", 30, 50);
		batch.draw(ObstacleTextures.coin, 1175, 665, 45, 45);
		Game.getGame().getFont().draw(batch, Game.getGame().getCoins() + "", 1230, 700);
	}

	@Override
	public void delete() {
		heart.dispose();
		heart_kaputt.dispose();
	}

}
