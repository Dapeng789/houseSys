package sys.qx.model;

public class UserBean {

	private  int id;
	private String userName;
	private String mypwd;
	private int authority;
	
	 
	
	
 
	 
	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMypwd() {
		return mypwd;
	}
	public void setMypwd(String mypwd) {
		this.mypwd = mypwd;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", userName=" + userName + ", mypwd=" + mypwd + ", authority=" + authority + "]";
	}
}
