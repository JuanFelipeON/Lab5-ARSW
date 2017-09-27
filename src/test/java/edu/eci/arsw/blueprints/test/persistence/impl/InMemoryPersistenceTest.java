/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filtro.BlueprintsFiltroException;
import edu.eci.arsw.blueprints.filtro.impl.RedunFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts0 = new Point[]{new Point(40, 40), new Point(15, 15)};
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        ibpp.saveBlueprint(bp0);

        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.", ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.", ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }

    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        Point[] pts2 = new Point[]{new Point(10, 10), new Point(20, 20)};
        Blueprint bp2 = new Blueprint("john", "thepaint", pts2);

        try {
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        } catch (BlueprintPersistenceException ex) {

        }

    }

    @Test
    public void getBPByAuthorTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("Goku", "Monalisa", pts);

        Point[] pts1 = new Point[]{new Point(10, 10), new Point(10, 10)};
        Blueprint bp1 = new Blueprint("Goku", "Abstract", pts1);

        Point[] pts2 = new Point[]{new Point(10, 10), new Point(0, 0)};
        Blueprint bp2 = new Blueprint("Goku", "La ultima cena", pts2);

        Point[] pts3 = new Point[]{new Point(0, 0), new Point(11, 11)};
        Blueprint bp3 = new Blueprint("kira", "Poker", pts3);

        try {
            ibpp.saveBlueprint(bp);
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            ibpp.saveBlueprint(bp3);

        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the  blueprint.");
        }

        try {
            assertNotNull("Loading all blueprints the Author returned null.", ibpp.getBlueprintsByAuthor("Goku").size());

            assertEquals("Loading all blueprints the Author returned a different size of blueprints.", ibpp.getBlueprintsByAuthor("Goku").size(), 3);

        } catch (BlueprintNotFoundException ex) {
            fail("failed in " + ex.getMessage());
        }

    }
    
    @Test
    public void filtroRebun() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        
        RedunFilter rf = new RedunFilter();

        Point[] pts = new Point[]{new Point(0, 0), new Point(1, 1), new Point(1,1)};
        Blueprint bp = new Blueprint("Goku", "Monalisa", pts);


        try {
            ibpp.saveBlueprint(rf.filtrar(bp));
            

        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the  blueprint.");
        } catch (BlueprintsFiltroException ex) {
           fail("fallo al filtrar");
        }

        try {
            assertNotNull("Loading all blueprints the Author returned null.", ibpp.getBlueprintsByAuthor("Goku").size());


            assertEquals("Error en el filtro Rebundante.", 2, ibpp.getBlueprint("Goku", "Monalisa").getPoints().size());

        } catch (BlueprintNotFoundException ex) {
            fail("failed in " + ex.getMessage());
        }

    }

}
