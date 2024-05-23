package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;

public class App extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    InputStream fxmlStream = getClass().getResourceAsStream("/formulario.fxml");
    if (fxmlStream == null) {
      throw new RuntimeException("No se pudo encontrar el archivo FXML.");
    }

    FXMLLoader fxmlLoader = new FXMLLoader();
    Parent root = fxmlLoader.load(fxmlStream);


    stage.setTitle("Control de Stock de Almacén");
    stage.setResizable(false);
    Scene scene = new Scene(root, 700, 450);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
