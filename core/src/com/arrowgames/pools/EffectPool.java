package com.arrowgames.pools;

import com.arrowgames.effects.Lightning;
import com.badlogic.gdx.utils.Pool;

public class EffectPool {

	public static final EffectPool instance = new EffectPool();
	
	public class LightningEffectPool extends Pool<Lightning> {

		@Override
		protected Lightning newObject() {
			return null;
		}
	}
}
