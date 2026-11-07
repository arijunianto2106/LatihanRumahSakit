abstract class PetugasMedis {
    protected String nama;

    PetugasMedis(String nama) {
        this.nama = nama;
    }

    void login() {
        System.out.println(nama + " berhasil login (simulasi).");
    }

    void lihatJadwal() {
        System.out.println(nama + " melihat jadwal kerja.");
    }
}

interface PenulisResep {
    void tulisResep();
}

interface PencatatTandaVital {
    void catatTandaVital();
}

class Dokter extends PetugasMedis implements PenulisResep {
    Dokter(String nama) {
        super(nama);
    }

    public void tulisResep() {
        System.out.println(nama + " menulis resep.");
    }
}

class Perawat extends PetugasMedis implements PencatatTandaVital {
    Perawat(String nama) {
        super(nama);
    }

    public void catatTandaVital() {
        System.out.println(nama + " mencatat tanda vital.");
    }
}

class RekamMedis {
    private String diagnosis;

    RekamMedis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    String bacaDiagnosis(boolean memilikiIzin) {
        if (!memilikiIzin) {
            return "Akses ditolak";
        }
        return diagnosis;
    }
}

class RiwayatMedis {
    private RekamMedis[] data = new RekamMedis[2];
    private int jumlah = 0;

    void tambah(RekamMedis rekam) {
        if (jumlah == data.length) { //memeriksa apakah seluruh slot array sudah terisi
            RekamMedis[] baru = new RekamMedis[data.length * 2]; //membuat array baru dengan kapasitas dua kali lebih besar
            for (int i = 0; i < jumlah; i++) { //perulangan for menyalin seluruh referensi objek dari array lama ke array baru
                baru[i] = data[i]; //mengubah referensi array yang digunakan oleh RiwayatMedis agar menunjuk ke array baru
            }
            data = baru;
            System.out.println("Kapasitas diperbesar menjadi " + data.length);
        }
        data[jumlah] = rekam;
        jumlah++;
    }

    void tampilkan(boolean memilikiIzin) {
        for (int i = 0; i < jumlah; i++) {
            System.out.println("Rekam medis " + (i + 1) + ": "
                    + data[i].bacaDiagnosis(memilikiIzin));
        }
    }
}

public class Main {
    public static void main(String[] args) {
      //  RekamMedis tes = new RekamMedis("Demam"); //membuat objek baru dari class RekamMedis dengan  menyimpannya ke dalam variabel tes
      //  System.out.println(tes.diagnosis); //perintah ini mencoba membaca atribut diagnosis secara langsung dari luar class RekamMedis
        Dokter dokter = new Dokter("Dr. Rina");
        Perawat perawat = new Perawat("Sari");
        dokter.login();
        dokter.lihatJadwal();
        dokter.tulisResep();
        perawat.login(); //perintah ini memanggil method login() melalui objek perawat
        perawat.lihatJadwal(); //method lihatJadwal() didefinisikan pada abstract class PetugasMedis
        perawat.catatTandaVital(); //perintah ini memanggil method khusus yang dimiliki Perawat melalui implementasi interface PencatatTandaVital

        RiwayatMedis riwayat = new RiwayatMedis();
        riwayat.tambah(new RekamMedis("Demam"));
        riwayat.tambah(new RekamMedis("Batuk"));
        riwayat.tambah(new RekamMedis("Flu"));
        riwayat.tambah(new RekamMedis("Migrain")); //perintah ini membuat objek RekamMedis baru yang menyimpan diagnosis "Migrain", kemudian mengirimkan objek tersebut ke method tambah() pada class RiwayatMedis.
        riwayat.tambah(new RekamMedis("Hipertensi")); //perintah ini membuat objek RekamMedis baru yang menyimpan diagnosis "Hipertensi", kemudian mengirimkan objek tersebut ke method tambah() pada class RiwayatMedis.

        System.out.println("\nAkses petugas berizin:");
        riwayat.tampilkan(true);
        System.out.println("\nAkses tanpa izin:");
        riwayat.tampilkan(false);
    }
}
