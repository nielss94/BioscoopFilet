package com.filet.bioscoopfilet.DomainModel;

/**
 * Created by Niels on 3/28/2017.
 */

public class Visitor {

    private int visitorID;
    private String firstName;
    private String lastName;

    public Visitor(int visitorID, String firstName, String lastName) {
        this.visitorID = visitorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorID=" + visitorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
