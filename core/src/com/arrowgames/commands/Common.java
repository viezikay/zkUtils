package com.arrowgames.commands;

import com.arrowgames.objects.GameObject;

public class Common {

	public static void moveBy(float dx, float dy, GameObject applyTo) {
		applyTo.getPosition().x += dx;
		applyTo.getPosition().y += dy;
	}
	
	public static void moveTo(float x, float y, GameObject applyTo) {
		applyTo.getPosition().set(x, y);
	}
	
	public static void move(float vx, float vy, GameObject applyTo) {
		applyTo.getVelocity().set(vx, vy);
	}
}
