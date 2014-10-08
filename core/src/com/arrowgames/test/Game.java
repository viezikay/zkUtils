package com.arrowgames.test;

import com.arrowgames.parents.ArrowGame;
import com.arrowgames.parents.ArrowScreen;
import com.arrowgames.test.objects.Imp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Game extends ArrowScreen {
	
	public static final String tag = Game.class.getSimpleName();
	
	SpriteBatch batch;
	Texture texture;
	
	Array<Imp> imps = new Array<Imp>();

	public Game(ArrowGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(.4f, .4f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for (Imp imp : imps) {
			imp.update(delta);
			imp.draw(batch, 1);
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		texture = new Texture("dot.png"	);
		
		for (int i = 0; i < 10; i++) {
			Imp imp = new Imp();
			imp.setPosition(MathUtils.random(-200, 1080), MathUtils.random(200, 900));
			imps.add(imp);
		}
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
