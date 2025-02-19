package core;

public class Lecturer extends Human {
	// Khai báo thuộc tính riêng password
	private String password;
	private String lecturerCode;

	public Lecturer() {
	}

	public Lecturer(String lecturerCode, String fullname) {
		this.lecturerCode = lecturerCode;
		this.fullname = fullname;
	}

	public Lecturer(String lecturerCode, String fullname, String address) {
		super(lecturerCode, fullname, address);
	}

	// Phương thức get, set ứng với thuộc tính password
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Ghi đè phương thức equals của lớp Object kiểm tra 2 đối tượng Lecturer
	// có tương đương nhau không (Có là một ko)
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Lecturer) {// Kiem tra kieu cua doi tuong obj
			Lecturer lecturer = (Lecturer) obj;
			// Neu ma code vs password tuong duong nhau
			if (code.equals(lecturer.getCode()) && password.equals(lecturer.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
