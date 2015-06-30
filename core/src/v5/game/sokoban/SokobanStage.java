package v5.game.sokoban;

import v5.game.sokoban.controller.Controller;
import v5.game.sokoban.view.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SokobanStage extends Stage {

	private OrthographicCamera _cam;
	private SpriteBatch batcher;

	public SokobanStage() {
		_cam = new OrthographicCamera();
		_cam.setToOrtho(true);
		setViewport(new ScreenViewport(_cam));
		
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(_cam.combined);
	}

	public void init() {

		final Controller controller = new Controller();

		View view = new SokobanView(this, controller.getEvents());

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
				case Input.Keys.NUM_1:
					controller.loadField(0);
					break;
				case Input.Keys.NUM_2:
					controller.loadField(1);
					break;
				case Input.Keys.NUM_3:
					controller.loadField(2);
					break;
				}
				return true;
			}

		});

		controller.init();
	}
	
}
