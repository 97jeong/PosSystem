package Pos;

public class Menu_main implements Menu{

	@Override
	public void showOption() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------- Main Menu ---------------------------------------------");
		System.out.println("1. 결제하기  2. 상품관리  3. 매출내역관리 4. 급여확인  5. 패스워드 변경 6. 로그아웃  7. 프로그램 종료");
	}

	@Override
	public void inputOption() {
		// TODO Auto-generated method stub
		boolean flag = false;
		int option = 0;	
		do {
			try{
				System.out.print("옵션을 선택하세요: ");
				option = sc.nextInt();
				sc.nextLine();
				flag = checkInput(option);
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
			};	
		} while(!flag);
		
		changeMenu(option);
	}

	@Override
	public void changeMenu(int option) {
		// TODO Auto-generated method stub
		switch(option) {
		case 1://결제하기
			Pos.getInstance().changeMenu(new Menu_Payment());
			break;
		case 2://상품관리
			Pos.getInstance().changeMenu(new Menu_ProductManagement());
			break;
		case 3://매출관리
			Pos.getInstance().changeMenu(new Menu_SalesManagement());
			break;
		case 4://급여확인
			Pos.getInstance().admin.showPay();
			break;
		case 5:// 패스워드 변경
			Pos.getInstance().admin.changePassword();
			break;
		case 7://프로그램 종료
			Pos.isOnPos = true;
		case 6://로그아웃
			Pos.getInstance().admin.logout();
			break;

		}
	}
	
	@Override
	public boolean checkInput(int option) {
		// TODO Auto-generated method stub
		if(option >= 1 && option <= 7) {
			return true;
		} else {
			System.out.println("잘못된 입력입니다. 선택옵션사항을 선택해주세요.");
			return false;	
		}
	}
	
}
