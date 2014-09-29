package com.arrowgames.test.objects;

import com.arrowgames.core.ArrowObject;
import com.arrowgames.core.SpriterComponent;
import com.arrowgames.test.Assets;

public class Imp extends ArrowObject {
	
	public Imp() {
		graphics.add(new SpriterComponent(Assets.instance.impData, "walk"));
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
