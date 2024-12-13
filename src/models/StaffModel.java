package models;

public class StaffModel extends PersonModel {
    private String status = "Karyawan";

    public StaffModel(
        String name,
        int age,
        String gender
    ) {
        super(name, age, gender);
    }

    @Override
    public void showInformation() {
        System.out.println("--------------------------------");
        System.out.println("ID    : " + getId());
        System.out.println("Name  : " + this.name);
        System.out.println("Age   : " + this.age);
        System.out.println("Gender: " + this.gender);
        System.out.println("Status: " + this.status);
        System.out.println("--------------------------------");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }
}
