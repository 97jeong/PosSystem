package Pos;

import java.util.ArrayList;
import java.util.Scanner;

public interface ProductDBManager {
	
	Scanner sc = new Scanner(System.in);
	
	// 상품 등록 메소드
	void addProduct(String name, int quantity, int expriyDate);
	void addProduct(String name, int quantity, int price, String company, boolean adult, int expriyDate);
	
	// 상품 폐기 메소드
	boolean discardProduct(long personalId);
	
	// 상품 검색 메소드
	Product searchProduct(String name);
	ArrayList<ProductInfo> searchProductInfo(String name);
	
	// 상품 결제 메소드
	boolean payment(long personalId, boolean payMethod);
	// 성인용 제품 결제 전 검사 메소드
	boolean checkAdult(int birth);
	
	// 결제방법 선택 메소드
	boolean selectPayMethod(int option);
	
	
	// 테이블 조회 메소드
	void showProductList();
	void showProductInfoList();
	void showSoldList();
	void showDiscardedList();
	
	//입력값 검사
	int inputInteger();
	boolean inputBirth(int input);
	boolean inputAdult();
	boolean inputExpriyDate(int input);
	boolean inputQuantity(int input, int quantity);
	
}
