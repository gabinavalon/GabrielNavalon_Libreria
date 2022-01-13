/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Libros;
import modelo.LibrosCrud;

/**
 *
 * @author DAW-A
 */
@MultipartConfig( fileSizeThreshold=1024*1024*10, 
            maxFileSize=1024*1024*50, maxRequestSize=1024*1024*10)
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    final int NUM_LINEAS_PAGINA = 5;
    List<Libros> listaLibros = LibrosCrud.getLibros();
    int pagina = 1;
    int offset = 0;      
    String path = "";          
    int num_paginas = (int) Math.ceil(listaLibros.size()/(double)NUM_LINEAS_PAGINA);
    
    public void init(ServletConfig config){
    path = config.getServletContext().getRealPath("").
                        concat(File.separator).concat("ficheros");

    }    
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
request.setAttribute("path", path);
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

             String portada = this.subirArchivo(request, response);
             
             Libros miLibro = new Libros();
             
             miLibro.setTitulo(titulo);
             miLibro.setAutor(autor);
             miLibro.setIsbn(isbn);
             miLibro.setPrecio(precio);
             miLibro.setPortada(portada);
              
                LibrosCrud.insertaLibro(miLibro);
                
                listaLibros = LibrosCrud.getLibros();
              
                request.setAttribute("listado", listaLibros);
                request.setAttribute("mensaje", "");
                request.setAttribute("pagina", pagina);
request.setAttribute("path", path);
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
                request.setAttribute("path", path);
                request.setAttribute("operacion", "Modificar");
                request.getRequestDispatcher("modificar.jsp").forward(request, response);
     }else if (op.equals("Modificar")) {
            
             int id = Integer.parseInt(request.getParameter("id"));
             String titulo= request.getParameter("titulo");
             String autor= request.getParameter("autor");
             long isbn = Long.parseLong(request.getParameter("isbn"));
             Float precio= Float.parseFloat(request.getParameter("precio"));
             String portada = this.subirArchivo(request, response);
             
             Libros miLibro = new Libros();
             
             miLibro.setId(id);
             miLibro.setTitulo(titulo);
             miLibro.setAutor(autor);
             miLibro.setIsbn(isbn);
             miLibro.setPrecio(precio);
             miLibro.setPortada(portada);
              
                if (LibrosCrud.actualizaLibro(miLibro)>0) {
                     request.setAttribute("mensaje", "El producto se ha actualizao");
                }else{
                     request.setAttribute("mensaje", "El producto no se ha podido actualizar");
                }
                request.setAttribute("operacion", "Modificar");
                listaLibros = LibrosCrud.getLibros();
                request.setAttribute("libro", miLibro);
request.setAttribute("path", path);
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

  public String  subirArchivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    // path = request.getServletContext().getRealPath("").concat(File.separator).concat("ficheros");
    Part filePart = request.getPart("portada"); // Obtiene el archivo el input en el form se llama imagen
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

    //InputStream fileContent = filePart.getInputStream(); //Lo transforma en InputStream

    //String path="/archivos/";
    File uploads = new File(path); //Carpeta donde se guardan los archivos
    uploads.mkdirs(); //Crea los directorios necesarios
    File file = File.createTempFile("cod"+""+"-", "-"+fileName, uploads); //Evita que hayan dos archivos con el mismo nombre

    try (InputStream input = filePart.getInputStream()){
        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    //return file.getPath();
    String archivo = file.getName();
    return archivo;
}

}
