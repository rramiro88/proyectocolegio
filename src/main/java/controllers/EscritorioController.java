package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.proyectocolegio.MainApp;
import screensframework.ScreensController;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logica.Logica;
import screensframework.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author ramiro
 */
public class EscritorioController implements Initializable, ControlledScreen{
    
    ScreensController myController;
    
    ToggleGroup tg;
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
    @FXML
    private TextField textoTotalMes;
    @FXML
    private TextField textoTotalAnio;
    @FXML
    private TextField textoTotalDia;
    @FXML
    private DatePicker pickerFecha;
    @FXML
    private ComboBox<String> comboMes;
    @FXML
    private ComboBox<String> comboAnio;
    @FXML
    private RadioButton radioManana;
    @FXML
    private RadioButton radioTarde;
    @FXML
    private ImageView imagenNuevoAlumno1;

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
        
        //seteo de radioButtons
        tg = new ToggleGroup();
        radioManana.setToggleGroup(tg);
        radioTarde.setToggleGroup(tg);
        
        ObservableList<String> anios
                = FXCollections.observableArrayList(
                        "2016",
                        "2017",
                        "2018",
                        "2019",
                        "2020",
                        "2021",
                        "2022",
                        "2023",
                        "2024",
                        "2025",
                        "2026");

        comboAnio.setItems(anios);
        comboAnio.setValue(String.valueOf(LocalDate.now().getYear()));

        ObservableList<String> meses
                = FXCollections.observableArrayList(
                        "Enero",
                        "Febrero",
                        "Marzo",
                        "Abril",
                        "Mayo",
                        "Junio",
                        "Julio",
                        "Agosto",
                        "Septiembre",
                        "Octubre",
                        "Noviembre",
                        "Diciembre"
                );

        comboMes.setItems(meses);
        comboMes.setValue(meses.get(LocalDate.now().getMonthValue() - 1));
        
        pickerFecha.setValue(LocalDate.now());
        
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
        myController.setScreen(MainApp.detalleCuota);
    }

    @FXML
    private void modificarAlumno(MouseEvent event) {
        myController.setScreen(MainApp.buscarAlumno);
    }

    @FXML
    private void salir(MouseEvent event) {

        System.exit(0);
    }

    @FXML
    private void calcularTotales(ActionEvent event) {
        
        
        Logica logica = new Logica();
        textoTotalDia.setText(logica.obtenerTotalDia(pickerFecha.getValue()));
        
        
        textoTotalMes.setText(logica.obtenerTotalMes(comboMes.getValue(),comboAnio.getValue()));
        
        textoTotalAnio.setText(logica.obtenerTotalAnio(comboAnio.getValue()));
        
        
        
        
        
        
    }

    @FXML
    private void calcularDeuda(MouseEvent event) {
        
        myController.setScreen(MainApp.calcularDeuda);
        
        
        
    }

    
}
