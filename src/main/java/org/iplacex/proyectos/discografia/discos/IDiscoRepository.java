package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;



//-	Se debe crear la interface “IDiscoRepository”, la cual debe extender desde MongoRepository, pasandole la configuración necesaria correspondiente. Adicionalmente, debe tener la firma del método personalizado 

public interface IDiscoRepository extends MongoRepository<Disco, String>{


    //anotación @Query(“{ ‘idArtista’: ?0 }”) 
    @Query("{ 'idArtista': ?0 }")

    List<Disco> findDiscosByIdArtista(String idArtista);

}