package com.arrowgames.effects;

import com.arrowgames.effects.impls.Effect;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public abstract class TrailEffect implements Effect {
	
	
	public TrailEffect(Builder builder) {
		
	}

	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		
	}

	public static class Builder {
		int segments;
		Texture texture;
		Color c;
		float t, d, h;
		
		public Builder() {
			texture = null;
			segments = 20;
			t = .05f;
			d = .05f;
		}
		
		public Builder setSegments(int count) {
			segments = count;
			return this;
		}
		
		public Builder setFadeTime(float in) {
			d = in;
			return this;
		}
		
		public Builder setUpdateTime(float delta) {
			t = delta;
			return this;
		}
		
		public Builder setSize(float size) {
			h = size;
			return this;
		}
		
		public Builder setColor(Color color) {
			c = color;
			return this;
		}
		
		public Builder setSegmentTexture(Texture texture) {
			this.texture = texture;
			return this;
		}
		
		public void build() {
			
		}
	}
}
