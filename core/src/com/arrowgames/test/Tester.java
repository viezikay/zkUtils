package com.arrowgames.test;

import com.arrowgames.parents.ArrowGame;

public class Tester extends ArrowGame {
	
	@Override
	public void create() {
		setScreen(new Splash(this));
	}
}
