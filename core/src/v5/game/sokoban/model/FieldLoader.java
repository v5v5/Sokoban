package v5.game.sokoban.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import v5.game.sokoban.model.E.CantLoadField;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;

public class FieldLoader {

	static public void load(State state, int index) throws CantLoadField {

		String file = "/v5/game/sokoban/resources/field" + index + ".txt";
		
		URL url = state.getClass().getResource(file);
		if (url == null) {
			throw new CantLoadField();
		}

		ArrayList<String> field = copyToArray(url);

		Point fieldSize = getFieldSize(field);

		state._field = new Unit[fieldSize._row][fieldSize._col];

		fillField(field, state);
	}

	private static ArrayList<String> copyToArray(URL url) {
		ArrayList<String> al = new ArrayList<String>();

//		URL url = al.getClass().getResource(file);

		BufferedReader reader = null;
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = reader.readLine()) != null) {
				al.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return al;
	}

	private static void fillField(ArrayList<String> field, State state) {
		String line;
		for (int row = 0; row < field.size(); row++) {
			line = field.get(row);

			for (int col = 0; col < line.length(); col++) {
				switch (line.charAt(col)) {
				case 'w':
					state._field[row][col] = Unit.WALL;
					break;
				case 'b':
					state._boxes.add(new BoxObject(state, row, col));
					break;
				case 't':
					state._field[row][col] = Unit.TARGET;
					break;
				case 'm':
					state._man = new ManObject(state, row, col);
					break;

				default:
					break;
				}

			}
		}
	}

	private static Point getFieldSize(ArrayList<String> field) {
		int countRow = 0;
		int countCol = 0;

		for (String line : field) {
			if (line.length() > countCol) {
				countCol = line.length();
			}
			countRow++;
		}

		return new Point(countRow, countCol);
	}

}
