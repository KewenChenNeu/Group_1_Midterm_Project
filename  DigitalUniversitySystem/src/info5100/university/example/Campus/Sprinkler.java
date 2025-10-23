/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Campus;

/**
 *
 * @author kal bugrara
 */
public class Sprinkler {

    Boolean status; // which takes on the true if sprinkler is active otherwise false 
    String model;
    String serialnumber;
    String lastinspectiondate; 
//  Sprinker spinkler = new Sprikler("294424", "001349");    
    public Sprinkler(String m, String s)
    {
        status = true;
        model = m;
        serialnumber = s;
    }
    // if(spinkler.isActive()) then send alert  ...
    public Boolean isActive(){
        return status;
    }
    public void reset(){
        status = true;
    }    
    public void disable(){
        status = false;
    } 
}
