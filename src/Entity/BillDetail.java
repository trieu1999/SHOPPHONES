package Entity;

public class BillDetail {
	public int CodeBill, Amount;
	public String CodeDrink, CodeFood;
	public float Price;
	
	public BillDetail() {
		super();
	}

	public BillDetail(int codeBill, String codeDrink, String codeFood, int amount, float price) {
		super();
		CodeBill = codeBill;
		CodeDrink = codeDrink;
		CodeFood = codeFood;
		Amount = amount;
		Price = price;
	}

	public int getCodeBill() {
		return CodeBill;
	}

	public void setCodeBill(int codeBill) {
		CodeBill = codeBill;
	}

	public String getCodeDrink() {
		return CodeDrink;
	}

	public void setCodeDrink(String codeDrink) {
		CodeDrink = codeDrink;
	}

	public String getCodeFood() {
		return CodeFood;
	}

	public void setCodeFood(String codeFood) {
		CodeFood = codeFood;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

}
