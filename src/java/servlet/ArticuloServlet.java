/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import dao.DaoArticulo;
import dao.impl.DaoArticuloImpl;
import dto.Articulo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martín
 */
@WebServlet(name = "ArticuloServlet", urlPatterns = {"/articulo"})
public class ArticuloServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gson gson = new Gson();
        DaoArticulo daoArticulo = new DaoArticuloImpl();
        
        String codigo = request.getParameter("codigo");

        String resultado;
        
        if (codigo != null) {
            int cod = Integer.parseInt(codigo);
            resultado = gson.toJson(daoArticulo.obtenerArticulo(cod));
        } else {
            resultado = gson.toJson(daoArticulo.obtenerArticulos());    
        }
        
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultado);

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gson myGson = new Gson();
        Articulo myarticulo = myGson.fromJson(request.getReader(), Articulo.class);
        
        DaoArticulo  myDaoArticulo = new DaoArticuloImpl();
        boolean resultado = myDaoArticulo.insertarArticulo(myarticulo);
     
        String resultadoString;
        
        if (resultado) {
            resultadoString = "{ \"msg\":\"Se registró correctamente\"}";
        } else {
            resultadoString = "{ \"msg\":\"Error\"}";
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultadoString);

        }
           
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      Gson myGson = new Gson();
      
        Articulo myarticulo = myGson.fromJson(req.getReader(), Articulo.class);
        
        DaoArticulo  myDaoArticulo = new DaoArticuloImpl();
        boolean resultado = myDaoArticulo.actualizarArticulo(myarticulo);
     
        String resultadoString;
        
        if (resultado) {
            resultadoString = "{ \"msg\":\"Se actualizó correctamente\"}";
        } else {
            resultadoString = "{ \"msg\":\"Error\"}";
        }
        
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultadoString);
        }
        

}

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Gson myGson = new Gson();
        DaoArticulo myDaoArticulo = new DaoArticuloImpl();
        String my_codigo = req.getParameter("codigo");
        String my_estado = req.getParameter("estado");
        
        int my_codigo2 = Integer.parseInt(my_codigo);
        
        boolean resultado = myDaoArticulo.habilitarArticulo(my_codigo2, my_estado);
        
        String resultadoString;
        
        if (resultado) {
            resultadoString = "{ \"msg\":\"Se actualizó correctamente\"}";
        } else {
            resultadoString = "{ \"msg\":\"Error\"}";
        }
        
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultadoString);
        }
        
        
        
        
    }

    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
