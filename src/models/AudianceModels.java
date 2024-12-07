package models;

import java.util.ArrayList;
import java.util.List;

public class AudianceModels extends PersonModel {
    private boolean haveTicket = false;
    private ArrayList<ItemsModels> items = null;

    public AudianceModels(
            String name,
            int age,
            String gender,
            boolean haveTicket,
            ArrayList<ItemsModels> items) {
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
            for (ItemsModels item : this.items) {
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

    public void setItems(ArrayList<ItemsModels> items) {
        this.items = items;
    }

    public List<ItemsModels> getItems() {
        return this.items;
    }

}
