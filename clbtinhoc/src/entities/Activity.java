package entities;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Activity {
	private int id;
	@NotBlank(message="Tên hoạt động không được trống")
	private String title;
	@NotBlank(message="Nội dung không được trống")
	private String content;
	@NotEmpty(message="Vui lòng chọn ngày bắt đầu")
	private String start_at;
	@NotEmpty(message="Vui lòng chọn ngày kết thúc")
	private String end_at;
	@Min(value=0,message="Vui lòng nhập phí hoạt động")
	private float fee;
	@Min(value=1,message="Vui lòng nhập số lượng giới hạn")
	private int limited;
	private int joined;
	private int status;
	public Activity() {
		// TODO Auto-generated constructor stub
	}
	public Activity(int id, String title, String content, String start_at, String end_at, float fee, int limited,
			int joined, int status) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.start_at = start_at;
		this.end_at = end_at;
		this.fee = fee;
		this.limited = limited;
		this.joined = joined;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStart_at() {
		return start_at;
	}
	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}
	public String getEnd_at() {
		return end_at;
	}
	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public int getLimited() {
		return limited;
	}
	public void setLimited(int limited) {
		this.limited = limited;
	}
	public int getJoined() {
		return joined;
	}
	public void setJoined(int joined) {
		this.joined = joined;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
