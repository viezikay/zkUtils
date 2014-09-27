package com.arrowgames.core;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Component implements Poolable {

	protected ArrowObject actor;
	protected Pool<Component> pool;
	
	public void setActor(ArrowObject actor) {
		this.actor = actor;
		if (actor == null) 
			if (pool != null) {
				pool.free(this);
				pool = null;
			}
	}
	
	public void setPool(Pool<Component> pool) {
		this.pool = pool;
	}
	
	public ArrowObject getActor() {
		return actor;
	}
	
	public Pool<Component> getPool() {
		return pool;
	}
}
