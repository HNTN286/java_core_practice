class DSHH {
    private HangHoa[] ds = new HangHoa[2]; 
    private int n = 0;

    public int size() { return n; }

    public boolean tonTai(String ma) {
        return timTheoMa(ma) != null;
    }

    public HangHoa timTheoMa(String ma) {
        for (int i = 0; i < n; i++)
            if (ds[i].getMaHang().equalsIgnoreCase(ma.trim())) return ds[i];
        return null;
    }

    public boolean them(HangHoa h) {
        if (h == null || tonTai(h.getMaHang())) return false;
        if (n == ds.length) { 
            HangHoa[] moi = new HangHoa[ds.length * 2];
            System.arraycopy(ds, 0, moi, 0, n);
            ds = moi;
        }
        ds[n++] = h;
        return true;
    }

    public void hienThi() {
        if (n == 0) { System.out.println("(Danh sách rỗng)"); return; }
        for (int i = 0; i < n; i++) System.out.println((i + 1) + ". " + ds[i]);
    }

    public void danhGiaTieuThu() {
        if (n == 0) { System.out.println("(Danh sách rỗng)"); return; }
        for (int i = 0; i < n; i++)
            System.out.printf("%-10s %-20s %-10s => %s%n",
                    ds[i].getMaHang(), ds[i].getTenHang(), ds[i].getLoai(), ds[i].danhGiaMucDoTieuThu());
    }

    public void thongKe() {
        int slThucPham = 0, slDienMay = 0, slSanhSu = 0;
        double vatThucPham = 0, vatDienMay = 0, vatSanhSu = 0;
        for (int i = 0; i < n; i++) {
            HangHoa h = ds[i];
            if (h instanceof ThucPham) { slThucPham += h.getSoLuongTon(); vatThucPham += h.getVAT(); }
            else if (h instanceof DienMay) { slDienMay += h.getSoLuongTon(); vatDienMay += h.getVAT(); }
            else if (h instanceof SanhSu) { slSanhSu += h.getSoLuongTon(); vatSanhSu += h.getVAT(); }
        }
        System.out.println("---------- THỐNG KÊ ----------");
        System.out.printf("%-12s | Tồn kho: %6d | Tiền VAT (5%%) : %,15.0f%n", "Thực phẩm", slThucPham, vatThucPham);
        System.out.printf("%-12s | Tồn kho: %6d | Tiền VAT (10%%): %,15.0f%n", "Điện máy", slDienMay, vatDienMay);
        System.out.printf("%-12s | Tồn kho: %6d | Tiền VAT (10%%): %,15.0f%n", "Sành sứ", slSanhSu, vatSanhSu);
    }
}
