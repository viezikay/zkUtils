package com.arrowgames.pools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Pools {

	public static final Pools instance = new Pools();
	
	public VectorPool vector;
	
	public Pools() {
		
		vector = new VectorPool();
	}
	
	public class VectorPool {
		
		private Pool<Vector2> vector2;
		
		public VectorPool() {
			
			vector2 = new Pool<Vector2>() {
				
				@Override
				protected Vector2 newObject() {
					return new Vector2();
				}
			};
		}
		
		public Vector2 obtainVector2() {
			return vector2.obtain();
		}
	}
}
