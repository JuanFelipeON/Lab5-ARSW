/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author 2092815
 
public class MainP {

   
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        
        Point[] pts1 = new Point[]{new Point(15, 10), new Point(10, 10)};
        Point[] pts2 = new Point[]{new Point(40, 10), new Point(10, 10)};
        Point[] pts3 = new Point[]{new Point(75, 10), new Point(10, 10)};
        try {
            bps.addNewBlueprint(new Blueprint("Juan", "Prueba", pts1));
            
            
            Blueprint blueprintPrueba = bps.getBlueprint("Juan", "Prueba");
            
            
            System.out.println(blueprintPrueba.toString());
            
            bps.addNewBlueprint(new Blueprint("Felipe", "Prueba2", pts2));
            bps.addNewBlueprint(new Blueprint("Ortiz", "Prueba3", pts3));
            
             System.out.println(bps.getAllBlueprints());
            
            //GrammarChecker gc = ac.getBean(GrammarChecker.class);
            //System.out.println(gc.check("la la la "));
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(MainP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(MainP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

*/