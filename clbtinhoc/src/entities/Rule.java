package entities;

import org.hibernate.validator.constraints.NotBlank;

public class Rule {
	private int id;
	
	@NotBlank(message="Nội dung không được trống")
	private String content;
	
	private int write_by;
	
	public Rule() {
		// TODO Auto-generated constructor stub
	}

	public Rule(int id, String content, int write_by) {
		super();
		this.id = id;
		this.content = content;
		this.write_by = write_by;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWrite_by() {
		return write_by;
	}

	public void setWrite_by(int write_by) {
		this.write_by = write_by;
	}
}
