package Pos;

public class User implements Admin{

	private String password = "123";
	static boolean isLogin = false;
	private long startTime;
	private long endTime;
	private double pay;
	
	public User() {
		this.startTime = 0;
		this.endTime = 0;
		this.pay = 0;
	}
	
	@Override
	public void login() {
		// TODO Auto-generated method stub
		String id;
		String pw;
		System.out.print("ID를 입력하세요: ");
		id = sc.nextLine();
		System.out.print("Password를 입력하세요: ");
		pw = sc.nextLine();
		if(id.equals(ID) && pw.equals(password)) {
			System.out.println(USERNAME + "님 안녕하세요");
			isLogin = true;	
		} else {
			System.out.println("등록된 계정이 아닙니다. 다시 시도해 주세요.");
		}
		startWork();
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		isLogin = false;
		showPay();
		System.out.println(USERNAME + "님 오늘 하루도 수고하셨습니다!");
	}

	@Override
	public void changePassword() {
		// TODO Auto-generated method stub
		String pw;
		String temp;
		System.out.print("현재 Password를 입력하세요: ");
		pw = sc.nextLine();
		System.out.print("변경할 Password를 입력하세요: ");
		temp = sc.nextLine();
		if(pw.equals(password)) {
			this.password = temp;
			System.out.println("Password가 변경되었습니다.");
		} else {
			System.out.println("현재 Password가 틀립니다.");
		}
	}

	@Override
	public void startWork() {
		// TODO Auto-generated method stub
		this.startTime = System.currentTimeMillis();
	}

	@Override
	public void endWork() {
		// TODO Auto-generated method stub
		this.endTime = System.currentTimeMillis();
	}

	@Override
	public double calculatePay(long startTime, long endTime) {
		// TODO Auto-generated method stub
		double pay = (double) WAGE * (endTime - startTime) / 36e5;
		return pay;
	}
	
	@Override
	public void showPay() {
		// TODO Auto-generated method stub
		endWork();
		pay = calculatePay(startTime, endTime);
		System.out.printf("%s님의 예상 급여는 %.2f원입니다.\n", USERNAME, pay);
	}
}
