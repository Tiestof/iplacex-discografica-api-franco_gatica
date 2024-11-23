package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;


//crear la interface “IArtistaRepository” , la cual debe extender desde MongoRepository, pasandole la configuración correspondiente

public interface IArtistaRepository extends MongoRepository<Artista, String>{

}
