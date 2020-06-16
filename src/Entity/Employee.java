package Entity;

import java.io.File;

public class Employee {
	public String CodeEmp, FullName, Password, Address, Gender, Birthday, CMND, Phone, Email;
	File Img;
	public byte[] Img2;
	
	public Employee() {
		
	}

	public Employee(String codeEmp, String password, String fullName, String address, String gender, String birthday, String cMND,
			String phone, String email, File Img) {
		super();
		CodeEmp = codeEmp;
		FullName = fullName;
		Password = password;
		Address = address;
		Gender = gender;
		Birthday = birthday;
		CMND = cMND;
		Phone = phone;
		Email = email;
		this.Img = Img;
	}

	public String getCodeEmp() {
		return CodeEmp;
	}

	public void setCodeEmp(String codeEmp) {
		CodeEmp = codeEmp;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}
 
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public File getImg() {
        return Img;
    }
	
	public void setImg(File Img) {
		this.Img = Img;
	}

	public void getImg2(byte[]Img){
        this.Img2 = Img;
    }
	
	public void setImg2(byte[]Img){
        this.Img2 = Img;
    }

}
