import java.time.LocalDate;

class ThucPham extends HangHoa {
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public ThucPham(String ma, String ten, int sl, double gia,
                    LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(ma, ten, sl, gia);
        if (ngaySanXuat == null || ngayHetHan == null)
            throw new IllegalArgumentException("Ngày sản xuất / hết hạn không được rỗng");
        if (ngayHetHan.isBefore(ngaySanXuat))
            throw new IllegalArgumentException("Ngày hết hạn phải sau hoặc bằng ngày sản xuất");
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public boolean daHetHan() {
        return ngayHetHan.isBefore(LocalDate.now());
    }

    @Override
    public double getVATRate() { return 0.05; }

    @Override
    public String danhGiaMucDoTieuThu() {
        if (soLuongTon > 0 && daHetHan()) return "Khó bán";
        return "Không đánh giá";
    }

    @Override
    public String getLoai() { return "Thực phẩm"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | NSX: %s | HSD: %s | NCC: %s",
                ngaySanXuat, ngayHetHan, nhaCungCap);
    }
}
