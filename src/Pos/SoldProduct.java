package Pos;

public class SoldProduct implements Comparable<SoldProduct>{
	
	long productId;
	long personalId;
	String name;
	int price;
	String company;
	
	public SoldProduct(ProductInfo discardedPruduct) {
		this.productId = discardedPruduct.productId;
		this.personalId = discardedPruduct.personalId;
		this.name = discardedPruduct.name;
		this.price = discardedPruduct.price;
		this.company = discardedPruduct.company;
	}
	
	public void showProductInfo() {
		System.out.printf("%10d %15d %10s %10d원 %10s\n", productId, personalId, name, price, company);
	}
	
	@Override
	public int compareTo(SoldProduct s) {
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
