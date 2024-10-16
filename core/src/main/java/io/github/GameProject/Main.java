package io.github.GameProject;

import java.util.ArrayList;
import java.util.Random;

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

		// Blank table to help with spacing

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

		board = new Board[4][12]; // Initialize array to represent main mastermind board.

		circles.addListener(new DragListener() {
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

				int row = 0;
				int col = 0;
				boolean toDraw = true;

				// Determine which row on the board the user clicked on

				if (y >= 224 && y <= 282) {
					row = 1;
				} else if (y >= 307 && y <= 361) {
					row = 2;
				} else if (y >= 385 && y <= 434) {
					row = 3;
				} else if (y >= 460 && y <= 500) {
					row = 4;
				} else if (y >= 527 && y <= 567) {
					row = 5;
				} else if (y >= 592 && y <= 646) {
					row = 6;
				} else if (y >= 661 && y <= 702) {
					row = 7;
				} else if (y >= 727 && y <= 768) {
					row = 8;
				} else if (y >= 794 && y <= 835) {
					row = 9;
				} else if (y >= 856 && y <= 901) {
					row = 10;
				} else if (y >= 926 && y <= 973) {
					row = 11;
				} else if (y >= 997 && y <= 1048) {
					row = 12;
				} else if (y >= 1074 && y <= 1145) {
					row = 13;
				} else {
					toDraw = false;
				}

				// Determine which column the user clicked on

				if (x >= 811 && x <= 958) {
					col = 1;
				} else if (x >= 972 && x <= 1106) {
					col = 2;
				} else if (x >= 1123 && x <= 1271) {
					col = 3;
				} else if (x >= 1288 && x <= 1441) {
					col = 4;
				} else if (x >= 1460 && x <= 1658) {
					col = 5;
				} else {
					toDraw = false;
				}

				/*
				 * Create a Sprite object with the cooresponding texture depending on the value
				 * of selected. Set the position of the Sprite to the coordinates of the touchUp
				 * event. Then add the Sprite to an ArrayList that is drawn in the render
				 * method. Will only draw if the touchUp is within the bounds of the board. Will
				 * also only allow red and green circles to be drawn in the 5th column. Finally
				 * it updates the 2D board array with the appropriate color.
				 */
				if (toDraw) {
					if (selected == 1 && col != 5) {
						Sprite draw = new Sprite(teal);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
						board[col][row] = Board.TEAL;
					} else if (selected == 2 && col != 5) {
						Sprite draw = new Sprite(blue);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
						board[col][row] = Board.BLUE;
					} else if (selected == 3 && col != 5) {
						Sprite draw = new Sprite(orange);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
						board[col][row] = Board.ORANGE;
					} else if (selected == 4 && col != 5) {
						Sprite draw = new Sprite(pink);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
						board[col][row] = Board.PINK;
					} else if (selected == 5 && col != 5) {
						Sprite draw = new Sprite(purple);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
						board[col][row] = Board.PURPLE;
					} else if (selected == 6 && col != 5) {
						Sprite draw = new Sprite(yellow);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
						board[col][row] = Board.YELLOW;
					} else if (selected == 7 && col == 5) {
						Sprite draw = new Sprite(green);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
					} else if (selected == 8 && col == 5) {
						Sprite draw = new Sprite(red);
						draw.setPosition(x - 10, y - 10);
						drawedCircles.add(draw);
					}
				}

				touchUp = true;
			}

		});

	}

	public void render() {
		stage.getBatch().begin(); // Begin the batch and draw the background
		stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// If a touchUp event has just occured

		if (touchUp) {
			for (int i = 0; i < drawedCircles.size(); i++) {
				drawedCircles.get(i).draw(stage.getBatch());
			}
		}
		stage.getBatch().end();
		stage.draw();

		logic();
	}

	private void logic() {
		
		// Create the random four spaced color code
		
		Board[] code = new Board[4];

		for (int i = 0; i < code.length; i++) {
			int rand = new Random().nextInt(6);
			switch (rand) {
			case 1:
				code[i] = Board.TEAL;
			case 2:
				code[i] = Board.BLUE;
			case 3:
				code[i] = Board.ORANGE;
			case 4:
				code[i] = Board.PINK;
			case 5:
				code[i] = Board.PURPLE;
			case 6:
				code[i] = Board.YELLOW;
			}

		}
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
