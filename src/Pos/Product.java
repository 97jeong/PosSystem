package Pos;

public class Product implements Comparable<Product>{
	long productId;
	String name;
	int price;
	String company;
	boolean adult;
	int quantity;
	int sum;
	
	public Product(String name, int price, String company, boolean adult) {
		// TODO Auto-generated constructor stub
		this.productId = hashCode();
		this.name = name;
		this.price = price;
		this.company = company;
		this.adult = adult;
		this.quantity = 0;
	}
	
	public void addQuantity() {
		this.quantity += 1;
		this.sum = quantity * price;
	}
	
	public void decreaseQuantity() {
		this.quantity -= 1;
	}
	
	public void showProduct() {
		System.out.printf("%10s %10d원 %10s %10b %10d %10d원\n", name, price, company, adult, quantity, sum);
	}
	
	@Override
	public int compareTo(Product p) {
		if(this.productId < p.productId) {
			return -1;
		} else if (this.productId > p.productId) {
			return 1;
		}
		return 0;
	}
}
