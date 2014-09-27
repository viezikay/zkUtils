package com.arrowgames.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.LibGdxDrawer;
import com.brashmonkey.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;

public class SpriterComponent extends GraphicsComponent {
	
	public static LibGdxLoader loader;
	public static LibGdxDrawer drawer;
	
	public String path;
	public SCMLReader reader;
	public Data data;
	public Player player;
	
	public SpriterComponent(String path) {
		this.path = path;
		
		FileHandle scmlHandle = Gdx.files.internal(path);
		reader = new SCMLReader(scmlHandle.read());
		data = reader.getData();
		
		loader = new LibGdxLoader(data);
		loader.load(scmlHandle.file());
		
		drawer = new LibGdxDrawer(loader, batch, renderer);
		
		player = new Player(data.getEntity(0));
	}
	@Override
	public void reset() {
		
	}

	@Override
	public void draw() {
		batch.begin();
		player.update();
		drawer.draw(player);
		batch.end();
	}
}
