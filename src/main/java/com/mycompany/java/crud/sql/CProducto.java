/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.crud.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author danie
 */
public class CProducto {
    int id;
    String nombre;
    String descripcion;
    float precio;

    public CProducto(int id, String nombre, String descripcion, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public CProducto(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void insertarProducto(JTextField jtNombre, JTextField jtDescripcion, JTextField jtPrecio){
        setNombre(jtNombre.getText());
        setDescripcion(jtDescripcion.getText());
        
        CConexion objConexion = new CConexion();
        
        setPrecio(Float.parseFloat(jtPrecio.getText()));
        
        String consulta = "INSERT INTO Producto(nombre, descripcion, precio) VALUES (?, ?, ?);";
        
        try{
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, nombre);
            cs.setString(2, descripcion);
            cs.setString(3, String.valueOf(precio));
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se inserto correctamente | error: " + e.toString());
        }
    }
    
    public void mostrarProductos(JTable jtProductos){
        CConexion objConexion = new CConexion();
        String sql = "";
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        jtProductos.setRowSorter(OrdenarTabla);
        
        sql = "SELECT * FROM Producto";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        String[] datos = new String[4];
        Statement st;
        try {
            st = objConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);
            }
            jtProductos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar | Error: " + e.toString());
        }
    }
    
    public void SeleccionarProducto(JTable jtTabla, JTextField paramId, JTextField paramNombre, JTextField paramDescrip, JTextField paramPrecio){
        try {
            int fila = jtTabla.getSelectedRow();
            if (fila >=0) {
                paramId.setText((jtTabla.getValueAt(fila, 0).toString()));
                paramNombre.setText((jtTabla.getValueAt(fila, 1).toString()));
                paramDescrip.setText((jtTabla.getValueAt(fila, 2).toString()));
                paramPrecio.setText((jtTabla.getValueAt(fila, 3).toString()));
            }else
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Fallo seleccionar alumn | Error: " + e.toString());
        }
    }
    
    public void modificarProducto(JTextField pId, JTextField pNombre, JTextField pDescrip, JTextField pPrecio){
        setId(Integer.parseInt(pId.getText()));
        setNombre(pNombre.getText());
        setDescripcion(pDescrip.getText());
        setPrecio(Float.parseFloat(pPrecio.getText()));
        
        CConexion objConexion = new CConexion();
        
        String consulta = "UPDATE Producto SET Producto.nombre = ?, Producto.descripcion = ?, Producto.precio = ? WHERE Producto.id = ?;";
        
        try {
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, nombre);
            cs.setString(2, descripcion);
            cs.setString(3, String.valueOf(precio));
            cs.setInt(4, id);
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificado Correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consulta modificar | Error: " + e.toString());
        }
    }
    
    public void EliminarProducto(JTextField pId){
        setId(Integer.parseInt(pId.getText()));
        
        CConexion objConexion = new CConexion();
        
        String consulta = "DELETE FROM Producto WHERE Producto.id = ?;";
        
        try {
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, id);
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar | Error: " + e.toString());
        }
    }
}
