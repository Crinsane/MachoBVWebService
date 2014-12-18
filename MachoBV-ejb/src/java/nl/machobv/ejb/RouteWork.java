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
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author robgloudemans
 */
@Stateless
public class RouteWork implements RouteWorkLocal {

    @Override
    public RouteData getRouteData(String origin, String destination, Date travelDateTime) {
        
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/macho";
        String driver = "com.mysql.jdbc.Driver";
        
        String userName = "root";
        String password = "root";
        
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, userName, password);
            
            PreparedStatement st =
                    con.prepareStatement("SELECT name FROM city WHERE name LIKE ? LIMIT 1");
            st.setString(1, "%" + origin + "%");
            
            ResultSet result = st.executeQuery();
            
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            
            
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
