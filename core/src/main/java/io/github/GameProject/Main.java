package io.github.GameProject;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main implements ApplicationListener {

	private Stage stage;
	Texture background;

	public class MyActor extends Actor {
		Texture circle;
		float x = 0, y = 0;
		public boolean started = false;

		public MyActor(Texture circle) {
			this.circle = circle;
			setBounds(x, y, circle.getWidth(), circle.getHeight());
		}

		@Override
		public void draw(Batch batch, float alpha) {
			batch.draw(circle, 200, 50); // Draws the appropriate circle at specified coordinates
		}
	}

	@Override
	public void create() {
		background = new Texture(Gdx.files.internal("mastermind.png"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Texture teal = new Texture(Gdx.files.internal("teal.png"));

		MyActor actor1 = new MyActor(teal);
		stage.addActor(actor1);

	}

	@Override
	public void render() {
		stage.getBatch().begin();
		stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.getBatch().end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
