package abstracts;

public abstract class Items {
    private String name;
    private double price;

    public Items(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}
