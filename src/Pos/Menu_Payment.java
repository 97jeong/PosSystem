package Pos;

import java.util.ArrayList;

public class Menu_Payment implements Menu{

	@Override
	public void showOption() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------- Payment Menu ---------------------------------------------");
		System.out.println("1. 현금결제  2. 카드결제  3. 판매내역 4. 뒤로가기");
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
		case 1://현금결제
			payment(option);
			Pos.getInstance().changeMenu(new Menu_Payment());
			break;
		case 2://카드결제
			payment(option);
			Pos.getInstance().changeMenu(new Menu_Payment());
			break;
		case 3://매출내역
			Pos.getInstance().db.showSoldList();
			Pos.getInstance().changeMenu(new Menu_Payment());
			break;
		case 4://뒤로가기
			Pos.getInstance().changeMenu(new Menu_main());
			break;
		}
	}

	@Override
	public boolean checkInput(int option) {
		// TODO Auto-generated method stub
		if(option >= 1 && option <= 4) {
			return true;
		} else {
			System.out.println("잘못된 입력입니다. 선택옵션사항을 선택해주세요.");
			return false;	
		}
	}
	
	public void payment(int option) {
		boolean payMethod = Pos.getInstance().db.selectPayMethod(option);
		int birth = 0;
		String name = null;
		int quantity = 0;
		long personalId = 0;
		ArrayList<ProductInfo> thisProductInfoList = new ArrayList<>();
		Product thisProduct;
		
		Pos.getInstance().db.showProductList();
		do {
			System.out.print("상품명을 입력하세요: ");
			name = sc.nextLine();
			thisProductInfoList = Pos.getInstance().db.searchProductInfo(name);
			thisProduct = Pos.getInstance().db.searchProduct(name);
			}while(thisProductInfoList == null);
		if(thisProduct.adult) {
			System.out.println("미성년자에게는 판매할 수 없는 상품입니다.");
			do{
				System.out.print("생년월일을 입력해주세요(ex- 20220811): ");
				birth = Pos.getInstance().db.inputInteger();
			}while(!Pos.getInstance().db.inputBirth(birth));
			
			if(Pos.getInstance().db.checkAdult(birth)) {
				do {
					System.out.print("결제할 수량을 입력하세요: ");
					quantity = Pos.getInstance().db.inputInteger();
				} while(!Pos.getInstance().db.inputQuantity(quantity, thisProduct.quantity));
				for(int i = 0; i < quantity; i++) {
					do{
						System.out.println("*********************************** Searched ProductInfo List **************************************");
						System.out.println(" 상 품 번 호       제 품 명        가격         제조사     성인용유무       유 통 기 한");
						for(int j = 0; j < Pos.getInstance().db.searchProductInfo(name).size(); j++) {
							Pos.getInstance().db.searchProductInfo(name).get(j).showProductInfo();
						}
						System.out.print("결제할 상품번호를 입력하세요: ");
						personalId = Pos.getInstance().db.inputInteger();
					}while(!Pos.getInstance().db.payment(personalId, payMethod));
					thisProduct.decreaseQuantity();
				}
			}
		} else {
				do {
					System.out.print("결제할 수량을 입력하세요: ");
					quantity = Pos.getInstance().db.inputInteger();
				} while(!Pos.getInstance().db.inputQuantity(quantity, thisProduct.quantity));
				for(int i = 0; i < quantity; i++) {
					do{
						System.out.println("*********************************** Searched ProductInfo List **************************************");
						System.out.println(" 상 품 번 호       제 품 명        가격         제조사     성인용유무       유 통 기 한");
						for(int j = 0; j < Pos.getInstance().db.searchProductInfo(name).size(); j++) {
							Pos.getInstance().db.searchProductInfo(name).get(j).showProductInfo();
						}
						System.out.print("결제할 상품번호를 입력하세요: ");
						personalId = Pos.getInstance().db.inputInteger();
					}while(!Pos.getInstance().db.payment(personalId, payMethod));
					thisProduct.decreaseQuantity();
				}
		}
	}

}
