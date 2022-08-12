package Pos;

import java.util.ArrayList;

public class Menu_SalesManagement implements Menu{
	
	@Override
	public void showOption() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------- SalesManagement Menu ---------------------------------------------");
		System.out.println("1. 현금결제내역  2. 카드결제내역  3. 폐기내역 4. 소득내역(판매금액 - 폐기금액) 5. 뒤로가기");
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
		case 1://현금결제내역
			System.out.println("*********************************** salesCash List **************************************");
			System.out.println(" 제 품 번 호        상 품 번 호      제 품 명         가격        제조사");
			showSalesList(Pos.getInstance().db.soldCashList, "현금");
			Pos.getInstance().changeMenu(new Menu_SalesManagement());
			break;
		case 2://카드결제내역
			System.out.println("*********************************** salesCard List **************************************");
			System.out.println(" 제 품 번 호        상 품 번 호      제 품 명         가격        제조사");
			showSalesList(Pos.getInstance().db.soldCardList, "카드");
			Pos.getInstance().changeMenu(new Menu_SalesManagement());
			break;
		case 3://폐기내역
			Pos.getInstance().db.showDiscardedList();
			Pos.getInstance().changeMenu(new Menu_SalesManagement());
			break;
		case 4://소득조회
			System.out.println("*********************************** Earnings **************************************");
			showEarnings(Pos.getInstance().db.soldCashList, Pos.getInstance().db.soldCardList, Pos.getInstance().db.discardedList);
			Pos.getInstance().changeMenu(new Menu_SalesManagement());
			break;	
		case 5://뒤로가기
			Pos.getInstance().changeMenu(new Menu_main());
			break;
		}
	}

	@Override
	public boolean checkInput(int option) {
		// TODO Auto-generated method stub
		if(option >= 1 && option <= 5) {
			return true;
		} else {
			System.out.println("잘못된 입력입니다. 선택옵션사항을 선택해주세요.");
			return false;	
		}
	}
	
	public void showSalesList(ArrayList<SoldProduct> s, String str) {
		int sum = 0;
		for(int i = 0; i < s.size(); i++) {
			s.get(i).showProductInfo();
			sum += s.get(i).price;
		}
		System.out.println("매출액(" + str + "):" + sum + "원");
	}
	
	public void showEarnings(ArrayList<SoldProduct> cash, ArrayList<SoldProduct> card, ArrayList<DiscardedProduct> discarded) {
		int sales = 0;
		int losings = 0;
		int Earnings = 0;
		for(int i = 0; i < cash.size(); i++) {
			sales += cash.get(i).price;
		}
		for(int i = 0; i < card.size(); i++) {
			sales += card.get(i).price;
		}
		for(int i = 0; i < discarded.size(); i++) {
			losings += discarded.get(i).price;
		}
		Earnings = sales - losings;
		System.out.println("총 매출액: " + sales + "원");
		System.out.println("폐기 총액: " + losings + "원");
		System.out.println("소득 총액(원가 계산 X): " + Earnings + "원");
	}
	
	
}
