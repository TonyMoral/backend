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
public class GeneroDAO {
     //Atributos
    private Connection conexion;
    private static final String INSERT = "INSERT INTO gemero ( nombre) VALUES (?)";
    private static final String DELETE = "DELETE FROM genero WHERE id = ?";
    private static final String UPDATE = "UPDATE genero SET nombre = ? WHERE id = ?";
    private static final String LISTALL = "SELECT *  FROM genero";
    private static final String LISTONE = "SELECT *  FROM genero WHERE id = ?";

    //Constructor
    public GeneroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    //Metodos
    //Metodo listar todo
    public void listAll() throws SQLException {
        //Preparar la consulta SQL  
        PreparedStatement statement = conexion.prepareStatement(LISTALL);

        //Ejecutar la consulta y obtener el resultado
        ResultSet rs = statement.executeQuery();

        //Recorrer cada fila de la consulta
        while (rs.next()) {

            Genero g = new Genero();

            g.setId(rs.getInt("id"));
            g.setNombre(rs.getString("nombre"));

            System.out.println(g.toString());

        }

        //Cerrar ResultSet y PreparedStatement
        rs.close();
        statement.close();

    }

    //Metodo para listar uno
    public void listOne() {
        try {
            //Crear el scanner
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el id del genero que quieras ver");
            int id = scanner.nextInt();

            //Preparar la consulta SQL
            PreparedStatement statement = conexion.prepareStatement(LISTONE);
            statement.setInt(1, id);
            //Ejecutar la consulta y obtener el resultado
            ResultSet rs = statement.executeQuery();

            //Variable para comprobar si existe el id introducido, si entra al bucle es porque si esta
            boolean idEncontrado = false;

            //Recorrer cada fila de la consulta
            while (rs.next()) {

                idEncontrado = true;

                Genero g = new Genero();

                g.setId(rs.getInt("id"));
                g.setNombre(rs.getString("titulo"));
               

                System.out.println("El genero con id: " + g.getId() + " es: ");
                System.out.println(g.toString());

            }

            if (!idEncontrado) {
                System.out.println("Id no encontrado");
            }

            //Cerrar ResultSet y PreparedStatement
            rs.close();
            statement.close();

        } catch (SQLException ex) {
            System.getLogger(FilmografiaDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    //Metodo insertar
    public void insertar(String nombre) throws SQLException {
        //Preparar la consulta SQL  
        PreparedStatement statement = conexion.prepareStatement(INSERT);
        statement.setString(1, nombre);

        //Ejecutar la consulta y actualiza la tabla
        statement.executeUpdate();

        //Cerrar ResultSet y PreparedStatement
        statement.close();

    }

    //Metodo actualizar
    public void actualizar(String nombre, int id) throws SQLException {
        //Preparar la consulta SQL  
        PreparedStatement statement = conexion.prepareStatement(UPDATE);
        statement.setString(1, nombre);
        statement.setInt(2, id);

        //Ejecutar la consulta y actualiza la tabla
        statement.executeUpdate();

        //Cerrar ResultSet y PreparedStatement
        statement.close();

    }

    //Metodo actualizar
    public void eliminar(int id) throws SQLException {
        //Preparar la consulta SQL  
        PreparedStatement statement = conexion.prepareStatement(DELETE);
        statement.setInt(1, id);

        //Ejecutar la consulta y actualiza la tabla
        statement.executeUpdate();

        //Cerrar ResultSet y PreparedStatement
        statement.close();

    }

}


