package Pos;

import java.util.ArrayList;

public class ProductInfo implements Comparable<ProductInfo>{
	
	long productId;
	String name;
	int price;
	String company;
	boolean adult;
	
	long personalId;
	int expriyDate;
	
	public ProductInfo(ArrayList<Product> productList, String name, int expriyDate) {
		for(int i = 0; i < productList.size(); i++) {
			if(productList.get(i).name.equals(name)) {
				this.name = name;
				this.price = productList.get(i).price;
				this.company = productList.get(i).company;
				this.adult = productList.get(i).adult;
				this.productId = productList.get(i).productId;
				productList.get(i).addQuantity();
			}
		}
		this.expriyDate = expriyDate;
		this.personalId = hashCode();
	}
	
	public ProductInfo(ArrayList<Product> productList, String name, int price, String company, boolean adult, int expriyDate) {
		this.name = name;
		this.price = price;
		this.company = company;
		this.adult = adult;
		this.expriyDate = expriyDate;
		productList.add(new Product(name, price, company, adult));
		productList.get(productList.size() - 1).addQuantity();;
		this.productId = productList.get(productList.size() - 1).productId;
		this.personalId = hashCode();
		
	}
	
	public void showProductInfo() {
		System.out.printf("%10d %10s %10d원 %10s %10b %15d\n", personalId, name, price, company, adult, expriyDate);
	}
	
	@Override
	public int compareTo(ProductInfo s) {
		if(this.productId < s.productId) {
			return -1;
		} else if (this.productId > s.productId) {
			return 1;
		} else {
			if(this.personalId < s.personalId) {
				return -1;
			} else {
				return 1;
			}
		}
	}
	
}
