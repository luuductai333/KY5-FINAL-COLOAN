/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm1;

/**
 *
 * @author PC
 */
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class QuanLySinhVien {
    private final Stack<SinhVien> danhSachSinhVien; // Đổi từ ArrayList thành Stack

    public QuanLySinhVien() {
        danhSachSinhVien = new Stack<>();
    }

    // Thêm sinh viên
    public void themSinhVien(SinhVien sv) {
        if (!danhSachSinhVien.contains(sv)) {
            danhSachSinhVien.push(sv); // Sử dụng push để thêm vào Stack
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student already exists.");
        }
    }

    // Sửa thông tin sinh viên
    public void suaSinhVien(String id, String tenMoi, double diemMoi) {
        SinhVien svTimThay = timKiemTheoIdLinearSearch(id);
        if (svTimThay != null) {
            svTimThay.setTen(tenMoi);
            svTimThay.setDiem(diemMoi);
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Xóa sinh viên
    public void xoaSinhVien(String id) {
        SinhVien svTimThay = timKiemTheoIdLinearSearch(id);
        if (svTimThay != null) {
            danhSachSinhVien.remove(svTimThay);
            System.out.println("Student with ID " + id + " has been removed.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Tìm kiếm sinh viên theo ID sử dụng Linear Search
    public SinhVien timKiemTheoIdLinearSearch(String id) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getId().equals(id)) {
                return sv;
            }
        }
        return null;
    }

    // Tìm kiếm sinh viên theo ID sử dụng Binary Search (chỉ hoạt động khi danh sách đã được sắp xếp theo ID)
    public SinhVien timKiemTheoIdBinarySearch(String id) {
        // Đảm bảo danh sách đã sắp xếp trước khi tìm kiếm nhị phân
        danhSachSinhVien.sort(Comparator.comparing(SinhVien::getId));
        int left = 0;
        int right = danhSachSinhVien.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            SinhVien midSv = danhSachSinhVien.get(mid);

            int compareResult = midSv.getId().compareTo(id);
            if (compareResult == 0) {
                return midSv; // Tìm thấy sinh viên
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Không tìm thấy
    }

    // Sắp xếp sinh viên theo điểm (Bubble Sort) - ascending or descending
    public void sapXepSinhVienTheoDiem(boolean isDescending) {
        int n = danhSachSinhVien.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean condition = isDescending ?
                        (danhSachSinhVien.get(j).getDiem() < danhSachSinhVien.get(j + 1).getDiem()) :
                        (danhSachSinhVien.get(j).getDiem() > danhSachSinhVien.get(j + 1).getDiem());
                if (condition) {
                    SinhVien temp = danhSachSinhVien.get(j);
                    danhSachSinhVien.set(j, danhSachSinhVien.get(j + 1));
                    danhSachSinhVien.set(j + 1, temp);
                }
            }
        }
    }

    // Sắp xếp sinh viên theo điểm (Quick Sort)
    private void quickSort(List<SinhVien> list, int low, int high, boolean isDescending) {
        if (low < high) {
            int pi = partition(list, low, high, isDescending);

            // Sắp xếp đệ quy các phần tử trước và sau phân vùng
            quickSort(list, low, pi - 1, isDescending);
            quickSort(list, pi + 1, high, isDescending);
        }
    }

    private int partition(List<SinhVien> list, int low, int high, boolean isDescending) {
        double pivot = list.get(high).getDiem();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            boolean condition = isDescending ?
                    (list.get(j).getDiem() > pivot) :
                    (list.get(j).getDiem() < pivot);

            if (condition) {
                i++;
                // Hoán đổi các yếu tố
                SinhVien temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Hoán đổi phần tử trục
        SinhVien temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    // Phương pháp công khai để sắp xếp bằng Quick Sort
    public void sapXepSinhVienQuickSort(boolean isDescending) {
        quickSort(danhSachSinhVien, 0, danhSachSinhVien.size() - 1, isDescending);
    }

    // Hiển thị danh sách sinh viên theo điểm
    public void hienThiDanhSach(boolean isDescending) {
        for (SinhVien sv : danhSachSinhVien) {
            System.out.println(sv);
        }
    }

    // Hiển thị danh sách sinh viên theo ID
    public void hienThiDanhSachTheoId() {
        danhSachSinhVien.sort(Comparator.comparing(SinhVien::getId));
        for (SinhVien sv : danhSachSinhVien) {
            System.out.println(sv);
        }
    }

    // Xác thực ID sinh viên, tên và điểm đầu vào
    public static String nhapId(Scanner scanner) {
        System.out.print("Enter student ID (numbers only): ");
        String id;
        while (true) {
            id = scanner.nextLine();
            if (id.matches("\\d+")) {
                break;
            } else {
                System.out.print("ID must contain numbers only. Please re-enter: ");
            }
        }
        return id;
    }

    public static String nhapTen(Scanner scanner) {
        System.out.print("Enter student name: ");
        String ten;
        while (true) {
            ten = scanner.nextLine();
            if (ten.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.print("Name must contain letters only. Please re-enter: ");
            }
        }
        return ten;
    }

    public static double nhapDiem(Scanner scanner) {
        System.out.print("Enter student score: ");
        double diem;
        while (true) {
            try {
                diem = scanner.nextDouble();
                if (diem >= 1 && diem <= 10) {
                    break;
                } else {
                    System.out.print("Score must be between 1 and 10. Please re-enter: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Score must be a number. Please re-enter: ");
                scanner.next(); // Xóa đầu vào không hợp lệ
            }
        }
        scanner.nextLine(); // Xóa ký tự xuống dòng
        return diem;
    }
}

