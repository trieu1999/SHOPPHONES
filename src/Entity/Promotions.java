package Entity;

public class Promotions {
	public int  CodePromo, DiscountPromo;
	public String PromoName, StartPromo, EndPromo, Description;
	
	public Promotions() {
		super();
	}

	public Promotions(int codePromo, String promoName, int discountPromo, String startPromo, String endPromo,
			String description) {
		super();
		CodePromo = codePromo;
		PromoName = promoName;
		DiscountPromo = discountPromo;
		StartPromo = startPromo;
		EndPromo = endPromo;
		Description = description;
	}

	public int getCodePromo() {
		return CodePromo;
	}

	public void setCodePromo(int codePromo) {
		CodePromo = codePromo;
	}

	public String getPromoName() {
		return PromoName;
	}

	public void setPromoName(String promoName) {
		PromoName = promoName;
	}

	public int getDiscountPromo() {
		return DiscountPromo;
	}

	public void setDiscountPromo(int discountPromo) {
		DiscountPromo = discountPromo;
	}

	public String getStartPromo() {
		return StartPromo;
	}

	public void setStartPromo(String startPromo) {
		StartPromo = startPromo;
	}

	public String getEndPromo() {
		return EndPromo;
	}

	public void setEndPromo(String endPromo) {
		EndPromo = endPromo;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
}
