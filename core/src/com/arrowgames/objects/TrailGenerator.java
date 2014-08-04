package com.arrowgames.objects;

import com.arrowgames.objects.TrailSegment.Vertex;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public class TrailGenerator {
	
	public static final String tag = TrailGenerator.class.getSimpleName();

	float genTime, t;
	Texture texture;
	Array<TrailSegment> segments;
	Body target;
	Vector2 v1, v2, v3, v4;
	
	public TrailGenerator(Body target) {
		segments = new Array<TrailSegment>();
		texture = new Texture("segment.png");
		this.target = target;
		v1 = new Vector2();
		v2 = new Vector2();
		v3 = new Vector2();
		v4 = new Vector2();
	}
	
	public void update(float deltaTime) {
		t = Math.min(genTime, t + deltaTime);
		if (t == genTime) {
			genSegment();
			t = 0;
		}
		for (TrailSegment segment : segments)
			segment.update(deltaTime);
		if (segments.size > 0)
		Gdx.app.log(tag, "" + segments.get(0).alpha);
	}
	
	public void render(Batch batch) {
		for (TrailSegment segment : segments)
			segment.render(batch);
	}
	
	public void genSegment() {
		
		int i = segments.size;
		TrailSegment segment = new TrailSegment();
		segment.setFadeTime(.5f);
		segment.setTexture(texture);
		
		v1.set(target.getWorldPoint(new Vector2(0, -.12f)));
		v2.set(target.getWorldPoint(new Vector2(0, .12f)));
		
		segment.setVertexLocation(Vertex.first, v1);
		segment.setVertexLocation(Vertex.second, v2);
		
		v3.set(target.getLinearVelocity()).nor().scl(-1).add(v2);
		v4.set(target.getLinearVelocity()).nor().scl(-1).add(v1);
		
		if (i != 0) {
			Vector2 r = new Vector2();
			Vector2 p1 = segments.get(i-1).getVertexLocation(Vertex.third);
			Vector2 p2 = segments.get(i-1).getVertexLocation(Vertex.second);
			Intersector.intersectLines(p1, p2, v2, v3, r);
			segments.get(i-1).setVertexLocation(Vertex.second, r);
			v3.set(r);
			
			p1 = segments.get(i-1).getVertexLocation(Vertex.fourth);
			p2 = segments.get(i-1).getVertexLocation(Vertex.first);
			Intersector.intersectLines(p1, p2, v1, v4, r);
			segments.get(i-1).setVertexLocation(Vertex.first, r);
			v4.set(r);
		}
		
		segment.setVertexLocation(Vertex.third, v3);
		segment.setVertexLocation(Vertex.fourth, v4);
		
		segments.add(segment);
	}
	
	public void setGentime(float time) {
		genTime = time;
	}
}
