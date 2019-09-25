/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import dao.DaoArticulo;
import db.ConnectDB;
import dto.Articulo;
import java.util.List;
import java.sql.*;
import java.util.LinkedList;


/**
 *
 * @author Martín
 */
public class DaoArticuloImpl implements DaoArticulo{
    private final ConnectDB db;   //db es un parametro que será instanciado de ConnectDB
    private final StringBuilder sql;

    public DaoArticuloImpl() { // se genera un constructor vacio para instancear
        db = new ConnectDB();
        sql = new StringBuilder();
    }

    @Override
    public List<Articulo> obtenerArticulos() {
        sql.delete(0, sql.length()); //Limpiando
        sql.append("SELECT in_codigo, vc_nombre, vc_descripcion, in_cantidad, ch_habilitar FROM t_articulo"); //escribo el script
        
        //manejo de errores
        try (
                //almacenar la conexion en cn
                Connection cn = db.getConnection();
               // Preparar el script a ejecutar
             PreparedStatement ps = cn.prepareCall(sql.toString());
             // ejecutar el script y alamacenar resultado en rs
             ResultSet rs = ps.executeQuery();              
                ){
            List<Articulo> listarArticulo = new LinkedList<>();
            //recorremos la tabla
            while (rs.next()) {                
                Articulo articulo = new Articulo(); // instancial la Clase articulo al parametro articulo
                articulo.setCodigo(rs.getInt("in_codigo"));
                articulo.setNombre(rs.getString("vc_nombre"));
                articulo.setDescripcion(rs.getString("vc_descripcion"));
                articulo.setCantidad(rs.getInt("in_cantidad"));
                articulo.setHabilitar(rs.getString("ch_habilitar"));
                
                listarArticulo.add(articulo); //añadiendo cada registro a la lista        
            }
             
            return listarArticulo;
                  
        } catch (Exception e) {
            return null;
        }      
    }

    @Override
    public Articulo obtenerArticulo(int codigo) {
        sql.delete(0, sql.length());
        sql.append("SELECT in_codigo, vc_nombre, vc_descripcion, in_cantidad, ch_habilitar "
                + "FROM t_articulo WHERE in_codigo = ? ");
        
        try (
                Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareCall(sql.toString());                
                ){
            
            ps.setInt(1, codigo); //seteado un valor en el string
            
            // se usa nuevamente Try para manipular el ps
            try (ResultSet rs = ps.executeQuery()  ){
                if (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setCodigo(rs.getInt("in_Codigo") );
                    articulo.setNombre(rs.getString("vc_Nombre") );
                    articulo.setDescripcion(rs.getString("vc_Descripcion") );
                    articulo.setCantidad(rs.getInt("in_Cantidad") );
                    articulo.setHabilitar(rs.getString("ch_Habilitar") );
                    
                    return articulo;
                } else {
                    return null;
                }
                                
            } catch (Exception e) {
                return null;
            }
                      
        } catch (Exception e) {
            return null;
        }
  
    }

    @Override
    public boolean insertarArticulo(Articulo Articulo) {
        sql.delete(0, sql.length());
        sql.append("INSERT INTO t_articulo (vc_nombre, vc_descripcion, in_cantidad, ch_habilitar) "
                + " VALUES (?,?,?,'Y') ");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareCall(sql.toString());
                ){
            
            ps.setString(1, Articulo.getNombre());
            ps.setString(2, Articulo.getDescripcion());
            ps.setInt(3, Articulo.getCantidad());

            int i = ps.executeUpdate();
            
            if (i==0) {
                return false;
            } else {
                return true;
            }        
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarArticulo(Articulo Articulo) {
        sql.delete(0, sql.length());
        sql.append("UPDATE t_articulo SET vc_nombre=?, vc_descripcion=?, in_cantidad=?, ch_habilitar=? "
                + "WHERE in_codigo=?");
                
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareCall(sql.toString());
                ){
            
            ps.setString(1, Articulo.getNombre());
            ps.setString(2, Articulo.getDescripcion());
            ps.setInt(3, Articulo.getCantidad());
            ps.setString(4, Articulo.getHabilitar());
            ps.setInt(5, Articulo.getCodigo());
     
            int i = ps.executeUpdate();
                    
            if (i==0) {
                return false;
            } else {
                return true;
            }       
            
        } catch (Exception e) {
            return false;
            
        }       
        
    }

    @Override
    public boolean habilitarArticulo(int codigo, String estado) {
        sql.delete(0, sql.length());
        sql.append("UPDATE t_articulo SET ch_habilitar=? WHERE in_codigo=?");
        
                try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareCall(sql.toString());
                ){
                    
            ps.setString(1, estado);            
            ps.setInt(2, codigo);
     
            int i = ps.executeUpdate();
                    
            if (i==0) {
                return false;
            } else {
                return true;
            }       
            
        } catch (Exception e) {
            return false;
            
        }
    }
    
    
    
    
    
    
}
