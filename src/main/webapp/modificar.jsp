<%-- 
    Document   : listar
    Created on : 29-nov-2021, 18:30:38
    Author     : Gabriel Navalón
--%>

<%@page import="modelo.Libros"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Listado Libros </title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/product/">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
    </style>

    
    <!-- Custom styles for this template -->
    <link href="product.css" rel="stylesheet">
  </head>
  <body>
    
<header class="site-header sticky-top py-1">
  <nav class="container d-flex flex-column flex-md-row justify-content-between">
    <a class="py-2" href="#" aria-label="Product">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mx-auto" role="img" viewBox="0 0 24 24"><title>Libreria Paradise</title><circle cx="12" cy="12" r="10"/><path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"/></svg>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="index.html">Inicio</a>
    <a class="py-2 d-none d-md-inline-block" href="Servlet?op=listar">Listado</a>    
    <a class="py-2 d-none d-md-inline-block" href="Servlet?op=insert">Insertar</a>
    <a class="py-2 d-none d-md-inline-block" href="#">Contacto</a>
    <a class="py-2 d-none d-md-inline-block" href="#">¿Algún problema?</a>

  </nav>
</header>

<main>
    
      <body>
                <% String mensaje = (String)request.getAttribute("mensaje"); %>
      <div class="position-relative overflow-hidden p-3 p-md-3 m-md-3 text-center bg-light" >
      <div class="col-md-3 p-lg-3 mx-auto my-3">
      <p class="lead fw-normal">Insertar nuevo registro</p>
      <p class="lead fw-normal">${mensaje}</p>
        </div>
        
        <form action="Servlet?op=${operacion}" method="POST" enctype="multipart/form-data">
            <input type="hidden" value="${operacion}" name="op">
            <input type="hidden" value="${libro.id}" name="id" class="form-control" />
            <div class="row"> 
            <div class="col-12 col-sm-6 col-lg-3">
                <label for="titulo">Titulo: </label>
                <input type="text" value="${libro.titulo}" name="titulo" class="form-control"/>
            </div>
            <div class="col-12 col-sm-6 col-lg-3">
                <label for="autor">Autor: </label>
                <input type="text" value="${libro.autor}" name="autor" class="form-control"/>
            </div>
            <div class="col-12 col-sm-6 col-lg-3">
                <label for="isbn">ISBN: </label>
                <input type="text" value="${libro.isbn}" name="isbn" class="form-control"/>
            </div>
            <div class="col-12 col-sm-6 col-lg-3">
                <label for="precio">Precio: </label>
                <input type="text" value="${libro.precio}" name="precio" class="form-control"/>
            </div>
            <div class="col-12 col-sm-6 col-lg-3">
                <label for="portada">Portada: ${producto.imagen} </label>
                <input type="file"  name="portada" class="form-control"/>
                <img src="ficheros/${libro.portada}" width="100"/>
            </div>
            
            <input type="submit" value="${operacion}" class="btn btn-default">
            
        </form>
            </div> 
      


</main>

<footer class="container py-5">
  <div class="row">
    <div class="col-12 col-md">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mb-2" role="img" viewBox="0 0 24 24"><title>Panic Room</title><circle cx="12" cy="12" r="10"/><path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"/></svg>
      <small class="d-block mb-3 text-muted">&copy; 2021–2022</small>
    </div>
    <div class="col-6 col-md">
      <h5>Gabriel Navalón Soriano</h5>
    </div>
    <div class="col-6 col-md">
      <h5>2º DAW</h5>
    </div>
    <div class="col-6 col-md">
      <h5>DAW</h5>
    </div>
  </div>
</footer>


    <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
    
      
  </body>
</html>
