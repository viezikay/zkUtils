package com.arrowgames;

import com.arrowgames.effects.Lightning;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyGame extends Game {
	
	OrthographicCamera camera;
	SpriteBatch batch;
	InputHandler handler;

	Lightning l1;
	
	Vector2 from;
	Vector2 to;
	float w, h;

	@Override
	public void create() {

		handler = new InputHandler();
		Gdx.input.setInputProcessor(handler);
		
		w = Gdx.graphics.getWidth(); h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		camera.position.set(w/2, h/2, 0);
		camera.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		from = new Vector2(w/2, h/1.5f);
		to = new Vector2(w/2, 0);
	}
	
	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		if (l1 != null) l1.render(batch, deltaTime);
	}
	
	public class InputHandler extends InputAdapter {
		
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			
			if (l1 != null && l1.isActive()) 
				return false;
			
			to.set(x, h-y);
			
			l1 = new Lightning.Builder(from, to)
				.setTurn(80)
				.setBundleSize(3)
				.setAmplitude(2f, 4f)
				.setSize(3)
				.setDuration(.2f)
				.setFadeTime(.15f)
				.setColor(1, 1, 1, 1)
				.setTextureRegion(ZkUtils.instance.lightningSample)
				.build();
			
			return false;
		}
		
		@Override
		public boolean touchUp(int x, int y, int pointer, int button) {
			
			return false;
		}
	}
}
