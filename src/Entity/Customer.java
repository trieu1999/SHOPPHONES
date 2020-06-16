package Entity;
/**
*
* @author lethinhattan
*/
public class Customer {
	public String CodeCus, CardType, FullName, Email, Birthday, Gender, CMND, Phone, Address;
	public int Point;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String codeCus, String cardType, String fullName, String email, String birthday, String gender,
			String cMND, String phone, String address, int point) {
		super();
		CodeCus = codeCus;
		CardType = cardType;
		FullName = fullName;
		Email = email;
		Birthday = birthday;
		Gender = gender;
		CMND = cMND;
		Phone = phone;
		Address = address;
		Point = point;
	}
	public String getCodeCus() {
		return CodeCus;
	}
	public void setCodeCus(String codeCus) {
		CodeCus = codeCus;
	}
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getPoint() {
		return Point;
	}
	public void setPoint(int point) {
		Point = point;
	}
	
}
