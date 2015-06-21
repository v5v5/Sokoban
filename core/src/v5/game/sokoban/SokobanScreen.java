package v5.game.sokoban;

import com.badlogic.gdx.Screen;

public class SokobanScreen implements Screen {

	SokobanStage _stage;

	@Override
	public void show() {
		_stage = new SokobanStage();
		_stage.init();
	}

	@Override
	public void render(float delta) {
		_stage.act(delta);
		_stage.draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
