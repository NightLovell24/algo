package my.nalab6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/mainScene.fxml"));

		Scene scene = new Scene(loader.load(), 810, 240);

        primaryStage.getIcons().add(new Image("C:\\Users\\North\\eclipse-workspace\\NALab6\\src\\icon.jpg"));
        
		primaryStage.setTitle("General");
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
