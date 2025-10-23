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
public class Building {
    int number;
    ArrayList<Floor> floors;
    Address address;
    public Building(int n, Address ad){
        number = n;
        address = ad;
        floors = new ArrayList();
    }
    public Floor addNewFloor(int n){
        Floor f = new Floor(n, this); //reference back to building;
        floors.add(f);
        return f;
    }
    public Boolean isBuildingSafe(){
        
        //Building is safe if all floors are safe
        return true; //to be completed
    }
    
}
