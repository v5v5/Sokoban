package v5.game.sokoban;

import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.view.View;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class SokobanView extends View {
	
	SokobanUnit _man = new SokobanUnit();
	
	Stage _stage;

	public SokobanView(Stage stage) {
		_stage = stage;
		
		_man.setBounds(-30, -30, 30, 30);
		_stage.addActor(_man);
	}

	@Override
	public void drawUnit(Unit unit, int row, int col) {

		if (null == unit) {

		} else {
			switch (unit) {
			case MAN:
				
				_man.setBounds(col * 30, row * 30, 30, 30);

				break;
			case BOX:

				break;

			case WALL:

				break;
			case TARGET:

				break;
			}
		}
	}

}
