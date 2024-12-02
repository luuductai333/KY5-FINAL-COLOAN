/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm1;

/**
 *
 * @author PC
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        QuanLySinhVien qlsv = new QuanLySinhVien();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = -1;

            do {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Add student");
                System.out.println("2. Edit student information");
                System.out.println("3. Delete student");
                System.out.println("4. Search for student by ID");
                System.out.println("5. Sort students by score");
                System.out.println("6. Display students sorted by ID");
                System.out.println("0. Exit");

                // Nhắc người dùng nhập và xử lý đầu vào không phải số nguyên
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Your choice: ");
                    try {
                        choice = scanner.nextInt();
                        validInput = true;  // Thoát vòng lặp nếu đầu vào hợp lệ
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an integer.");
                        scanner.nextLine();  // Xóa đầu vào không hợp lệ
                    }
                }
                scanner.nextLine(); // Tiêu thụ ký tự xuống dòng sau khi nhập số nguyên hợp lệ

                switch (choice) {
                    case 1 -> {
                        String id = QuanLySinhVien.nhapId(scanner);
                        String ten = QuanLySinhVien.nhapTen(scanner);
                        double diem = QuanLySinhVien.nhapDiem(scanner);
                        qlsv.themSinhVien(new SinhVien(id, ten, diem));
                    }
                    case 2 -> {
                        String idSua = QuanLySinhVien.nhapId(scanner);
                        String tenMoi = QuanLySinhVien.nhapTen(scanner);
                        double diemMoi = QuanLySinhVien.nhapDiem(scanner);
                        qlsv.suaSinhVien(idSua, tenMoi, diemMoi);
                    }
                    case 3 -> {
                        String idXoa = QuanLySinhVien.nhapId(scanner);
                        qlsv.xoaSinhVien(idXoa);
                    }
                    case 4 -> {
                        System.out.print("Choose search algorithm (1: Linear Search, 2: Binary Search): ");
                        int searchAlgorithm = -1;
                        boolean validSearchInput = false;

                        while (!validSearchInput) {
                            try {
                                searchAlgorithm = scanner.nextInt();
                                if (searchAlgorithm == 1 || searchAlgorithm == 2) {
                                    validSearchInput = true;
                                } else {
                                    System.out.println("Choose 1 (Linear Search) or 2 (Binary Search) only.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                scanner.nextLine(); // Xóa đầu vào không hợp lệ
                            }
                        }
                        scanner.nextLine(); // Xóa ký tự xuống dòng sau khi nhập

                        String idTimKiem = QuanLySinhVien.nhapId(scanner);
                        SinhVien sv;
                        if (searchAlgorithm == 1) {
                            System.out.println("Searching using Linear Search...");
                            sv = qlsv.timKiemTheoIdLinearSearch(idTimKiem);
                        } else {
                            System.out.println("Searching using Binary Search...");
                            sv = qlsv.timKiemTheoIdBinarySearch(idTimKiem);
                        }

                        if (sv != null) {
                            System.out.println("Search result: " + sv);
                        } else {
                            System.out.println("No student found with ID: " + idTimKiem);
                        }
                    }
                    case 5 -> {
                        // Lựa chọn thuật toán sắp xếp
                        System.out.print("Choose sorting algorithm (1: Bubble Sort, 2: Quick Sort): ");
                        int sortAlgorithm = -1;
                        boolean validAlgorithmInput = false;

                        while (!validAlgorithmInput) {
                            try {
                                sortAlgorithm = scanner.nextInt();
                                if (sortAlgorithm == 1 || sortAlgorithm == 2) {
                                    validAlgorithmInput = true;
                                } else {
                                    System.out.println("Choose 1 (Bubble Sort) or 2 (Quick Sort) only.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                scanner.nextLine(); // Xóa đầu vào không hợp lệ
                            }
                        }

                        // Lựa chọn kiểu sắp xếp
                        System.out.print("Sort scores by (1: Ascending, 2: Descending): ");
                        int sortOrder = -1;
                        boolean validSortOrderInput = false;

                        while (!validSortOrderInput) {
                            try {
                                sortOrder = scanner.nextInt();
                                if (sortOrder == 1 || sortOrder == 2) {
                                    validSortOrderInput = true;
                                } else {
                                    System.out.println("Choose 1 (Ascending) or 2 (Descending) only.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                scanner.nextLine(); // Xóa đầu vào không hợp lệ
                            }
                        }
                        scanner.nextLine(); // Xóa ký tự xuống dòng
                        boolean isDescending = sortOrder == 2;

                        // Sắp xếp theo thuật toán được chọn
                        if (sortAlgorithm == 1) {
                            System.out.println("Sorting using Bubble Sort:");
                            qlsv.sapXepSinhVienTheoDiem(isDescending);
                        } else {
                            System.out.println("Sorting using Quick Sort:");
                            qlsv.sapXepSinhVienQuickSort(isDescending);
                        }

                        // Hiển thị danh sách sau khi sắp xếp
                        System.out.println("Student list after sorting:");
                        qlsv.hienThiDanhSach(isDescending);
                    }
                    case 6 -> {
                        System.out.println("Student list sorted by ID:");
                        qlsv.hienThiDanhSachTheoId();
                    }
                    case 0 ->
                        System.out.println("Program exited.");
                    default ->
                        System.out.println("Invalid choice, please try again.");
                }
            } while (choice != 0);
        }
    }
}

