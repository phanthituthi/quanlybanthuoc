package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Database.SessionFactoryUtil;
import Model.KhachHang;
import Model.LoThuoc;
import Model.NhomThuoc;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.Thuoc;
import Service.ThongKeService;

public class ThongKeDAO implements ThongKeService {
	
	private SessionFactory sessionFactory=null;
	
	public ThongKeDAO() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}

	@Override
	public double thongKeDoanhThuTrongNgay(int ngay, int thang, int nam) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double thongKeDoanhThuTrongThang(int thang, int nam) {
		double dt = 0;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select sum(b.gia*b.soLuong) as Doanhthu from HoaDon as a join CT_HoaDon as b on a.maHD=b.maHD where MONTH(a.ngayLapHD)="+thang+" and YEAR(a.ngayLapHD)="+nam+" group by month(a.ngayLapHD)";
		try {
			transaction.begin();
			dt =(Double) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			return dt;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dt;
	}

	@Override
	public double thongKeDoanhThuTrongNam(int nam) {
		double dt = 0;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select sum(b.gia*b.soLuong) as Doanhthu from HoaDon as a join CT_HoaDon as b on a.maHD=b.maHD where YEAR(a.ngayLapHD)="+nam+" group by year(a.ngayLapHD)";
		try {
			transaction.begin();
			dt =(Double) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			return dt;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dt;
	}

	
//	public static int demNhanVien() {
//		int a=0;
//		try {
//			
//			String add="select COUNT(*)from NhanVien";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				a=resultSet.getInt(1);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	return a;
//	}
//	
//	public static int demKhachHang() {
//		int a=0;
//		try {
//			
//			String add="select COUNT(*)from KhachHang";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				a=resultSet.getInt(1);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	return a;
//	}
//	
//	public static int demThuoc() {
//		int a=0;
//		try {
//			
//			String add="select COUNT(*)from Thuoc";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				a=resultSet.getInt(1);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	return a;
//	}
//	
//	
//	public static ArrayList<Thuoc> layThuocSapHetHang() {
//		ArrayList<Thuoc>Dst=new ArrayList<Thuoc>();
//		Thuoc thuoc = null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format("select a.maThuoc,a.tenThuoc,c.tenLoai,a.dangBaoChe,a.hoatChat,a.hamLuong,b.tenNSX,a.donVi,a.soLuong,a.giaBan,a.giaTriQuyDoi from Thuoc as a join NhaSanXuat as b on a.maNSX=b.maNSX join LoaiThuoc as c on a.maLoai=c.maLoai where a.trangThai!=N'Ngừng bán' and soLuong<=100 and soLuong>0");
//			
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				String mat=resultSet.getString(1);
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
//				thuoc= new Thuoc(mat,tent, new NhomThuoc("",loait,""), 0, gb, dbc, hc, hl,new NhaCungCap("",nsx,"",""),dv,"",gtqd,"",sl, "");
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
//	public static ArrayList<Thuoc> layThuocDaHetHang() {
//		ArrayList<Thuoc>Dst=new ArrayList<Thuoc>();
//		Thuoc thuoc = null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format("select a.maThuoc,a.tenThuoc,c.tenLoai,a.dangBaoChe,a.hoatChat,a.hamLuong,b.tenNSX,a.donVi,a.soLuong,a.giaBan,a.giaTriQuyDoi from Thuoc as a join NhaSanXuat as b on a.maNSX=b.maNSX join LoaiThuoc as c on a.maLoai=c.maLoai where soLuong=0");
//			
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				String mat=resultSet.getString(1);
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
//				thuoc= new Thuoc(mat,tent, new NhomThuoc("",loait,""), 0, gb, dbc, hc, hl,new NhaCungCap("",nsx,"",""),dv,"",gtqd,"",sl, "");
//				Dst.add(thuoc);
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//	public static ArrayList<Thuoc> layThuocBanChay() {
//		ArrayList<Thuoc>Dst=new ArrayList<Thuoc>();
//		Thuoc thuoc = null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format("select top 5 a.maThuoc,b.tenThuoc,d.tenLoai,b.dangBaoChe,b.hoatChat,b.hamLuong,c.tenNSX,b.donVi,b.soLuong,b.giaTriQuyDoi,COUNT(a.maThuoc) as Solan from ChiTietHoaDon as a join Thuoc as b on a.maThuoc=b.maThuoc join NhaSanXuat as c on b.maNSX=c.maNSX join LoaiThuoc as d on b.maLoai=d.maLoai group by a.maThuoc,b.tenThuoc,d.tenLoai,b.dangBaoChe,b.hoatChat,b.hamLuong,c.tenNSX,b.donVi,b.soLuong,b.giaTriQuyDoi order by Solan desc");
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				String mat=resultSet.getString(1);
//				String tent = resultSet.getString(2);
//				String loait = resultSet.getString(3);
//				String dbc = resultSet.getString(4);
//				String hc = resultSet.getString(5);
//				String hl = resultSet.getString(6);
//				String nsx = resultSet.getString(7);
//				String dv = resultSet.getString(8);
//				int sl = resultSet.getInt(9);
//				int gtqd = resultSet.getInt(10);
//				int lmua = resultSet.getInt(11);
//				thuoc= new Thuoc(mat,tent, new NhomThuoc("",loait,""), 0, lmua, dbc, hc, hl,new NhaCungCap("",nsx,"",""),dv,"",gtqd,"",sl,lmua+"");
//				Dst.add(thuoc);
//			
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//	
//	public static ArrayList<Thuoc> layThuocLaiNhat() {
//		ArrayList<Thuoc>Dst=new ArrayList<Thuoc>();
//		Thuoc thuoc = null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format("select top 5 a.maThuoc,b.tenThuoc,d.tenLoai,b.dangBaoChe,b.hoatChat,b.hamLuong,c.tenNSX,b.donVi,b.soLuong,b.giaTriQuyDoi,sum(b.giaBan*a.soLuong-b.giaNhap*a.soLuong) as Lai from ChiTietHoaDon as a join Thuoc as b on a.maThuoc=b.maThuoc join NhaSanXuat as c on b.maNSX=c.maNSX join LoaiThuoc as d on b.maLoai=d.maLoai group by a.maThuoc,b.tenThuoc,d.tenLoai,b.dangBaoChe,b.hoatChat,b.hamLuong,c.tenNSX,b.donVi,b.soLuong,b.giaTriQuyDoi order by Lai desc ");
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				String mat=resultSet.getString(1);
//				String tent = resultSet.getString(2);
//				String loait = resultSet.getString(3);
//				String dbc = resultSet.getString(4);
//				String hc = resultSet.getString(5);
//				String hl = resultSet.getString(6);
//				String nsx = resultSet.getString(7);
//				String dv = resultSet.getString(8);
//				int sl = resultSet.getInt(9);
//				int gtqd = resultSet.getInt(10);
//				Double lai = resultSet.getDouble(11);
//				thuoc= new Thuoc(mat,tent, new NhomThuoc("",loait,""), 0,0, dbc, hc, hl,new NhaCungCap("",nsx,"",""),dv,"",gtqd,"",sl,lai+"");
//				Dst.add(thuoc);
//			
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//	
//	public static ArrayList<LoThuoc> layThuocSapHetHan() {
//		ArrayList<LoThuoc>Dst=new ArrayList<LoThuoc>();
//		LoThuoc lothuoc=null;
//	Thuoc thuoc=null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format("select a.maLo,a.maThuoc,a.ngayNhap,a.hanSuDung,b.tenThuoc from LoThuoc as a join Thuoc as b on a.maThuoc=b.maThuoc");
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				int mal=resultSet.getInt(1);
//				String mat = resultSet.getString(2);
//				//int sln = resultSet.getInt(3);
//				String nn = resultSet.getString(3);
//				String hsd = resultSet.getString(4);
//				//int slb=resultSet.getInt(6);
//				String tent=resultSet.getString(5);
//				thuoc=new Thuoc(mat);
//				thuoc.setTenThuoc(tent);
//				lothuoc=new LoThuoc(thuoc,mal,0,LocalDate.parse(nn).plusDays(2),LocalDate.parse(hsd).plusDays(2),0,"");
//				//thuoc=new ThuocSapHetHan(mal,mat,tent,LocalDate.parse(nn).plusDays(2),LocalDate.parse(hsd).plusDays(2));
//				if(LocalDate.parse(lothuoc.getHanSuDung()+"").isAfter(LocalDate.now())&&(LocalDate.parse(lothuoc.getHanSuDung()+"").isBefore(LocalDate.now().plusDays(30))))Dst.add(lothuoc);
//			
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//	public static ArrayList<LoThuoc> layThuocDaHetHan() {
//		ArrayList<LoThuoc>Dst=new ArrayList<LoThuoc>();
//		LoThuoc lothuoc=null;
//	Thuoc thuoc=null;
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format("select a.maLo,a.maThuoc,a.ngayNhap,a.hanSuDung,b.tenThuoc from LoThuoc as a join Thuoc as b on a.maThuoc=b.maThuoc");
//			ResultSet resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				int mal=resultSet.getInt(1);
//				String mat = resultSet.getString(2);
//				//int sln = resultSet.getInt(3);
//				String nn = resultSet.getString(3);
//				String hsd = resultSet.getString(4);
//				//int slb=resultSet.getInt(6);
//				String tent=resultSet.getString(5);
//				thuoc=new Thuoc(mat);
//				thuoc.setTenThuoc(tent);
//				lothuoc=new LoThuoc(thuoc,mal,0,LocalDate.parse(nn).plusDays(2),LocalDate.parse(hsd).plusDays(2),0,"");
//				//thuoc=new ThuocSapHetHan(mal,mat,tent,LocalDate.parse(nn).plusDays(2),LocalDate.parse(hsd).plusDays(2));
//				if(LocalDate.parse(lothuoc.getHanSuDung()+"").isBefore(LocalDate.now()))Dst.add(lothuoc);
//			
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return Dst;
//	}
//	
//	public static double timDoanhThuTheoNgay(String key) {
//		double a=0;
//		try {
//			
//			String add="select a.ngayLapHD,sum(b.giaBan*b.soLuong) as Doanhthu from HoaDon as a join ChiTietHoaDon as b on a.maHD=b.maHD group by a.ngayLapHD having a.ngayLapHD='"+key+"'";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				String nl=resultSet.getString(1);
//				a = resultSet.getLong(2);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	return a;
//	}
//	
//	
//	public static double timDoanhThuTheoThang(LocalDate key) {
//		double a=0;
//		try {
//			
//			String add="select MONTH(a.ngayLapHD),sum(b.giaBan*b.soLuong) as Doanhthu from HoaDon as a join ChiTietHoaDon as b on a.maHD=b.maHD where MONTH(a.ngayLapHD)="+key.getMonthValue()+" and YEAR(a.ngayLapHD)="+key.getYear()+" group by month(a.ngayLapHD)";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				String nl=resultSet.getString(1);
//				a = resultSet.getLong(2);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	return a;
//	}
//	public static double timDoanhThuTheoNam(LocalDate key) {
//		double a=0;
//		try {
//			
//			String add="select year(a.ngayLapHD),sum(b.giaBan*b.soLuong) as Doanhthu from HoaDon as a join ChiTietHoaDon as b on a.maHD=b.maHD where YEAR(a.ngayLapHD)="+key.getYear()+" group by year(a.ngayLapHD)";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				String nl=resultSet.getString(1);
//				a = resultSet.getLong(2);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	return a;
//	}
}
