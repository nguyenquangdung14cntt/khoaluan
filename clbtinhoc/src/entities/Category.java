package entities;

import org.hibernate.validator.constraints.NotBlank;

public class Category {
	private int id;
	@NotBlank(message="Tên danh mục không được trống")
	private String name_cat;
	public Category() {
		// TODO Auto-generated constructor stub
	}
	public Category(int id, String name_cat) {
		super();
		this.id = id;
		this.name_cat = name_cat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_cat() {
		return name_cat;
	}
	public void setName_cat(String name_cat) {
		this.name_cat = name_cat;
	}
	
}
