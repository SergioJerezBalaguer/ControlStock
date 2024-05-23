package org.example.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {

    private final StringProperty identificador;
    private final StringProperty nomProducto;
    private final StringProperty stock;

    public Producto(String identificador, String nomProducto, String stock) {
        this.identificador = new SimpleStringProperty(identificador);
        this.nomProducto = new SimpleStringProperty(nomProducto);
        this.stock = new SimpleStringProperty(stock);
    }

    public String getIdentificador() {
        return identificador.get();
    }

    public StringProperty identificadorProperty() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador.set(identificador);
    }

    public String getNomProducto() {
        return nomProducto.get();
    }

    public StringProperty nomProductoProperty() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto.set(nomProducto);
    }

    public String getStock() {
        return stock.get();
    }

    public StringProperty stockProperty() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock.set(stock);
    }
}
