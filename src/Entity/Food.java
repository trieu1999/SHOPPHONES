package Entity;

public class Food {
	public String CodeFood, CodeType, FoodName;
    public int Amount;
    public float Price;
    
	public Food() {
		super();
	}
	
	public Food(String codeFood, String codeType, String foodName, int amount, float price) {
		super();
		CodeFood = codeFood;
		CodeType = codeType;
		FoodName = foodName;
		Amount = amount;
		Price = price;
	}

	public String getCodeFood() {
		return CodeFood;
	}

	public void setCodeFood(String codeFood) {
		CodeFood = codeFood;
	}

	public String getCodeType() {
		return CodeType;
	}

	public void setCodeType(String codeType) {
		CodeType = codeType;
	}

	public String getFoodName() {
		return FoodName;
	}

	public void setFoodName(String foodName) {
		FoodName = foodName;
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
