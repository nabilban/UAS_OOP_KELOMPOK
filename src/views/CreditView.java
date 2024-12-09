package views;

import utils.ScreenCleaning;

public class CreditView implements SubCredit {

    @Override
    public void programsDefinition() {
        ScreenCleaning.ClearScreen();
        System.out.println("-----------------------");
        System.out.println("| Apa itu Nova Cinema |");
        System.out.println("-----------------------");
        System.out.println();

        System.out.println("""
        -------------------------------------------------------
        | Nova Cinema adalah program manajemen bioskop,       |
        | di mana pengguna dapat melihat daftar film,         |
        | daftar makanan, membeli tiket, dan membeli makanan. |
        | Terdapat juga fitur "Masuk sebagai Pegawai",        |
        | di mana pegawai dapat menambah daftar film,         |
        | dan menambah stok makanan.                          | 
        ------------------------------------------------------- 
        """);
    }

    @Override
    public void availableFeatures() {
        ScreenCleaning.ClearScreen();
        System.out.println("-----------------------------");
        System.out.println("| Fitur-fitur yang tersedia |");
        System.out.println("-----------------------------");
        System.out.println();

        System.out.println("""
        -------------------------------------------
        | * Masuk sebagai Penonton                |
        |     * Daftar Film                       |
        |     * Daftar Film yang Sedang Tayang    |
        |     * Pesan Tiket                       |
        |     * Daftar Makanan dan Minuman        |
        |     * Pesan Makanan dan Minuman         |
        | * Masuk sebagai Pegawai                 |
        |     * Datar Film                        |
        |     * Daftar Film yang Sedang Tayang    |
        |     * Tambah Film                       |
        |     * Daftar Makanan dan Minuman        |
        |     * Tambah Makanan dan Minuman        |
        | * About Us (Credit)                     |
        |     * Anggota Kelompok                  |
        |     * Definisi Program                  |
        |     * Fitur-fitur yang Tersedia         |
        |     * Materi atau Konsep yang Digunakan |
        -------------------------------------------
        """);
    }

    @Override
    public void conceptsUsed() {
        ScreenCleaning.ClearScreen();
        System.out.println("-------------------------------------");
        System.out.println("| Materi atau Konsep yang Digunakan |");
        System.out.println("-------------------------------------");
        System.out.println();

        System.out.println("""
        ---------------------------------------------
        | * Class (Yes)                             |
        | * Inheritance (Yes)                       |
        | * Polimorfisme (Yes)                      | 
        | * Organisasi Source Code / Package (Yes)  |
        | * MVC (Yes)                               |
        | * Extend (Yes)                            |
        | * Implement (Yes)                         |
        | * Deprecated (Yes)                        |
        ---------------------------------------------
        """);
    }

    @Override
    public void groupMembers() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------");
        System.out.println("| Kelompok 2 |");
        System.out.println("--------------");
        System.out.println();

        System.out.println("""
        ---------------------------------------------
        | * Rafly Syauqi Abrori       (23051204370) |
        | * M. Farhan Nabil           (23051204372) | 
        | * Naufal Andrianto Nugraha  (23051204373) | 
        | * Rafi Arnandha Pramudianto (23051204374) | 
        | * Lukman Adi Wijaya         (23051204380) | 
        ---------------------------------------------
        """);
    }
}
