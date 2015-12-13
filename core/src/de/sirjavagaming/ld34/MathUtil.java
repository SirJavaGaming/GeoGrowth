package de.sirjavagaming.ld34;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class MathUtil {
	static Random rand = new Random();
	
	public static final int GAME_LENGTH = 40000;
	
	public static float pixelsToScale(int meters) {
		float metersf = (float)meters;
		return metersf * 4f / (float)GAME_LENGTH + 1f;
	}
	
	public static Color generateRandomColor(boolean preventRed) {
//		float r = rand.nextFloat() / 2f + 0.4f;
//		float g = rand.nextFloat() / 2f + 0.4f;   //Looks quite ugly
//		float b = rand.nextFloat() / 2f + 0.4f;
		
		//HSV color generation, converting to RGB
		
		float h = (rand.nextFloat() + 0.618033988749895f) % 1;
		
		float v = 0.99f;
		float s = 0.99f;
		
		int h_i = (int)(h*6);
		float f = h*6 - h_i;
		float p = v * (1f - s);
		float q = v * (1 - f*s);
		float t = v * (1 - (1 - f) * s);
		float r = 0,g = 0,b = 0;
		
		switch (h_i) {
		case 0:
			r = v;
			g = t;
			b = p;
			break;
		case 1:
			r = q;
			g = v;
			b = p;
			break;
		case 2:
			r = p;
			g = v;
			b = t;
			break;
		case 3:
			r = p;
			g = q;
			b = v;
			break;
		case 4:
			r = t;
			g = p;
			b = v;
			break;
		case 5:
			r = v;
			g = p;
			b = q;
			break;
		}
		
		if(r > g && r > b && preventRed) {
			return generateRandomColor(true);
		} else {
			return new Color(r, g, b, 1);
		}
	}
	
	public static int calcTotalPoints() {
		int difficultyBonus = 0;
		
		int totalDistance = 500-((MathUtil.GAME_LENGTH - Game.getGame().getPixels()) * 500 / MathUtil.GAME_LENGTH );
		
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
		
		return (totalDistance * 600 / 500) + difficultyBonus + (Game.getGame().getCoins() * 10) + Game.getGame().getLives() * 3;
	}
	
}
