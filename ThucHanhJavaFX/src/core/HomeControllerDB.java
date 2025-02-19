package core;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeControllerDB {
	@FXML
	private TableView<Student> table = new TableView<Student>();
	@FXML
	private TextField textCode;
	@FXML
	private TextField textName;
	@FXML
	private TextField textClass;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Label message;
	// Tao doi tuong tang Model
	HRMDB hrmDB = new HRMDB("jdbc:ucanaccess://‪D:\\ProjectJava\\ThucHanhJavaFX\\hrm.accdb", "", "");

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
		// run later để nhận được các giá trị role, userName truyền sang
		Platform.runLater(() -> {
			// Lấy về các cột 0 của bảng và gắn với thuộc tính "code" tương ứng trong đối
			// tượng sinh viên
			TableColumn<Student, String> studentCodeColumn = (TableColumn<Student, String>) table
					.getVisibleLeafColumn(0);

			studentCodeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("code"));

			// Gắn với thuộc tính fullname
			TableColumn<Student, String> fullnameColumn = (TableColumn<Student, String>) table.getVisibleLeafColumn(1);

			fullnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fullname"));

			// Gắn với thuộc tính class_
			TableColumn<Student, String> classColumn = (TableColumn<Student, String>) table.getVisibleLeafColumn(2);

			classColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("class_"));

			// Lấy danh sách sinh viên
			List<Student> studentList = hrmDB.getStudentList();

			// Chuyển kiểu cho danh sách sinh viên sang dạng ObservableList có thể đưa vào
			// tableview
			ObservableList<Student> obsStudentList = FXCollections.observableArrayList(studentList);

			// Gán dữ liệu cho tableview
			table.setItems(obsStudentList);
		});
	}

	// Xử lý sự kiện nút bấm Add sau khi nhập dữ liệu vào 3 ô
	@SuppressWarnings("unckecked")
	public void onClickAddButton() {
		// Tạo đối tượng sinh viên từ 3 ô dữ liệu nhập vào
		Student std = new Student(textCode.getText(), textName.getText(), textClass.getText());

		// Thêm vào danh sách dữ liệu của tableview
		boolean addResult = hrmDB.addStudent(std);
		if (addResult) {
			table.getItems().add(std);
			System.out.println("Add successfull!");
			message.setText("Add successfull!");
		} else {
			System.out.println("Error add to database!");
			message.setText("Error add to database!!");
		}
	}

	// Xử lý sự kiện bấm nút Update sau khi sửa dữ liệu tương ứng với một dòng được
	// chọn trên tableview
	public void onClickUpdateButton() {
		// Lấy về chỉ số dòng đang được chọn trên bảng
		int selectedIndex = table.getSelectionModel().getSelectedIndex();

		// Lấy về đối tượng dữ liệu tương ứng với dòng được chọn
		Student selectedStudent = table.getItems().get(selectedIndex);

		// Thay đổi dữ liệu trong đối tượng sinh viên được chọn
		selectedStudent.setCode(textCode.getText());
		selectedStudent.setFullname(textName.getText());
		selectedStudent.setClass_(textClass.getText());

		// Cập nhật lại đối tượng dữ liệu tại vị trí được chọn để hiển thị lên bảng
		if (selectedIndex >= 0) {
			boolean updateResult = hrmDB.updateStudent(selectedStudent);
			if (updateResult) {
				table.getItems().set(selectedIndex, selectedStudent);
				System.out.println("Update successfull!");
				message.setText("Update successfull!");
			} else {
				System.out.println("Error update to database!");
				message.setText("Error update to database!");
			}
		}
	}

	// Xử lý sự kiện bấm nút xóa 1 dòng được chọn trên bảng
	public void onClickDeleteButton() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		Student std = table.getItems().get(selectedIndex);
		if (selectedIndex >= 0) {
			boolean deleteResult = hrmDB.deleteStudent(std.getCode());
			if (deleteResult) {
				table.getItems().remove(selectedIndex);
				System.out.println("Delete successfull!");
				message.setText("Delete successfull!");
			} else {
				System.out.println("Error delete to database!");
				message.setText("Error delete to database!");
			}
		}
	}

	// Xử lý sự kiện click chọn 1 dòng trên bảng
	public void onClickRow() {
		// Lấy dữ liệu từ dòng được chọn gán vào các ô text tương ứng bên dưới
		textCode.setText(table.getSelectionModel().getSelectedItem().getCode());
		textName.setText(table.getSelectionModel().getSelectedItem().getFullname());
		textClass.setText(table.getSelectionModel().getSelectedItem().getClass_());
	}

}
