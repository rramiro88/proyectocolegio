/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import screensframework.ControlledScreen;
import screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author ramiro
 */
public class CalcularDeudaController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
