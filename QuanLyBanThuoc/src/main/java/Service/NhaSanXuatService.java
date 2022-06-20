package Service;

import java.util.List;

import Model.NhaCungCap;

public interface NhaSanXuatService {
	public List<NhaCungCap> layDanhSachNSX();
	public List<NhaCungCap> layDanhSachNSXTheoTen(String ten);
	public List<NhaCungCap> layDanhSachNSXTheoSDT(String sdt);
	public NhaCungCap layDanhSachNSXTheoMa(String ma);
	public Boolean xoaNSX(String manv);
	public Boolean themNSX(NhaCungCap nv);
	public Boolean suaNSX(NhaCungCap nv);
	public NhaCungCap timNSXTheoCMND(String cmnd);
	public String setCodeEmployees();
	public List<NhaCungCap> timNSXTheoMail(String mail);
	public NhaCungCap timNSXTheoTen(String ten);
}
