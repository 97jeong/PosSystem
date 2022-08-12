package Pos;


public class PosRunnable {
	public static void main(String[] args) {
		Pos pos = Pos.getInstance();
		while(!pos.isOnPos) {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("----------------------------------------- 24시간 편의점 Pos System ----------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("Login Page");
			pos.admin.login();
			while(User.isLogin) {
				pos.changeMenu(new Menu_main());
			}
			
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
