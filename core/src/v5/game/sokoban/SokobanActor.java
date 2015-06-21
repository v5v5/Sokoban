package v5.game.sokoban;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SokobanActor extends Actor {

	protected static final int SIZE = 30;

	private Texture _texture = createTexture(Color.RED);

	private static Texture createTexture(Color color) {
		Texture texture = null;

		Pixmap image = new Pixmap(SIZE, SIZE, Pixmap.Format.RGBA8888);

		image.setColor(color);
		image.fill();

		texture = new Texture(image);

		return texture;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(_texture, this.getX(), getY(), this.getOriginX(),
				this.getOriginY(), _texture.getWidth(), _texture.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				_texture.getWidth(), _texture.getHeight(), false, false);
	}

	public static SokobanActor createFieldFloor() {
		SokobanActor a = new SokobanActor();
		a._texture = createTexture(Color.GRAY);
		return a;
	}

	public static SokobanActor createFieldWall() {
		SokobanActor a = new SokobanActor();
		a._texture = createTexture(Color.BLACK);
		return a;
	}

	public static SokobanActor createFieldTarget() {
		SokobanActor a = new SokobanActor();
		a._texture = createTexture(Color.RED);
		return a;
	}

	public static SokobanActor createMan() {
		SokobanActor a = new SokobanActor();
		a._texture = createTexture(Color.GREEN);
		return a;
	}

	public static SokobanActor createBox() {
		SokobanActor a = new SokobanActor();
		a._texture = createTexture(Color.BLUE);
		return a;
	}
}
