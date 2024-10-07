package io.github.GameProject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {

	
	private Stage stage;

	Texture background;

	Image tealCircle, blueCircle, orangeCircle, pinkCircle, purpleCircle, yellowCircle, greenCircle, redCircle;

	public void create() {
		background = new Texture(Gdx.files.internal("mastermind.png"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		/*
		 * Create a Texture for each circle. Then create an actor object with the
		 * Texture and the coordinates of the circle. Finally add each actor object to
		 * the stage.
		 */
		Texture teal = new Texture(Gdx.files.internal("teal.png"));
		Texture blue = new Texture(Gdx.files.internal("blue.png"));
		Texture orange = new Texture(Gdx.files.internal("orange.png"));
		Texture pink = new Texture(Gdx.files.internal("pink.png"));
		Texture purple = new Texture(Gdx.files.internal("purple.png"));
		Texture yellow = new Texture(Gdx.files.internal("yellow.png"));
		Texture green = new Texture(Gdx.files.internal("green.png"));
		Texture red = new Texture(Gdx.files.internal("red.png"));

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		tealCircle = new Image(teal);
		table.add(tealCircle);

	}

	public void render() {
		stage.getBatch().begin();
		stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.getBatch().end();
		stage.draw();

	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void pause() {

	}

	public void resume() {

	}

	public void dispose() {
		stage.dispose();
	}
}
