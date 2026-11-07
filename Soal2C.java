public class Soal2C {
    static final int DOKTER = 50; //static final digunakan untuk membuat konstanta yang nilainya tidak berubah selama program berjalan
    static final int HARI = 30; //static final digunakan untuk membuat konstanta yang nilainya tidak berubah selama program berjalan
    static final int SLOT = 10; //static final digunakan untuk membuat konstanta yang nilainya tidak berubah selama program berjalan.
    // true = terisi/tidak tersedia; false = belum terisi
    static boolean[][][] jadwalArray = new boolean[DOKTER][HARI][SLOT]; //untuk array tiga dimensi yang menyimpan status slot berdasarkan indeks dokter, hari, dan kuota.

    static class Entri { //untuk menyimpan satu kelompok jadwal dokter pada satu tanggal tertentu
        String key; //untuk menyimpan identitas gabungan antara kode dokter dan tanggal
        boolean[] slot = new boolean[SLOT]; //atribut slot untuk array yang menyimpan 10 status slot pada hari tersebut
        boolean cuti = false; //cuti untuk menunjukkan apakah dokter tidak tersedia pada tanggal tersebut (jika false dokter belum di tandai cuti)

        Entri(String key) {
            this.key = key;
        }
    }

    static class TabelJadwal { //untuk membuat hash table sederhana menggunakan array Java
        private Entri[] tabel = new Entri[4099]; //untuk array tabel mempunyai kapasitas 4099 posisi atau bucket dan setiap posisi dapat menyimpan satu objek entry

        int hash(String key) { //untuk mengubah sebuah key menjadi indeks penyimpanan pada array hash table
            int hasil = 0;
            for (int i = 0; i < key.length(); i++) {
                hasil = (31 * hasil + key.charAt(i)) & 0x7fffffff; //program memproses setiap karakter key dan menggabungkannya ke dalam nilai hash
            }
            return hasil % tabel.length; //untuk membatasi nilai hash agar berada dalam rentang indeks array, yaitu 0 sampai 4098
        }

        void tambah(String key) { //untuk memasukkan jadwal baru ke dalam hash table
            int indeks = hash(key);
            int awal = indeks;
            while (tabel[indeks] != null) {
                if (tabel[indeks].key.equals(key)) return;
                indeks = (indeks + 1) % tabel.length;
                if (indeks == awal) throw new IllegalStateException("Hash table penuh");
            }
            tabel[indeks] = new Entri(key);
        }

        Entri cari(String key) { //untuk mencari jadwal berdasarkan gabungan kode dokter dan tanggal
            int indeks = hash(key);
            int awal = indeks;
            while (tabel[indeks] != null) {
                if (tabel[indeks].key.equals(key)) return tabel[indeks];
                indeks = (indeks + 1) % tabel.length;
                if (indeks == awal) break;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int dokter = 4; //ketiga variabel ini menentukan jadwal yang akan diuji
        int hari = 9;
        int slot = 2;

        System.out.println("=== ARRAY 3 DIMENSI ===");
        jadwalArray[dokter][hari][slot] = true; //untuk menandai slot sebagai tidak tersedia
        System.out.println("Slot sudah terisi: " + jadwalArray[dokter][hari][slot]);

        System.out.println("\n=== HASH TABLE ===");
        TabelJadwal hash = new TabelJadwal();
        String key = "DOK05|2026-11-10";
        hash.tambah(key);
        Entri jadwal = hash.cari(key);
        if (jadwal != null) {
            jadwal.slot[slot] = true;
            System.out.println("Jadwal ditemukan: " + jadwal.key);
            System.out.println("Slot sudah terisi: " + jadwal.slot[slot]);
        }

        System.out.println("\n=== SIMULASI DOKTER CUTI ===");
        for (int i = 0; i < SLOT; i++) jadwalArray[dokter][hari][i] = true; //untuk perulangan ini menandai seluruh 10 slot pada hari tertentu sebagai tidak tersedia
        if (jadwal != null) {
            jadwal.cuti = true;
            System.out.println("Array: semua slot hari itu ditutup");
            System.out.println("Hash Table: status cuti = " + jadwal.cuti);
            System.out.println("Slot hash tersedia untuk booking: " + (!jadwal.cuti && !jadwal.slot[slot]));
        }
    }
}