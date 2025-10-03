<h1>Kasir Warkop Tepian</h1>
<div align="Justify">Aplikasi kasir sederhana berbasis konsol untuk warung kopi (warkop) yang memungkinkan pengelolaan katalog menu, keranjang belanja, dan transaksi pembayaran.</div>
<h3>Penjelasan Warkop Tepian</h3>
<div align="Justify">Warkop Tepian adalah sistem Point of Sale (POS) sederhana yang dirancang untuk membantu pengelolaan transaksi di warung kopi. Aplikasi ini memungkinkan pengguna untuk melihat katalog menu, menambahkan item ke keranjang, menghitung total pembayaran termasuk pajak (PPN 10%), dan mencetak struk pembayaran.</div>

Fitur utama meliputi:
1. Katalog menu dengan kategori (Food, Drink, Snack)
2. Pencarian menu berdasarkan keyword
3. Manajemen keranjang belanja
4. Perhitungan otomatis pajak (PPN 10%)
5. Sistem checkout dengan konfirmasi
<h3>Diagram Class</h3>
Struktur package dan class:
<pre>KasirWarkopTepian
├── app
│ └── Main.java 
├── exception
│ └── ValidationException.java
├── model
│ ├── CartItem.java 
│ └── MenuItem.java 
└── service
└── WarkopService.java 
</pre>
<h3>Contoh Penggunaan Fitur Utama</h3>
<h4>1. Melihat Semua Daftar Menu</h4>

• Saat memilih opsi 1, sistem menampilkan seluruh daftar menu lengkap dengan kode, nama, harga, dan kategori.

• Contoh: S01 Kentang Goreng, F01 Mie Goreng, D02 Cappuccino, dll.

<img width="300" height="700" alt="image" src="https://github.com/user-attachments/assets/0c321693-5fa4-4a69-a097-dd5beafe0fe4" />
<h4>2. Melihat Daftar Menu per Kategori</h4>

• Saat memilih opsi 2, sistem meminta input kategori: F (Food), D (Drink), atau S (Snack).

• Setelah dipilih, sistem menampilkan hanya menu pada kategori tersebut.

• Contoh input: F → muncul daftar Mie Goreng, Mie Rebus, Seblak, Telur, dll.

<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/2ca09e64-de8e-4c16-bb9d-567d7c4352f6" />
<h4>3. Pencarian Menu</h4>

• Saat memilih opsi 3, sistem meminta keyword nama menu.

• Input contoh: Mie Goreng → sistem menampilkan hanya item yang cocok.

<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/b2a5a21a-a583-44e6-9acc-901a9fe3d003" />
<h4>4. Menambah ke Keranjang</h4>

• Saat memilih opsi 4, pelanggan memasukkan kode menu dan jumlah Qty.

• Contoh: Kode = D09 (Nutrisari), Qty = 2.

• Output: "Berhasil ditambahkan."

<img width="180" height="280" alt="image" src="https://github.com/user-attachments/assets/f355ecd7-070d-42b3-8956-0712a7cd5da5" />
<h4>5. Menghapus dari Keranjang</h4>

• Saat memilih opsi 5, sistem minta kode menu yang akan dihapus.

• Contoh: d09 → Y untuk konfirmasi.

• Output: "Berhasil dihapus."

<img width="180" height="280" alt="image" src="https://github.com/user-attachments/assets/ade3552d-f2d7-4bdf-8d5d-8586a91cef93" />
<h4>6. Melihat Keranjang</h4>

• Saat memilih opsi 6, sistem menampilkan isi keranjang: No, Kode, Nama, Qty, Subtotal.

• Juga dihitung Total sebelum pajak, PPN (10%), dan Total bayar.

• Contoh: Nutrisari x2 = Rp8.000, PPN Rp800, total Rp8.800.

<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/7da752fb-4384-4b07-a597-783195537fbc" />
<h4>7. Checkout</h4>

• Saat memilih opsi 7, pelanggan memasukkan nama (contoh: Daffa).

• Sistem mencetak struk pembayaran lengkap dengan daftar item, subtotal, pajak, total bayar.

• Ada konfirmasi pembayaran (y/n). Jika y, muncul pesan: "Pembayaran selesai. Terima kasih!"

<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/f2d21752-4525-4767-8e2e-435fa9720b2f" />
<h4>0. Keluar</h4>

• Saat memilih opsi 0, sistem keluar dengan pesan: "Sampai jumpa!"

<img width="175" height="275" alt="image" src="https://github.com/user-attachments/assets/567a85aa-a22b-41b6-a065-d3b1e3fbfa97" />
<h3>Link Video Youtube</h3>
https://youtu.be/nauEJZBPSOc?si=yIE3ryR-MyS45C0-
<h3>Petunjuk Cara Menjalankan Program</h3>
<h4>1. Buka Project di NetBeans</h4>

• Jalankan aplikasi **Apache NetBeans IDE**.

• Pilih menu **File** → **Open Project**, lalu arahkan ke folder KasirWarkopTepian.

• Pastikan semua package (app, model, service, exception) sudah terbuka di panel Projects.

<h4>2. Pastikan File Main.java Dipilih</h4>

• Buka file Main.java yang ada di package app.

• File inilah yang berfungsi sebagai entry point program.

<h4>3. Compile dan Run Program</h4>

• Klik kanan pada file Main.java → pilih **Run File**.

• Atau langsung tekan tombol hijau **Run Project (F6)** di toolbar.

• NetBeans akan otomatis melakukan build (kompilasi) dan menampilkan output di tab **Output**.

<h4>4. Interaksi di Console</h4>

• Program akan menampilkan **menu utama**, misalnya:

1. Tampilkan Semua Menu

2. Cari Menu per Kategori

3. Cari Menu
   
4. Tambah ke Keranjang

5. Hapus dari Keranjang

6. Lihat Keranjang

7. Checkout

0. Keluar

• Masukkan angka sesuai pilihan, lalu tekan **Enter**.

• Jika memilih menambah item, masukkan nomor menu dan jumlah pembelian.

• Jika input salah (misalnya qty = 0), akan muncul pesan error dari ValidationException tanpa membuat program berhenti.

<h4>5. Checkout dan Keluar</h4>

• Saat memilih opsi checkout, program akan menghitung total belanja, menambahkan pajak/biaya layanan (jika ada), lalu meminta input uang bayar.

• Program menampilkan **total, uang bayar, dan kembalian**.

• Setelah selesai, pilih opsi **0 – Keluar** untuk menutup aplikasi.
