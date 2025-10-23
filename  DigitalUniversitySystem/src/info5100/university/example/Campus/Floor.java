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
public class Floor {

    int number;
    ArrayList<Classroom> rooms;
    Building building;

    public Floor(int n, Building b) {
        number = n;
        building = b;
        rooms = new ArrayList();
    }
    public void newRoom(int n){
            Classroom r = new Classroom(n, this); //pass the floor object for reference
            rooms.add(r);
    }
    public Boolean isFloorSafe(){
        
        //check each classroom. If all classrooms are safe then floor is safe
        return true;
    }
}
