package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeGUIDB extends Application {
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
