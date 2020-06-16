package Entity;

public class Drink {
    public String CodeDrink, CodeType, DrinkName;
    public int Amount;
    public float Price;
    
	public Drink() {
		super();
	}

	public Drink(String codeDrink, String codeType, String drinkName, int amount, float price) {
		super();
		CodeDrink = codeDrink;
		CodeType = codeType;
		DrinkName = drinkName;
		Amount = amount;
		Price = price;
	}

	public String getCodeDrink() {
		return CodeDrink;
	}

	public void setCodeDrink(String codeDrink) {
		CodeDrink = codeDrink;
	}

	public String getCodeType() {
		return CodeType;
	}

	public void setCodeType(String codeType) {
		CodeType = codeType;
	}

	public String getDrinkName() {
		return DrinkName;
	}

	public void setDrinkName(String drinkName) {
		DrinkName = drinkName;
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
