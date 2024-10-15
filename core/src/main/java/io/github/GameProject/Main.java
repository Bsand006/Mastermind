package io.github.GameProject;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {

	private Stage stage;
	private Table root;
	private Board[][] board;

	ArrayList<Sprite> drawedCircles;

	boolean touchUp = false;

	Texture background;

	public void create() {
		background = new Texture(Gdx.files.internal("mastermind.png"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		drawedCircles = new ArrayList<Sprite>();

		/*
		 * Create a Texture for each circle. Each texture is then added to an Image
		 * object, which is then added to a Table object. Another Table object with
		 * blank actors is created to properly place the circles. Both tables are then
		 * added to a root Table object, which is then added to the stage.
		 */

		Texture teal = new Texture(Gdx.files.internal("teal.png"));
		Texture blue = new Texture(Gdx.files.internal("blue.png"));
		Texture orange = new Texture(Gdx.files.internal("orange.png"));
		Texture pink = new Texture(Gdx.files.internal("pink.png"));
		Texture purple = new Texture(Gdx.files.internal("purple.png"));
		Texture yellow = new Texture(Gdx.files.internal("yellow.png"));
		Texture green = new Texture(Gdx.files.internal("green.png"));
		Texture red = new Texture(Gdx.files.internal("red.png"));

		root = new Table();
		root.setFillParent(true);
		stage.addActor(root);

		Table blank = new Table(); // A blank table is used to make properly placing the circles easier
		Table circles = new Table();

		// Initializing Image objects

		Image tealCircle = new Image(teal);
		Image blueCircle = new Image(blue);
		Image orangeCircle = new Image(orange);
		Image pinkCircle = new Image(pink);
		Image purpleCircle = new Image(purple);
		Image yellowCircle = new Image(yellow);
		Image greenCircle = new Image(green);
		Image redCircle = new Image(red);

		// Blank table

		blank.add(new Actor()).space(10);
		blank.add(new Actor()).space(10);
		blank.row();
		blank.add(new Actor()).space(10);
		blank.add(new Actor()).space(10);
		blank.row();
		blank.add(new Actor()).space(10);
		blank.add(new Actor()).space(10);
		blank.row();
		blank.add(new Actor()).space(10);
		blank.add(new Actor()).space(10);

		// Circles table

		circles.add(tealCircle).space(10);
		circles.add(blueCircle).space(10);
		circles.row();
		circles.add(orangeCircle).space(10);
		circles.add(pinkCircle).space(10);
		circles.row();
		circles.add(purpleCircle).space(10);
		circles.add(yellowCircle).space(10);
		circles.row();
		circles.add(greenCircle).space(10);
		circles.add(redCircle).space(10);

		// Add both tables to root and space properly

		root.add(blank).space(1000);
		root.add(circles).space(1000);

		circles.setTouchable(Touchable.enabled);

		// Stage coordinates of the circles. This is used to determined which is clicked
		// on in the DragListener.

		board = new Board[13][4]; // Initialize array to represent main mastermind board.

		circles.addListener(new DragListener() {

			// Get the x and y coordinates of every circle relative to the stage.

			float margin = 30.0f;

			int selected = 0;

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				/*
				 * These are the X and Y float values for each circle related to the stage.
				 * These values will be compared to the X and Y of the touchDown event to
				 * determine which circle the user is clicking on.
				 */

				float tealX = tealCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float tealY = tealCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float blueX = blueCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float blueY = blueCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float orangeX = orangeCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float orangeY = orangeCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float pinkX = pinkCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float pinkY = pinkCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float purpleX = purpleCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float purpleY = purpleCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float yellowX = yellowCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float yellowY = yellowCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float greenX = greenCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float greenY = greenCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				float redX = redCircle.localToStageCoordinates(new Vector2(0, 0)).x;
				float redY = redCircle.localToStageCoordinates(new Vector2(0, 0)).y;

				// Get the X and Y of where the user clicked down

				x = event.getStageX();
				y = event.getStageY();

				/*
				 * If the x coordinate of the touchDown minus the X coordinate of a circle is
				 * less than or equal to the margin of 30.0, then we know the click is on that
				 * circle. The same is done with the y coordinate. The y coordinate must also be
				 * greater than zero, otherwise the circle below will also trigger.
				 */

				if (Math.abs(x - tealX) <= margin && (y - tealY) <= margin && (y - tealY) > 0) {
					selected = 1;
				} else if (Math.abs(x - blueX) <= margin && (y - blueY) <= margin && (y - blueY) > 0) {
					selected = 2;
				} else if (Math.abs(x - orangeX) <= margin && (y - orangeY) <= margin && (y - orangeY) > 0) {
					selected = 3;
				} else if (Math.abs(x - pinkX) <= margin && (y - pinkY) <= margin && (y - pinkY) > 0) {
					selected = 4;
				} else if (Math.abs(x - purpleX) <= margin && (y - purpleY) <= margin && (y - purpleY) > 0) {
					selected = 5;
				} else if (Math.abs(x - yellowX) <= margin && (y - yellowY) <= margin && (y - yellowY) > 0) {
					selected = 6;
				} else if (Math.abs(x - greenX) <= margin && (y - greenY) <= margin && (y - greenY) > 0) {
					selected = 7;
				} else if (Math.abs(x - redX) <= margin && (y - redY) <= margin && (y - redY) > 0) {
					selected = 8;
				}

				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				x = event.getStageX();
				y = event.getStageY();

				Sprite draw = new Sprite(teal);
				draw.setPosition(x, y);
				drawedCircles.add(draw);

				touchUp = true;
			}

		});

	}

	public void render() {
		stage.getBatch().begin(); // Begin the batch and draw the background
		stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (touchUp) {

			for (int i = 0; i < drawedCircles.size(); i++) {
				drawedCircles.get(i).draw(stage.getBatch());
			}
		}
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
