package com.ucv.lab12.repository;

import com.ucv.lab12.config.DatabaseConfig;
import com.ucv.lab12.model.Videojuego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoRepository implements IVideojuegoRepository {

    private final DatabaseConfig dbConfig = new DatabaseConfig();

    @Override
    public void registrar(Videojuego videojuego) {
        String sql = "INSERT INTO Videojuego (idVideojuego, Consola, Nombre, Genero, Clasificacion, Descripcion, IDdesarrollador, IDdistribuidor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, videojuego.getIdVideojuego());
            ps.setString(2, videojuego.getConsola());
            ps.setString(3, videojuego.getNombre());
            ps.setString(4, videojuego.getGenero());
            ps.setString(5, videojuego.getClasificacion());
            ps.setString(6, videojuego.getDescripcion());
            ps.setInt(7, videojuego.getIdDesarrollador());
            ps.setInt(8, videojuego.getIdDistribuidor());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(Videojuego videojuego) {
        String sql = "UPDATE Videojuego SET Consola = ?, Nombre = ?, Genero = ?, Clasificacion = ?, Descripcion = ?, IDdesarrollador = ?, IDdistribuidor = ? WHERE idVideojuego = ?";

        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, videojuego.getConsola());
            ps.setString(2, videojuego.getNombre());
            ps.setString(3, videojuego.getGenero());
            ps.setString(4, videojuego.getClasificacion());
            ps.setString(5, videojuego.getDescripcion());
            ps.setInt(6, videojuego.getIdDesarrollador());
            ps.setInt(7, videojuego.getIdDistribuidor());
            ps.setInt(8, videojuego.getIdVideojuego());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idVideojuego) {
        String sql = "DELETE FROM Videojuego WHERE idVideojuego = ?";

        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVideojuego);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Videojuego> listarTodos() {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM Videojuego";

        try (Connection conn = dbConfig.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Videojuego vj = new Videojuego(
                        rs.getInt("idVideojuego"),
                        rs.getString("Consola"),
                        rs.getString("Nombre"),
                        rs.getString("Genero"),
                        rs.getString("Clasificacion"),
                        rs.getString("Descripcion"),
                        rs.getInt("IDdesarrollador"),
                        rs.getInt("IDdistribuidor")
                );
                lista.add(vj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Videojuego buscarPorId(int idVideojuego) {
        String sql = "SELECT * FROM Videojuego WHERE idVideojuego = ?";

        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVideojuego);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Videojuego(
                            rs.getInt("idVideojuego"),
                            rs.getString("Consola"),
                            rs.getString("Nombre"),
                            rs.getString("Genero"),
                            rs.getString("Clasificacion"),
                            rs.getString("Descripcion"),
                            rs.getInt("IDdesarrollador"),
                            rs.getInt("IDdistribuidor")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}