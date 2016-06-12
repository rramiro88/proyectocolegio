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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PaperSource;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane panelImpresion;
    private Label etiquetaNombreImpresion;
    @FXML
    private Label etiquetaNumeroRecibo;
    @FXML
    private Label etiquetaConcepto;
    @FXML
    private Label etiquetaImporte;
    @FXML
    private Label etiquetaNombre;
    @FXML
    private Label etiquetaNumeroRecibo1;
    @FXML
    private Label etiquetaConcepto1;
    @FXML
    private Label etiquetaImporte1;
    @FXML
    private Label etiquetaNombre1;

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

        data.clear();
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

    public void print(final Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A5, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        
        
        
        

        PrinterJob job = PrinterJob.createPrinterJob();
        
        
        
        job.setPrinter(printer);
        //job.showPageSetupDialog(null);
        //job.showPrintDialog(null);
        job.getJobSettings().setJobName("Recibo");
        
        if (job != null) {
            boolean success = job.printPage(pageLayout,node);
            if (success) {
                job.endJob();
                System.out.println("Impresion aparentemente exitosa");
            }
        }
    }

    @FXML
    private void imprimirRecibo(MouseEvent event) {
        
        etiquetaConcepto.setText(data.get(0).getConcepto());
        etiquetaImporte.setText(String.valueOf(data.get(0).getMonto()));
        etiquetaNumeroRecibo.setText(String.valueOf(data.get(0).getId()));
        etiquetaNombre.setText(textoNombreYApellido.getText());
        
        etiquetaConcepto1.setText(data.get(0).getConcepto());
        etiquetaImporte1.setText(String.valueOf(data.get(0).getMonto()));
        etiquetaNumeroRecibo1.setText(String.valueOf(data.get(0).getId()));
        etiquetaNombre1.setText(textoNombreYApellido.getText());
        
        
        
        panelImpresion.setVisible(true);
        print(panelImpresion);
        panelImpresion.setVisible(false);
    }
}
