/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Articulo;
import java.util.List;

/**
 *
 * @author Martín
 */
public interface DaoArticulo {
    
    List<Articulo> obtenerArticulos();
    Articulo obtenerArticulo(int codigo); // Obterer un articulo
    boolean insertarArticulo(Articulo Articulo); //Clase y nombre del parametro
    boolean actualizarArticulo(Articulo Articulo);
    boolean habilitarArticulo(int Codigo, String estado);
}
