package bot;

public class utente {
	
	private String first_name;
	private String last_name;
	private long user_id;
	private String phone;

	public utente() {
		
	}
	
	public utente(String first_name, String last_name, long user_id, String phone) {
		
		this.first_name=first_name;
		this.last_name=last_name;
		this.user_id=user_id;
		this.phone=phone;
		
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
