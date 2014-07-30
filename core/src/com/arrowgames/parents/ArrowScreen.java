package com.arrowgames.parents;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class ArrowScreen implements Screen {

	protected ArrowGame game;
	
	public ArrowScreen(ArrowGame game) {
		this.game = game;
	}
	
	public abstract InputProcessor getInput();
}
