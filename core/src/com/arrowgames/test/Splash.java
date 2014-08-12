package com.arrowgames.test;

import static com.badlogic.gdx.Gdx.*;

import com.arrowgames.parents.ArrowGame;
import com.arrowgames.parents.ArrowScreen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Splash extends ArrowScreen {
	
	public static final String tag = ArrowScreen.class.getSimpleName();
	
	ShaderProgram shaderProgram;
	SpriteBatch batch;
	Texture texture, background;
	float t, vertices[];

	public Splash(ArrowGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		gl.glClearColor(.4f, .4f, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		t = Math.min(1, t+delta);
		
		batch.begin();
		batch.draw(background, 0, 0);
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
		texture = new Texture("badlogic.jpg");
		background = new Texture("hd-1920-1080.png");
		ShaderProgram.pedantic = false;
		shaderProgram = new ShaderProgram(
				files.internal("Shaders/diffuse_lighting.vp"), 
				files.internal("Shaders/diffuse_lighting.fp"));
		app.log(tag, shaderProgram.getLog());
		batch.setShader(shaderProgram);
		
		int idx = 0;
		
		float x = (graphics.getWidth()-texture.getWidth())/2;
		float y = (graphics.getHeight()-texture.getHeight())/2;
		float fx2 = x + texture.getWidth();
		float fy2 = y + texture.getHeight();
		float color = Color.toFloatBits(255, 255, 255, 255);
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
