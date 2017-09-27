/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import static java.lang.reflect.Array.set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

 
    private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
        
        Point[] pts1 = new Point[]{new Point(150, 170), new Point(115, 115)};
        Blueprint bp1 = new Blueprint("Juan", "Justicia", pts1);
        Point[] pts2 = new Point[]{new Point(160, 180), new Point(115, 115)};
        Blueprint bp2 = new Blueprint("Juan", "Injusticia", pts2);
        
        
        Point[] pts3 = new Point[]{new Point(140, 140), new Point(115, 115)};
        Blueprint bp3 = new Blueprint("Ana", "Dulces", pts3);
        
        
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3 );

        
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> bps = new LinkedHashSet<>();
        
        
        for (Blueprint bp : blueprints.values()) {
            if(bp.getAuthor().equals(author)){
                bps.add(bp);
            }
        }
        if (bps == null) {
            throw new BlueprintNotFoundException("No se encontro el autor dado" + author );
        }
        return bps;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Blueprint> getAllBlueprint() throws BlueprintNotFoundException {
        Set<Blueprint> bps = new LinkedHashSet<>();
        
        
        for (Blueprint bp : blueprints.values()) {
            bps.add(bp);
        }
        return bps;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
