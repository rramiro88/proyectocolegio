package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.proyectocolegio.MainApp;
import screensframework.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import screensframework.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author ramiro
 */
public class EscritorioController implements Initializable, ControlledScreen{
    
    ScreensController myController;
    @FXML
    private ImageView imagenNuevoAlumno;
    @FXML
    private ImageView imagenPagarCuota;
    @FXML
    private ImageView imagenImprimirRecibo;
    @FXML
    private ImageView imagenMopdificarAlumno;
    @FXML
    private TabPane AnchorPane;
    @FXML
    private ImageView imagenSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Tooltip.install(imagenNuevoAlumno, new Tooltip("Crear un alumno nuevo"));
        Tooltip.install(imagenImprimirRecibo, new Tooltip("Imprimir un recibo"));
        Tooltip.install(imagenPagarCuota, new Tooltip("Pagar una cuota"));
        Tooltip.install(imagenMopdificarAlumno, new Tooltip("Modificar los datos de un alumno"));
        Tooltip.install(imagenSalir, new Tooltip("Salir de la aplicacion"));
        
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void nuevoAlumno(MouseEvent event) {
        
        myController.setScreen(MainApp.nuevoAlumno);
    }

    @FXML
    private void pagarCuota(MouseEvent event) {
        myController.setScreen(MainApp.pagarCuota);
    }

    @FXML
    private void imprimirRecibo(MouseEvent event) {
        System.out.println("Imprimir Recibo");
    }

    @FXML
    private void modificarAlumno(MouseEvent event) {
        myController.setScreen(MainApp.buscarAlumno);
    }

    @FXML
    private void salir(MouseEvent event) {

        System.exit(0);
    }

    
}
