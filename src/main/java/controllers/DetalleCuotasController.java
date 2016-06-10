/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.proyectocolegio.MainApp;
import entidades.Alumno;
import entidades.Pago;
import java.net.URL;
import java.util.Date;
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
import logica.Logica;
import screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class DetalleCuotasController implements Initializable, screensframework.ControlledScreen {

    @FXML
    private TableView<Pago> tablaCuotas;
    @FXML
    private TableColumn<Pago, Date> columnaFechaPago;
    @FXML
    private TableColumn<Pago, String> columnaMes;
    @FXML
    private TableColumn<Pago, String> columnaConcepto;
    @FXML
    private TableColumn<Pago, String> columnaMonto;
    @FXML
    private TextField textoNombreYApellido;
    @FXML
    private TextField textoId;

    ObservableList<Pago> data;

    Alumno alumno;

    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        columnaConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        columnaFechaPago.setCellValueFactory(new PropertyValueFactory<>("fechaDePago"));
        columnaMes.setCellValueFactory(new PropertyValueFactory<>("mes"));
        columnaMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));

        data = FXCollections.observableArrayList();

        tablaCuotas.setItems(data);

    }

    @FXML
    private void buscarCuotas(ActionEvent event) {

        Logica logica = new Logica();

        try {
            alumno = logica.obtenerAlumnos(textoNombreYApellido.getText().trim(), textoId.getText().trim()).get(0);
            data.addAll(alumno.getPagos());
            textoId.setText(String.valueOf(alumno.getId()));
            textoNombreYApellido.setText(alumno.getNombreYApellido());
        } catch (Exception e) {
            
        }

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void volverAlInicio(ActionEvent event) {
        data.clear();
        myController.setScreen(MainApp.escritorio);
    }

}
