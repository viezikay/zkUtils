package com.arrowgames.parents;

import com.arrowgames.effects.impls.ScreenTransition;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public abstract class ArrowGame implements ApplicationListener {
	
	public static final String tag = ArrowGame.class.getSimpleName();
	
	ArrowScreen curr, next;
	ScreenTransition transition;
	float delta;
	
	public void setScreen(ArrowScreen screen) {
		setScreen(screen, null);
	}
	
	public void setScreen(ArrowScreen screen, ScreenTransition transition) {
		next = screen;
		next.show();
		// Make sure current transition has done or null
		if (this.transition == null) {
			if (transition != null) {
				transition.ini(curr, screen, 1);
				this.transition = transition;
			}
		}
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void render() {
		
		delta = Gdx.graphics.getDeltaTime();
		
		if (next == null) {
			if (curr != null) 
				curr.render(delta);
		}
		else {
			if (transition != null) {
				transition.render(delta);
				if (transition.isCompleted()) {
					swapScreen();
					transition.dispose();
					transition = null;
				}
			}
			else swapScreen();
		}
	}

	@Override
	public void resize(int width, int height) {
		if (curr != null) curr.resize(width, height);
	}

	@Override
	public void pause() {
		if (curr != null) curr.pause();
	}

	@Override
	public void resume() {
		if (curr != null) curr.resume();
	}

	@Override
	public void dispose() {
		if (curr != null) curr.dispose();
	}
	
	private void swapScreen() {
		curr = next;
		next = null;
		Gdx.input.setInputProcessor(curr.getInput());
	}
}
