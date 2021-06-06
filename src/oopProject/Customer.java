package oopProject;

public abstract class Customer {
	private String citizenshipNr;
	private String name;
	private String tel;
	private String mail;

	public Customer(String citizenshipNr) {
		this.citizenshipNr = citizenshipNr;
	}

	public String getCitizenshipNr() {
		return citizenshipNr;
	}

	public void setCitizenshipNr(String citizenshipNr) {
		this.citizenshipNr = citizenshipNr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return name+ "( "+ citizenshipNr + ") [ Phone Number= " + tel + "  Mail Address= " + mail + "]";
	}

}
