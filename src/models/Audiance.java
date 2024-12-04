package models;

import java.util.ArrayList;
import java.util.List;

public class Audiance extends Person {
    private boolean haveTicket = false;
    private ArrayList<Items> items = null;

    public Audiance(
            String name,
            int age,
            String gender,
            boolean haveTicket,
            ArrayList<Items> items) {
        super(name, age, gender);
        this.haveTicket = haveTicket;
        this.items = items;
    }

    @Override
    public void showInformation() {
        System.out.println("--------------------------------");
        System.out.println("ID    : " + getId());
        System.out.println("Name  : " + this.name);
        System.out.println("Age   : " + this.age);
        System.out.println("Gender: " + this.gender);
        System.out.println("Ticket: " + this.haveTicket);
        if (this.items != null) {
            System.out.println("Items : ");
            for (Items item : this.items) {
                item.displayDetails();
            }
        }
        System.out.println("--------------------------------");
    }

    public void setHaveTicket(boolean haveTicket) {
        this.haveTicket = haveTicket;
    }

    public boolean getHaveTicket() {
        return this.haveTicket;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return this.items;
    }

}
