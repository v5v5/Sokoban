package v5.game.sokoban.model;

public class E {

	static class OutInField extends Exception {

		private static final long serialVersionUID = 1L;

		public OutInField() {
			super();
		}

		public OutInField(String s) {
			super(s);
		}

	}

	
	static class CantLoadField extends Exception {

		private static final long serialVersionUID = 1L;
		
	}
}
