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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import logica.Logica;
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
    private TableColumn<Alumno, String> columnaNombre;
    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoId;
    @FXML
    private TableColumn<Alumno, String> columnaNivel;
    @FXML
    private TableColumn<Alumno, String> columnaDivision;
    @FXML
    private TableColumn<Alumno, String> columnaTurno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data = FXCollections.observableArrayList();

        //Acá se coloca el nombre del campo de la entidad que se quiere mostrar en la tabla.
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreYApellido"));
        columnaDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
        columnaTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        columnaNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));

        //se indica a la tabla que datos mostrar.
        tablaAlumnos.setItems(data);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void buscarAlumnos(ActionEvent event) {

//        int indice = tablaAlumnos.getSelectionModel().getSelectedIndex();
//        //data.remove(indice);
//        
//        
//        System.out.println(indice);
        data.clear();

        Logica logica = new Logica();
        List<Alumno> resultado = logica.obtenerAlumnos(textoNombre.getText().trim(), textoId.getText().trim());

        data.addAll(resultado);

    }

    @FXML
    private void volverAlInicio(ActionEvent event) {
        data.clear();
        myController.setScreen(MainApp.escritorio);
    }

    @FXML
    private void editarAlumno(MouseEvent event) {

//        columnaNombre.setCellFactory(TextFieldTableCell.<Alumno>forTableColumn());
        int indice = tablaAlumnos.getSelectionModel().getSelectedIndex();

        if (indice > -1) {
            MainApp.alumnoAEditar = data.get(indice);

            System.out.println(MainApp.alumnoAEditar.getNombreYApellido());

            data.clear();
            myController.setScreen(MainApp.editarAlumno);
        }

    }

    @FXML
    private void eliminarAlumno(MouseEvent event) {
        int indice = tablaAlumnos.getSelectionModel().getSelectedIndex();

        if (indice > -1) {
            Alumno alumnoAEliminar = data.get(indice);
            Logica logica = new Logica();
            logica.eliminarAlumno(alumnoAEliminar);

            Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText("Informacion");
            dialogo.setContentText("Alumno eliminado con exito");
            dialogo.show();

            data.remove(indice);
        }

    }

}
