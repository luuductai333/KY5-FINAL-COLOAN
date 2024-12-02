/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author PC
 */
public class SinhVien {

    private final String id;
    private String ten; // Tên sinh viên
    private double diem; // Điểm số của sinh viên

    // Constructor khởi tạo đối tượng SinhVien
    public SinhVien(String id, String ten, double diem) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
    }

    // Getter cho ID sinh viên
    public String getId() {
        return id;
    }

    // Getter cho tên sinh viên
    public String getTen() {
        return ten;
    }

    // Getter cho điểm số sinh viên
    public double getDiem() {
        return diem;
    }

    // Phương thức tính xếp hạng dựa trên điểm số
    public String getXepHang() {
        if (diem < 5.0) {
            return "Fail";
        } else if (diem < 6.5) {
            return "Medium";
        } else if (diem < 7.5) {
            return "Good";
        } else if (diem < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    // Phương thức trả về thông tin sinh viên dưới dạng chuỗi
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + ten + ", Score: " + diem + ", Ranking: " + getXepHang();
    }

    // Setters để chỉnh sửa tên và điểm số của sinh viên
    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}

