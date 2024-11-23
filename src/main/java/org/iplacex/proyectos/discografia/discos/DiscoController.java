package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//•	Debe llevar la anotación “@RestController”
//•	Debe llevar la anotación “@CrossOrigin”
//•	Debe llevar la anotación “@RequestMapping” con valor “/api”

@RestController
@RequestMapping("/api")
@CrossOrigin

//-	Se debe crear la clase controladora “DiscoController”, 
public class DiscoController {

    //•	Debe llevar una propiedad privada de tipo IDiscoRepository con todas las configuraciones necesarias
    @Autowired
    private IDiscoRepository discoRepo;

    //•	Debe llevar una propiedad privada de tipo IArtistaRepository con todas las configuraciones necesarias
    @Autowired
    private IArtistaRepository artistaRepo;

    //•	El método “HandlePostDiscoRequest” debe permitir la inserción de registro, retornando dicho objeto. 
    // POST Enviar datos del disco
    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepo.existsById(disco.idArtista)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Disco temp = discoRepo.insert(disco);
        return new ResponseEntity<>(temp, HttpStatus.CREATED);
    }

    //•	El método “HandleGetDiscoRequest” debe permitir obtener un registro en base a su id
    // GET por ID Obtener datos de un disco por ID
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> temp = discoRepo.findById(id);

        if (!temp.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(), HttpStatus.OK);
    }

    //•	El método “HandleGetDiscosRequest” debe permitir obtener todos los registros de la colección “discos”
    // GET all Obtener todos los discos
    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepo.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    //•	El método “HandleGetDiscosByArtistaRequest” debe permitir obtener todos los discos de un artista en base a su id desde la colección “discos”.
    // GET Discos por Artista ID Obtener discos por ID de artista
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        if (!artistaRepo.existsById(idArtista)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<Disco> discos = discoRepo.findDiscosByIdArtista(idArtista);

        if (discos.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
