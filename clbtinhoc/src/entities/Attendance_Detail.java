package entities;

public class Attendance_Detail {
	private int id;
	private int id_user;
	private String firstname;
	private String lastname;
	private String klass;
	private int status;
	private int absent;
	public Attendance_Detail() {
		// TODO Auto-generated constructor stub
	}

	public Attendance_Detail(int id, int id_user, String firstname, String lastname, String klass, int status) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.firstname = firstname;
		this.lastname = lastname;
		this.klass = klass;
		this.status = status;
	}
 
	
	
	public Attendance_Detail(int id, int id_user, String firstname, String lastname,String klass, int status, int absent) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.firstname = firstname;
		this.lastname = lastname;
		this.klass = klass;
		this.status = status;
		this.absent = absent;
	}
	
	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
}
