/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Pais;

/**
 *
 * @author User
 */
public class PaisDAO extends DAO<Pais>{

    //Atributos
    private static final String INSERT = "INSERT INTO pais (nombre) VALUES (?)";
    private static final String DELETE = "DELETE FROM pais WHERE id = ?";
    private static final String UPDATE = "UPDATE pais SET nombre = ? WHERE id = ?";
    private static final String LISTALL = "SELECT *  FROM pais";
    private static final String LISTONE = "SELECT *  FROM pais WHERE id = ?";
    //Constructor
    public PaisDAO(Connection conexion) {
        super(conexion);
    }

    //Metodos
    @Override
    public void listAll() throws SQLException {
    }

    @Override
    public void listOne() throws SQLException {
    }

    @Override
    public void insertar(Pais t) throws SQLException {
    }

    @Override
    public void actualizar(Pais t, int id) throws SQLException {
    }

    @Override
    public void eliminar(int id) throws SQLException {
    }

    private void mostrarPais(ResultSet rs) {
        try {
            Pais p = new Pais();

            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));

            System.out.println(p.toString());
            
        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }    
    
}
