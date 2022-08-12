package Pos;

public class Pos {
	private static final Pos pos = new Pos();
	ProductDB db = ProductDB.getInstance();
	Admin admin;
	static boolean isOnPos = false;
	
	private Pos() {
		this.isOnPos = true;
		admin = new User();	
	}
	
	public static Pos getInstance() {
		return pos;
	}
	
	public void changeMenu(Menu menu) {
		menu.showOption();
		menu.inputOption();
	}
}
