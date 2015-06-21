package v5.game.sokoban.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import v5.game.sokoban.AssetLoader;

public class GameTests {

	Model _model;

	@Before
	public void setup() {
		_model = new Model();
	}

	@Test
	public void loadField() {
		_model.loadField(0);
	}
	
	@Test
	public void gameOver() {
//		_model.
	}

	@Test
	public void assertLoader() {
		AssetLoader.load();
//		assertEquals(null, AssetLoader.texture);
	}
}
