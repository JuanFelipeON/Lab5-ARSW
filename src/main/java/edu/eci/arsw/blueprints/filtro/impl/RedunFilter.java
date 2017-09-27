/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filtro.impl;

import edu.eci.arsw.blueprints.filtro.BlueprintsFiltro;
import edu.eci.arsw.blueprints.filtro.BlueprintsFiltroException;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2092815
 */
@Service
public class RedunFilter implements BlueprintsFiltro{

    @Override
    public Blueprint filtrar(Blueprint bp) throws BlueprintsFiltroException {
            List<Point> lp = bp.getPoints();
            
            List<Point> lp1 = new ArrayList<>();
            
           
            for(int i = 0; i<lp.size();i++){
                Point p = lp.get(i);
                if(i != lp.size()-1 && repetidos(p,lp.get(i+1))){
                    //lp.remove(i+1);
                    lp1.add(p);
                    i++;
                    
                }else{
                    lp1.add(p);
                }
            }
            
            
            bp.setPoints(lp1);
            return bp;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean repetidos(Point p1, Point p2){
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        boolean cx = false, cy = false;
        if(x1 == x2){
            cx = true;
        }
        if(y1 == y2){
            cy = true;
        }
        return cx && cy;
    }
    
   
}
