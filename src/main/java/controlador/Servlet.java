/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Libros;
import modelo.LibrosCrud;

/**
 *
 * @author DAW-A
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    final int NUM_LINEAS_PAGINA = 5;
    List<Libros> listaLibros = LibrosCrud.getLibros();
    int pagina = 1;
    int offset = 0;                
    int num_paginas = (int) Math.ceil(listaLibros.size()/(double)NUM_LINEAS_PAGINA);
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
        response.setContentType("text/html;charset=UTF-8");
            String op = request.getParameter("op");
            
            if (op.equals("listar")) {
               if (request.getParameter("pagina")!=null) {
                    pagina = Integer.parseInt(request.getParameter("pagina"));
                    offset = (pagina-1)*NUM_LINEAS_PAGINA;
                }
                
                listaLibros = LibrosCrud.getLibros();
                
                request.setAttribute("listado", listaLibros);
                request.setAttribute("pagina", pagina);
                request.setAttribute("num_paginas", num_paginas);
                request.setAttribute("mensaje", "");
                request.getRequestDispatcher("listar.jsp").forward(request, response);
         
    } else if (op.equals("insert")){
        request.setAttribute("", "mensaje");
        request.setAttribute("operacion", "Insertar");
        request.getRequestDispatcher("modificar.jsp").forward(request, response);
    }
        else if (op.equals("Insertar")){
           
             String titulo= request.getParameter("titulo");
             String autor= request.getParameter("autor");
             long isbn = Long.parseLong(request.getParameter("isbn"));
             Float precio= Float.parseFloat(request.getParameter("precio"));

             
             Libros miLibro = new Libros();
             
             miLibro.setTitulo(titulo);
             miLibro.setAutor(autor);
             miLibro.setIsbn(isbn);
             miLibro.setPrecio(precio);
              
                LibrosCrud.insertaLibro(miLibro);
                
                listaLibros = LibrosCrud.getLibros();
              
                request.setAttribute("listado", listaLibros);
                request.setAttribute("mensaje", "");
                request.setAttribute("pagina", pagina);
                request.setAttribute("num_paginas", num_paginas);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
                
    }else if (op.equals("borrar")) {
                
                int id = Integer.parseInt(request.getParameter("id"));
                
                if (LibrosCrud.destroyLibro(id)>0) {
                    request.setAttribute("mensaje", "El ejemplar con id " + id + " se ha borrado");
                }else{
                    request.setAttribute("mensaje", "No se ha podido borrar el ejemplar");
                }
                listaLibros = LibrosCrud.getLibros();
                request.setAttribute("pagina", pagina);
                request.setAttribute("num_paginas", num_paginas);
                request.setAttribute("listado", listaLibros);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
     }else if (op.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Libros miLibro = LibrosCrud.getLibro(id);
                request.setAttribute("libro", miLibro);
                request.setAttribute("operacion", "Modificar");
                request.getRequestDispatcher("modificar.jsp").forward(request, response);
     }else if (op.equals("Modificar")) {
            
             int id = Integer.parseInt(request.getParameter("id"));
             String titulo= request.getParameter("titulo");
             String autor= request.getParameter("autor");
             long isbn = Long.parseLong(request.getParameter("isbn"));
             Float precio= Float.parseFloat(request.getParameter("precio"));
             
             Libros miLibro = new Libros();
             
             miLibro.setId(id);
             miLibro.setTitulo(titulo);
             miLibro.setAutor(autor);
             miLibro.setIsbn(isbn);
             miLibro.setPrecio(precio);
              
                if (LibrosCrud.actualizaLibro(miLibro)>0) {
                     request.setAttribute("mensaje", "El producto se ha actualizao");
                }else{
                     request.setAttribute("mensaje", "El producto no se ha podido actualizar");
                }
                request.setAttribute("operacion", "Modificar");
                listaLibros = LibrosCrud.getLibros();
                request.setAttribute("libro", miLibro);
                request.getRequestDispatcher("modificar.jsp").forward(request, response);
     }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
                
    }

}
