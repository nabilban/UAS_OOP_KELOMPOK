package models;

public abstract class ItemsModels {
    private static int idCounter = 0;
    private int id;
    {
        id = ++idCounter;
    }
    private String name;
    private double price;

    public ItemsModels(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name.replace("_", " ");
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public abstract void displayDetails();
}
