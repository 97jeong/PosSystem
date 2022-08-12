package Pos;

public class DiscardedProduct implements Comparable<DiscardedProduct>{
	
	long productId;
	long personalId;
	String name;
	int price;
	String company;
	int expriyDate;
	
	public DiscardedProduct(ProductInfo discardedPruduct) {
		this.productId = discardedPruduct.productId;
		this.personalId = discardedPruduct.personalId;
		this.name = discardedPruduct.name;
		this.price = discardedPruduct.price;
		this.company = discardedPruduct.company;
		this.expriyDate = discardedPruduct.expriyDate;
	}
	
	public void showProductInfo() {
		System.out.printf("%10d %15d %10s %10d원 %10s %15d\n", productId, personalId, name, price, company, expriyDate);
	}
	
	@Override
	public int compareTo(DiscardedProduct d) {
		if(this.productId < d.productId) {
			return -1;
		} else if (this.productId > d.productId) {
			return 1;
		} else {
			if(this.personalId < d.personalId) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
