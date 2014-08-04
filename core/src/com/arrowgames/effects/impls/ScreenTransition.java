package com.arrowgames.effects.impls;

import com.badlogic.gdx.Screen;

public interface ScreenTransition {
	
	public ScreenTransition ini(Screen from, Screen to, float in);
	public void render(float deltaTime);
	public boolean isCompleted();
	public void dispose();
	
	public enum TransitionType {
		FaceIn
	}
}
