/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import DbManager.DatabaseManager;
import dao.*;
import java.sql.Connection;
import java.sql.SQLException;

        /**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) throws SQLException {

    
        //Crear la conexion
        Connection conexion = DatabaseManager.conectar();
        
         //Crear DAO 
        FilmografiaDAO filmografiaDAO = new FilmografiaDAO(conexion);
        GeneroDAO generoDAO = new GeneroDAO(conexion);
        

        
        //Llamar al metodo listar uno
       // filmografiaDAO.listOne();
       
       //LLamar al metodo para insertar
       //filmografiaDAO.insertar("La comunidad del anillo", java.sql.Date.valueOf("2001-05-10"), "Unos hobbits se van de paseo", 1, 4);
        
       //Llamar al metodo para actualizar
      //filmografiaDAO.actualizar("Las dos torres",  java.sql.Date.valueOf("2002-07-10"), "Los hobbits siguen de excursion", 2, 3, 9);
       
       //Llamar al metodo para eliminar
       //filmografiaDAO.eliminar(8);

        //Llamar al metodo listar todo
        filmografiaDAO.listAll();      
        generoDAO.listAll();
   
        //Cerrar conexion
        DatabaseManager.desconectar();

        }
    

        
    }

