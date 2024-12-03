package models;

import abstracts.*;

public class Audiance extends Person {
    private boolean haveTicket = false;

    public Audiance(
            String name,
            int age,
            String gender,
            boolean haveTicket) {
        super(name, age, gender);
        this.haveTicket = haveTicket;
    }

    @Override
    public void showInformation() {
        System.out.println("--------------------------------");
        System.out.println("Name  : " + this.name);
        System.out.println("Age   : " + this.age);
        System.out.println("Gender: " + this.gender);
        System.out.println("Ticket: " + this.haveTicket);
        System.out.println("--------------------------------");
    }

    public void setHaveTicket(boolean haveTicket) {
        this.haveTicket = haveTicket;
    }

    public boolean getHaveTicket() {
        return this.haveTicket;
    }
}
