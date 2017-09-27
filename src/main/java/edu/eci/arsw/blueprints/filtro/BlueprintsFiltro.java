/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filtro;

import edu.eci.arsw.blueprints.model.Blueprint;

/**
 *
 * @author 2092815
 */
public interface BlueprintsFiltro {
    
    /**
     * 
     * @param bp the  blueprint to filter
     * @return blueprint filtered
     * @throws BlueprintsFiltroException if a blueprint bad.
     */
    public Blueprint filtrar(Blueprint bp) throws BlueprintsFiltroException;
}
