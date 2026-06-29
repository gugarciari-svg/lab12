package com.ucv.lab12.service;

import com.ucv.lab12.model.Videojuego;
import com.ucv.lab12.repository.IVideojuegoRepository;
import com.ucv.lab12.repository.VideojuegoRepository;
import java.util.List;

public class VideojuegoService implements IVideojuegoService {

    private final IVideojuegoRepository repository;

    public VideojuegoService() {
        this.repository = new VideojuegoRepository();
    }

    @Override
    public void registrar(Videojuego videojuego) {
        repository.registrar(videojuego);
    }

    @Override
    public List<Videojuego> listarTodos() {
        return repository.listarTodos();
    }
}