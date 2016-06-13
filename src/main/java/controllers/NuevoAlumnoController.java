/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.proyectocolegio.MainApp;
import entidades.Alumno;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import logica.Logica;
import screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class NuevoAlumnoController implements Initializable, screensframework.ControlledScreen {

    ScreensController myController;

    ToggleGroup tg;
    @FXML
    private RadioButton radioManana;
    @FXML
    private RadioButton radioTarde;
    @FXML
    private ComboBox<String> comboNivel;
    @FXML
    private ComboBox<String> comboDivision;
    @FXML
    private TextField textoNombreYApellido;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //seteo de radioButtons
        tg = new ToggleGroup();
        radioManana.setToggleGroup(tg);
        radioTarde.setToggleGroup(tg);
        

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
        
        
        this.restablecerCampos();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void volverAlInicio(ActionEvent event) {
        myController.setScreen(MainApp.escritorio);
        restablecerCampos();
    }

    @FXML
    private void crearAlumno(ActionEvent event) {
        Logica logica = new Logica();

        Alumno alumnoACrear = new Alumno();
        alumnoACrear.setDivision(comboDivision.getValue());
        alumnoACrear.setNivel(comboNivel.getValue());

        if (radioManana.isSelected()) {
            alumnoACrear.setTurno("Mañana");
        } else {
            alumnoACrear.setTurno("Tarde");
        }

        if ("".equals(textoNombreYApellido.getText().trim())) {
            TextInputDialog dialog = new TextInputDialog("Nombre y Apellido");
            dialog.setTitle("Complete el nombre");
            dialog.setHeaderText("Dato faltante");
            dialog.setContentText("Ingrese el nombre del alumno");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                textoNombreYApellido.setText(result.get());
                alumnoACrear.setNombreYApellido(textoNombreYApellido.getText());
            }

        } else {

            alumnoACrear.setNombreYApellido(textoNombreYApellido.getText());

            if (logica.guardarAlumno(alumnoACrear)) {

                Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                dialogo.setHeaderText("Informacion");
                dialogo.setContentText("Alumno creado con exito");
                dialogo.show();
                

            }else {
                Alert dialogo = new Alert(Alert.AlertType.ERROR);
                dialogo.setHeaderText("Informacion");
                dialogo.setContentText("No se pudo guardar el alumno");
                dialogo.show();
            }

        }

    }
    
    private void restablecerCampos(){
        
         comboNivel.setValue("1° Grado");
         comboDivision.setValue("Rojo");
         textoNombreYApellido.setText("");
         radioManana.setSelected(true);
         
    }

}
