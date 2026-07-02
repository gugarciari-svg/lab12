package com.ucv.lab12.controller;

import com.ucv.lab12.model.Videojuego;
import com.ucv.lab12.service.VideojuegoService;
import com.ucv.lab12.service.IVideojuegoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VideojuegoController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtConsola;
    @FXML private TextField txtGenero;
    @FXML private TextField txtClasificacion;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtIdDesarrollador;
    @FXML private TextField txtIdDistribuidor;

    @FXML private TableView<Videojuego> tblVideojuegos;
    @FXML private TableColumn<Videojuego, Integer> colId;
    @FXML private TableColumn<Videojuego, String> colNombre;
    @FXML private TableColumn<Videojuego, String> colConsola;
    @FXML private TableColumn<Videojuego, String> colGenero;
    @FXML private TableColumn<Videojuego, String> colClasificacion;
    @FXML private TableColumn<Videojuego, Integer> colIdDistribuidor;

    private final IVideojuegoService videojuegoService = new VideojuegoService();
    private final ObservableList<Videojuego> listaObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idVideojuego"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colClasificacion.setCellValueFactory(new PropertyValueFactory<>("clasificacion"));
        colIdDistribuidor.setCellValueFactory(new PropertyValueFactory<>("idDistribuidor"));

        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        listaObservable.clear();
        try {
            listaObservable.addAll(videojuegoService.listarTodos());
            tblVideojuegos.setItems(listaObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBtnRegistrarClick() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            int idDesarrollador = Integer.parseInt(txtIdDesarrollador.getText().trim());
            int idDistribuidor = Integer.parseInt(txtIdDistribuidor.getText().trim());

            Videojuego nuevo = new Videojuego(
                    id,
                    txtConsola.getText(),
                    txtNombre.getText(),
                    txtGenero.getText(),
                    txtClasificacion.getText(),
                    txtDescripcion.getText(),
                    idDesarrollador,
                    idDistribuidor
            );

            videojuegoService.registrar(nuevo);
            cargarDatosTabla();
            limpiarCampos();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Éxito");
            alerta.setHeaderText(null);
            alerta.setContentText("Videojuego registrado correctamente.");
            alerta.showAndWait();

        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Formato");
            alerta.setContentText("Los campos ID deben ser números.");
            alerta.showAndWait();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("Ocurrió un error al registrar el videojuego.");
            alerta.showAndWait();
        }
    }

    @FXML
    void onBtnLimpiarClick() {
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtConsola.clear();
        txtGenero.clear();
        txtClasificacion.clear();
        txtDescripcion.clear();
        txtIdDesarrollador.clear();
        txtIdDistribuidor.clear();
    }
}