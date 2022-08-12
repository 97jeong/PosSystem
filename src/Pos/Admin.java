package Pos;

import java.util.Scanner;

public interface Admin {
	
	Scanner sc = new Scanner(System.in);
	String ID = "asd";
	String USERNAME = "조정현";
	int WAGE = 9800;
	
	void login();
	void logout();
	void changePassword();
	void startWork();
	void endWork();
	void showPay();
	double calculatePay(long startTime, long endTime);
	
}
