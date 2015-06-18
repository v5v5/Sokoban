package v5.game.sokoban.model;

public class T {

	public enum Unit {
		WALL, BOX, TARGET,
	}

	public enum Direction {
		UP, DOWN, RIGHT, LEFT,
	}

	static public class Point {
		public Point() {
			_row = 0;
			_col = 0;
		}

		public Point(int row, int col) {
			_row = row;
			_col = col;
		}

		public int _row;
		public int _col;
	}

}
