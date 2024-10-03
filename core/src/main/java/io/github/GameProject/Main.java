package io.github.GameProject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Main extends ApplicationAdapter {
	private static final int FRAME_COLS = 8, FRAME_ROWS = 1;
	private static final int JUMP_HEIGHT = 20;

	Animation<TextureRegion> walkRightAnimation;
	Animation<TextureRegion> walkLeftAnimation;
	Animation<TextureRegion> waveAnimation;
	TextureRegion idleFrame;
	TextureRegion jumpFrame;

	float stateTime;
	Texture img;
	SpriteBatch batch;
	Sprite sprite;

	int canJump = 0;
	float gravity = -2;
	int jumpCounter = 0;

	@Override
	public void create() {
		// Loads texture (image file) into memory
		img = new Texture(Gdx.files.internal("libgdx.png"));

		// Creates a new sprite batch and splits the it into frames based on our static
		// variables
		batch = new SpriteBatch();
		TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / FRAME_COLS, img.getHeight() / FRAME_ROWS);

		// creating a texture region which is an array of areas on img texture this
		// texture region has two frames and the size of those frames is taken from tmp
		// texture regions

		TextureRegion[] idleFrames = new TextureRegion[1];
		idleFrames[0] = tmp[0][0];
		TextureRegion[] walkRightFrames = new TextureRegion[2];
		walkRightFrames[0] = tmp[0][1];
		walkRightFrames[1] = tmp[0][2];
		TextureRegion[] walkLeftFrames = new TextureRegion[2];
		walkLeftFrames[0] = tmp[0][3];
		walkLeftFrames[1] = tmp[0][4];
		TextureRegion[] jumpFrames = new TextureRegion[1];
		jumpFrames[0] = tmp[0][5];
		TextureRegion[] waveFrames = new TextureRegion[2];
		waveFrames[0] = tmp[0][6];
		waveFrames[1] = tmp[0][7];

		// Creates a animation and assigns a set of frames as well as a time between
		// each frame
		walkRightAnimation = new Animation<TextureRegion>(0.25f, walkRightFrames);
		walkLeftAnimation = new Animation<TextureRegion>(0.25f, walkLeftFrames);
		waveAnimation = new Animation<TextureRegion>(0.25f, waveFrames);
		idleFrame = idleFrames[0];
		jumpFrame = jumpFrames[0];

		// floating point value this is important!
		stateTime = 0f;

		sprite = new Sprite(idleFrame);
		sprite.setPosition(50, 50);
	}

	@Override
	public void render() {

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			sprite.setRegion(waveAnimation.getKeyFrame(stateTime, true));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			sprite.setRegion(walkRightAnimation.getKeyFrame(stateTime, true));
			sprite.translateX(-5f);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			sprite.setRegion(walkLeftAnimation.getKeyFrame(stateTime, true));
			sprite.translateX(5f);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			sprite.setRegion(jumpFrame);
			if (jumpCounter == 0 && canJump == 0) {
				canJump = 1;
				gravity = 8;
			} else if (canJump == 1 && jumpCounter >= JUMP_HEIGHT) {
				canJump = 1;
				gravity = -2;
			}
		}

		if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			gravity = -2;
		}

		if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			sprite.setRegion(idleFrame);
		}

		if (canJump == 1)
			jumpCounter++;
		sprite.translateY(gravity);
		if (sprite.getY() < 0) {
			jumpCounter = 0;
			canJump = 0;
			sprite.setY(0);
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(sprite, sprite.getX(), sprite.getY(), 200, 180); // Draw current frame at (50, 50)
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
