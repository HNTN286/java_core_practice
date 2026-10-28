import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class SanhSu extends HangHoa {
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public SanhSu(String ma, String ten, int sl, double gia, String nhaSanXuat, LocalDate ngayNhapKho) {
        super(ma, ten, sl, gia);
        if (ngayNhapKho == null)
            throw new IllegalArgumentException("Ngày nhập kho không được rỗng");
        if (ngayNhapKho.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Ngày nhập kho không được ở tương lai");
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public long soNgayLuuKho() {
        return ChronoUnit.DAYS.between(ngayNhapKho, LocalDate.now());
    }

    @Override
    public double getVATRate() { return 0.10; }

    @Override
    public String danhGiaMucDoTieuThu() {
        if (soLuongTon > 50 && soNgayLuuKho() > 10) return "Bán chậm";
        return "Không đánh giá";
    }

    @Override
    public String getLoai() { return "Sành sứ"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | NSX: %s | Ngày nhập: %s (%d ngày)",
                nhaSanXuat, ngayNhapKho, soNgayLuuKho());
    }
}
