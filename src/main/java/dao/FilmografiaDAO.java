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
import  models.*;

/**
 *
 * @author User
 */
public class FilmografiaDAO {
    
    //Atributos
    private Connection conexion;
    private static final String INSERT = "INSERT INTO filmografia ( titulo, fecha_estreno, sinopsis,  pais_id, clasificacion_id) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM filmografia WHERE id = ?";
    private static final String UPDATE = "UPDATE filmografia SET titulo = ?, fecha_estreno = ?, sinopsis = ?, pais_id = ?, clasificacion_id = ? WHERE id = ?";
    private static final String LISTALL = "SELECT *  FROM filmografia";
    private static final String LISTONE = "SELECT *  FROM filmografia WHERE id = ?";

    //Constructor
    public FilmografiaDAO(Connection conexion) {
        this.conexion = conexion;
    }


    //Metodos
    
    //Metodo listar todo
    public void listAll () throws SQLException {
         //Preparar la consulta SQL  
        PreparedStatement statement = conexion.prepareStatement(LISTALL); 
        
        //Ejecutar la consulta y obtener el resultado
        ResultSet rs = statement.executeQuery();
        
        //Recorrer cada fila de la consulta
        while (rs.next()) {
            
            Filmografia f = new Filmografia();
            
            f.setId( rs.getInt("id"));
            f.setTitulo(rs.getString("titulo"));
            f.setFecha_estreno(rs.getDate("fecha_estreno"));
            f.setSinopsis(rs.getString("sinopsis"));
            f.setPais_id(rs.getInt("pais_id"));
            f.setClasificacion_id(rs.getInt("clasificacion_id"));
            
            System.out.println(f.toString());

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
           System.out.println("Introduce el id de la pelicula que quieras ver");
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
                
                Filmografia f = new Filmografia();
                
                f.setId( rs.getInt("id"));
                f.setTitulo(rs.getString("titulo"));
                f.setFecha_estreno(rs.getDate("fecha_estreno"));
                f.setSinopsis(rs.getString("sinopsis"));
                f.setPais_id(rs.getInt("pais_id"));
                f.setClasificacion_id(rs.getInt("clasificacion_id"));
                
                System.out.println("La pelicula con id: " + f.getId() + " es: ");
                System.out.println(f.toString());
                
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

}
