package com.ucv.lab12.repository;

import com.ucv.lab12.model.Videojuego;
import java.util.List;

public interface IVideojuegoRepository {
    void registrar(Videojuego videojuego);
    void modificar(Videojuego videojuego);
    void eliminar(int idVideojuego);
    List<Videojuego> listarTodos();
    Videojuego buscarPorId(int idVideojuego);
}