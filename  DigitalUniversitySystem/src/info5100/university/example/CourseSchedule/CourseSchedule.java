/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseSchedule {

    CourseCatalog coursecatalog;

    ArrayList<CourseOffer> schedule;
    String semester;

    public CourseSchedule(String s, CourseCatalog cc) {
        semester = s;
        coursecatalog = cc;
        schedule = new ArrayList();

    }

    public CourseOffer newCourseOffer(String n) {

        Course c = coursecatalog.getCourseByNumber(n);
        if (c == null) {
            return null;
        }
        CourseOffer co;
        co = new CourseOffer(c);
        schedule.add(co);
        return co;
    }

    public CourseOffer getCourseOfferByNumber(String n) {

        for (CourseOffer co : schedule) {

            if (co.getCourseNumber().equals(n)) {
                return co;
            }
        }
        return null;
    }
    
    public ArrayList<CourseOffer> getCourseOfferByCourseName(String n){
        ArrayList<CourseOffer> courseOffers = new ArrayList<CourseOffer>();
        for (CourseOffer co : schedule) {

            if (co.getCourseName().toLowerCase().contains(n.toLowerCase())) {
                courseOffers.add(co);
            }
        }
        
        return courseOffers;
    }
    
    public ArrayList<CourseOffer> getCourseOfferByTeacherName(String n){
        ArrayList<CourseOffer> courseOffers = new ArrayList<CourseOffer>();
        for (CourseOffer co : schedule) {

            if (co.getTeacherName().toLowerCase().contains(n.toLowerCase())) {
                courseOffers.add(co);
            }
        }
        
        return courseOffers;
    }

    public int calculateTotalRevenues() {
        int sum = 0;
        for (CourseOffer co : schedule) {

            sum = sum + co.getTotalCourseRevenues();

        }
        return sum;
    }

    public java.util.List<CourseOffer> getAllCourseOffers() {
        return schedule;
    }

    public ArrayList<CourseOffer> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<CourseOffer> schedule) {
        this.schedule = schedule;
    }

    
}
