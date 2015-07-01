package v5.game.sokoban;

import java.util.ArrayList;
import java.util.LinkedList;

import v5.game.sokoban.model.ModelListener.Event;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;
import v5.game.sokoban.view.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SokobanView extends View {

	Stage _stage;

	SokobanActor[][] _field;
	SokobanActor _man;
	SokobanActor[] _boxes;
	Label label = SokobanActor.createLabel();

	final int SIZE = SokobanActor.SIZE;

	final String TEXT_GAMENAME = "SOKOBAN\n";
	final String TEXT_WINNER = "You Winner!\n";
	final String TEXT_MENU = "press \"1\"..\"3\" for load map";

	public SokobanView(Stage stage, LinkedList<Event> events) {
		super(events);
		_stage = stage;
	}

	@Override
	public void init(State state) {
		// _stage.getRoot().clearChildren();
		// this.createField(state);
		// this.createMan(state);
		// this.createBoxes(state);

//		label.setPosition(32, 64);
//		drawLabel(TEXT_GAMENAME + TEXT_MENU);
	}

	static int offsetX;
	static int offsetY;

	@Override
	public void draw(State state) {
		Event event;
		while (!_events.isEmpty()) {
			event = _events.remove();
			switch (event) {
			case INIT:
				_stage.getRoot().clearChildren();
				this.createField(state);
				drawField(state);
				drawLabel(TEXT_GAMENAME + TEXT_MENU);
				label.setPosition(32, 64);
				_stage.addActor(label);
				drawInit();
				break;
			case NEW_GAME:
				_stage.getRoot().clearChildren();

				// offsetY = (int) ((_stage.getHeight() -
				// state.getField().length * SIZE) / 2);
				// offsetX = (int) ((_stage.getHeight() -
				// state.getField()[0].length * SIZE) / 2);

				this.createField(state);
				this.createMan(state);
				this.createBoxes(state);

				drawField(state);
				drawMan(state);
				drawBoxes(state);

				drawLabel("");
				_stage.addActor(label);				
				break;
			case MOVE_MAN:
				// drawLabel("");
				drawMan(state);
				drawBoxes(state);
				break;
			case GAME_OVER:
				RunnableAction ra = new RunnableAction() {
					@Override
					public boolean act(float delta) {
						System.out.println("SokobanView.draw(): You Winner!");
						label.setPosition(32, 64);
						label.setColor(Color.WHITE);
						drawLabel(TEXT_WINNER);
						return true;
					}
				};
				_man.addAction(Actions.after(Actions.sequence(ra,
						Actions.rotateTo(540, 0.7f))));

				for (int i = 0; i < _boxes.length; i++) {
					_boxes[i].addAction(Actions.after(Actions.scaleTo(0, 0,
							0.7f)));
				}

				break;
			default:
				break;
			}
		}
	}

	private void drawInit() {
		_man = SokobanActor.createMan();
		_stage.addActor(_man);
		
		_man.setBounds(10, 200, 32, 32);

		Action a00 = Actions.moveTo(200, 200, 1);
		Action a01 = Actions.rotateBy(30, 1);		
		
		Action a10 = Actions.moveTo(10, 250, 1);
		Action a11 = Actions.rotateBy(-30, 1);		

		Action a0 = Actions.parallel(a00, a01);
		Action a1 = Actions.parallel(a10, a11);
		
		Action a = Actions.repeat(-1, Actions.sequence(a0, a1));
//		Action a = Actions.repeat(20, Actions.sequence(a0, a1));
		
		_man.addAction(a);
	}

	private void drawBoxes(State state) {
		ArrayList<BoxObject> boxes = state.getBoxes();

		int i = 0;
		int row;
		int col;

		for (BoxObject box : boxes) {
			
			row = box.getRow();
			col = box.getCol();

			int x = (int) _boxes[i].getX() / SIZE;
			int y = (int) _boxes[i].getY() / SIZE;
			if ((x == col) && (y == row)) {
				i++;
				continue;
			}
			// _boxes[i].addAction(Actions.moveTo(col * SIZE - offsetX, row *
			// SIZE
			// - offsetY, 0.5f));
			_boxes[i].clearActions();
			_boxes[i].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(col * SIZE - offsetX, row * SIZE
					- offsetY, 0.5f), Actions.rotateTo(10, 0.25f)), Actions.rotateTo(0, 0.25f)));
			i++;
		}
	}

	private void drawMan(State state) {
		ManObject m = state.getMan();
		// _man.setBounds(m.getCol() * SIZE, m.getRow() * SIZE, SIZE, SIZE);
		_man.addAction(Actions.moveTo(m.getCol() * SIZE - offsetX, m.getRow()
				* SIZE - offsetY, 0.5f));
	}

	private void drawField(State state) {
		for (int row = 0; row < _field.length; row++) {
			for (int col = 0; col < _field[row].length; col++) {
				_field[row][col].setBounds(col * SIZE - offsetX, row * SIZE
						- offsetY, SIZE, SIZE);
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
			_boxes[i].setBounds(col * SIZE - offsetX, row * SIZE - offsetY,
					SIZE, SIZE);
			_stage.addActor(_boxes[i]);
			i++;
		}
	}

	private void createMan(State state) {
		_man = SokobanActor.createMan();

		ManObject m = state.getMan();
		_man.setBounds(m.getCol() * SIZE - offsetX,
				m.getRow() * SIZE - offsetY, SIZE, SIZE);
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
				_field[row][col].setBounds(col * SIZE - offsetX, row * SIZE
						- offsetY, SIZE, SIZE);
				_stage.addActor(_field[row][col]);
			}
		}
	}

	private void drawLabel(String text) {
		label.setText(text);
	}

}
