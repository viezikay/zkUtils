package com.arrowgames.utils;

import com.badlogic.gdx.math.Vector2;

public class MathExtends {

	/**
	 * Find value of X on Vector 
	 * @param on
	 * @param y
	 * @return
	 */
	public static float findX(Vector2 on, float y) {
		return findX(0, 0, on.x, on.y, y);
	}
	
	public static float findX(Vector2 v1, Vector2 v2, float y) {
		return findX(v1.x, v1.y, v2.x, v2.y, y);
	}
	
	public static float findX(float x0, float y0, float x1, float y1, float y) {
		return (y-y0)*(x1-x0)/(y1-y0) + x0;
	}
	
	public static float findY(Vector2 on, float x) {
		return findY(0, 0, on.x, on.y, x);
	}
	
	public static float findY(Vector2 v1, Vector2 v2, float x) {
		return findY(v1.x, v1.y, v2.x, v2.y, x);
	}
	
	public static float findY(float x0, float y0, float x1, float y1, float x) {
		return (x-x0)*(y1-y0)/(x1-x0) + y0;
	}
}
