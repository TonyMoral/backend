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
import models.*;

/**
 *
 * @author User
 */
public class FilmografiaDAO extends DAO<Filmografia> {

    //Atributos
    private static final String INSERT = "INSERT INTO filmografia ( titulo, fecha_estreno, sinopsis,  pais_id, clasificacion_id) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM filmografia WHERE id = ?";
    private static final String UPDATE = "UPDATE filmografia SET titulo = ?, fecha_estreno = ?, sinopsis = ?, pais_id = ?, clasificacion_id = ? WHERE id = ?";
    private static final String LISTALL = "SELECT *  FROM filmografia";
    private static final String LISTONE = "SELECT *  FROM filmografia WHERE id = ?";

    //Constructor
    public FilmografiaDAO(Connection conexion) {
        super(conexion);
    }

    //Metodos
    //Metodo listar todo
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

                mostrarFilmografia(rs);

            }
        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            //Cerrar ResultSet y PreparedStatement
            cerrarEstados(rs, statement);
        }

    }

    //Metodo para listar uno
    @Override
    public void listOne() throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //Crear el scanner
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el id de la pelicula que quieras ver");
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

                mostrarFilmografia(rs);

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

    //Metodo insertar
    @Override
    public void insertar(Filmografia film) throws SQLException {
        PreparedStatement statement = null;
        try {
            //Preparar la consulta SQL  
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, film.getTitulo());
            statement.setDate(2, film.getFecha_estreno());
            statement.setString(3, film.getSinopsis());
            statement.setInt(4, film.getPais_id());
            statement.setInt(5, film.getClasificacion_id());

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

    //Metodo actualizar
    @Override
    public void actualizar(Filmografia film, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            //Preparar la consulta SQL  
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, film.getTitulo());
            statement.setDate(2, film.getFecha_estreno());
            statement.setString(3, film.getSinopsis());
            statement.setInt(4, film.getPais_id());
            statement.setInt(5, film.getClasificacion_id());
            statement.setInt(6, id);

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

    //Metodo  eliminar
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

    private void mostrarFilmografia(ResultSet rs) {
        try {
            Filmografia f = new Filmografia();

            f.setId(rs.getInt("id"));
            f.setTitulo(rs.getString("titulo"));
            f.setFecha_estreno(rs.getDate("fecha_estreno"));
            f.setSinopsis(rs.getString("sinopsis"));
            f.setPais_id(rs.getInt("pais_id"));
            f.setClasificacion_id(rs.getInt("clasificacion_id"));

            System.out.println(f.toString());
        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
