/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import DbManager.DatabaseManager;
import dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import models.*;
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
        
        //Llamar al metodo listar todo
        //filmografiaDAO.listAll();
        
        //Llamar al metodo listar uno
        filmografiaDAO.listOne();
            
        //Cerrar conexion
        DatabaseManager.desconectar();

        }
    

        
    }

