package Service;

import java.util.List;

import Model.KhachHang;
import Model.NhanVien;

public interface KhachHangService {

	public List<KhachHang> layDanhSachKhachHang();
	public List<KhachHang> layDanhSachKhachHangTheoTen(String ten);
	public List<KhachHang> layDanhSachKhachHangTheoSDT(String sdt);
	public KhachHang layDanhSachKhachHangTheoMa(String ma);
	public Boolean xoaKhachHang(String manv);
	public Boolean themKhachHang(KhachHang kh);
	public Boolean suaKhachHang(KhachHang kh);
	public List<KhachHang> timKhachHangTheoTieuChi();
	public KhachHang timKhachHangTheoCMND(String cmnd);
	public String setCodeEmployees();
	public List<KhachHang> timKhachHangTheoMail(String mail);
}
