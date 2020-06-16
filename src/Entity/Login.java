package Entity;
/**
*
* @author lethinhattan
*/
public class Login {
	private String UserName;
	private String Password;
	
	public Login() {
	}

	public Login(String userName,String password) {
		super();
		UserName = userName;
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
}
