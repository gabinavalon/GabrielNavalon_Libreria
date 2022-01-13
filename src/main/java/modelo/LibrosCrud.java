package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Gabriel Navalón Soriano
 */
public class LibrosCrud {



    public static List<Libros> getLibros() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM Libros";
        Query q = manager.createNativeQuery(sql, Libros.class); //método para consultas en SQL
        List<Libros> librosBD = q.getResultList();

        return librosBD;
    }
    public static List<Libros> getLibrosPaginado(int offset, int lineas_pagina) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM Libros";
        Query q = manager.createNativeQuery(sql, Libros.class); //método para consultas en SQL
        
        
        q.setMaxResults(lineas_pagina);
        q.setFirstResult(offset);
        
        List<Libros> librosBD = q.getResultList();
        
        return librosBD;
    }

    public static int destroyLibro(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE FROM Libros WHERE id=" + id;
        Query q = manager.createQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;
    }

    public static int actualizaLibro(Libros miLibro) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Libros l SET l.titulo = :titulo, l.autor = :autor, l.isbn = :isbn, l.precio = :precio , l.portada = :portada  WHERE l.id = :id";
        Query q = manager.createQuery(sql, Libros.class);
        q.setParameter("titulo", miLibro.getTitulo());
        q.setParameter("autor", miLibro.getAutor());
        q.setParameter("isbn", miLibro.getIsbn());
        q.setParameter("precio", miLibro.getPrecio());
        q.setParameter("portada", miLibro.getPortada());
        q.setParameter("id", miLibro.getId());
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();
        return filasAfectadas;
    }

    public static Libros getLibro(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT l FROM Libros l WHERE l.id=" + id; //consulta en JPQL 
        Query q = manager.createQuery(sql, Libros.class); //método para consultas en JPQL
        Libros libroBD = (Libros) q.getSingleResult();
        return libroBD;
    }
    
     public static void insertaLibro(Libros libro) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(libro);
        manager.getTransaction().commit();
        }
}

