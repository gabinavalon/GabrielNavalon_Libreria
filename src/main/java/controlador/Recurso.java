/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package controlador;

import java.util.List;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Libros;
import modelo.LibrosCrud;

/**
 * REST Web Service
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Path("rest")
public class Recurso {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Recurso
     */
    public Recurso() {
    }

 
    @GET
    public Response ping(){
        return Response
                .ok("Ping funciona!")
                .build();
    }
    
    @GET
    @Path("/test/{name}")
    public Response ping2( @PathParam("name") String name ){
                return Response
                .ok("Hola " + name)
                .build();
    }  
    
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {

        //TODO return proper representation object
       JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject persona = factory.createObjectBuilder()
     .add("nombre", "Juan")
     .add("apellido", "Sanchez")
     .add("edad", 25)
     .add("direccion", factory.createObjectBuilder()
         .add("calle", "Norte 21")
         .add("ciudad", "Alcázar")
         .add("provincia", "Ciudad Real ")
         .add("codpostal", "10021"))
     .add("telefono", factory.createArrayBuilder()
         .add(factory.createObjectBuilder()
             .add("tipo", "casa")
             .add("numero", "212 555-1234"))
         .add(factory.createObjectBuilder()
             .add("tipo", "fax")
             .add("numero", "646 555-4567")))
     .build();
    /* ResponseBuilder res = Response.ok(persona.toString());   
    return res.build();*/
    return Response
                .ok(persona.toString())
                .build();
    }
 
     @GET
     @Path("/libros/")
     @Produces(MediaType.APPLICATION_JSON)
     public List<Libros> getLibros(){
        List<Libros>  misProductos = LibrosCrud.getLibros();
        return misProductos;
}    
     
     @GET
     @Path("/libro/{id}")
     @Produces(MediaType.APPLICATION_JSON)
     public Libros getLibro(@PathParam("id") int id){
        Libros  miLibro = LibrosCrud.getLibro(id);
        return miLibro;
     }
     
    @PUT
    @Path("/libro/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Libros updateLibro(Libros libro) {
        LibrosCrud.actualizaLibro(libro);
        return libro;
    }
    
    @POST
    @Path("/libro/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void SetLibro(Libros libro) {
        LibrosCrud.insertaLibro(libro);
            }
    
    @DELETE
     @Path("/libro/{id}")
     public void borraLibro(@PathParam("id") int id){
       LibrosCrud.destroyLibro(id);
     }

     
}
