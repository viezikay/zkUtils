package com.arrowgames.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public abstract class ArrowObject extends Actor {

	public static final String tag = ArrowObject.class.getSimpleName();
	protected Array<GraphicsComponent> graphics = new Array<GraphicsComponent>();
	
	@Override
	public void act(float delta) {
		super.act(delta);
		update(delta);
	}
	
	public abstract void update(float deltaTime);
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		for (GraphicsComponent component : graphics) {
			component.setBatch(batch);
			component.draw();
		}
	}
	
	public void addComponent(GraphicsComponent component) {
		component.setActor(this);
		graphics.add(component);
	}

	public void setBatch(SpriteBatch batch) {
		for (GraphicsComponent comp : graphics) 
			comp.setBatch(batch);
	}
}
