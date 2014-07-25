package com.arrowgames.effects;

import com.arrowgames.effects.impls.ScreenTransition;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class ScreenFaceIn implements ScreenTransition {
	
	public static final String tag = ScreenFaceIn.class.getSimpleName();
	
	public static final ScreenFaceIn instance = new ScreenFaceIn();
	
	FrameBuffer currFBO;
	FrameBuffer nextFBO;
	
	float d, t, a;
	Screen current;
	Screen next;
	
	int w, h;
	
	public ScreenFaceIn ini(Screen from, Screen to, float in) {
		current = from;
		next = to;
		d = in;
		a = 0;
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		currFBO = new FrameBuffer(Format.RGBA8888, w, h, false);
		nextFBO = new FrameBuffer(Format.RGBA8888, w, h, false);
		
		current.resize(w, h);
		next.resize(w, h);
		
		return this;
	}
	
	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		t = Math.min(t + deltaTime, d);
		currFBO.begin();		
		current.render(deltaTime);
		currFBO.end();
		
		nextFBO.begin();
		next.render(deltaTime);
		nextFBO.end();
		
		a = Math.min(1, a+deltaTime*2);
		
		batch.begin();
		batch.setColor(1, 1, 1, 1);
		batch.draw(currFBO.getColorBufferTexture(), 0, 0);
		batch.setColor(1, 1, 1, a);
		batch.draw(nextFBO.getColorBufferTexture(), 0, 0);
		batch.end();
	}

	@Override
	public boolean isCompleted() {
		return t==d;
	}
}