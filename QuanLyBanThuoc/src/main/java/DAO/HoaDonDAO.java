package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.KhachHang;
import Model.NhomThuoc;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.Thuoc;

public class HoaDonDAO {
//	public static ArrayList<Thuoc> layDanhSachThuoc() {
//		ArrayList<Thuoc> Dst = new ArrayList<Thuoc>();
//		Thuoc thuoc = null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(
//					"select a.maThuoc,a.tenThuoc,c.tenLoai,a.dangBaoChe,a.hoatChat,a.hamLuong,b.tenNSX,a.donVi,a.soLuong,a.giaBan,a.giaTriQuyDoi,a.donViQuyDoi from Thuoc as a join NhaSanXuat as b on a.maNSX=b.maNSX join LoaiThuoc as c on a.maLoai=c.maLoai where a.trangThai!=N'Ngừng bán'");
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				String mat = resultSet.getString(1);
//				String tent = resultSet.getString(2);
//				String loait = resultSet.getString(3);
//				String dbc = resultSet.getString(4);
//				String hc = resultSet.getString(5);
//				String hl = resultSet.getString(6);
//				String nsx = resultSet.getString(7);
//				String dv = resultSet.getString(8);
//				int sl = resultSet.getInt(9);
//				Double gb = resultSet.getDouble(10);
//				int gtqd = resultSet.getInt(11);
//				String dvqd = resultSet.getString(12);
//				thuoc = new Thuoc(mat, tent, new NhomThuoc("", loait, ""), 0, gb, dbc, hc, hl,
//						new NhaCungCap("", nsx, "", ""), dv,dvqd, gtqd, "", sl, "");
//				Dst.add(thuoc);
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//
//	public static Thuoc timThuocTheoMa(String key) {
//		Thuoc thuoc = null;
//		try {
//
//			String add = String.format(
//					"select a.maThuoc,a.tenThuoc,c.tenLoai,a.dangBaoChe,a.hoatChat,a.hamLuong,b.tenNSX,a.donVi,a.soLuong,a.giaBan,a.giaTriQuyDoi,a.donViQuyDoi from Thuoc as a join NhaSanXuat as b on a.maNSX=b.maNSX join LoaiThuoc as c on a.maLoai=c.maLoai where maThuoc='"
//							+ key + "' and a.trangThai!=N'Ngừng bán'");
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				String mat = resultSet.getString(1);
//				String tent = resultSet.getString(2);
//				String loait = resultSet.getString(3);
//				String dbc = resultSet.getString(4);
//				String hc = resultSet.getString(5);
//				String hl = resultSet.getString(6);
//				String nsx = resultSet.getString(7);
//				String dv = resultSet.getString(8);
//				int sl = resultSet.getInt(9);
//				Double gb = resultSet.getDouble(10);
//				int gtqd = resultSet.getInt(11);
//				String dvqd = resultSet.getString(12);
//				thuoc = new Thuoc(mat, tent, new NhomThuoc("", loait, ""), 0, gb, dbc, hc, hl,
//						new NhaCungCap("", nsx, "", ""), dv,dvqd, gtqd, "",sl, "");
//
//			}
//			connection.close();
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return thuoc;
//	}
//
//	public static ArrayList<Thuoc> timThuocTheoCacTieuChi(String ten, String loai, String hoatchat, String nhasanxuat,String hamluong,String dangbaoche) {
//		ArrayList<Thuoc> Dst = new ArrayList<Thuoc>();
//		Thuoc thuoc = null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(
//					"select a.maThuoc,a.tenThuoc,c.tenLoai,a.dangBaoChe,a.hoatChat,a.hamLuong,b.tenNSX,a.donVi,a.soLuong,a.giaBan,a.giaTriQuyDoi,a.donViQuyDoi from Thuoc as a join NhaSanXuat as b on a.maNSX=b.maNSX join LoaiThuoc as c on a.maLoai=c.maLoai where a.tenThuoc like N'"
//							+ ten + "%%' and c.tenLoai like N'" + loai + "%%' and a.hoatChat like N'" + hoatchat
//							+ "%%' and b.tenNSX like N'" + nhasanxuat + "%%'   and a.hamLuong like N'"+hamluong+"%%' and a.dangBaoChe like N'"+dangbaoche+"%%'  and a.trangThai!=N'Ngừng bán'");
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				String mat = resultSet.getString(1);
//				String tent = resultSet.getString(2);
//				String loait = resultSet.getString(3);
//				String dbc = resultSet.getString(4);
//				String hc = resultSet.getString(5);
//				String hl = resultSet.getString(6);
//				String nsx = resultSet.getString(7);
//				String dv = resultSet.getString(8);
//				int sl = resultSet.getInt(9);
//				Double gb = resultSet.getDouble(10);
//				int gtqd = resultSet.getInt(11);
//				String dvqd = resultSet.getString(12);
//				thuoc = new Thuoc(mat, tent, new NhomThuoc("", loait, ""), 0, gb, dbc, hc, hl,
//						new NhaCungCap("", nsx, "", ""), dv,dvqd, gtqd, "", sl, "");
//				Dst.add(thuoc);
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//
//	public static int setCodeOrders() {
//		int max = 0;
//		ArrayList<String> listcode = new ArrayList<String>();
//		try {
//
//			String add = "select maHD from HoaDon";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				int manv = resultSet.getInt(1);
//				// String[] spl=manv.split("HD");
//				// String kq=spl[1];
//				if (max < manv)
//					max = manv;
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return max;
//	}
//
//	public static KhachHang layThongTinKhachHang(String ma, String ten, String sodt) {
//		KhachHang khachhang = null;
//		try {
//
//			String add = String.format("select maKH,tenKH,soDT from KhachHang where soDT like '%%%%" + sodt
//					+ "%%%%' and maKH like '%%%%" + ma + "%%%%' and tenKH like N'%%%%" + ten + "%%%%'");
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				String makh = resultSet.getString(1);
//				String tenkh = resultSet.getString(2);
//				String sdt = resultSet.getString(3);
//				khachhang = new KhachHang(makh, tenkh, 0, sdt, "", "");
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return khachhang;
//	}
//
//	public static boolean themHoaDon(String ngaylap, String manv, String makh, String chuandoan, Double VAT) {
//
//		try {
//
//			String add = "insert HoaDon values('" + ngaylap + "','" + manv + "','" + makh + "',N'" + chuandoan + "',"
//					+ VAT + ")";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			statement.executeUpdate(sql);
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			// e.printStackTrace();
//			return false;
//		}
//
//		return true;
//	}
//
//	public static void themChiTietHoaDon(int mahd, String mathuoc, double dongia, int soluong) {
//
//		try {
//
//			String add = "insert ChiTietHoaDon values(" + mahd + ",'" + mathuoc + "'," + dongia + "," + soluong + ")";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			statement.executeUpdate(sql);
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			// return false;
//		}
//
//		// return true;
//	}
//
//	public static void capnhatSoLuong(String mathuoc, int soluong) {
//
//		try {
//
//			String add = "update Thuoc set soLuong=" + soluong + " where maThuoc='" + mathuoc + "'";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			statement.executeUpdate(sql);
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			// return false;
//		}
//
//		// return true;
//	}
//
//	public static ArrayList<NhomThuoc> getDSLoaiThuoc() {
//		ArrayList<NhomThuoc> arrayList = new ArrayList<NhomThuoc>();
//		NhomThuoc loaiThuoc;
//		String sql = "select tenLoai from LoaiThuoc group by tenLoai";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				String tenloai = resultSet.getString(1);
//				loaiThuoc = new NhomThuoc("", tenloai, "");
//				arrayList.add(loaiThuoc);
//			}
//			connection.close();
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return arrayList;
//	}
//
//	public static int laySLThuocHetHan(String x) {
//		int kq = 0;
//		String sql = "select sum(soLuong) from LoThuoc where hanSuDung<GETDATE() and maThuoc='" + x + "'";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				kq = resultSet.getInt(1);
//			}
//			connection.close();
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//	
//	public static ArrayList<KhachHang> getDSKhachHang(String ma1, String ten1, String sodt1) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = "select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where (soDT like '" +sodt1+"%%%%' and maKH like '%%%%" + ma1+ "%%%%' and tenKH like N'%%%%" + ten1+ "%%%%')"+"or (soDT is null and maKH like '%%%%"+ ma1+ "%%%%' and tenKH like N'%%%%" + ten1+ "%%%%')";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			String ma, ten, gioitinh, sdt, diachi;
//			int namsinh;
//			while (resultSet.next()) {
//				ma = resultSet.getString("maKH");
//				ten = resultSet.getNString("tenKH");
//				namsinh = resultSet.getInt("namSinh");
//				sdt = resultSet.getString("soDT");
//				gioitinh = resultSet.getNString("gioiTinh");
//				diachi = resultSet.getNString("diaChi");
//				khachHang = new KhachHang(ma, ten, namsinh, sdt, gioitinh, diachi);
//				arrayList.add(khachHang);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return arrayList;
//
//	}
//	
//public static void capNhatSoLuongLo(String mat,int sl) {
//		
//		try {
//			
//			String add="declare @check int,@mal int,@mat nvarchar(10) set @check="+sl+" set @mat='"+mat+"' while(@check>0) begin set @mal=(select top 1 maLo from  LoThuoc where maThuoc=@mat and hanSuDung >=GETDATE() and soLuongBanRa<soLuongNhapVao) if(@check<=(select top 1 soLuongNhapVao-soLuongBanRa from  LoThuoc where maLo=@mal))begin update LoThuoc set soLuongBanRa=soLuongBanRa+@check where maLo=@mal set @check=0 end if(@check>(select top 1 soLuongNhapVao-soLuongBanRa from  LoThuoc where maLo=@mal)) begin set @check=@check-(select top 1 soLuongNhapVao-soLuongBanRa from  LoThuoc where maLo=@mal) update LoThuoc set soLuongBanRa=soLuongNhapVao where maLo=@mal end if(@check>(select sum(soLuongNhapVao-soLuongBanRa) from LoThuoc where maThuoc='TH11' group by maThuoc))return end";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//		statement.executeUpdate(sql);
//		connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			//return false;
//		}
//		//return true;
//	}
//	

}
