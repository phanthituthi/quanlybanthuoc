package Service;

import java.util.List;


import Model.NhanVien;

public interface NhanVienService {
	
	public List<NhanVien> layDanhSachNhanVien();
	public List<NhanVien> layDanhSachNhanVienTheoTen(String ten);
	public NhanVien layDanhSachNhanVienTheoSDT(String sdt);
	public NhanVien layDanhSachNhanVienTheoMa(String ma);
	public Boolean xoaNhanVien(String manv);
	public Boolean themNhanVien(NhanVien nv);
	public Boolean suaNhanVien(NhanVien nv);
	public List<NhanVien> timNhanVienTheoTieuChi();
	public NhanVien timNhanVienTheoCMND(String cmnd);
	public String setCodeEmployees(NhanVien nv, String role);
	public NhanVien timNhanVienTheoMail(String mail);
}
