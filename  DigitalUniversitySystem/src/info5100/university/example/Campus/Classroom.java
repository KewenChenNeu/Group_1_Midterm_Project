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
public class Classroom {

    int number;
    Floor floor;
    Sprinkler sprinkler;
    ArrayList<Seat> seats;

    public Classroom(int n, Floor f) {
        number = n;
        seats = new ArrayList();
        floor = f;
        
    
    }
    public void setSprinkler(String m, String sn){
        
        sprinkler = new Sprinkler(m, sn);
        
    }
    public void generateSeats(int size){
        
    }
    public Boolean isRoomSafe(){
        if(sprinkler==null) return false;        
        if(sprinkler.isActive()==true) return true;
        else return false;
    }

}
