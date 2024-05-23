package org.example.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.modelo.Producto;

public class Controlador {

  @FXML private Button bt_agregar;

  @FXML private Button bt_modificar;

  @FXML private TableColumn<Producto, String> identificador;

  @FXML private TableColumn<Producto, String> nomProducto;

  @FXML private TableColumn<Producto, String> stock;

  @FXML private TableView<Producto> tableView;

  @FXML private TextField tf_identificador;

  @FXML private TextField tf_nombreProducto;

  @FXML private TextField tf_stock;

  @FXML private Label error_Identificador;

  @FXML private Label error_NombreProducto;

  @FXML private Label error_Stock;

  private ObservableList<Producto> productoList;

  private Producto productoSeleccionado;
  private boolean enModificacion = false;

  @FXML
  private void initialize() {
    productoList = FXCollections.observableArrayList();
    tableView.setItems(productoList);
    identificador.setCellValueFactory(cellData -> cellData.getValue().identificadorProperty());
    nomProducto.setCellValueFactory(cellData -> cellData.getValue().nomProductoProperty());
    stock.setCellValueFactory(cellData -> cellData.getValue().stockProperty());
  }

  @FXML
  void agregar(ActionEvent event) {
    String id = tf_identificador.getText();
    String nombre = tf_nombreProducto.getText();
    String stock = tf_stock.getText();

    boolean exists = false;
    for (Producto producto : productoList) {
      if (producto.getIdentificador().equals(id)) {
        exists = true;
        break;
      }
    }

    boolean hasError = false;

    if (id.isEmpty()) {
      error_Identificador.setText("Error: identificador obligatorio.");
      error_Identificador.setStyle("-fx-text-fill: red;");
      hasError = true;
    } else if (exists) {
      error_Identificador.setText("Error: el identificador ya existe.");
      error_Identificador.setStyle("-fx-text-fill: red;");
      error_Stock.setText("");
      error_NombreProducto.setText("");
      hasError = true;
    } else if (!id.isEmpty() && !exists) {
      error_Identificador.setText("");
    }

    if (nombre.isEmpty()) {
      error_NombreProducto.setText("Error: nombre del producto obligatorio.");
      error_NombreProducto.setStyle("-fx-text-fill: red;");
      hasError = true;
    } else if (!nombre.isEmpty()) {
      error_NombreProducto.setText("");
    }

    if (stock.isEmpty()) {
      error_Stock.setText("Error: stock obligatorio.");
      error_Stock.setStyle("-fx-text-fill: red;");
      hasError = true;
    } else if (!stock.isEmpty()) {
      error_Stock.setText("");
    }

    if (!exists && !hasError) {
      Producto producto = new Producto(id, nombre, stock);
      productoList.add(producto);
      tf_identificador.clear();
      tf_nombreProducto.clear();
      tf_stock.clear();
      error_Identificador.setText("");
      error_NombreProducto.setText("");
      error_Stock.setText("");
    }
  }

  @FXML
  void modificar(ActionEvent event) {

    error_Identificador.setText("");
    error_NombreProducto.setText("");
    error_Stock.setText("");

    if (!enModificacion) {
      int indiceSeleccionado = tableView.getSelectionModel().getSelectedIndex();
      if (indiceSeleccionado != -1) {
        productoSeleccionado = tableView.getItems().get(indiceSeleccionado);
        tf_identificador.setText(productoSeleccionado.getIdentificador());
        tf_nombreProducto.setText(productoSeleccionado.getNomProducto());
        tf_stock.setText(productoSeleccionado.getStock());
        enModificacion = true;
        bt_modificar.setText("Modificar");
      }
    } else {
      String nuevoId = tf_identificador.getText();
      String nuevoNombre = tf_nombreProducto.getText();
      String nuevoStock = tf_stock.getText();

      boolean hasError = gestorErrores(nuevoId, nuevoNombre, nuevoStock);

      if (!hasError) {
        boolean identificadorExistente =
            productoList.stream()
                .anyMatch(
                    p -> p.getIdentificador().equals(nuevoId) && !p.equals(productoSeleccionado));

        if (!identificadorExistente) {
          productoSeleccionado.setIdentificador(nuevoId);
          productoSeleccionado.setNomProducto(nuevoNombre);
          productoSeleccionado.setStock(nuevoStock);

          tf_identificador.clear();
          tf_nombreProducto.clear();
          tf_stock.clear();

          error_Identificador.setText("");
          error_NombreProducto.setText("");
          error_Stock.setText("");

          tableView.refresh();
          productoSeleccionado = null;
          enModificacion = false;
          bt_modificar.setText("Modificar"); // Restablece el texto del botón
        } else {
          error_Identificador.setText("Error: el identificador ya existe.");
          error_Identificador.setStyle("-fx-text-fill: red;");
        }
      }
    }
  }

  public boolean gestorErrores(String nuevoId, String nuevoNombre, String nuevoStock) {

    boolean hasError = false;

    if (nuevoId.isEmpty()) {
      error_Identificador.setText("Error: identificador obligatorio.");
      error_Identificador.setStyle("-fx-text-fill: red;");
      hasError = true;
    }

    if (nuevoNombre.isEmpty()) {
      error_NombreProducto.setText("Error: nombre del producto obligatorio.");
      error_NombreProducto.setStyle("-fx-text-fill: red;");
      hasError = true;
    }

    if (nuevoStock.isEmpty()) {
      error_Stock.setText("Error: stock obligatorio.");
      error_Stock.setStyle("-fx-text-fill: red;");
      hasError = true;
    }
    return hasError;
  }
}
