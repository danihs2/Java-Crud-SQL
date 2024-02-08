/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.crud.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class CConexion {
    Connection conectar = null;
    
    String user = "root";
    String pass = "familia21";
    String db = "bdtienda";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+db;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, user, pass);
            JOptionPane.showMessageDialog(null, "Conexion exitosa a db");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse db, error: " + e.toString());
        }
        return conectar;
    }
}
