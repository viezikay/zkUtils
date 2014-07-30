package com.arrowgames.test;

import com.arrowgames.effects.ScreenFaceIn;
import com.arrowgames.parents.ArrowGame;
import com.arrowgames.parents.ArrowScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash extends ArrowScreen {
	
	public static final String tag = ArrowScreen.class.getSimpleName();
	
	SpriteBatch batch;
	Texture texture;
	float t;

	public Splash(ArrowGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		t = Math.min(1, t+delta);

		float w = (Gdx.graphics.getWidth()-texture.getWidth())/2;
		float h = (Gdx.graphics.getHeight()-texture.getHeight())/2;
		
		batch.begin();
		batch.draw(texture, w, h);
		batch.end();
		
		if (t == 1) {
			game.setScreen(new Game(game), ScreenFaceIn.instance);
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		texture = new Texture("badlogic.jpg");
	}

	@Override
	public void hide() {
		texture.dispose();
		texture = null;
		batch.dispose();
		batch = null;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		if (batch != null) batch.dispose();
	}

	@Override
	public InputProcessor getInput() {
		return null;
	}
}
