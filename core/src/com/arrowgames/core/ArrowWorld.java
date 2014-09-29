package com.arrowgames.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ArrowWorld {
	
	private final Array<Actor> objects = new Array<Actor>();
	public float t;
	public Batch batch;
	
	public ArrowWorld(Builder builder) {
		batch = builder.batch;
	}
	
	public void update(float deltaTime) {
		
		if (batch != null) render();
	}
	
	private void render() {
		
	}
	
	public void addActor(Actor actor) {
		objects.add(actor);
	}
	
	public static class Builder {
		public Batch batch;
		
		public Builder setBatch(Batch batch) {
			this.batch = batch;
			return this;
		}
		
		public ArrowWorld build() {
			return new ArrowWorld(this);
		}
	}
}
