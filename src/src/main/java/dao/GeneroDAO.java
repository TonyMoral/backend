/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import models.Genero;

/**
 *
 * @author User
 */
public class GeneroDAO extends DAO<Genero> {

    //Atributos
    private static final String INSERT = "INSERT INTO genero (nombre) VALUES (?)";
    private static final String DELETE = "DELETE FROM genero WHERE id = ?";
    private static final String UPDATE = "UPDATE genero SET nombre = ? WHERE id = ?";
    private static final String LISTALL = "SELECT *  FROM genero";
    private static final String LISTONE = "SELECT *  FROM genero WHERE id = ?";

    //Constructor
    public GeneroDAO(Connection conexion) {
        super(conexion);
    }

    //Metodos
    @Override
    public void listAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //Preparar la consulta SQL  
            statement = conexion.prepareStatement(LISTALL);

            //Ejecutar la consulta y obtener el resultado
            rs = statement.executeQuery();
            conexion.commit();

            //Recorrer cada fila de la consulta
            while (rs.next()) {

                mostrarGenero(rs);

            }
        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            //Cerrar ResultSet y PreparedStatement
            cerrarEstados(rs, statement);
        }
    }

    @Override
    public void listOne() throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //Crear el scanner
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el id del genero que quieras ver");
            int id = scanner.nextInt();

            //Preparar la consulta SQL
            statement = conexion.prepareStatement(LISTONE);
            statement.setInt(1, id);
            //Ejecutar la consulta y obtener el resultado
            rs = statement.executeQuery();
            conexion.commit();

            //Variable para comprobar si existe el id introducido, si entra al bucle es porque si esta
            boolean idEncontrado = false;

            //Recorrer cada fila de la consulta
            while (rs.next()) {

                idEncontrado = true;

                mostrarGenero(rs);

            }

            if (!idEncontrado) {
                System.out.println("Id no encontrado");
            }

        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            //Cerrar ResultSet y PreparedStatement
            cerrarEstados(rs, statement);
        }

    }

    @Override
    public void insertar(Genero genero) throws SQLException {
        PreparedStatement statement = null;
        try {
            //Preparar la consulta SQL  
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, genero.getNombre());

            //Ejecutar la consulta y actualiza la tabla
            statement.executeUpdate();
            conexion.commit();
        } //Hacemos rollback si falla algo
        catch (SQLException e) {
            hacerRollback(conexion);
            throw e;
        } finally {
            //Cerrar ResultSet y PreparedStatement
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void actualizar(Genero genero, int id) throws SQLException {
         PreparedStatement statement = null;
        try {
            //Preparar la consulta SQL  
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, genero.getNombre());
            statement.setInt(2, id);

            //Ejecutar la consulta y actualiza la tabla
            statement.executeUpdate();
            conexion.commit();
        } //Hacemos rollback si falla algo
        catch (SQLException e) {
            hacerRollback(conexion);
            throw e;
        } finally {
            //Si no es nulo cerramos el statement
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            //Preparar la consulta SQL  
            statement = conexion.prepareStatement(DELETE);
            statement.setInt(1, id);

            //Ejecutar la consulta y actualiza la tabla
            statement.executeUpdate();
            conexion.commit();
        } //Hacemos rollback si falla algo
        catch (SQLException e) {
            hacerRollback(conexion);
            throw e;
        } finally {
            //Si no es nulo cerramos el statement
            statement.close();
        }
    }

    private void mostrarGenero(ResultSet rs) {
        try {
            Genero g = new Genero();

            g.setId(rs.getInt("id"));
            g.setNombre(rs.getString("nombre"));

            System.out.println(g.toString());
        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
