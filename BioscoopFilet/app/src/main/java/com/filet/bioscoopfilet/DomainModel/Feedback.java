package com.filet.bioscoopfilet.DomainModel;

/**
 * Created by Niels on 3/28/2017.
 */

public class Feedback {

    private int ID;
    private Visitor visitor;
    private String description;

    public Feedback(int ID, Visitor visitor, String description) {
        this.ID = ID;
        this.visitor = visitor;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "ID=" + ID +
                ", visitor=" + visitor +
                ", description='" + description + '\'' +
                '}';
    }
}
