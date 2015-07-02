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
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
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

		// label.setPosition(SIZE, SIZE);
		// drawLabel(TEXT_GAMENAME + TEXT_MENU);
	}

	static int offsetX;
	static int offsetY;

	@Override
	public void draw(State state) {
		Event event;
		while (!_events.isEmpty()) {
			event = _events.remove();
			switch (event) {
			case SHOW_MENU:
				_stage.getRoot().clearChildren();

				offsetX = 0;
				offsetY = 0;

				this.createField(state);
				drawField(state);
				drawLabel(TEXT_GAMENAME + TEXT_MENU);
				label.setPosition(SIZE, SIZE);
				_stage.addActor(label);
				drawMenu();
				break;
			case NEW_GAME:
				_stage.getRoot().clearChildren();

				offsetX = (int) ((_stage.getWidth() - state.getField()[0].length
						* SIZE) / 2);
				offsetY = (int) ((_stage.getHeight() - state.getField().length
						* SIZE) / 2);

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
						label.setPosition(SIZE, SIZE);
						label.setColor(Color.WHITE);
						drawLabel(TEXT_WINNER + TEXT_MENU);
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

	private void drawMenu() {
		final int S = SokobanActor.SIZE;
		final int cX = 300;//offsetX;// 300;
		final int cY = 220;//offsetY;// 220;
		final float t = 0.6f;
		final float s = 1.0f;

		// center box
		SokobanActor box = SokobanActor.createBox();
		_stage.addActor(box);
		box.setBounds(cX, cY, S, S);
		box.addAction(Actions.repeat(RepeatAction.FOREVER,
				Actions.rotateBy(-30, 1)));

		// box coordinates
		int b[][] = { { -150, -60 }, { -50, +160 }, { 100, 100 } };

		for (int i = 0; i < b.length; i++) {
			box = SokobanActor.createBox();
			_stage.addActor(box);
			box.setBounds(cX + b[i][0], cY + b[i][1], S, S);
		}

		// wall coordinates
		int w[][] = { { 150, -60 } };
		SokobanActor wall;
		for (int i = 0; i < w.length; i++) {
			wall = SokobanActor.createFieldWall();
			_stage.addActor(wall);
			wall.setBounds(cX + w[i][0], cY + w[i][1], S, S);
		}

		// man actions
		Action a[] = new Action[6];

		_man = SokobanActor.createMan();
		_stage.addActor(_man);
		_man.setBounds(cX - s * 50, cY - s * 100, S, S);
		a[0] = Actions.moveTo(cX + s * 25, cY - s * 50, t);
		a[1] = Actions.moveTo(cX + s * 75, cY + s * 50, t);
		a[2] = Actions.moveTo(cX + s * 50, cY + s * 100, t);
		a[3] = Actions.moveTo(cX - s * 50, cY + s * 50, t);
		a[4] = Actions.moveTo(cX - s * 75, cY - s * 25, t);
		a[5] = Actions.moveTo(cX - s * 50, cY - s * 100, t);
		_man.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(a)));

		_man = SokobanActor.createMan();
		_stage.addActor(_man);
		_man.setBounds(cX - s * 50, cY + s * 100, S, S);
		a[0] = Actions.moveTo(cX - s * 75, cY - s * 0, t);
		a[1] = Actions.moveTo(cX - s * 0, cY - s * 50, t);
		a[2] = Actions.moveTo(cX + s * 50, cY - s * 50, t);
		a[3] = Actions.moveTo(cX + s * 50, cY + s * 0, t);
		a[4] = Actions.moveTo(cX + s * 25, cY + s * 50, t);
		a[5] = Actions.moveTo(cX - s * 50, cY + s * 100, t);
		_man.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(a)));

		_man = SokobanActor.createMan();
		_stage.addActor(_man);
		_man.setBounds(cX + s * 150, cY - s * 0, S, S);
		a[0] = Actions.moveTo(cX + s * 50, cY + s * 50, t);
		a[1] = Actions.moveTo(cX - s * 50, cY + s * 50, t);
		a[2] = Actions.moveTo(cX - s * 150, cY + s * 0, t);
		a[3] = Actions.moveTo(cX - s * 50, cY - s * 50, t);
		a[4] = Actions.moveTo(cX + s * 50, cY - s * 50, t);
		a[5] = Actions.moveTo(cX + s * 150, cY - s * 0, t);
		_man.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(a)));

		_man = SokobanActor.createMan();
		_stage.addActor(_man);
		_man.setOrigin(-1 * S, 0);
		_man.setBounds(cX - s * 200, cY - s * 0, S, S);
		_man.addAction(Actions.repeat(RepeatAction.FOREVER,
				Actions.rotateBy(100, 2)));

	}

	private void drawBoxes(State state) {
		ArrayList<BoxObject> boxes = state.getBoxes();

		int i = 0;
		int row;
		int col;

		for (BoxObject box : boxes) {

			row = box.getRow();
			col = box.getCol();

			int x = (int) (_boxes[i].getX() - offsetX) / SIZE;
			int y = (int) (_boxes[i].getY() - offsetY) / SIZE;
			if ((x == col) && (y == row)) {
				i++;
				continue;
			}
			_boxes[i].clearActions();
			_boxes[i].addAction(Actions.sequence(
					Actions.parallel(
							Actions.moveTo(col * SIZE + offsetX, row * SIZE
									+ offsetY, 0.5f),
							Actions.rotateTo(10, 0.25f)),
					Actions.rotateTo(0, 0.25f)));
			i++;
		}
	}

	private void drawMan(State state) {
		ManObject m = state.getMan();
		// _man.setBounds(m.getCol() * SIZE, m.getRow() * SIZE, SIZE, SIZE);
		_man.addAction(Actions.moveTo(m.getCol() * SIZE + offsetX, m.getRow()
				* SIZE + offsetY, 0.5f));
	}

	private void drawField(State state) {
		for (int row = 0; row < _field.length; row++) {
			for (int col = 0; col < _field[row].length; col++) {
				_field[row][col].setBounds(col * SIZE + offsetX, row * SIZE
						+ offsetY, SIZE, SIZE);
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
			_boxes[i].setBounds(col * SIZE + offsetX, row * SIZE + offsetY,
					SIZE, SIZE);
			_stage.addActor(_boxes[i]);
			i++;
		}
	}

	private void createMan(State state) {
		_man = SokobanActor.createMan();

		ManObject m = state.getMan();
		_man.setBounds(m.getCol() * SIZE + offsetX,
				m.getRow() * SIZE + offsetY, SIZE, SIZE);
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
				_field[row][col].setBounds(col * SIZE + offsetX, row * SIZE
						+ offsetY, SIZE, SIZE);
				_stage.addActor(_field[row][col]);
			}
		}
	}

	private void drawLabel(String text) {
		label.setText(text);
	}

}
