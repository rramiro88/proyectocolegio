package com.mycompany.proyectocolegio;

import entidades.Alumno;
import screensframework.ScreensController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static String pantallaPrincipal = "main";
    public static String mainArchivoFXML = "/fxml/Login.fxml";
    public static String escritorio = "escritorio";
    public static String escritorioArchivoFXML = "/fxml/Escritorio.fxml";
    public static String nuevoAlumno = "nuevoalumno";
    public static String nuevoAlumnoArchivoFXML = "/fxml/NuevoAlumno.fxml";
    public static String buscarAlumno = "buscaralumno";
    public static String buscarAlumnoArchivoFXML = "/fxml/BuscarAlumno.fxml";
    public static String editarAlumno = "editaralumno";
    public static String editarAlumnoArchivoFXML = "/fxml/EditarAlumno.fxml";
    public static String pagarCuota = "pagarcuota";
    public static String pagarCuotaArchivoFXML = "/fxml/PagarCuota.fxml";
    public static String detalleCuota = "detallecuota";
    public static String detalleCuotaArchivoFXML = "/fxml/DetalleCuotas.fxml";
    public static String calcularDeuda = "calculardeuda";
    public static String calcularDeudaArchivoFXML = "/fxml/CalcularDeuda.fxml";
    
    public static Alumno alumnoAEditar;

    @Override
    public void start(Stage stage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(pantallaPrincipal, mainArchivoFXML);
        mainContainer.loadScreen(escritorio, escritorioArchivoFXML);
        mainContainer.loadScreen(nuevoAlumno, nuevoAlumnoArchivoFXML);
        mainContainer.loadScreen(buscarAlumno, buscarAlumnoArchivoFXML);
        mainContainer.loadScreen(editarAlumno, editarAlumnoArchivoFXML);
        mainContainer.loadScreen(pagarCuota, pagarCuotaArchivoFXML);
        mainContainer.loadScreen(detalleCuota, detalleCuotaArchivoFXML);
        mainContainer.loadScreen(calcularDeuda, calcularDeudaArchivoFXML);

        mainContainer.setScreen(pantallaPrincipal);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);

        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setTitle("Sistema de Gestión de Cobros - Colegio Nuestra Señora de Guadalupe");

//        stage.initStyle( StageStyle.UNDECORATED );
   

        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
