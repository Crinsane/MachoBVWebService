/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.machobv.ejb;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author robgloudemans
 */
@Local
public interface RouteWorkLocal {

    RouteData getRouteData(String origin, String destination, Date travelDateTime);
    
}
