/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@Service
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bps = null;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBLueprints() {
        try {

            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al cargar blueprints", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> getBPbyAuthor(@PathVariable String author) {
        try {
            if(bps.getBlueprintsByAuthor(author) == null){
                return new ResponseEntity<>("Error al cargar por author", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al cargar por author", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrint(@PathVariable String author, @PathVariable String bpname) {
        try {

            return new ResponseEntity<>(bps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al cargar un blueprint especifico", HttpStatus.NOT_FOUND);

        }
    }

    //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/blueprints -d '{"author":"Marco","points":[{"x":120,"y":140},{"x":115,"y":115}],"name":"dientes "}'

    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBlueprint(@RequestBody Blueprint bp) {
        try {
            
            bps.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al agregar un nuevo blueprint", HttpStatus.FORBIDDEN);
        }

    }
    
    
    //curl -i -X PUT -HContent-Type:application/json -HAccept:application/json http://localhost:8080/blueprints/puntos/Marco/dientes -d '{"point":{"x":45,"y":76}}'

    @RequestMapping(path = "/puntos/{author}/{bpname}", method = RequestMethod.PUT)
    public ResponseEntity<?> UpdateBlueprintPoint(@PathVariable String author, @PathVariable String bpname,@RequestBody Point p ) {
        try {
            bps.getBlueprint(author, bpname).addPoint(p);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al agregar un nuevop punto a un blueprint", HttpStatus.FORBIDDEN);
        }

    }
    
  
    
    
}

