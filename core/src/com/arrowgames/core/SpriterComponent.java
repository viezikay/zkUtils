package com.arrowgames.core;

import com.badlogic.gdx.math.MathUtils;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.LibGdxDrawer;
import com.brashmonkey.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Player;

public class SpriterComponent extends GraphicsComponent {
	
	public static final String tag = "SpriterComponent";	
	public static final LibGdxDrawer drawer = new LibGdxDrawer();
	
	public LibGdxLoader loader;
	public Player player;
	
	public SpriterComponent(LibGdxLoader loader, String entityName) {
		
		this.loader = loader;
		Data data = loader.getData();
		player = new Player(data.getEntity(entityName));
		changeAnimation(MathUtils.random(6));
	}
	
	@Override
	public void reset() {
		
	}

	@Override
	public void draw() {
		batch.begin();
		player.update();
		player.setPosition(actor.getX(), actor.getY());
		drawer.setLoader(loader);
		drawer.setBatch(batch);
		drawer.draw(player);
		batch.end();
	}
	
	public void changeAnimation(int animationIndex) {
		player.setAnimation(animationIndex);
	}
}
