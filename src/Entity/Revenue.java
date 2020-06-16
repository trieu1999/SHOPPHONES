package Entity;

public class Revenue {
	private int Revenue;
        private String date;
        private String money;

    public int getRevenue() {
        return Revenue;
    }

    public void setRevenue(int Revenue) {
        this.Revenue = Revenue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Revenue(int Revenue, String date, String money) {
        this.Revenue = Revenue;
        this.date = date;
        this.money = money;
    }
	
	public Revenue(int revenue) {
		this.Revenue = revenue;
	}
	
	public Revenue(){
		
	}
}
