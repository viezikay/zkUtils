package com.arrowgames.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameObject {

	Color debugColor = Color.WHITE;

	protected boolean updatable;
	protected boolean renderable;
	protected boolean debugVisual;

	protected float minVx = Float.MIN_VALUE;
	protected float maxVx = Float.MAX_VALUE;

	protected float stateTime;

	public Vector2 position;
	public Vector2 velocity;
	protected Vector2 dimension;
	protected Vector2 origin;
	protected Vector2 scale;
	protected Vector2 corrector;
	protected float rotation;

	protected RenderInfo info;
	protected TextureRegion textureRegion;
	protected Animation animation;

	protected Body body;

	public GameObject() {

		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		scale = new Vector2(1, 1);
		velocity = new Vector2();
		corrector = new Vector2();

		info = new RenderInfo();
		
		ini();
		iniPhysics();
		postIni();
	}

	public abstract void ini();
	public abstract void iniPhysics();

	public void postIni() {

		if (textureRegion != null) {
			dimension.x = textureRegion.getRegionWidth();
			dimension.y = textureRegion.getRegionHeight();
		}
		origin.x = dimension.x / 2;
		origin.y = dimension.y / 2;
		
		scale.x = Math.signum(velocity.x);
	}

	public void objectUpdate(float deltaTime) {
		
		if (!updatable) return;

		stateTime +=deltaTime;
		update(deltaTime);
	}

	public abstract void update(float deltaTime);

	public void render(SpriteBatch batch) {

		if (!renderable) return;

		info.w = dimension.x + corrector.x;
		info.h = dimension.y + corrector.y;
		info.oX = info.w / 2;
		info.oY = info.h / 2;
		info.x = position.x - info.oX;
		info.y = position.y - info.oY;
		
		batch.draw(textureRegion, info.x, info.y, info.oX, info.oY,
				info.w, info.h, scale.x, scale.y, rotation);
	}
	
	public void renderBoundingBox(ShapeRenderer renderer) {
		
		if (!debugVisual) return;
		
		renderer.setColor(debugColor);
		renderer.begin(ShapeType.Line);
		renderer.rect(position.x, position.y, 
				dimension.x*scale.x, dimension.y*scale.y);
		renderer.end();
	}
	
	public void setAnimation(Animation animation) {
		
		this.animation = animation;
	}

	public void setActive(boolean active) {
		
		updatable = active;
		renderable = active;
	}
	
	public class RenderInfo {
		float x, y, w, h, oX, oY;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
}
