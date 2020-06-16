package Entity;

import java.sql.Date;

public class Bill {
	public int CodeBill;
	public String CodeEmp, CodeCus, CodeTable;
	public float TotalMoney;
	public Date TimePayment;
	
	public Bill() {
		super();
	}
	
	public Bill(String codeEmp, String codeCus, String codeTable, Date timePayment) {
		super();
		CodeEmp = codeEmp;
		CodeCus = codeCus;
		CodeTable = codeTable;
		TimePayment = timePayment;
	}

	public int getCodeBill() {
		return CodeBill;
	}
	public void setCodeBill(int codeBill) {
		CodeBill = codeBill;
	}
	public String getCodeEmp() {
		return CodeEmp;
	}
	public void setCodeEmp(String codeEmp) {
		CodeEmp = codeEmp;
	}
	public String getCodeCus() {
		return CodeCus;
	}
	public void setCodeCus(String codeCus) {
		CodeCus = codeCus;
	}
	public String getCodeTable() {
		return CodeTable;
	}
	public void setCodeTable(String codeTable) {
		CodeTable = codeTable;
	}
	public float getTotalMoney() {
		return TotalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		TotalMoney = totalMoney;
	}
	public Date getTimePayment() {
		return TimePayment;
	}
	public void setTimePayment(Date timePayment) {
		TimePayment = timePayment;
	}
	
}
