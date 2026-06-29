package com.ucv.lab12.service;

import com.ucv.lab12.model.Videojuego;
import java.util.List;

public interface IVideojuegoService {
    void registrar(Videojuego videojuego);
    List<Videojuego> listarTodos();
}