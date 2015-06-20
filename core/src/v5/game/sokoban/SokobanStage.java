package v5.game.sokoban;

import v5.game.sokoban.controller.Controller;
import v5.game.sokoban.view.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SokobanStage extends Stage {

	public SokobanStage() {
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true);
		setViewport(new ScreenViewport(camera));
	}

	public void init() {

		final Controller controller = new Controller();

		View view = new SokobanView(this);

		controller.setView(view);

		Gdx.input.setInputProcessor(this);

		addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch (keycode) {
				case Input.Keys.LEFT:
					controller.moveMan(v5.game.sokoban.model.T.Direction.LEFT);
					break;
				case Input.Keys.RIGHT:
					controller.moveMan(v5.game.sokoban.model.T.Direction.RIGHT);
					break;
				case Input.Keys.DOWN:
					controller.moveMan(v5.game.sokoban.model.T.Direction.DOWN);
					break;
				case Input.Keys.UP:
					controller.moveMan(v5.game.sokoban.model.T.Direction.UP);
					break;
				}
				return true;
			}

		});

		controller.setFieldDefault();
	}

}
