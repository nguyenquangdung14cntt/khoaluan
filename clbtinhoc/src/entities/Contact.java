package entities;

public class Contact {
	private int id;
	private int id_user;
	private String firstname;
	private String lastname;
	private String klass;
	private String context;
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}

	

	public Contact(int id, int id_user, String firstname, String lastname, String klass, String context) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.firstname = firstname;
		this.lastname = lastname;
		this.klass = klass;
		this.context = context;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getKlass() {
		return klass;
	}



	public void setKlass(String klass) {
		this.klass = klass;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}