package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//-	Se debe crear la clase “Disco”, la cual debe llevar la anotación @Document con valor “discos”
@Document("discos")

public class Disco {
    
    @Id
    public String _id;

    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
 }