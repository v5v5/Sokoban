package v5.game.sokoban.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public class FieldLoader {

	static public void load(State state) {

		String file = "/v5/game/sokoban/resources/field0.txt";

		ArrayList<String> field = copyToArray(file);

		Point fieldSize = getFieldSize(field);
		int countTargets = getCountTargets(field);

		state._field = new Unit[fieldSize._row][fieldSize._col];
		state._targets = new Point[countTargets];

		fillField(field, state);
	}

	private static ArrayList<String> copyToArray(String file) {
		ArrayList<String> al = new ArrayList<String>();

		URL url = al.getClass().getResource(file);

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
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return al;
	}

	private static void fillField(ArrayList<String> field, State state) {
		int iTarget = 0;

		String line;
		for (int row = 0; row < field.size(); row++) {
			line = field.get(row);

			for (int col = 0; col < line.length(); col++) {
				switch (line.charAt(col)) {
				case 'w':
					state._field[row][col] = Unit.WALL;
					break;
				case 'b':
					state._field[row][col] = Unit.BOX;
					break;
				case 't':
					state._targets[iTarget] = new Point(row, col);
					iTarget++;
					break;
				case 'm':
					state._manPos = new Point(row, col);
					break;

				default:
					break;
				}

			}
		}
	}

	private static int getCountTargets(ArrayList<String> field) {
		int countTargets = 0;

		for (String line : field) {
			for (int col = 0; col < line.length(); col++) {
				if ('t' == line.charAt(col)) {
					countTargets++;
				}
			}
		}
		return countTargets;
	}

	private static Point getFieldSize(ArrayList<String> field) {
		int countRow = 0;
		int countCol = 0;

		for (String line : field) {
			if (line.length() > countCol)
				countCol = line.length();
			countRow++;
		}

		return new Point(countRow, countCol);
	}

}
