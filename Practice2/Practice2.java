import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Practice2 {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static String nhapChuoi(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    private static int nhapSoNguyen(String label) {
        while (true) {
            try { return Integer.parseInt(nhapChuoi(label)); }
            catch (NumberFormatException e) { System.out.println("  ! Hãy nhập số nguyên."); }
        }
    }

    private static double nhapSoThuc(String label) {
        while (true) {
            try { return Double.parseDouble(nhapChuoi(label)); }
            catch (NumberFormatException e) { System.out.println("  ! Hãy nhập số."); }
        }
    }

    private static LocalDate nhapNgay(String label) {
        while (true) {
            try { return LocalDate.parse(nhapChuoi(label + " (dd/MM/yyyy): "), FMT); }
            catch (DateTimeParseException e) { System.out.println("  ! Sai định dạng ngày."); }
        }
    }

    private static void themHang(DSHH dshh) {
        System.out.println("Chọn loại hàng: 1. Thực phẩm  2. Điện máy  3. Sành sứ");
        int loai = nhapSoNguyen("Loại: ");
        if (loai < 1 || loai > 3) { System.out.println("Loại không hợp lệ."); return; }

        String ma = nhapChuoi("Mã hàng: ");
        if (dshh.tonTai(ma)) { System.out.println("=> Thêm thất bại: trùng mã hàng!"); return; }
        String ten = nhapChuoi("Tên hàng: ");
        int sl = nhapSoNguyen("Số lượng tồn (>=0): ");
        double gia = nhapSoThuc("Đơn giá: ");

        try {
            HangHoa h;
            if (loai == 1) {
                LocalDate nsx = nhapNgay("Ngày sản xuất");
                LocalDate hsd = nhapNgay("Ngày hết hạn");
                String ncc = nhapChuoi("Nhà cung cấp: ");
                h = new ThucPham(ma, ten, sl, gia, nsx, hsd, ncc);
            } else if (loai == 2) {
                int bh = nhapSoNguyen("Thời gian bảo hành (tháng, >=0): ");
                double cs = nhapSoThuc("Công suất (kW, >=0): ");
                h = new DienMay(ma, ten, sl, gia, bh, cs);
            } else {
                String nsx = nhapChuoi("Nhà sản xuất: ");
                LocalDate ngay = nhapNgay("Ngày nhập kho");
                h = new SanhSu(ma, ten, sl, gia, nsx, ngay);
            }
            System.out.println(dshh.them(h) ? "=> Thêm thành công!" : "=> Thêm thất bại: trùng mã hàng!");
        } catch (IllegalArgumentException e) {
            System.out.println("=> Thêm thất bại: " + e.getMessage());
        }
    }

    private static void napDuLieuMau(DSHH dshh) {
        LocalDate today = LocalDate.now();
        dshh.them(new ThucPham("TP01", "Sữa tươi", 100, 25000, today.minusDays(60), today.minusDays(5), "Vinamilk"));
        dshh.them(new ThucPham("TP02", "Mì gói", 200, 4000, today.minusDays(10), today.plusDays(180), "Acecook"));
        dshh.them(new DienMay("DM01", "Tủ lạnh", 2, 12000000, 24, 0.15));
        dshh.them(new DienMay("DM02", "Máy giặt", 10, 8000000, 12, 0.5));
        dshh.them(new SanhSu("SS01", "Bộ chén sứ", 80, 150000, "Minh Long", today.minusDays(30)));
        dshh.them(new SanhSu("SS02", "Bình hoa", 20, 300000, "Bát Tràng", today.minusDays(40)));
        System.out.println("=> Đã nạp dữ liệu mẫu.");
    }

    public static void main(String[] args) {
        DSHH dshh = new DSHH();
        while (true) {
            System.out.println("\n===== QUẢN LÝ HÀNG HÓA SIÊU THỊ =====");
            System.out.println("1. Thêm hàng hóa");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Đánh giá mức độ tiêu thụ");
            System.out.println("4. Thống kê tồn kho & VAT");
            System.out.println("5. Nạp dữ liệu mẫu");
            System.out.println("0. Thoát");
            if (!sc.hasNextLine()) return;
            int chon = nhapSoNguyen("Chọn: ");
            switch (chon) {
                case 1: themHang(dshh); break;
                case 2: dshh.hienThi(); break;
                case 3: dshh.danhGiaTieuThu(); break;
                case 4: dshh.thongKe(); break;
                case 5: napDuLieuMau(dshh); break;
                case 0: System.out.println("Tạm biệt!"); return;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
