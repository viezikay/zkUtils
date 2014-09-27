package com.arrowgames.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GraphicsComponent extends Component {

	protected SpriteBatch batch;
	protected ShapeRenderer renderer;
	
	public abstract void draw();
	
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public void setShapeRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return renderer;
	}
}
