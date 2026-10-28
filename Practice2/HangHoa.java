abstract class HangHoa {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;

    public HangHoa(String maHang, String tenHang, int soLuongTon, double donGia) {
        if (maHang == null || maHang.trim().isEmpty())
            throw new IllegalArgumentException("Mã hàng không được rỗng");
        if (tenHang == null || tenHang.trim().isEmpty())
            throw new IllegalArgumentException("Tên hàng không được rỗng");
        if (soLuongTon < 0)
            throw new IllegalArgumentException("Số lượng tồn phải >= 0");
        if (donGia < 0)
            throw new IllegalArgumentException("Đơn giá phải >= 0");
        this.maHang = maHang.trim();
        this.tenHang = tenHang.trim();
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    public String getMaHang() { return maHang; }
    public String getTenHang() { return tenHang; }
    public int getSoLuongTon() { return soLuongTon; }
    public double getDonGia() { return donGia; }

    public abstract double getVATRate();

    public abstract String danhGiaMucDoTieuThu();

    public double getVAT() {
        return donGia * soLuongTon * getVATRate();
    }

    public abstract String getLoai();

    @Override
    public String toString() {
        return String.format("[%s] Mã: %s | Tên: %s | Tồn: %d | Đơn giá: %,.0f | VAT(%.0f%%): %,.0f",
                getLoai(), maHang, tenHang, soLuongTon, donGia, getVATRate() * 100, getVAT());
    }
}
