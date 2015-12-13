package de.sirjavagaming.ld34;

public enum Difficulty {
	
	EASY(16),
	MEDIUM(11),
	HARD(7);
	
	private int i;
	Difficulty(int i) {
		this.i = i;
	}
	
	public int getI() {
		return i;
	}

}
