public class Soal2B {
    // T(n) = T(n - 1) + k; T(0) = 0
    static int hitungRekursif(int n, int k) { //menghitung jumlah slot menggunakan rekursif dengan teknik ketika sebuah method memanggil dirinya sendiri untuk menyelesaikan permasalahan yang lebih kecil (untuk N menunjukkan jumlah hari dan K menunjukkan jumlah slot yang tersedia setiap hari)
        if (n == 0) return 0; //merupakan base case atau kondisi dasar dan ketika jumlah hari mencapai nol, maka tidak ada slot yang perlu dihitung sehingga method mengembalikan nilai 0
        return k + hitungRekursif(n - 1, k); //untuk menambahkan k slot untuk hari yang sedang dihitung dan memanggil kembali method yang sama dengan jumlah hari dikurangi satu
    }

    static int hitungIteratif(int n, int k) { //untuk menghitung jumlah slot menggunakan perulangan for, tanpa memanggil dirinya sendiri
        int total = 0; //untuk membuat variabel untuk menyimpan hasil penjumlahan sementara
        for (int i = 1; i <= n; i++) total += k; //untuk melakukan perulangan dari hari pertama sampai hari ke-n
        return total;
    }

    static int hitungLangsung(int n, int k) { //untuk menggunakan pemanggilan berulang maupun perulangan.
        return n * k;
    }

    public static void main(String[] args) {
        int hari = 30; //untuk menentukan nilai yang akan dihitung 30 hari
        int slotPerHari = 10; //untuk menentukan nilai yang akan dihitung dengan 10 kuota per hari
        System.out.println("=== PERHITUNGAN KUOTA RAWAT JALAN ===");
        System.out.println("Jumlah hari: " + hari);
        System.out.println("Kuota per hari: " + slotPerHari);
        int rekursif = hitungRekursif(hari, slotPerHari); //untuk dipanggil dengan nilai parameter yang sama dengan hasilnya disimpan ke dalam variabel masing-masing
        int iteratif = hitungIteratif(hari, slotPerHari); //untuk dipanggil dengan nilai parameter yang sama dengan hasilnya disimpan ke dalam variabel masing-masing
        int langsung = hitungLangsung(hari, slotPerHari); //untuk dipanggil dengan nilai parameter yang sama dengan hasilnya disimpan ke dalam variabel masing-masing
        System.out.println("\nHasil Rekursif: " + rekursif);
        System.out.println("Hasil Iteratif: " + iteratif);
        System.out.println("Hasil Rumus Langsung: " + langsung);
        if (rekursif == iteratif && iteratif == langsung) { //untuk memeriksa apakah ketiga metode menghasilkan angka yang sama
            System.out.println("\nSemua metode menghasilkan nilai yang sama.");
        }
    }
}
