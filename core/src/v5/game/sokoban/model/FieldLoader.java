package v5.game.sokoban.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public class FieldLoader {

	static public void load(State state) {
		
		String file = "resources/field0.txt";

		Point fieldSize = getFieldSize(file);
		int countTargets = getCountTargets(file);

		state._field = new Unit[fieldSize._row][fieldSize._col];
		state._targets = new Point[countTargets];
		
		fillField(file, state);
	}

	private static void fillField(String file, State state) {
		String line;
		BufferedReader br = null;
		int iTarget = 0;

		try {
			br = new BufferedReader(new FileReader(file));
			int row = 0;
			while ((line = br.readLine()) != null) {
				System.out.println(line);

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
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private static int getCountTargets(String file) {
		String line;
		BufferedReader br = null;
		int countTargets = 0;
		
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				for (int col = 0; col < line.length(); col++) {
					if ('t' == line.charAt(col)) {
						countTargets++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return countTargets;
	}

	private static Point getFieldSize(String string) {
		int countRow = 0;
		int countCol = 0;

		String line;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(string));
			while ((line = br.readLine()) != null) {
				if (line.length() > countCol)
					countCol = line.length();
				countRow++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return new Point(countRow, countCol);
	}

}
