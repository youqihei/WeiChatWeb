package entity;

public class LoginResponse {
	   private String token;
	   private String userid;
	   private String username;
	   private String userphoto;
	   private String password;
	   
       public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	public LoginResponse(String token)
       {
    	   this.token = token;
       }
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
}
