package core;

import java.util.ArrayList;
import java.util.List;

public class HRM {
	private List<Human> hrList;

	public HRM() {

		// Khởi tạo đối tượng hrList là một ArrayList<Human>
		hrList = new ArrayList<Human>();
		// Gọi phương thức khởi tạo dữ liệu cho hrList
		initDemoData();
	}

	// Thêm một Human vào hrList (Human có thể là Student, Lecturer)
	public void addHR(Human hm) {
		hrList.add(hm);
	}

	// Trả về danh sách nhân sự
	public List<Human> getHrList() {
		return hrList;
	}

	// Trả về danh sách sinh viên lọc ra từ ds nhân sự
	public List<Student> getStudentList() {
		List<Student> studentList = new ArrayList<Student>();
		// Duyệt ds nhân sự
		for (Human hm : hrList) {
			if (hm instanceof Human) {// Kiểm tra nếu đúng kiểu sinh viên
				studentList.add((Student) hm); // Thêm vào ds sinh viên
			}
		}
		return studentList;
	}

	// Kiểm tra một nhân sự có trong danh sách ko
	public boolean checkHR(Human user) {
		for (Human hm : hrList) {
			// Phương thức equals hoạt động theo quy tắc đa hình
			// tùy vào user là Student hay Lecturer
			if (user.equals(hm)) {
				return true;
			}
		}
		return false;
	}

	public void initDemoData() {
		Student std1 = new Student("634593", "Nguyen Van Hai", "K63THA");
		// Human std1 = new Student()
		Student std2 = new Student("611289", "Dinh Quang Huy", "K61CNPMP");
		Lecturer lec1 = new Lecturer("tot01", "Tran Trung Hieu", "Khoa CNTT");
		lec1.setPassword("123");
		Lecturer lec2 = new Lecturer("tot09", "Ngo Trong Kien", "Khoa Thu Y");
		lec2.setPassword("123");
		addHR(std2);
		addHR(lec1);
		addHR(lec2);
		addHR(std1);
	}

	// Test thử hoạt động của các lớp Human, Student, Lecturer, HRM vừa tạo
	public static void main(String[] args) {
		HRM hrm = new HRM();
		// Gọi getHrList() lấy về danh sách
		List<Human> hrList = hrm.getHrList();
		// Duyệt và in thử dữ liệu chứa trong danh sách
		for (Human hm : hrList) {
			System.out.println(hm.code);
			// hrList.forEach(System.out::println);
		}
	}
}
