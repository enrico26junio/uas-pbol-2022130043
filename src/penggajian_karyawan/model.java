package penggajian_karyawan;

public class model {

    private String namaKaryawan;
    private int jumlahHariKerja;
    private double upahPerHari;
    
    public model() {
    }

    public model(String namaKaryawan, int jumlahHariKerja, double upahPerHari) {
        this.namaKaryawan = namaKaryawan;
        this.jumlahHariKerja = jumlahHariKerja;
        this.upahPerHari = upahPerHari;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public int getJumlahHariKerja() {
        return jumlahHariKerja;
    }

    public void setJumlahHariKerja(int jumlahHariKerja) {
        this.jumlahHariKerja = jumlahHariKerja;
    }

    public double getUpahPerHari() {
        return upahPerHari;
    }

    public void setUpahPerHari(double upahPerHari) {
        this.upahPerHari = upahPerHari;
    }

    public double hitungTotalGaji() {
        return jumlahHariKerja * upahPerHari;
    }
    
    @Override
    public String toString() {
        return "Nama Karyawan: " + namaKaryawan + ", Total Gaji: Rp " + hitungTotalGaji();
    }
}
