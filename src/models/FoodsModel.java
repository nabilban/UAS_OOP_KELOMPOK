package models;

public class FoodsModel extends ItemsModels {
    private String nama;
    private double harga;
    private int stok;

    public FoodsModel(String nama, double harga, int stok) {
        super(nama, harga);
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama.replace("_", " ");
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public void displayDetails() {
        System.out.println("- ID                  :" + this.getId());
        System.out.println("- Nama Makanan:       :" + this.nama);
        System.out.println("- Harga Makanan:      :" + this.harga);
        System.out.println("- Stok Makanan:       :" + this.stok);
    }

}
