package com.arrowgames.parents;

import com.arrowgames.effects.impls.ScreenTransition;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ArrowGame extends Game {
	
	public static final String tag = ArrowGame.class.getSimpleName();
	
	private SpriteBatch batch;
	ScreenTransition transition;
	float delta;
	
	@Override
	public void setScreen(Screen screen) {
		setScreen(screen, null);
	}
	
	public void setScreen(Screen screen, ScreenTransition transition) {
		super.setScreen(screen);
		this.transition = transition;
		if (batch == null) batch = new SpriteBatch();
	}
	
	@Override
	public void render() {
		
		delta = Gdx.graphics.getDeltaTime();
		
		if (transition != null) {
			transition.render(batch, delta);
			if (transition.isCompleted())
				transition = null;
		}
		else 
			super.render();
			
	}
}
