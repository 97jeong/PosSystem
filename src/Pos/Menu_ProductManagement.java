package Pos;

import java.util.ArrayList;

public class Menu_ProductManagement implements Menu{

	@Override
	public void showOption() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------- ProductManagement Menu ---------------------------------------------");
		System.out.println("1. 상품등록  2. 상품폐기  3. 상품검색 4. 재고확인 5. 뒤로가기");
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
		case 1://상품등록
			addProduct();
			Pos.getInstance().changeMenu(new Menu_ProductManagement());
			break;
		case 2://상품폐기
			discardProduct();
			Pos.getInstance().changeMenu(new Menu_ProductManagement());
			break;
		case 3://상품검색
			searchProduct();
			Pos.getInstance().changeMenu(new Menu_ProductManagement());
			break;
		case 4://재고확인
			Pos.getInstance().db.showProductList();
			Pos.getInstance().changeMenu(new Menu_ProductManagement());
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
	
	public void addProduct() {
		String name = null;
		int quantity = 0;
		int price = 0;
		String company = null;
		boolean adult = false;
		int expriyDate = 0;
		System.out.println("*********************************** Add Product **************************************");
		System.out.print("상품명을 입력하세요: ");
		name = sc.nextLine();
		Product thisProduct = Pos.getInstance().db.searchProduct(name);
		if(thisProduct != null) {
			while(quantity == 0) {
				System.out.print("추가할 수량을 입력하세요: ");
				quantity = Pos.getInstance().db.inputInteger();
			}
			 do{
				System.out.print("유통기한을 입력하세요(ex- 20220811): ");
				expriyDate = Pos.getInstance().db.inputInteger();	
			}while(!Pos.getInstance().db.inputExpriyDate(expriyDate));
			 Pos.getInstance().db.addProduct(name, quantity, expriyDate);
		} else {
			while(quantity == 0) {
				System.out.print("추가할 수량을 입력하세요: ");
				quantity = Pos.getInstance().db.inputInteger();
			}
			while(price == 0) {
			System.out.print("상품의 가격을 입력하세요: ");
			price = Pos.getInstance().db.inputInteger();
			}
			System.out.print("제조사를 입력하세요: ");
			company = sc.nextLine();
			adult = Pos.getInstance().db.inputAdult();
			do{
				System.out.print("유통기한을 입력하세요(ex- 20220811): ");
				expriyDate = Pos.getInstance().db.inputInteger();
			}while(!Pos.getInstance().db.inputExpriyDate(expriyDate));	
			Pos.getInstance().db.addProduct(name, quantity, price, company, adult, expriyDate);	
		}
	}
	
	public void discardProduct() {
		String name = null;
		int quantity = 0;
		long personalId = 0;
		ArrayList<ProductInfo> thisProductInfoList = new ArrayList<>();
		Product thisProduct;
		System.out.println("*********************************** Discard Product **************************************");
		Pos.getInstance().db.showProductInfoList();
		do {
		System.out.print("상품명을 입력하세요: ");
		name = sc.nextLine();
		thisProductInfoList = Pos.getInstance().db.searchProductInfo(name);
		thisProduct = Pos.getInstance().db.searchProduct(name);
		}while(thisProductInfoList == null);
		System.out.println("*********************************** Searched ProductInfo List **************************************");
		System.out.println(" 상 품 번 호       제 품 명        가격         제조사     성인용유무       유 통 기 한");
		for(int i = 0; i < thisProductInfoList.size(); i++) {
			thisProductInfoList.get(i).showProductInfo();
		}
		do {
			System.out.print("폐기할 수량을 입력하세요: ");
			quantity = Pos.getInstance().db.inputInteger();
		} while(!Pos.getInstance().db.inputQuantity(quantity, thisProduct.quantity));
		for(int i = 0; i < quantity; i++) {
			do{
				System.out.print("폐기할 상품번호를 입력하세요: ");
				personalId = Pos.getInstance().db.inputInteger();
			}while(!Pos.getInstance().db.discardProduct(personalId));
			thisProduct.decreaseQuantity();
			System.out.println("*********************************** Searched ProductInfo List **************************************");
			System.out.println(" 상 품 번 호       제 품 명        가격         제조사     성인용유무       유 통 기 한");
			for(int j = 0; j < Pos.getInstance().db.searchProductInfo(name).size(); j++) {
				Pos.getInstance().db.searchProductInfo(name).get(j).showProductInfo();
			}
		}
	}
	
	public void searchProduct() {
		String name = null;
		Product thisProduct = null;
		System.out.print("찾으시는 상품을 입력하세요: ");
		name = sc.nextLine();
		thisProduct = Pos.getInstance().db.searchProduct(name);
		if(thisProduct != null) {
			System.out.println("*********************************** Product List **************************************");
			System.out.println("      제 품 명        가격         제조사     성인용유무       수량      총가격");
			thisProduct.showProduct();
		} else {
			System.out.println("등록되지 않은 상품입니다.");
		}
	}

}
