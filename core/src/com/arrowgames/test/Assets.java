package com.arrowgames.test;

import java.io.File;

import com.arrowgames.core.SpriterComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.LibGdxLoader;
import com.brashmonkey.spriter.SCMLReader;

public class Assets {
	
	public static final Assets instance = new Assets();
	
	public ObjectMap<String, LibGdxLoader> loaders;
	
	public void init() {
		
		loaders = new ObjectMap<String, LibGdxLoader>();
		loadSpriter("imp", "imp/imp_000.scml");
	}

	public Data loadSpriter(String name, String path) {
		
		FileHandle scmlHandler = Gdx.files.internal(path);
		SCMLReader reader = new SCMLReader(scmlHandler.read());
		Data data = reader.getData();
		
		File file = new File(path);
		LibGdxLoader loader = new LibGdxLoader(data);
		loader.load(file);
		
		loaders.put(name, loader);
		
		return data;
	}
}
