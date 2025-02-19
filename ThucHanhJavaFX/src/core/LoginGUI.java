//package gui;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class LoginGUI extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			// Tạo FXMLLoader tương ứng LoginLayout.fxml
//			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScene.fxml"));
//			// Lấy về đối tượng root layout
//			Parent root = (Parent) fxmlLoader.load();
//
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//}

package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeSceneDB.fxml"));
		Pane root = loader.load();
		Scene sc = new Scene(root);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Example");
		primaryStage.show();
	}
}
