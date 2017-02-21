package uk.ac.tees.q5045715.lecturemanager;

/**
 * Created by q5045715 on 07/02/2017.
 */

public class Lecturer {


    private int ID;
    private String name;
    private String phoneNumber;


    // Constructor without all parameter
    public Lecturer(int ID, String name, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Constructor without id parameter
    public Lecturer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}

