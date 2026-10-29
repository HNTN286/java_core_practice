import java.util.List;
import java.util.Scanner;

public class Practice3 {
    private static final Scanner sc = new Scanner(System.in);

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

    private static void themPhuongTien(VehicleManager qly) {
        System.out.println("Loại: 1. Ô tô  2. Xe máy  3. Xe tải");
        int loai = nhapSoNguyen("Chọn loại: ");
        if (loai < 1 || loai > 3) { System.out.println("Loại không hợp lệ."); return; }

        String bienSo = nhapChuoi("Biển số (đúng 5 ký tự): ");
        if (bienSo.length() != 5) { System.out.println("=> Thất bại: biển số phải đúng 5 ký tự."); return; }
        if (qly.search(bienSo) != null) { System.out.println("=> Thất bại: biển số đã tồn tại."); return; }

        String hang = nhapChuoi("Hãng (Honda, Yamaha, Toyota, Suzuki): ");
        int nam = nhapSoNguyen("Năm sản xuất: ");
        String mau = nhapChuoi("Màu xe: ");

        String cmnd = nhapChuoi("Số CMND chủ xe (12 số): ");
        Owner chu = qly.findOwner(cmnd);
        try {
            if (chu == null) {
                String ten = nhapChuoi("Họ tên chủ xe: ");
                String email = nhapChuoi("Email: ");
                chu = new Owner(cmnd, ten, email);
            } else {
                System.out.println("  (Chủ xe đã tồn tại: " + chu.getFullName() + ")");
            }

            Vehicle v;
            if (loai == 1) {
                int cho = nhapSoNguyen("Số chỗ ngồi: ");
                String dc = nhapChuoi("Loại động cơ: ");
                v = new Car(bienSo, hang, nam, mau, chu, cho, dc);
            } else if (loai == 2) {
                int dt = nhapSoNguyen("Dung tích (cc): ");
                v = new Motorbike(bienSo, hang, nam, mau, chu, dt);
            } else {
                double tan = nhapSoThuc("Trọng tải (tấn): ");
                v = new Truck(bienSo, hang, nam, mau, chu, tan);
            }
            if (qly.findOwner(chu.getCmnd()) == null) qly.registerOwner(chu);
            System.out.println(qly.add(v) ? "=> Thêm thành công!" : "=> Thất bại: biển số đã tồn tại.");
        } catch (IllegalArgumentException e) {
            System.out.println("=> Thất bại: " + e.getMessage());
        }
    }

    private static void napDuLieuMau(VehicleManager qly) {
        Owner a = new Owner("012345678901", "Nguyen Van A", "a.nguyen@gmail.com");
        Owner b = new Owner("987654321098", "Tran Thi B", "b.tran@yahoo.com");
        qly.registerOwner(a);
        qly.registerOwner(b);
        qly.add(new Car("29A01", "Toyota", 2018, "Trắng", a, 5, "Xăng"));
        qly.add(new Car("30B02", "Honda", 2020, "Đen", b, 7, "Dầu"));
        qly.add(new Motorbike("59C03", "Honda", 2015, "Đỏ", a, 125));
        qly.add(new Motorbike("51D04", "Yamaha", 2019, "Xanh", b, 150));
        qly.add(new Motorbike("43E05", "Honda", 2022, "Xám", a, 110));
        qly.add(new Truck("37F06", "Suzuki", 2012, "Vàng", b, 2.5));
        System.out.println("=> Đã nạp dữ liệu mẫu.");
    }

    public static void main(String[] args) {
        VehicleManager qly = new VehicleManager();
        while (true) {
            System.out.println("\n===== QUẢN LÝ PHƯƠNG TIỆN GIAO THÔNG =====");
            System.out.println("1. Thêm phương tiện");
            System.out.println("2. Tìm phương tiện theo biển số");
            System.out.println("3. Tìm xe của chủ xe theo CMND");
            System.out.println("4. Xóa tất cả xe của một hãng");
            System.out.println("5. Hãng có nhiều xe nhất");
            System.out.println("6. Sắp xếp theo số lượng xe (giảm dần)");
            System.out.println("7. Thống kê số xe theo loại");
            System.out.println("8. Hiển thị danh sách");
            System.out.println("9. Nạp dữ liệu mẫu");
            System.out.println("0. Thoát");
            if (!sc.hasNextLine()) return;
            int chon = nhapSoNguyen("Chọn: ");
            switch (chon) {
                case 1: themPhuongTien(qly); break;
                case 2: {
                    Vehicle v = qly.search(nhapChuoi("Biển số cần tìm: "));
                    System.out.println(v == null ? "Không tìm thấy." : v);
                    break;
                }
                case 3: {
                    List<Vehicle> ds = qly.findByOwner(nhapChuoi("Số CMND: "));
                    if (ds.isEmpty()) System.out.println("Không tìm thấy xe nào.");
                    else ds.forEach(System.out::println);
                    break;
                }
                case 4: {
                    int xoa = qly.deleteByManufacturer(nhapChuoi("Hãng cần xóa: "));
                    System.out.println("Đã xóa " + xoa + " xe.");
                    break;
                }
                case 5: {
                    List<String> top = qly.topManufacturers();
                    System.out.println(top.isEmpty() ? "Chưa có xe nào." : "Hãng nhiều xe nhất: " + String.join(", ", top));
                    break;
                }
                case 6: qly.sortByManufacturerCountDesc(); qly.display(); break;
                case 7: qly.statisticsByType(); break;
                case 8: qly.display(); break;
                case 9: napDuLieuMau(qly); break;
                case 0: System.out.println("Tạm biệt!"); return;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
