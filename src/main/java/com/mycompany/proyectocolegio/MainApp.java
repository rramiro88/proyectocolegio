package com.mycompany.proyectocolegio;

import controllers.ScreensController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainApp extends Application {
    
    public static String screen1ID = "main";
    public static String screen1File = "/fxml/Login.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "/fxml/Escritorio.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "Screen3.fxml";

    @Override
    public void start(Stage stage) throws Exception {
       ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(screen1ID, screen1File);
        mainContainer.loadScreen(screen2ID, screen2File);
        mainContainer.loadScreen(screen3ID, screen3File);
        
        mainContainer.setScreen(screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
