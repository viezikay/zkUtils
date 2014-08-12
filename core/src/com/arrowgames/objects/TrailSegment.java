package com.arrowgames.objects;

import com.arrowgames.pools.Pools;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class TrailSegment {
	
	public static final String tag = TrailSegment.class.getSimpleName();

	Texture texture;
	float fadeTime, alpha, duration;
	float vertices[] = new float[20];
	
	public TrailSegment() {
		fadeTime = .03f;
		alpha = 1;
		duration = .03f;
	}
	
	public void update(float deltaTime) {
		duration = Math.max(0, duration - deltaTime);
		if (duration > 0) return;
		alpha = Math.max(0, alpha - deltaTime/fadeTime);
		vertices[Batch.C1] = Color.toFloatBits(1, 1, 1, alpha);
		vertices[Batch.C2] = Color.toFloatBits(1, 1, 1, alpha);
		vertices[Batch.C3] = Color.toFloatBits(1, 1, 1, alpha);
		vertices[Batch.C4] = Color.toFloatBits(1, 1, 1, alpha);
	}
	
	public void render(Batch batch) {
		batch.begin();
		batch.draw(texture, vertices, 0, 20);
		batch.end();
	}

	public void setVertexLocation(Vertex vertex, Vector2 location) {
		vertices[vertex.ordinal()*5] = location.x;
		vertices[vertex.ordinal()*5 + 1] = location.y;
		vertices[vertex.ordinal()*5 + 2] = Color.toFloatBits(255, 255, 255, 255);
		
		switch (vertex) {
		case first:
			vertices[3] = 0;
			vertices[4] = 1;
			break;
		case second:
			vertices[8] = 0;
			vertices[9] = 0;
			break;
		case third:
			vertices[13] = 1;
			vertices[14] = 0;
			break;
		case fourth:
			vertices[18] = 1;
			vertices[19] = 1;
			break;
		default:
			break;
		}
	}
	
	public Vector2 getVertexLocation(Vertex vertex) {
		Vector2 v = new Vector2();
		v.x = vertices[vertex.ordinal()*5];
		v.y = vertices[vertex.ordinal()*5+1];
		return v;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setFadeTime(float in) {
		fadeTime = in;
	}
	
	public enum Vertex {
		first,
		second,
		third,
		fourth
	}
}
