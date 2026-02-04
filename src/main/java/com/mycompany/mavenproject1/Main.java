/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import DbManager.DatabaseManager;

/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {
        
        DatabaseManager.conectar();
        
        DatabaseManager.desconectar();
        
        
    }
}
