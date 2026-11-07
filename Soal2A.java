public class Soal2A {
    static class Pasien { //untuk merepresentasikan setiap pasien yang masuk ke IGD
        String nama; //untuk menyimpan nama identitas pasien
        int prioritas; //untuk menyimpan kategori tingkat kegawatan pasien

        Pasien(String nama, int prioritas) {
            this.nama = nama;
            this.prioritas = prioritas;
        }
    }

    static class FIFO { //untuk membuat antrean pasien berdasarkan urutan kedatangan
        private Pasien[] data = new Pasien[10]; //merupakan array yang dapat menyimpan maksimal 10 referensi objek pasien
        private int depan = 0;
        private int jumlah = 0;

        void tambah(Pasien pasien) { ////untuk memasukkan pasien baru ke bagian belakang antrean
            if (jumlah == data.length) throw new IllegalStateException("Antrean FIFO penuh");
            data[(depan + jumlah) % data.length] = pasien; //menghitung indeks penyimpanan pasien baru
            jumlah++;
        }

        Pasien ambil() {
            if (jumlah == 0) throw new IllegalStateException("Antrean FIFO kosong");
            Pasien hasil = data[depan]; //untuk mengambil referensi pasien yang paling awal
            data[depan] = null; //untuk mengosongkan posisi tersebut
            depan = (depan + 1) % data.length; //memindahkan posisi depan ke pasien berikutnya
            jumlah--; //untuk mengurangi jumlah pasien dalam antrean
            return hasil;
        }
    }

    static class MaxHeap { //untuk membuat antrean berdasarkan nilai prioritas pasien
        private Pasien[] data = new Pasien[10];
        private int jumlah = 0;

        void tambah(Pasien pasien) { //untuk memasukkan pasien baru ke dalam heap
            if (jumlah == data.length) throw new IllegalStateException("Heap penuh");
            int i = jumlah++;
            data[i] = pasien;
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (data[parent].prioritas >= data[i].prioritas) break;
                tukar(parent, i);
                i = parent;
            }
        }

        Pasien ambil() { //untuk mengambil pasien yang mempunyai prioritas tertinggi
            if (jumlah == 0) throw new IllegalStateException("Heap kosong");
            Pasien hasil = data[0];
            jumlah--;
            if (jumlah == 0) {
                data[0] = null;
                return hasil;
            }
            data[0] = data[jumlah];
            data[jumlah] = null;
            int i = 0;
            while (2 * i + 1 < jumlah) {
                int anak = 2 * i + 1;
                int kanan = anak + 1;
                if (kanan < jumlah && data[kanan].prioritas > data[anak].prioritas) anak = kanan;
                if (data[i].prioritas >= data[anak].prioritas) break;
                tukar(i, anak);
                i = anak;
            }
            return hasil;
        }

        void tukar(int a, int b) { //untuk menukar posisi dua pasien di dalam array.
            Pasien sementara = data[a];
            data[a] = data[b];
            data[b] = sementara; //untuk referensi objek pada indeks pertama tidak hilang ketika kedua posisi ditukar
        }
    }

    public static void main(String[] args) {
        FIFO fifo = new FIFO();
        MaxHeap heap = new MaxHeap();
        Pasien[] pasien = {
            new Pasien("Pasien 1 - Ringan", 1),
            new Pasien("Pasien 2 - Sedang", 2),
            new Pasien("Pasien 3 - Kritis", 3)
        };
        for (int i = 0; i < pasien.length; i++) {
            fifo.tambah(pasien[i]);
            heap.tambah(pasien[i]);
        }
        System.out.println("=== ANTREAN FIFO ===");
        for (int i = 0; i < pasien.length; i++) System.out.println(fifo.ambil().nama);
        System.out.println("\n=== PRIORITY QUEUE MAX-HEAP ===");
        for (int i = 0; i < pasien.length; i++) System.out.println(heap.ambil().nama);
    }
}
