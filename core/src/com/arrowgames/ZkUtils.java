package com.arrowgames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ZkUtils {
	
	public static final ZkUtils instance = new ZkUtils();

	private boolean init = false;
	
	public TextureRegion lightningSample;
	public TextureRegion lightningDot;
	
	private ZkUtils() {
		init();
	}
	
	public final boolean init() {
		if (!init) {
			init = true;
			lightningSample = new TextureRegion(new Texture("sample.png"));
			lightningDot = new TextureRegion(new Texture("dot.png"));
		}
		return isInit();
	}
	
	public final boolean isInit() {
		return init;
	}
}
