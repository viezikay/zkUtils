package com.arrowgames;

import com.arrowgames.effects.Lightning;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyGame extends Game {
	
	OrthographicCamera camera;
	SpriteBatch batch;

	Lightning lightning;

	@Override
	public void create() {

		camera = new OrthographicCamera(15, 10);
		camera.position.set(7.5f, 5, 0);
		camera.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		Vector2 from = new Vector2(0, 5);
		Vector2 to = new Vector2(10, 5);
		lightning = new Lightning.Builder(from, to)
			.setTurn(80)
			.setAmplitude(.05f, .1f)
			.setSize(0.1f)
			.setColor(Color.WHITE)
			.build();
	}
	
	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		lightning.render(batch, Gdx.graphics.getDeltaTime());
	}
}
