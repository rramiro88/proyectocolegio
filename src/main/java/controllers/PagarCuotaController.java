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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import logica.Logica;
import screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class PagarCuotaController implements Initializable, screensframework.ControlledScreen {

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
    @FXML
    private TableColumn<Alumno, String> columnaId;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabAlumnos;
    @FXML
    private Tab tabPagos;
    @FXML
    private ComboBox<String> comboMes;
    @FXML
    private ComboBox<String> comboConcepto;
    @FXML
    private TextField textoAlumnoSeleccionado;
    @FXML
    private TextField textoMonto;
    @FXML
    private ImageView imagenPagar;
    @FXML
    private Label etiquetaPagar;

    @FXML
    private ComboBox<String> comboAnio;
    @FXML
    private Pane panelImpresion;
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
    @FXML
    private Label etiquetaMes;
    @FXML
    private Label etiquetaAnio;
    @FXML
    private Label etiquetaMes1;
    @FXML
    private Label etiquetaAnio1;
    @FXML
    private TableColumn<Alumno, String> columnaUltimoPago;
    @FXML
    private Label etiquetaCurso;
    @FXML
    private Label etiquetaCurso1;

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
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaUltimoPago.setCellValueFactory(new PropertyValueFactory<>("ultimoPago"));

        //se indica a la tabla que datos mostrar.
        tablaAlumnos.setItems(data);

        ObservableList<String> conceptos
                = FXCollections.observableArrayList(
                        "Colaboracion Mensual",
                        "Seguro",
                        "Materiales",
                        "Colaboracion Inicial"
                );
        comboConcepto.setItems(conceptos);
        comboConcepto.setValue("Colaboracion Mensual");

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

        tabPagos.setDisable(true);
        imagenPagar.setDisable(true);
        etiquetaPagar.setDisable(true);

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
        
        for(Alumno a : resultado){
            a.obtenerUltimoPago();
        }
        
        
        
        

        data.addAll(resultado);

    }

    @FXML
    private void volverAlInicio(ActionEvent event) {
        data.clear();
        tabAlumnos.setDisable(false);
        tabPagos.setDisable(true);
        tabPane.getSelectionModel().select(0);
        imagenPagar.setDisable(true);
        etiquetaPagar.setDisable(true);
        myController.setScreen(MainApp.escritorio);
        
        textoNombre.setText("");
        textoId.setText("");
        textoMonto.setText("");
        textoAlumnoSeleccionado.setText("");
    }

    @FXML
    private void pagarCuota(MouseEvent event) {

        try {

            Pago pago = new Pago();

            //Seteo los campos de la tabla
            pago.setConcepto(comboConcepto.getValue().trim());
            pago.setFechaDePago(Date.valueOf(LocalDate.now()));
            pago.setMonto(Integer.valueOf(textoMonto.getText().trim()));
            pago.setMes(comboMes.getValue().trim());
            pago.setAnio(comboAnio.getValue().trim());

            int indice = tablaAlumnos.getSelectionModel().getSelectedIndex();
            Alumno alumnoPagador = data.get(indice);

            Logica logica = new Logica();

            //pago.setAlumno(alumnoPagador);
            //logica.guardarPago(pago);
            Alert dialogoConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoConfirmacion.initModality(Modality.WINDOW_MODAL);
            dialogoConfirmacion.setTitle("Confirmar Pago");
            dialogoConfirmacion.setContentText("¿Está seguro que desea registrar este pago?");

            Optional<ButtonType> respuestaDialogo = dialogoConfirmacion.showAndWait();

            if (respuestaDialogo.get() == ButtonType.OK
                    && alumnoPagador.getPagos().add(pago)
                    && logica.actualizarAlumno(alumnoPagador)) {

                Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                dialogo.initModality(Modality.WINDOW_MODAL);
                dialogo.setHeaderText("Informacion");
                dialogo.setContentText("Colaboracion registrada correctamente.");
                dialogo.show();

                //Seteo los campos a imprimir
                etiquetaConcepto.setText(pago.getConcepto());
                etiquetaImporte.setText(String.valueOf(pago.getMonto()));
                etiquetaNombre.setText(textoNombre.getText().trim());
                etiquetaNumeroRecibo.setText(String.valueOf(pago.getId()));
                etiquetaAnio.setText(String.valueOf(pago.getAnio()));
                etiquetaMes.setText(pago.getMes());
                etiquetaCurso.setText(alumnoPagador.getNivel() + " " + alumnoPagador.getDivision());
                etiquetaConcepto1.setText(pago.getConcepto());
                etiquetaImporte1.setText(String.valueOf(pago.getMonto()));
                etiquetaNombre1.setText(textoNombre.getText().trim());
                etiquetaNumeroRecibo1.setText(String.valueOf(pago.getId()));
                etiquetaAnio1.setText(String.valueOf(pago.getAnio()));
                etiquetaMes1.setText(pago.getMes());
                etiquetaCurso1.setText(alumnoPagador.getNivel() + " " + alumnoPagador.getDivision());
                
                tabAlumnos.setDisable(false);
                tabPagos.setDisable(true);
                tabPane.getSelectionModel().select(0);
                imagenPagar.setDisable(true);
                etiquetaPagar.setDisable(true);
                data.clear();

                panelImpresion.setVisible(true);

                try {
                    print(panelImpresion);
                } catch (Exception e) {
                    System.out.println("error en impresion");
                }

                panelImpresion.setVisible(false);

                pago = null;

            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error al registrar el pago.");
            e.printStackTrace();
        }

    }

    @FXML
    private void seleccionarAlumno(MouseEvent event) {

        int indice = tablaAlumnos.getSelectionModel().getSelectedIndex();

        if (indice > -1) {
            Alumno alumno = data.get(indice);

            textoAlumnoSeleccionado.setText(alumno.getNombreYApellido());

            tabPagos.setDisable(false);
            tabPane.getSelectionModel().select(1);
            tabAlumnos.setDisable(true);
            imagenPagar.setDisable(false);
            etiquetaPagar.setDisable(false);
            textoNombre.setText(alumno.getNombreYApellido());
            textoId.setText(String.valueOf(alumno.getId()));
        }

    }

    public void print(final Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

        PrinterJob job = PrinterJob.createPrinterJob();

        job.setPrinter(printer);
        //job.showPageSetupDialog(null);
        //job.showPrintDialog(null);
        job.getJobSettings().setJobName("Recibo");

        if (job != null) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                System.out.println("Impresion aparentemente exitosa");
            }
        }
    }

}
