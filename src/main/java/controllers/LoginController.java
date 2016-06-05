package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import screensframework.ScreensController;
import com.mycompany.proyectocolegio.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sun.awt.X11.Screen;

/**
 * FXML Controller class
 *
 * @author ramiro
 */
public class LoginController implements Initializable, screensframework.ControlledScreen {

    ScreensController myController;

    @FXML
    private Button botonIniciarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
        
        
        
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {

        myController.setScreen(MainApp.escritorio);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
