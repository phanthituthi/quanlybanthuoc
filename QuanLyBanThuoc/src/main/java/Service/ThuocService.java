package Service;

import java.util.List;

import Model.LoThuoc;
import Model.NhaCungCap;
import Model.Thuoc;
import dto.ThuocDTO;

public interface ThuocService {

	public Boolean themThuoc(Thuoc thuoc);
	public Boolean suaThuoc(Thuoc thuoc);
	public Boolean themLoThuoc(LoThuoc loThuoc);
	public Boolean xoaLoThuoc(String maLoThuoc);
	public Boolean suaLoThuoc(LoThuoc loThuoc);
	public List<ThuocDTO> layDanhSachThuoc();
	public List<String> layDanhSachTenThuoc();
	public List<String> layDanhSachDVQD();
	public List<String> layDanhSachDVT();
	public List<ThuocDTO> layDanhSachThuocTheoTenThuoc(String tenThuoc);
	public List<ThuocDTO> layDanhSachThuocTheoHoatChatChinh(String hcc);
	public List<ThuocDTO> layDanhSachThuocTheoLoaiThuoc(String tenLoaiThuoc);
	public List<ThuocDTO> xuLyTimTheoTenAndLoaiAndHoatChatAndNSX(String tenT, String tenLoai, String hcc, String ncc);
	public List<ThuocDTO> xuLyTimTheoTenLoaiVaHoatChat(String tenT, String tenLoai, String hcc);
	public List<ThuocDTO> xuLyTimTheoTenLoaiVaNSX(String tenT, String tenLoai, String ncc);
	public List<ThuocDTO> xuLyTimTheoTenHoatChatVaNSX(String tenT, String hcc, String ncc);
	public List<ThuocDTO> xuLyTimTheoLoaiHoatChatVaNSX(String loaiThuoc, String hcc, String ncc);
	public List<ThuocDTO> xuLyTimTheoTenVaLoai(String tenT, String tenLoai);
	public List<ThuocDTO> xuLyTimTheoTenVaHoatChat(String tenT, String hcc);
	public List<ThuocDTO> xuLyTimTheoTenVaNSX(String tenT, String ncc);
	public List<ThuocDTO> xuLyTimTheoLoaiVaHoatChat( String tenLoai, String hcc);
	public List<ThuocDTO> xuLyTimTheoLoaiVaNSX(String tenLoai, String ncc);
	public List<ThuocDTO> xuLyTimTheoHoatChatVaNSX(String hcc, String ncc);
	public String getCodeMaLoThuoc();
	public String getCodeMaThuoc();
	public List<ThuocDTO> timThuocTheoNCC(String ncc);
	public Thuoc timThuocTheoTen(String ten);
}
