package models;

import abstracts.Person;

public class Staff extends Person{
    public Staff(
        String name,
        int age,
        String gender
    ) {
        super(name, age, gender);
    }
}
