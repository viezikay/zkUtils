package com.arrowgames.test;

import java.io.File;

import com.arrowgames.core.SpriterComponent;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.LibGdxLoader;
import com.brashmonkey.spriter.SCMLReader;

public class Assets {
	
	public static final Assets instance = new Assets();
	private LibGdxLoader loader = SpriterComponent.loader;
	
	public Data impData;
	
	public Assets() {
		impData = loadSpriter("imp/imp.scml");
	}

	public Data loadSpriter(String path) {
		
		SCMLReader reader = new SCMLReader(path);
		Data data = reader.getData();
		
		File file = new File(path);
		loader.setData(data);
		loader.load(file);
		
		return data;
	}
}
