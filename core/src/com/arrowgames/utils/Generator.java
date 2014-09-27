package com.arrowgames.utils;

public abstract class Generator {

	protected float T, t;
	
	public void update(float deltaTime) {
		t -= deltaTime;
		if (t <= 0) {
			t = T;
			gen();
		}
	}
	
	public void setTurn(float turn) {
		T = turn;
	}
	
	public abstract void gen();
}
