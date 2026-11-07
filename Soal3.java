class RiwayatKunjungan {
    private static class NodeKunjungan {
        String catatan;
        NodeKunjungan prev;
        NodeKunjungan next;

        NodeKunjungan(String catatan) {
            this.catatan = catatan;
            this.prev = null;
            this.next = null;
        }
    }

    private NodeKunjungan head;
    private NodeKunjungan tail;
    private NodeKunjungan current;
    private int jumlah;

    public RiwayatKunjungan() {
        head = null;
        tail = null;
        current = null;
        jumlah = 0;
    }

    // menambah kunjungan di akhir dan mengaktifkan node baru O(1)
    public void tambahKunjungan(String catatan) {
        if (catatan == null || catatan.trim().isEmpty()) {
            throw new IllegalArgumentException("Catatan kunjungan tidak boleh kosong.");
        }

        NodeKunjungan baru = new NodeKunjungan(catatan);
        if (head == null) {
            head = baru;
            tail = baru;
        } else {
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
        }
        current = baru;
        jumlah++;
    }

    //melakukan pemindahan ke kunjungan sebelumnya jika tersedia O(1)
    public void sebelumnya() {
        if (current == null) {
            System.out.println("Riwayat kunjungan masih kosong.");
        } else if (current.prev == null) {
            System.out.println("Tidak ada kunjungan sebelumnya.");
        } else {
            current = current.prev;
        }
    }

    // melakukan pemindahan ke kunjungan berikutnya jika tersedia O(1).
    public void berikutnya() {
        if (current == null) {
            System.out.println("Riwayat kunjungan masih kosong.");
        } else if (current.next == null) {
            System.out.println("Tidak ada kunjungan berikutnya.");
        } else {
            current = current.next;
        }
    }

    // menampilkan catatan node aktif pada O(1)
    public void tampilkanSaatIni() {
        if (current == null) {
            System.out.println("Kunjungan saat ini: (belum ada kunjungan)");
        } else {
            System.out.println("Kunjungan saat ini: " + current.catatan);
        }
    }

    public int getJumlah() {
        return jumlah;
    }
    // menghapus kunjungan aktif dengan current diarahkan ke node sesudahnya, jika belum maka ke node sebelumnya O(1) karna referensi telah diketahui
    public void hapusKunjunganSaatIni() {
        if (current == null) {
            System.out.println("Tidak ada kunjungan yang dapat dihapus.");
            return;
        }

        NodeKunjungan target = current;
        NodeKunjungan sebelum = target.prev;
        NodeKunjungan sesudah = target.next;

        if (sebelum != null) {
            sebelum.next = sesudah;
        } else {
            head = sesudah;
        }

        if (sesudah != null) {
            sesudah.prev = sebelum;
        } else {
            tail = sebelum;
        }

        current = (sesudah != null) ? sesudah : sebelum;
        target.prev = null;
        target.next = null;
        jumlah--;
    }
}

public class Soal3 {
    public static void main(String[] args) {
        RiwayatKunjungan riwayat = new RiwayatKunjungan();
        System.out.println("=== RIWAYAT KUNJUNGAN PASIEN ===");

        riwayat.tambahKunjungan("Kunjungan 1 - Pemeriksaan Awal");
        riwayat.tambahKunjungan("Kunjungan 2 - Kontrol Pertama");
        riwayat.tambahKunjungan("Kunjungan 3 - Pemeriksaan Lanjutan");
        riwayat.tambahKunjungan("Kunjungan 4 - Kontrol Kedua");

        System.out.println("\nSetelah menambah 4 kunjungan:");
        riwayat.tampilkanSaatIni();

        System.out.println("\nMundur pertama:");
        riwayat.sebelumnya();
        riwayat.tampilkanSaatIni();

        System.out.println("\nMundur kedua:");
        riwayat.sebelumnya();
        riwayat.tampilkanSaatIni();

        System.out.println("\nMaju satu kali:");
        riwayat.berikutnya();
        riwayat.tampilkanSaatIni();

        System.out.println("\nJumlah kunjungan: " + riwayat.getJumlah());

        // melakukan hapus kunjungan aktif pada Kunjungan 3 tanpa memutus node 2 dan 4
        System.out.println("\n=== PENGHAPUSAN KUNJUNGAN AKTIF ===");
        riwayat.hapusKunjunganSaatIni();
        System.out.println("Setelah menghapus Kunjungan 3:");
        riwayat.tampilkanSaatIni(); // maka berpindah ke Kunjungan 4
        riwayat.sebelumnya();
        System.out.println("Mundur dari Kunjungan 4:");
        riwayat.tampilkanSaatIni(); // langsung ke Kunjungan 2
        System.out.println("Jumlah kunjungan: " + riwayat.getJumlah());
    }
}
