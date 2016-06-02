/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.proyectocolegio.MainApp;
import entidades.Alumno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class BuscarAlumnoController implements Initializable, screensframework.ControlledScreen {

    ObservableList<Alumno> data;

    ScreensController myController;
    @FXML
    private TableView<Alumno> tablaAlumnos;
    @FXML
    private TextField nombreAlumno;
    @FXML
    private TextField idAlumno;
    @FXML
    private TableColumn<Alumno, String> columnaNombre;
    @FXML
    private TableColumn<Alumno, String> columnaAccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        Alumno a = new Alumno();
        a.setNombre("Ramiro");
        
        Alumno b = new Alumno();
        b.setNombre("Alumno dos");
        
        data = FXCollections.observableArrayList(a,b);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaAlumnos.setItems(data);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void buscarAlumnos(ActionEvent event) {
        
        int indice = tablaAlumnos.getSelectionModel().getSelectedIndex();
        //data.remove(indice);
        
        
        System.out.println(indice);

    }

    @FXML
    private void volverAlInicio(ActionEvent event) {
        myController.setScreen(MainApp.escritorio);
    }

}
