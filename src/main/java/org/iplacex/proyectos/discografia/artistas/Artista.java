package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//Se debe crear la clase “Artista”, la cual debe llevar la anotación @Document con valor “artistas”
@Document("artistas")

public class Artista {
    

    // propiedades segun indidcacion.
    @Id
    public String _id;

    public String nombre;
    public List<String> estilos;
    public int anioFundacion;
    public boolean estaActivo;

    }
