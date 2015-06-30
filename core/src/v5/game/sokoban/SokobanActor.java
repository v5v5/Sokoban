package v5.game.sokoban;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class SokobanActor extends Actor {

	public static final int SIZE = 32;

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
//		a._texture = createTexture(Color.GRAY);
		a._texture = new Texture(Gdx.files.internal("space.png"));
		return a;
	}

	public static SokobanActor createFieldWall() {
		SokobanActor a = new SokobanActor();
//		a._texture = createTexture(Color.BLACK);
		a._texture = new Texture(Gdx.files.internal("wall.png"));
		return a;
	}

	public static SokobanActor createFieldTarget() {
		SokobanActor a = new SokobanActor();
//		a._texture = createTexture(Color.RED);
		a._texture = new Texture(Gdx.files.internal("site.png"));
		return a;
	}

	public static SokobanActor createMan() {
		SokobanActor a = new SokobanActor();
//		a._texture = createTexture(Color.GREEN);
		a._texture = new Texture(Gdx.files.internal("player.png"));
		a.setOrigin(SIZE / 2, SIZE / 2);		
		a.rotateBy(180);
		return a;
	}

	public static SokobanActor createBox() {
		SokobanActor a = new SokobanActor();
//		a._texture = createTexture(Color.BLUE);
		a._texture = new Texture(Gdx.files.internal("box.png"));
		return a;
	}
	
	public static Label createLabel() {
		BitmapFont bf = new BitmapFont(true);
		LabelStyle ls = new LabelStyle(bf, Color.WHITE);
		Label a = new Label("", ls);
		a.setFontScale(1.2f);
		return a;
	}
}
