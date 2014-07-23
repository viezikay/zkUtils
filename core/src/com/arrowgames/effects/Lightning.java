package com.arrowgames.effects;

import com.arrowgames.pools.Pools;
import com.arrowgames.pools.Pools.VectorPool;
import com.arrowgames.utils.MathExtends;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Lightning implements Effect, Disposable {

	public static final String TAG = Lightning.class.getName();
	public static final VectorPool VECTOR_POOL = Pools.instance.vector;

	Array<ChainLightning> chains = new Array<Lightning.ChainLightning>();
	
	float stateTime, duration, fadeTime;
	Color color;
	
	boolean active;

	public Lightning() {
	}

	public void ini(Builder builder) {
		
		color = builder.color;
		duration = builder.duration;
		fadeTime = builder.fadeTime;
		stateTime = 0;
		active = true;

		for (int i = 0; i < builder.size; i++) {
			
			chains.add(new ChainLightning());
			chains.get(i).ini(builder);			
		}
	}

	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		
		stateTime += deltaTime;
		
		if (stateTime > duration) {
			color.a -= deltaTime*fadeTime;
			color.a = Math.max(color.a, 0);
		}

		for (ChainLightning chain : chains) {
			chain.render(batch, deltaTime);
		}
		
		if (color.a == 0 && active) dispose();
	}

	public static class Builder {

		Color color;
		Vector2 from;
		Vector2 to;
		int turn, size;
		float s, d, a, h, duration;
		float ampMax, ampMin, fadeTime;
		TextureRegion texture;

		public Builder(Vector2 from, Vector2 to) {

			this.from = from;
			this.to = to;
			s = to.dst(from);
			a = (float) Math.atan2(to.y - from.y, to.x - from.x);
			h = 2;
			ampMax = 1;
			ampMax = 4;
			size = 3;
			duration = 1;
			fadeTime = 10;
			color = Color.WHITE;
			turn = MathUtils.random(160, 200);
		}

		public Builder setTurn(int turn) {
			this.turn = turn;
			return this;
		}
		
		public Builder setBundleSize(int size) {
			this.size = size;
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
			h = size;
			return this;
		}
		
		public Builder setDuration(float in) {
			duration = in;
			return this;
		}
		
		public Builder setFadeTime(float in) {
			fadeTime = 1/in;
			return this;
		}

		public Builder setColor(float r, float g, float b, float a) {
			color.set(r, g, b, a);
			return this;
		}
		
		public Builder setTextureRegion(TextureRegion sample) {
			texture = sample;
			return this;
		}

		public Lightning build() {

			Lightning lightning = Pools.instance.lightning.obtain();
			lightning.ini(this);
			return lightning;
		}
	}

	public class ChainLightning {
		
		Color color;
		Vector2 f, t, from, centripetal;
		Array<Vector2> chain = new Array<Vector2>();
		float w, h, s, a, turn, ampMax, ampMin;

		TextureRegion sample;

		public void ini(Builder builder) {

			if (chain.size > 0)
				dispose();
			
			this.color = builder.color;
			
			h = builder.h;
			s = builder.s; 
			turn = builder.turn;
			a = builder.a;
			ampMax = builder.ampMax;
			ampMin = builder.ampMin;
			from = VECTOR_POOL.obtainVector2(builder.from);
			
			genLightning();
			transform();
			
			sample = builder.texture;
			f = VECTOR_POOL.obtainVector2();
			t = VECTOR_POOL.obtainVector2();
		}

		public void render(SpriteBatch batch, float deltaTime) {

			batch.setColor(color);
			batch.begin();
			for (int i = 0; i < chain.size-1; i++) {

				f.set(chain.get(i));
				t.set(chain.get(i+1));

				w = f.dst(t);

				batch.draw(sample, f.x, f.y, 0, h/2, 
						w, h, 1, 1, t.sub(f).angle());
			}
			batch.end();
			batch.setColor(1, 1, 1, 1);
		}

		private void genLightning() {

			float d = 0,  m = 0;
			int i;

			while (d < s) {

				i = chain.size;

				float n = MathUtils.random(s/turn * 0.4f, s/turn * 2f);
				d = i == 0 ? 0 : d + n;
				d = Math.min(d, s);
				float x = d >= s*.95f ? s : d;
				
				float y = 0;
				float sig = MathUtils.randomBoolean() ? 1 : -1;
				float amplitude = MathUtils.random(ampMin, ampMax);
				
				if (d >= s*.8f) {
					
					if (centripetal == null) {
						centripetal = VECTOR_POOL.obtainVector2(s, 0);
						centripetal.sub(chain.get(i-1));
					}
					
					if (d < s*.95f) {
						m += n;
						y = MathExtends.findY(centripetal, m);
						y += sig*amplitude-centripetal.y;
					} else 
						y = 0;
				}
				else {
					y = i == 0 ? 0 : chain.get(i-1).y + sig*amplitude;
				}

				chain.add(VECTOR_POOL.obtainVector2(x, y));
			}
			
			VECTOR_POOL.free(centripetal);
			centripetal = null;
		}

		private void transform() {

			Vector2 temp = VECTOR_POOL.obtainVector2();
			
			float sin = MathUtils.sin(a);
			float cos = MathUtils.cos(a);
				
			for (Vector2 sector : chain) {

				temp.set(sector);
				sector.x = cos*temp.x - sin*temp.y;
				sector.y = cos*temp.y + sin*temp.x;

				sector.add(from);
			}
			
			VECTOR_POOL.free(temp);
		}
		
		public void dispose() {
			
			for (Vector2 v : chain)
				VECTOR_POOL.free(v);
			chain.clear();
			VECTOR_POOL.free(from);
			VECTOR_POOL.free(t);
			VECTOR_POOL.free(f);
			f = null;
			t = null;
			w = h = 0;
			Gdx.app.log(TAG, "Wasted object: "
					+ VECTOR_POOL.getWastedObject());			
		}
	}

	@Override
	public void dispose() {
		
		active = false;
		duration = stateTime = 0;
		
		for (ChainLightning chain : chains) {
			chain.dispose();
		}
	}
	
	public boolean isActive() {
		return active;
	}
}
