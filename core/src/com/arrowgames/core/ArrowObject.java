package com.arrowgames.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public abstract class ArrowObject extends Actor {

	protected Array<GraphicsComponent> graphics;
	
	@Override
	public void act(float delta) {
		super.act(delta);
		update(delta);
	}
	
	public abstract void update(float deltaTime);
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (GraphicsComponent component : graphics) 
			component.draw();
	}
	
	public void addComponent(GraphicsComponent component) {
		graphics.add(component);
	}
}
