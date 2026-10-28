class DienMay extends HangHoa {
    private int thoiGianBaoHanh; 
    private double congSuat;     

    public DienMay(String ma, String ten, int sl, double gia, int thoiGianBaoHanh, double congSuat) {
        super(ma, ten, sl, gia);
        if (thoiGianBaoHanh < 0)
            throw new IllegalArgumentException("Thời gian bảo hành phải >= 0");
        if (congSuat < 0)
            throw new IllegalArgumentException("Công suất phải >= 0");
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    @Override
    public double getVATRate() { return 0.10; }

    @Override
    public String danhGiaMucDoTieuThu() {
        if (soLuongTon < 3) return "Bán được";
        return "Không đánh giá";
    }

    @Override
    public String getLoai() { return "Điện máy"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Bảo hành: %d tháng | Công suất: %.2f kW",
                thoiGianBaoHanh, congSuat);
    }
}
