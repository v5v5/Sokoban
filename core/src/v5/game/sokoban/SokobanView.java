package v5.game.sokoban;

import java.util.ArrayList;
import java.util.LinkedList;

import v5.game.sokoban.model.ModelListener.Event;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;
import v5.game.sokoban.view.View;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class SokobanView extends View {

	Stage _stage;
	
	SokobanActor[][] _field;
	SokobanActor _man;
	SokobanActor[] _boxes;
	
	final int SIZE = SokobanActor.SIZE;

	public SokobanView(Stage stage, LinkedList<Event> events) {
		super(events);
		_stage = stage;
	}

	public void init(State state) {
//		_stage.getRoot().clearChildren();
//		this.createField(state);
//		this.createMan(state);
//		this.createBoxes(state);		
	}

	public void draw(State state) {
		Event event = _events.remove();
		switch (event) {
		case NEW_GAME:
			_stage.getRoot().clearChildren();
			this.createField(state);
			this.createMan(state);
			this.createBoxes(state);		
			break;
		case MOVE_MAN:
			break;
		case GAME_OVER:
			System.out.println("SokobanView.draw(): You Winner!");
			break;
		default:
			break;
		}
		
		drawField(state);
		drawMan(state);
		drawBoxes(state);
	}

	private void drawBoxes(State state) {
		ArrayList<BoxObject> boxes = state.getBoxes();

		int i = 0;
		int row;
		int col;

		for (BoxObject box : boxes) {
			row = box.getRow();
			col = box.getCol();
//			_boxes[i].setBounds(col * SIZE, row * SIZE, SIZE, SIZE);
			_boxes[i].addAction(Actions.moveTo(col * SIZE, row * SIZE, 0.5f));
			i++;
		}
	}

	private void drawMan(State state) {
		ManObject m = state.getMan();
//		_man.setBounds(m.getCol() * SIZE, m.getRow() * SIZE, SIZE, SIZE);
		_man.addAction(Actions.moveTo(m.getCol() * SIZE, m.getRow() * SIZE, 0.5f));
	}

	private void drawField(State state) {
		for (int row = 0; row < _field.length; row++) {
			for (int col = 0; col < _field[row].length; col++) {
				_field[row][col].setBounds(col * SIZE, row * SIZE, SIZE, SIZE);
			}
		}
	}

	private void createBoxes(State state) {
		ArrayList<BoxObject> boxes = state.getBoxes();
		_boxes = new SokobanActor[boxes.size()];

		int i = 0;
		int row;
		int col;

		for (BoxObject box : boxes) {
			_boxes[i] = SokobanActor.createBox();
			row = box.getRow();
			col = box.getCol();
			_boxes[i].setBounds(col * SIZE, row * SIZE, SIZE, SIZE);
			_stage.addActor(_boxes[i]);
			i++;
		}
	}

	private void createMan(State state) {
		_man = SokobanActor.createMan();

		ManObject m = state.getMan();
		_man.setBounds(m.getCol() * SIZE, m.getRow() * SIZE, SIZE, SIZE);
		_stage.addActor(_man);
	}

	private void createField(State state) {
		Unit[][] fieldModel = state.getField();

		_field = new SokobanActor[fieldModel.length][fieldModel[0].length];

		for (int row = 0; row < fieldModel.length; row++) {
			for (int col = 0; col < fieldModel[row].length; col++) {
				if (null == fieldModel[row][col]) {
					_field[row][col] = SokobanActor.createFieldFloor();
				} else {
					switch (fieldModel[row][col]) {
					case WALL:
						_field[row][col] = SokobanActor.createFieldWall();
						break;
					case TARGET:
						_field[row][col] = SokobanActor.createFieldTarget();
						break;
					default:
						break;
					}
				}
				_field[row][col].setBounds(col * SIZE, row * SIZE, SIZE, SIZE);
				_stage.addActor(_field[row][col]);
			}
		}
	}

}
