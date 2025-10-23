/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Campus;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class Campus {
    
    Address address;
    ArrayList<Building> buildings;
    public Campus(Address a){
    
        address = a;
        buildings = new ArrayList();
        
    }
    
}
