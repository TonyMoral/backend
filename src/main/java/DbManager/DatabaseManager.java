/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbManager;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseManager {
    //Atributos
    private static final Dotenv dotenv = Dotenv.load() ;
    private static final String USER = dotenv.get("USER");
    private static final String PASSWORD = dotenv.get("PASSWORD");
    private static final String URL = dotenv.get("URL");
    private static Connection conexion = null;
    
    //Metodos
    public static Connection conectar() {
        try {
            
            // Verificar si ya existe una conexión activa
            if (conexion != null && !conexion.isClosed()) {
                return conexion;
            }
            
            //Cargar el driver
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
                    
        }
        catch (SQLException e){            
            System.out.println("Error de conexión: " + e.getMessage());

        } 
        
        return conexion;
    }
    
    public static void desconectar(){
         try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
                
    }
    
    
}
