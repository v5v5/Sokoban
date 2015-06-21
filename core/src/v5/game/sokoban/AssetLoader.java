package v5.game.sokoban;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture;

	public static Animation manAnimation;
	public static TextureRegion[] man = new TextureRegion[6];

	public static void load() {

		texture = new Texture(Gdx.files.internal("man.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		man[0] = new TextureRegion(texture, 12, 8, 60, 70);
		man[0].flip(false, true);

		man[1] = new TextureRegion(texture, 72, 78, 60, 70);
		man[1].flip(false, true);

		man[2] = new TextureRegion(texture, 132, 148, 60, 70);
		man[2].flip(false, true);

		man[3] = new TextureRegion(texture, 86, 8, 60, 70);
		man[3].flip(false, true);

		man[4] = new TextureRegion(texture, 146, 78, 60, 70);
		man[4].flip(false, true);

		man[5] = new TextureRegion(texture, 206, 148, 60, 70);
		man[5].flip(false, true);

		manAnimation = new Animation(0.06f, man);
		manAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
	}

}