/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author ADVAN
 */
import exception.ValidationException;
import service.WarkopService;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);

    private static void header() {
        System.out.println("==============================");
        System.out.println("        " + WarkopService.APP_NAME);
        System.out.println("==============================");
        System.out.println();
    }

    private static void pause() {
        System.out.print("\nTekan ENTER untuk lanjut...");
        in.nextLine();
    }

    private static boolean confirm(String prompt) {
        System.out.print(prompt + " (y/n): ");
        String s = in.nextLine().trim();
        char c = s.isEmpty() ? 'N' : Character.toUpperCase(s.charAt(0));
        return c == 'Y';
    }

    public static void main(String[] args) {
        WarkopService svc = new WarkopService();

        while (true) {
            header();
            System.out.println("Menu:");
            System.out.println("1. Lihat Semua Katalog");
            System.out.println("2. Lihat Katalog per Kategori");
            System.out.println("3. Cari Menu");
            System.out.println("4. Tambah ke Keranjang");
            System.out.println("5. Hapus dari Keranjang");
            System.out.println("6. Lihat Keranjang");
            System.out.println("7. Checkout");
            System.out.println("0. Keluar");
            System.out.print("Pilih [0-7]: ");

            String pilih = in.nextLine().trim();
            System.out.println();

            switch (pilih) {
                case "1":
                    svc.printCatalog();
                    pause();
                    break;

                case "2":
                    System.out.print("Masukkan kategori (F/D/S): ");
                    String k = in.nextLine();
                    if (k.isEmpty()) {
                        System.out.println("Kategori kosong.");
                    } else {
                        char c = Character.toUpperCase(k.charAt(0));
                        svc.printCatalogByCategory(c);
                    }
                    pause();
                    break;

                case "3":
                    System.out.print("Keyword: ");
                    String q = in.nextLine();
                    svc.searchCatalog(q);
                    pause();
                    break;

                case "4":
                    try {
                        System.out.print("Kode menu: ");
                        String code = in.nextLine();
                        System.out.print("Qty: ");
                        int qty = Integer.parseInt(in.nextLine());
                        svc.addToCart(code, qty);
                        System.out.println("Berhasil ditambahkan.");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Qty harus berupa angka bulat.");
                    } catch (ValidationException ve) {
                        System.out.println("Gagal: " + ve.getMessage());
                    }
                    pause();
                    break;

                case "5":
                    System.out.print("Kode menu yang dihapus: ");
                    String rm = in.nextLine();
                    if (confirm("Yakin hapus item " + rm + "?")) {
                        boolean ok = svc.removeFromCart(rm);
                        System.out.println(ok ? "Berhasil dihapus." : "Kode tidak ditemukan.");
                    } else {
                        System.out.println("Dibatalkan.");
                    }
                    pause();
                    break;

                case "6":
                    svc.printCart();
                    pause();
                    break;

                case "7": 
                    if (svc.getTotalBeforeTax() == 0) {
                        System.out.println("Keranjang masih kosong.");
                        pause();
                        break;
                    }
                    System.out.print("Nama pelanggan: ");
                    String nama = in.nextLine().trim();
                    if (nama.isEmpty()) nama = "Tamu";

                    System.out.println("\n===== STRUK PEMBAYARAN =====");
                    System.out.println("Pelanggan : " + formatName(nama));
                    svc.printCart();
                    System.out.println("============================\n");

                    if (confirm("Konfirmasi pembayaran")) {
                        System.out.println("Pembayaran selesai. Terima kasih!");
                        svc.clearCart();
                    } else {
                        System.out.println("Dibatalkan.");
                    }
                    pause();
                    break;

                case "0":
                    System.out.println("Sampai jumpa!");
                    return;

                default:
                    System.out.println("Menu tidak dikenal.");
                    pause();
            }
            System.out.println();
        }
    }

    private static String formatName(String raw) {
        raw = raw.trim().toLowerCase();
        char[] chars = raw.toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i]) && capitalizeNext) {
                chars[i] = Character.toUpperCase(chars[i]);
                capitalizeNext = false;
            } else if (chars[i] == ' ') {
                capitalizeNext = true;
            }
        }
        return new String(chars);
    }
}
