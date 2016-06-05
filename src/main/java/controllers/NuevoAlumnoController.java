/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.proyectocolegio.MainApp;
import dao.DAOGeneral;
import entidades.Alumno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
        radioManana.setSelected(true);

        //seteo de comboBoxes
        ObservableList<String> niveles
                = FXCollections.observableArrayList(
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
        comboNivel.setValue("1° Grado");
        
        ObservableList<String> divisiones
                = FXCollections.observableArrayList(
                        "Rojo",
                        "Azul",
                        "A",
                        "B"
                );
        comboDivision.setItems(divisiones);
        comboDivision.setValue("Rojo");
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void volverAlInicio(ActionEvent event) {
        myController.setScreen(MainApp.escritorio);
    }

    @FXML
    private void crearAlumno(ActionEvent event) {
        DAOGeneral dao = new DAOGeneral();
        
        Alumno alumnoACrear = new Alumno();
        alumnoACrear.setDivision(comboDivision.getValue());
        alumnoACrear.setNivel(comboNivel.getValue());
        alumnoACrear.setNombreYApellido(textoNombreYApellido.getText());
        if(radioManana.isSelected()){
            alumnoACrear.setTurno("Mañana");
        }else{
            alumnoACrear.setTurno("Tarde");
        }
        
            
        System.out.println(alumnoACrear.getNombreYApellido());
        System.out.println(alumnoACrear.getDivision());
        System.out.println(alumnoACrear.getNivel());
        System.out.println(alumnoACrear.getTurno());
        
        dao.guardarAlumno(alumnoACrear);
        
        
    }

}
