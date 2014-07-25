package com.arrowgames.effects.impls;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ScreenTransition {
	public ScreenTransition ini(Screen from, Screen to, float in);
	public void render(SpriteBatch batch, float deltaTime);
	public boolean isCompleted();
	
	public enum TransitionType {
		FaceIn
	}
}
