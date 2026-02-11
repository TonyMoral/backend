/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public abstract class DAO <T>{
    //Atributos
    protected Connection conexion;
    //Constructor

    public DAO(Connection conexion) {
        this.conexion = conexion;
    }
    
   
    //Metodos
    //Metodo listar todo
    public abstract void listAll() throws SQLException;

    //Metodo para listar uno
    public abstract void listOne() throws SQLException;

    //Metodo insertar
    public abstract void insertar( T t) throws SQLException;

    //Metodo actualizar
    public abstract void actualizar(T t, int id) throws SQLException;

    //Metodo  eliminar
    public abstract void eliminar(int id) throws SQLException;

    protected void cerrarEstados(ResultSet rs, PreparedStatement statement) throws SQLException {
        //Si no es nulo cerramos el resultset
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        //Si no es nulo cerramos el statement
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }


    protected void hacerRollback(Connection conexion) {
        try {
            conexion.rollback();
        } catch (SQLException e2) {
            System.out.println("Error en el rollback");
        }
    }
}
