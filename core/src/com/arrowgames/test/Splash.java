package com.arrowgames.test;

import com.arrowgames.effects.ScreenFaceIn;
import com.arrowgames.parents.ArrowGame;
import com.arrowgames.parents.ArrowScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash extends ArrowScreen {
	
	public static final String tag = ArrowScreen.class.getSimpleName();
	
	SpriteBatch batch;
	Texture texture;
	float t, vertices[];

	public Splash(ArrowGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.4f, .4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		t = Math.min(1, t+delta);

		batch.begin();
		batch.draw(texture, vertices, 0, 20);
		batch.end();
		
//		if (t == 1) {
//			game.setScreen(new Game(game), ScreenFaceIn.instance);
//		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 15, 10);
		texture = new Texture("sample.png");
		
		float color = Color.toFloatBits(255, 255, 255, 255);
		int idx = 0;
		
		float x = 0;
		float y = 0;
		float fx2 = 5;
		float fy2 = 5;
		float u = 0, v = 1, u2 = 1, v2 = 0;
		
		vertices = new float[20];
		
		vertices[idx++] = x;
		vertices[idx++] = y;
		vertices[idx++] = color;
		vertices[idx++] = u;
		vertices[idx++] = v;

		vertices[idx++] = x;
		vertices[idx++] = fy2;
		vertices[idx++] = color;
		vertices[idx++] = u;
		vertices[idx++] = v2;

		vertices[idx++] = fx2;
		vertices[idx++] = fy2;
		vertices[idx++] = color;
		vertices[idx++] = u2;
		vertices[idx++] = v2;

		vertices[idx++] = fx2;
		vertices[idx++] = y;
		vertices[idx++] = color;
		vertices[idx++] = u2;
		vertices[idx++] = v;
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
