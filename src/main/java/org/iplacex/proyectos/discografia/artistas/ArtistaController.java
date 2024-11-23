package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// •	Debe llevar la anotación “@RestController”
// •	Debe llevar la anotación “@CrossOrigin”
// •	Debe llevar la anotación “@RequestMapping” con valor “/api”

@RestController
@RequestMapping("/api")
@CrossOrigin


public class ArtistaController {

    // •	Debe llevar una propiedad privada de tipo IArtistaRepository con todas las configuraciones necesarias
    @Autowired
    private IArtistaRepository artistaRepo;

    //•	Se deben crear controladores para la creación, obtención, actualización y eliminación de registros



    //•	El método “HandleInsertArtistaRequest” debe permitir la inserción de registro, retornando dicho objeto
    // POST Enviar datos del artista
    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista tempArtista = artistaRepo.insert(artista);
        return new ResponseEntity<>(tempArtista, HttpStatus.CREATED);
    }


    //•	El método “HandleGetArtistaRequest” debe permitir obtener un registro en base a su id
    // GET Obtener datos de un artista por ID
    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> tempArtista = artistaRepo.findById(id);

        if (!tempArtista.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tempArtista.get(), HttpStatus.OK);
    }


    //•	El método “HandleGetAristasRequest” debe permitir obtener todos los registros de la colección “artistas”
    // GET all Obtener todos los artistas
    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> HandleGetArtistasRequest() {
        List<Artista> artistas = artistaRepo.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    //•	El método “HandleUpdateArtistaRequest” debe permitir actualizar un registro en base a su id y el objeto entregado
    // PUT Actualizar un artista por ID
    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleUpdateArtistaRequest(
        @PathVariable("id") String id, 
        @RequestBody Artista artista
    ) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        artista._id = id;
        Artista temp = artistaRepo.save(artista);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


    // •	El método “HandleDeleteArtistaRequest” debe permitir eliminar un registro en base a su id
    // DELETE Eliminar un artista por ID
    @DeleteMapping(value = "/artista/{id}")
    public ResponseEntity<Artista> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Artista temp = artistaRepo.findById(id).get();
        artistaRepo.deleteById(id);
        return new ResponseEntity<>(temp, HttpStatus.GONE);
    }
}
