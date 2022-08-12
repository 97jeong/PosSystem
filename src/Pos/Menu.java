package Pos;

import java.util.Scanner;

public interface Menu {
	Scanner sc = new Scanner(System.in);
	
	void showOption();
	void inputOption();
	void changeMenu(int option);
	boolean checkInput(int option);
}
