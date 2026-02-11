/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import DbManager.DatabaseManager;
import dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import models.*;

        /**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) throws SQLException {

    
        //Crear la conexion
        Connection conexion = DatabaseManager.conectar();
         //Quitar el autocommit
        DatabaseManager.conectar().setAutoCommit(false);
         //Crear DAO 
        FilmografiaDAO filmografiaDAO = new FilmografiaDAO(conexion);
        Filmografia film1 = new Filmografia ("La comunidad del anillo", java.sql.Date.valueOf("2001-05-10"), "Unos hobbits se van de paseo", 1, 4);
        Filmografia film2 = new Filmografia ("La comunidad del anillo 2.0", java.sql.Date.valueOf("2001-05-10"), "Unos hobbits se van de paseo", 1, 4);

        GeneroDAO generoDAO = new GeneroDAO(conexion);
        Genero gen1 = new Genero ("Terror");
        Genero gen2 = new Genero("Mas Terror");

        
        //Llamar al metodo listar uno
        //filmografiaDAO.listOne();
        //generoDAO.listOne();
       
       //LLamar al metodo para insertar
       //filmografiaDAO.insertar(film1);
        //generoDAO.insertar(gen1);
        
       //Llamar al metodo para actualizar
      //filmografiaDAO.actualizar(film2, 14);
     // generoDAO.actualizar(gen2, 6);
       
       //Llamar al metodo para eliminar
       //filmografiaDAO.eliminar(13);
      // generoDAO.eliminar(6);

        //Llamar al metodo listar todo
        //filmografiaDAO.listAll();      
        generoDAO.listAll();
   
        //Cerrar conexion
        DatabaseManager.desconectar();
        


        }
    

        
    }

