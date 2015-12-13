package de.sirjavagaming.ld34;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	
	private static Game game;
	
	private SpriteBatch batch;
	
	private OrthographicCamera cam;
	
	private Character character;
	
	private Texture background;
	
	private ObstacleManager obstacleManager;
	
	private GameState state;
	
	private int pixels;
	
	private int lives;
	
	private Gui gui;
	
	private Music music;
	
	private BitmapFont font;
	
	private int coins;
	
	private Sound coinPick;
	
	private Difficulty difficulty;
	
	
	@Override
	public void create () {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1280, 720);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
			
		background = new Texture(Gdx.files.internal("bg.png"));
		
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		
		game = this;
		
		state = GameState.MAIN;
		
		setGui(new GuiMain());
		
		music = Gdx.audio.newMusic(Gdx.files.local("music.ogg"));
		music.setVolume(0.4f);
		music.setLooping(true);
		music.play();
		
		coinPick = Gdx.audio.newSound(Gdx.files.internal("coin.ogg"));
		
		difficulty = Difficulty.EASY;
		Gdx.input.setCursorCatched(true);
	}
	
	long startTime;
	
	long start = System.currentTimeMillis();
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
			return;
		}
		
		batch.begin();
		
		switch (state) {
		case MAIN:
			batch.draw(background, 0, Math.round(0-pixels%720));
			batch.draw(background, 0, Math.round(0-pixels%720+720));
			gui.render(batch);
			break;
		case INGAME:
			pixels = (int) ((System.currentTimeMillis() - startTime) / 3);
			_render();
			break;
		case DEAD:
			_render();
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				state = GameState.MAIN;
				
				setGui(new GuiMain());
			}
			break;
		default:
			break;
		}
		
		if(gui != null)
			gui.render(batch);
		
		batch.end();
		
		try {
//			System.out.println(((1000 / (System.currentTimeMillis() - start))) + "fps");
			start = System.currentTimeMillis();
		} catch(ArithmeticException e) {
			System.err.println("Infinite FPS! Whoops");
		}
		
	}
	
	public void setUpNewGame() {
		state = GameState.INGAME;
		setGui(new GuiIngame());
		startTime = System.currentTimeMillis();
		lives = 3;
		coins = 0;
		obstacleManager = new ObstacleManager();
		obstacleManager.create();
		character = new Character();
		character.create();
	}
	
	private void _render() {		
		batch.draw(background, 0, Math.round(0-pixels%720));
		batch.draw(background, 0, Math.round(0-pixels%720+720));
		obstacleManager.updateAndRender(batch, pixels);
		character.updateAndRender(batch, pixels);
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public static final Game getGame() {
		return game;
	}
	
	public ObstacleManager getObstacleManager() {
		return obstacleManager;
	}
	
	public void setGameState(GameState state) {
		this.state = state;
	}
	
	public boolean isInGame() {
		return state == GameState.INGAME;
	}
	
	public boolean isDead() {
		return state == GameState.DEAD;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void hit() {
		lives--;
		if(lives == 0) {
			state = GameState.DEAD;
			setGui(new GuiOver());
		}
	}
	
	public void setGui(Gui gui) {
		if(this.gui != null)
			this.gui.delete();
		gui.create();
		this.gui = gui;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public int getPixels() {
		return pixels;
	}
	
	public void addCoin() {
		coinPick.play();
		coins++;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}
