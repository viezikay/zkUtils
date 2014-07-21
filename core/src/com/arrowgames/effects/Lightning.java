package com.arrowgames.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Lightning implements Effect {

	Color color;
	Vector2 f, t;
	float w, h;
	Array<Vector2> chain;
	
	TextureRegion sample;
	Builder builder;
	
	public Lightning() {
	}
	
	public void ini(Builder builder) {
		
		this.builder = builder;
		this.color = builder.color;
		this.chain = new Array<Vector2>();
		
		genLightning();
		transform();
		
		sample = new TextureRegion(new Texture("sample.png"));
		f = new Vector2();
		t = new Vector2();
	}

	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		
		batch.setColor(color);
		batch.begin();
		for (int i = 0; i < chain.size-2; i++) {
			
			f.set(chain.get(i));
			t.set(chain.get(i+1));
			
			w = f.dst(t);
			h = builder.h;
			
			batch.draw(sample, f.x, f.y, 0, h/2, w, h, 1, 1, t.sub(f).angle());
		}
		batch.end();
		batch.setColor(1, 1, 1, 1);
	}
	
	private void genLightning() {

		float d = builder.d, s = builder.s, turn = builder.turn;
		int i;
		
		while (d < s) {
			
			i = chain.size;
			
			d += MathUtils.random(s/turn * 0.8f, s/turn * 1.2f);
			d = Math.min(d, s);
			
			float x = d;
			
			float sig = MathUtils.randomBoolean() ? 1 : -1;
			float amplitude = MathUtils.random(builder.ampMin, builder.ampMax);
			float y = i == 0 ? 0 : chain.get(i-1).y + sig*amplitude;
			
			chain.add(new Vector2(x, y));
		}
	}
	
	private void transform() {
		
		Vector2 temp = new Vector2();
		
		for (Vector2 sector : chain) {
			
			temp.set(sector);
			
			float sin = MathUtils.sin(builder.a/MathUtils.radiansToDegrees);
			float cos = MathUtils.cos(builder.a/MathUtils.radiansToDegrees);
			
			sector.x = sin*temp.y + cos*temp.x;
			sector.y = cos*temp.y - sin*temp.x;
			
			sector.add(builder.from);
		}
	}
	
	public static class Builder {
		
		Color color = Color.WHITE;
		Vector2 from;
		Vector2 to;
		int turn;
		float s, d, a, h;
		float ampMax, ampMin;
		
		public Builder(Vector2 from, Vector2 to) {
			
			this.from = from;
			this.to = to;
			Vector2 temp = new Vector2(to);
			s = temp.dst(from);
			a = to.sub(from).angle();
			h = .05f;
			
			turn = MathUtils.random(160, 200);
		}
		
		public Builder setTurn(int turn) {
			this.turn = turn;
			return this;
		}
		
		public Builder setAmplitude(float from, float to) {
			ampMin = from;
			ampMax = to;
			return this;
		}
		
		public Builder setColor(Color color) {
			this.color = color;
			return this;
		}
		
		public Builder setSize(float size) {
			size = h;
			return this;
		}
		
		public Builder setColor(float r, float g, float b, float a) {
			color.set(r, g, b, a);
			return this;
		}
		
		public Lightning build() {
			
			Lightning lightning = new Lightning();
			lightning.ini(this);
			return lightning;
		}
	}
}
