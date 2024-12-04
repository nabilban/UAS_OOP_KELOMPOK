package models;

abstract public class Person {
    private static int idCounter = 0;
    private int id;
    {
        id = ++idCounter;
    }
    protected String name;
    protected int age;
    protected String gender;

    public int getId() {
        return id;
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void showInformation() {
        System.out.println("--------------------------------");
        System.out.println("Name  : " + this.name);
        System.out.println("Age   : " + this.age);
        System.out.println("Gender: " + this.gender);
        System.out.println("--------------------------------");
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public int getAge() {
        return this.age;
    }

    public void setGender(String newGender) {
        this.gender = newGender;
    }

    public String getGender() {
        return this.gender;
    }
}
