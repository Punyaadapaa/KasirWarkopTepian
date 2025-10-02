/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADVAN
 */
import exception.ValidationException;
import model.CartItem;
import model.MenuItem;

public class WarkopService {
    public static final String APP_NAME = "Warkop Tepian";
    public static final double TAX_RATE = 0.10;

    private final MenuItem[] catalog;
    private final CartItem[] cart;
    private int cartCount = 0;

    // ==== KONSTRUKTOR ====
    public WarkopService() {
        catalog = seedCatalog();
        cart = new CartItem[100];
    }

    // ==== DAFTAR MENU (sesuai PDF) ====
    private MenuItem[] seedCatalog() {
        MenuItem[] data = new MenuItem[] {
            // SNACK / FOOD UTAMA
            new MenuItem("S01", "Kentang Goreng (porsi)", 5000, 'S'),
            new MenuItem("S02", "Pisang Goreng (pcs)",    2000, 'S'),
            new MenuItem("F01", "Mie Goreng",             8000, 'F'),
            new MenuItem("F02", "Mie Rebus",              8000, 'F'),
            new MenuItem("F03", "Seblak",                10000, 'F'),
            new MenuItem("S03", "Mendoan (pcs)",          2000, 'S'),
            new MenuItem("F04", "Telur Ceplok",           3500, 'F'),

            // ADD TOPPING
            new MenuItem("T01", "Telur Rebus (topping)",  3500, 'F'),
            new MenuItem("T02", "Sosis Goreng (topping)", 3500, 'S'),
            new MenuItem("T03", "Telur Dadar (topping)",  3500, 'F'),

            // MINUMAN
            new MenuItem("D01", "Kopi Sachet",            5000, 'D'),
            new MenuItem("D02", "Cappuccino",             6000, 'D'),
            new MenuItem("D03", "Jeruk (hangat/dingin)",  3000, 'D'),
            new MenuItem("D04", "Susu (panas/dingin)",    4000, 'D'),
            new MenuItem("D05", "Teh (hangat/dingin)",    4000, 'D'),
            new MenuItem("D06", "Wedang Jahe",            3000, 'D'),
            new MenuItem("D07", "Susu Jahe",              4000, 'D'),
            new MenuItem("D08", "Chocolatos",             5000, 'D'),
            new MenuItem("D09", "Nutrisari",              4000, 'D'),
            new MenuItem("D10", "Milo",                   5000, 'D'),
            new MenuItem("D11", "Lemon Tea",              4000, 'D')
        };
        return data;
    }

    // ==== FORMAT TABEL ====
    private static final int W_CODE = 6;
    private static final int W_NAME = 26;
    private static final int W_PRICE = 7;
    private static final int W_CAT = 8;
    private static final int TABLE_WIDTH = 2 + W_CODE + 3 + W_NAME + 3 + W_PRICE + 3 + W_CAT;

    private static String repeat(char c, int n) {
        return new String(new char[n]).replace('\0', c);
    }

    private static void printLine() {
        System.out.println(repeat('-', TABLE_WIDTH));
    }

    private static void printHeader() {
        printLine();
        System.out.printf("| %-" + W_CODE + "s | %-" + W_NAME + "s | %"
                + W_PRICE + "s | %-" + W_CAT + "s%n",
                "Kode", "Nama", "Harga", "Kategori");
        printLine();
    }

    private static void printRow(String code, String name, double price, String cat) {
        System.out.printf("| %-" + W_CODE + "s | %-" + W_NAME + "s | %"
                + W_PRICE + ".0f | %-" + W_CAT + "s%n",
                code, name, price, cat);
    }

    // ==== CETAK KATALOG ====
    public void printCatalog() {
        printHeader();
        for (MenuItem item : catalog) {
            printRow(item.getCode(), item.getName(), item.getPrice(),
                     MenuItem.categoryToText(item.getCategory()));
        }
        printLine();
    }

    public void printCatalogByCategory(char category) {
        category = Character.toUpperCase(category);
        printHeader();
        boolean any = false;
        for (MenuItem item : catalog) {
            if (item.getCategory() == category) {
                any = true;
                printRow(item.getCode(), item.getName(), item.getPrice(),
                         MenuItem.categoryToText(item.getCategory()));
            }
        }
        if (!any) System.out.printf("| %-" + (TABLE_WIDTH - 2) + "s|%n", "(tidak ada item)");
        printLine();
    }

    public void searchCatalog(String keyword) {
        keyword = keyword.toLowerCase().trim();
        printHeader();
        int hit = 0;
        for (MenuItem item : catalog) {
            if (item.getName().toLowerCase().contains(keyword)) {
                printRow(item.getCode(), item.getName(), item.getPrice(),
                         MenuItem.categoryToText(item.getCategory()));
                hit++;
            }
        }
        if (hit == 0) System.out.printf("| %-" + (TABLE_WIDTH - 2) + "s|%n", "(tidak ada hasil)");
        printLine();
    }

    // ==== FIND ====
    private MenuItem findByCode(String code) {
        code = code.toUpperCase();
        for (MenuItem item : catalog) {
            if (item.getCode().equals(code)) return item;
        }
        return null;
    }

    private int findCartIndex(String code) {
        code = code.toUpperCase();
        for (int i = 0; i < cartCount; i++) {
            if (cart[i].getItem().getCode().equals(code)) return i;
        }
        return -1;
    }

    // ==== KERANJANG ====
    public void addToCart(String code, int qty) throws ValidationException {
        if (qty <= 0) throw new ValidationException("Kuantitas harus > 0.");
        MenuItem item = findByCode(code);
        if (item == null) throw new ValidationException("Kode menu tidak ditemukan.");
        int idx = findCartIndex(code);
        if (idx >= 0) {
            cart[idx].addQty(qty);
        } else {
            cart[cartCount++] = new CartItem(item, qty);
        }
    }

    public boolean removeFromCart(String code) {
        int idx = findCartIndex(code);
        if (idx < 0) return false;
        for (int i = idx; i < cartCount - 1; i++) {
            cart[i] = cart[i + 1];
        }
        cart[--cartCount] = null;
        return true;
    }

    public void printCart() {
        if (cartCount == 0) {
            System.out.println("(Keranjang kosong)");
            return;
        }

        int W_NO = 3;
        int width = 2 + W_NO + 3 + W_CODE + 3 + W_NAME + 3 + 3 + 3 + W_PRICE;

        System.out.println(repeat('-', width));
        System.out.printf("| %-" + W_NO + "s | %-" + W_CODE + "s | %-" + W_NAME + "s | %3s | %"
                + W_PRICE + "s%n", "No", "Kode", "Nama", "Qty", "Subtotal");
        System.out.println(repeat('-', width));

        for (int i = 0; i < cartCount; i++) {
            CartItem ci = cart[i];
            System.out.printf("| %-" + W_NO + "d | %-" + W_CODE + "s | %-" + W_NAME + "s | %3d | %"
                    + W_PRICE + ".0f%n",
                    (i + 1),
                    ci.getItem().getCode(),
                    ci.getItem().getName(),
                    ci.getQty(),
                    ci.getSubtotal()
            );
        }
        System.out.println(repeat('-', width));
        System.out.printf("%-" + (width - W_PRICE - 2) + "s %"
                + W_PRICE + ".0f%n", "Total sebelum pajak :", getTotalBeforeTax());
        System.out.printf("%-" + (width - W_PRICE - 2) + "s %"
                + W_PRICE + ".0f%n", "PPN (" + (int)(TAX_RATE * 100) + "%) :", getTax());
        System.out.printf("%-" + (width - W_PRICE - 2) + "s %"
                + W_PRICE + ".0f%n", "Total bayar :", getTotalAfterTax());
    }

    // ==== TOTAL ====
    public double getTotalBeforeTax() {
        double sum = 0;
        for (int i = 0; i < cartCount; i++) {
            sum += cart[i].getSubtotal();
        }
        return sum;
    }

    public double getTax() {
        return getTotalBeforeTax() * TAX_RATE;
    }

    public double getTotalAfterTax() {
        return getTotalBeforeTax() + getTax();
    }

    public void clearCart() {
        for (int i = 0; i < cartCount; i++) cart[i] = null;
        cartCount = 0;
    }
}
