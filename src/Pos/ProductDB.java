package Pos;


import java.util.ArrayList;
import java.util.Collections;

public class ProductDB implements ProductDBManager{
	
	private static final ProductDB db = new ProductDB();
	ArrayList<Product> productList = new ArrayList<>();
	ArrayList<ProductInfo> productInfoList = new ArrayList<>();
	ArrayList<SoldProduct> soldCashList = new ArrayList<>();
	ArrayList<SoldProduct> soldCardList = new ArrayList<>();
	ArrayList<DiscardedProduct> discardedList = new ArrayList<>();
	
	private ProductDB() {
		productList.add(new Product("에쎄체인지", 4500, "KT&G", true));
		productList.add(new Product("신라면", 910, "농심", false));
		productList.add(new Product("불닭볶음면", 1200, "삼양", false));
		productList.add(new Product("참이슬", 1800, "진로", true));
		productList.add(new Product("처음처럼", 1800, "롯데주류", true));
		for(int j = 0; j < 5; j++) {
			for(int i = 0; i < productList.size(); i++) {
				productInfoList.add(new ProductInfo(productList, productList.get(i).name, 20231225));
			}
		}
	}
	
	public static ProductDB getInstance() {
		return db;
	}

	@Override
	public void addProduct(String name, int quantity, int expriyDate) {
		// TODO Auto-generated method stub
		for(int i = 0; i < quantity; i++) {
			productInfoList.add(new ProductInfo(productList, name, expriyDate));	
		}
	}

	@Override
	public void addProduct(String name, int quantity, int price, String company, boolean adult, int expriyDate) {
		// TODO Auto-generated method stub
		productInfoList.add(new ProductInfo(productList, name, price, company, adult, expriyDate));
		addProduct(name, quantity-1, expriyDate);
	}

	@Override
	public boolean discardProduct(long personalId) {
		// TODO Auto-generated method stub
		for(int i = 0; i < productInfoList.size(); i++) {
			if(personalId == productInfoList.get(i).personalId) {
				ProductInfo thisProductInfo = productInfoList.get(i);
				productInfoList.remove(thisProductInfo);
				discardedList.add(new DiscardedProduct(thisProductInfo));
				return true;
			}
		}
		System.out.println("존재하지 않는 상품번호입니다.");
		return false;
	}

	@Override
	public Product searchProduct(String name) {
		// TODO Auto-generated method stub
		Collections.sort(productList);
		Product thisProduct = null;
		for(int i = 0; i < productList.size(); i++) {
			if(name.equals(productList.get(i).name)) {
				thisProduct = productList.get(i);
			}
		}
		return thisProduct;
	}

	@Override
	public ArrayList<ProductInfo> searchProductInfo(String name) {
		// TODO Auto-generated method stub
		Collections.sort(productInfoList);
		boolean isHave = false;
		ArrayList<ProductInfo> thisProductInfoList = new ArrayList<>();
		for(int i = 0; i < productInfoList.size(); i++) {
			if(name.equals(productInfoList.get(i).name)) {
				thisProductInfoList.add(productInfoList.get(i));
				isHave = true;
			}
		}
		if(isHave) {
			return thisProductInfoList;
		}
		else {
			System.out.println("등록되지 않은 상품입니다.");
			return null;
		}
	}

	@Override
	public boolean payment(long personalId, boolean payMethod) {
		// TODO Auto-generated method stub
		for(int i = 0; i < productInfoList.size(); i++) {
			if(personalId == productInfoList.get(i).personalId) {
				ProductInfo thisProductInfo = productInfoList.get(i);
				productInfoList.remove(thisProductInfo);
				if(payMethod) {
					soldCashList.add(new SoldProduct(thisProductInfo));	
				} else {
					soldCardList.add(new SoldProduct(thisProductInfo));
				}
				return true;
			}
		}
		System.out.println("존재하지 않는 상품번호입니다.");
		return false;
	}

	@Override
	public boolean selectPayMethod(int option) {
		// TODO Auto-generated method stub
		if(option == 1) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public boolean checkAdult(int birth) {
		// TODO Auto-generated method stub
		int year = birth / 10000;
		if(year <= 2003) {
			return true;
		} else {
			System.out.println("미성년자에게는 판매할 수 없습니다.");
			return false;
		}
	}

	@Override
	public void showProductList() {
		// TODO Auto-generated method stub
		Collections.sort(productList);
		System.out.println("*********************************** Product List **************************************");
		System.out.println("      제 품 명        가격         제조사     성인용유무       수량      총가격");
		for(int i = 0; i < productList.size(); i++) {
			productList.get(i).showProduct();
		}
		System.out.println();
	}

	@Override
	public void showProductInfoList() {
		// TODO Auto-generated method stub
		Collections.sort(productInfoList);
		System.out.println("*********************************** ProductInfo List **************************************");
		System.out.println(" 상 품 번 호       제 품 명        가격         제조사     성인용유무       유 통 기 한");
		for(int i = 0; i < productInfoList.size(); i++) {
			productInfoList.get(i).showProductInfo();
		}
		System.out.println();
	}

	@Override
	public void showSoldList() {
		// TODO Auto-generated method stub
		Collections.sort(soldCashList);
		System.out.println("*********************************** soldCash List **************************************");
		System.out.println(" 제 품 번 호        상 품 번 호      제 품 명         가격        제조사");
		for(int i = 0; i < soldCashList.size(); i++) {
			soldCashList.get(i).showProductInfo();
		}
		
		Collections.sort(soldCardList);
		System.out.println("*********************************** soldCard List **************************************");
		System.out.println(" 제 품 번 호        상 품 번 호      제 품 명         가격        제조사");
		for(int i = 0; i < soldCardList.size(); i++) {
			soldCardList.get(i).showProductInfo();
		}
		System.out.println();
	}

	@Override
	public void showDiscardedList() {
		// TODO Auto-generated method stub
		Collections.sort(productInfoList);
		System.out.println("*********************************** ProductInfo List **************************************");
		System.out.println("제 품 번 호        상 품 번 호       제 품 명        가격         제 조 사     유 통 기 한");
		for(int i = 0; i < discardedList.size(); i++) {
			discardedList.get(i).showProductInfo();
		}
		System.out.println();
	}

	@Override
	public boolean inputAdult() {
		// TODO Auto-generated method stub
		boolean isChecked = false;
		boolean adult = false;
		while(!isChecked) {
			try{
				System.out.print("성인용 유무를 입력하세요(true or false): ");
				adult = sc.nextBoolean();
				sc.nextLine();
				isChecked = true;
			} catch (Exception e) {
				// TODO: handle exception
				sc.nextLine();
				System.out.println("true 혹은 false를 입력해주세요.");
			}	
		}
		return adult;
	}

	@Override
	public int inputInteger() {
		// TODO Auto-generated method stub
		int input = 0;
		try{
			input = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			sc.nextLine();
			System.out.println("숫자를 입력하세요.");
		}
		return input;
	}

	@Override
	public boolean inputExpriyDate(int input) {
		// TODO Auto-generated method stub
		if(input >= 20220811 && input <= 21000101) {
			int year = input / 10000;
			int month = input % 10000 / 100;
			int day = input % 100;
			if(month >=1 && month <=12 && day >=1 && day <= 31) {
				if(month == 2) {
					if(day <= 28) {
						return true;
					} else {
						System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
						return false;
					}
				}
				else if(month % 2 == 1) {
					if(month <= 7) {
						return true;
					} else {
						if(day <= 30) {
							return true;
						} else {
							System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
							return false;
						}
					}
				}
				else {
					if(month >= 8) {
						return true;
					} else {
						if(day <= 30) {
							return true;
						} else {
							System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
							return false;
						}
					}
				}
			}
			else {
				System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
				return false;
			}
		} else {
			System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
			return false;
		}
	}

	
	@Override
	public boolean inputBirth(int input) {
		// TODO Auto-generated method stub
		if(input >= 1000101 && input <= 20221231) {
			int year = input / 10000;
			int month = input % 10000 / 100;
			int day = input % 100;
			if(month >=1 && month <=12 && day >=1 && day <= 31) {
				if(month == 2) {
					if(day <= 28) {
						return true;
					} else {
						System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
						return false;
					}
				}
				else if(month % 2 == 1) {
					if(month <= 7) {
						return true;
					} else {
						if(day <= 30) {
							return true;
						} else {
							System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
							return false;
						}
					}
				}
				else {
					if(month >= 8) {
						return true;
					} else {
						if(day <= 30) {
							return true;
						} else {
							System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
							return false;
						}
					}
				}
			}
			else {
				System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
				return false;
			}
		} else {
			System.out.println("날짜형식이 잘못되었습니다. 다시 확인해 주세요.");
			return false;
		}
	}

	@Override
	public boolean inputQuantity(int input, int quantity) {
		// TODO Auto-generated method stub
		if(input <= quantity) {
			return true;
		} else {
			System.out.println("물품 수량이 부족합니다.");			
			return false;
		}
	}


	
}
