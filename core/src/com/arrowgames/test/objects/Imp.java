package com.arrowgames.test.objects;

import com.arrowgames.core.ArrowObject;
import com.arrowgames.core.SpriterComponent;
import com.arrowgames.test.Assets;
import com.brashmonkey.spriter.LibGdxLoader;

public class Imp extends ArrowObject {
	
	public Imp() {
		LibGdxLoader loader = Assets.instance.loaders.get("imp");
		addComponent(new SpriterComponent(loader, "Imp"));
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
}
