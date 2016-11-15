/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.proyectocolegio.MainApp;
import entidades.Alumno;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.Logica;
import screensframework.ControlledScreen;
import screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author ramiro
 */
public class CalcularDeudaController implements Initializable, ControlledScreen {
    
    Logica logica = new Logica();

    ScreensController myController;
    
    ObservableList<Alumno> data;
    @FXML
    private TextField textoId;
    @FXML
    private TextField textoNombre;
    @FXML
    private TableColumn<Alumno, String> columnaNombre;
    @FXML
    private TableColumn<Alumno, String> columnaDeuda;
    @FXML
    private TableColumn<Alumno, String> columnaDivision;
    @FXML
    private TableColumn<Alumno, String> columnaCodigo;
    @FXML
    private TableView<Alumno> tablaAlumnos;
    @FXML
    private TableColumn<Alumno, String> columnaNivel;
    @FXML
    private ComboBox<String> comboNivel;
    @FXML
    private ComboBox<String> comboDivision;
    
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //seteo de comboBoxes
        ObservableList<String> niveles
                = FXCollections.observableArrayList(
                        "Preescolar",
                        "1° Grado",
                        "2° Grado",
                        "3° Grado",
                        "4° Grado",
                        "5° Grado",
                        "6° Grado",
                        "7° Grado",
                        "1° Año",
                        "2° Año",
                        "3° Año",
                        "4° Año",
                        "5° Año"
                );

        comboNivel.setItems(niveles);

        ObservableList<String> divisiones
                = FXCollections.observableArrayList(
                        "Unica",
                        "Rojo",
                        "Azul",
                        "A",
                        "B"
                );
        comboDivision.setItems(divisiones);
        
        
        
        
        data = FXCollections.observableArrayList();

        //Acá se coloca el nombre del campo de la entidad que se quiere mostrar en la tabla.
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreYApellido"));
        columnaDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        columnaDeuda.setCellValueFactory(new PropertyValueFactory<>("deuda"));

        
        
        //se indica a la tabla que datos mostrar.
        tablaAlumnos.setItems(data);
        
        
    }    
    
   

    @FXML
    private void volverAlInicio(ActionEvent event) {
        
        data.clear();
        myController.setScreen(MainApp.escritorio);
        
    }

    @FXML
    private void buscarAlumnosDeudores(ActionEvent event) {
        data.clear();

        List<Alumno> resultado = logica.obtenerDeudores(textoNombre.getText().trim(), textoId.getText().trim(),comboNivel.getValue(), comboDivision.getValue());

        data.addAll(resultado);
    }

    
    
    
    
    
}
