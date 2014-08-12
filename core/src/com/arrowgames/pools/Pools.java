package com.arrowgames.pools;

import com.arrowgames.effects.Lightning;
import com.arrowgames.objects.TrailSegment;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Pools {
	
	public static final String tag = Pools.class.getName();

	public static final Pools instance = new Pools();
	
	public VectorPool vector;
	public LightningPool lightning;
	public TrailSegmentPool trail;
	
	int vectorCounter;
	
	public Pools() {
		
		vector = new VectorPool();
		lightning = new LightningPool();
		trail = new TrailSegmentPool();
	}
	
	public class VectorPool {
		
		private Pool<Vector2> vector2;
		
		public VectorPool() {
			
			vector2 = new Pool<Vector2>() {
				@Override
				protected Vector2 newObject() {
					vectorCounter++;
					return new Vector2();
				}
			};
		}
		
		public Vector2 obtainVector2() {
			return vector2.obtain();
		}
		
		public Vector2 obtainVector2(Vector2 v) {
			return obtainVector2(v.x, v.y);
		}
		
		public Vector2 obtainVector2(float x, float y) {
			Vector2 v = obtainVector2();
			v.set(x, y);
			return v;
		}
		
		public void free(Vector2 object) {
			vector2.free(object);
		}
		
		public int getWastedObject() {
			return vectorCounter-vector2.getFree();
		}
		
		public String toString() {
			return "[Total created object: " + vectorCounter + 
					"][Total available object: " + vector2.getFree() + "]";
		}
	}
	
	public class LightningPool extends Pool<Lightning>{

		@Override
		protected Lightning newObject() {
			return new Lightning();
		}
	}
	
	public class TrailSegmentPool extends Pool<TrailSegment> {

		@Override
		protected TrailSegment newObject() {
			return new TrailSegment();
		}
	}
}
