package gui;

import core.Human;
import core.Lecturer;
import core.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;

public class LoginController {
	/*
	 * Khai báo các thành phần cần quản lý trên LoginSence.fxml
	 */
	@FXML
	private RadioButton studentRadio;
	@FXML
	private ToggleGroup group;
	@FXML
	private RadioButton lecturerRadio;
	@FXML
	private TextField userNameTF;
	@FXML
	private PasswordField passwordTF;
	@FXML
	private Label userNameEmpty;
	@FXML
	private Label passwordEmpty;
	@FXML
	private Label invalidLogin;
	@FXML
	private Button loginButton;
	@FXML
	private Button cancelButton;

	// Tạo đối tượng dịch vụ xác thực
	private AuthenticationService authService = new AuthenticationServiceImpl();

	// Xử lý sự kiện click nút Login
	public void onClickLogin(ActionEvent event) {
		// Xử lý nút enter
		loginButton.setDefaultButton(true);
		// Nếu dữ liệu nhập vào form đã hợp lệ
		if (validateForm()) {
			Human user;
			// Nếu trường password bị disable, User là giảng viên
			if (!passwordTF.isDisable()) {
				// user = new Lecturer(userNameTF.getText(),);
				user = new Lecturer(userNameTF.getText(), passwordTF.getText(), null);
			} else {
				// user = new Student(userNameTF.getText(),);
				user = new Student(userNameTF.getText(), "", "");
			}
			// Nếu login thành công
			if (authService.login(user)) {
				// Ẩn trang đăng nhập
				((Node) (event.getSource())).getScene().getWindow().hide();
				// Lấy về lựa chọn hiện tại và quy ước quyền sinh viên (role = 0), giảng viên
				// (role = 1)
				int role = group.getSelectedToggle().equals(studentRadio) ? 0 : 1;
				// truyền role và username vào trang chủ
				showHomeGUI(role, userNameTF.getText());
			} else { // Nếu đăng nhập ko thành công, gán lỗi vào trường ẩn
				invalidLogin.setText("username or password is invalid!");
			}
		}
	}

	// Xử lý sự kiện click nút Cancel
	public void onClickCancel(ActionEvent event) {
		// Xử lý nút enter
		cancelButton.setCancelButton(true);
		// Ẩn trang đăng nhập
		passwordTF.getScene().getWindow().hide();
	}

	// Xử lý sự kiện thay đổi lựa chọn loại user
	public void radioButtonChanged() {
		// Nếu chọn loại user là sinh viên
		if (group.getSelectedToggle().equals(studentRadio)) {
			// Vô hiệu hóa trường password
			passwordTF.setDisable(true);
		} else {
			// Enable trường password
			passwordTF.setDisable(false);
		}
	}

	// Kiểm chứng tính hợp lệ của các trường trong FORM
	private boolean validateForm() {
		boolean result = true;
		// Reset các nhãn messge ẩn trên form
		resetMessage();
		// Nếu chưa nhập trường username
		if ("".equals(userNameTF.getText())) {
			userNameEmpty.setText("Username is empty!");
			result = false;
		}
		// Nếu trường password đang enable và chưa nhập
		if ((!passwordTF.isDisabled()) && ("".equals(passwordTF.getText()))) {
			passwordEmpty.setText("Password is empty!");
			result = false;
		}
		return result;
	}

	// Reset các nhãn messge ẩn trên form
	public void resetMessage() {
		userNameEmpty.setText("");
		passwordEmpty.setText("");
		invalidLogin.setText("");
	}

	// Truyền dữ liệu cho HomeController và hiển thị màn hình trang chủ
	public void showHomeGUI(int role, String userName) {
		try {
			// Tạo FXMLLoader tương ứng HomeScene.fxml
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScene.fxml"));
			// Lấy về đối tượng root layout
			Parent root = (Parent) fxmlLoader.load();
			// Lấy về đối tượng HomeController từ fxmlLoader
			HomeController controller = fxmlLoader.getController();
			// Truyền dữ liệu vào đối tượng controller
			// Lớp HomeController cần khai báo thêm 2 thuộc tính role,
			// username và phương thức get, set tương ứng
			// controller.setRole(?);
			controller.setRole(role);
			// controller.setUserName(?);
			controller.setUserName(userNameTF.getText());

			// Tạo Stage, Scene từ root
			Stage homeStage = new Stage();
			homeStage.setTitle("Home");
			homeStage.setScene(new Scene(root));
			homeStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
