package com.arrowgames.core;

import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.LibGdxDrawer;
import com.brashmonkey.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Player;

public class SpriterComponent extends GraphicsComponent {
	
	public static final LibGdxLoader loader = new LibGdxLoader();
	public static final LibGdxDrawer drawer = new LibGdxDrawer(loader, null, null);
	
	public Player player;
	
	public SpriterComponent(Data data, String entityName) {
		loader.setData(data);
		player = new Player(data.getEntity(entityName));
	}
	
	@Override
	public void reset() {
		
	}

	@Override
	public void draw() {
		batch.begin();
		player.setPosition(actor.getX(), actor.getY());
		player.update();
		drawer.setBatch(batch);
		drawer.draw(player);
		batch.end();
	}
}
