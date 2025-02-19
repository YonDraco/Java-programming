package core;

public class Student extends Human {
	private String class_;

	// constructor 1
	public Student() {
	}

	public Student(String studentCode) {
		this.code = studentCode;
	}

	// constructor 2
	public Student(String studentCode, String fullname, String class_) {
		this.code = studentCode;
		this.fullname = fullname;
		this.class_ = class_;
	}

	// constructor 3
	public Student(String studentCode, String fullname, String class_, String address) {
		super(studentCode, fullname, address);
		this.class_ = class_;
	}

	// Hàm kiểm tra 2 sinh viên có là một không
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			Student student = (Student) obj;
			if (code.equals(student.code)) {
				return true;
			}
		}
		return false;
	}

	// Phương thức get, set cho thuộc tính class_
	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}
}
