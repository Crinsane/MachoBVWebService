/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.machobv.ejb;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author robgloudemans
 */
@Stateless
public class RouteWork implements RouteWorkLocal {
    
    String url = "jdbc:mysql://localhost:3306/macho";
    String driver = "com.mysql.jdbc.Driver";
        
    String userName = "root";
    String password = "root";
    
    Connection con = null;
            
    
    public RouteWork()
    {
        try
        {
            Class.forName(this.driver).newInstance();
        
            con = DriverManager.getConnection(url, userName, password);
        } catch(Exception ex) {
            
        }
    }

    @Override
    public RouteData getRouteData(String origin, String destination, Date travelDateTime) {
        String originID;
        String destinationID;
        
        try {
            originID = getOriginID(origin);
            destinationID = getDestinationID(destination);
            
            System.out.println(originID);
            System.out.println(destinationID);
            
        } catch (Exception ex) {
            System.out.println("Exception");
            System.out.println(ex.getMessage());
            ex.printStackTrace(new PrintStream(System.out));
        }
        
        RouteData routeData = new RouteData();
        
        routeData.setDistance(100);
        routeData.setDuration(30);
        routeData.setDelay(10);
        routeData.setClosed(false);
        
        System.out.println("Jazeker wat route data!");
        
        return routeData;
    }

    private String getDestinationID(String destination) throws SQLException {
        PreparedStatement st = 
                con.prepareStatement("SELECT id FROM city WHERE name LIKE ? LIMIT 1");
        st.setString(1, "%" + destination + "%");
        
        ResultSet result;
        result = st.executeQuery();
        
        String destinationID = "";
        
        result.next();
        
        destinationID = result.getString(1);
        
        return destinationID;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private String getOriginID(String origin) throws SQLException {
        PreparedStatement st =
                con.prepareStatement("SELECT id FROM city WHERE name LIKE ? LIMIT 1");
        st.setString(1, "%" + origin + "%");
        
        ResultSet result;
        result = st.executeQuery();
        
        String originID = "";
        
        result.next();
        
        originID = result.getString(1);
        
        return originID;
    }
}
