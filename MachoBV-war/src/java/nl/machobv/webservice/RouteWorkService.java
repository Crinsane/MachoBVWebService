/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.machobv.webservice;

import java.util.Date;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import nl.machobv.ejb.RouteData;
import nl.machobv.ejb.RouteWorkLocal;

/**
 *
 * @author robgloudemans
 */
@WebService(serviceName = "RouteWorkService")
public class RouteWorkService {
    @EJB
    private RouteWorkLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getRouteData")
    public RouteData getRouteData(@WebParam(name = "origin") String origin, @WebParam(name = "destination") String destination, @WebParam(name = "travelDateTime") Date travelDateTime) {
        return ejbRef.getRouteData(origin, destination, travelDateTime);
    }
    
}
