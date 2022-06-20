package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Database.SessionFactoryUtil;
import Model.KhachHang;
import Model.NhanVien;
import Service.KhachHangService;

public class KhachHangDAO implements KhachHangService{
	private SessionFactory sessionFactory=null;
	
	public KhachHangDAO() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}

	@Override
	public List<KhachHang> layDanhSachKhachHang() {
		List<KhachHang> dskh = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dskh = session.createQuery("from KhachHang",KhachHang.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dskh;
	}

	@Override
	public List<KhachHang> layDanhSachKhachHangTheoTen(String ten) {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[KhachHang] as nv where nv.tenKH like N'%"+ten+"%'";
		try {
			transaction.begin();
			dskh = session.createNativeQuery(sql, KhachHang.class).getResultList();
			transaction.commit();
			return dskh;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<KhachHang> layDanhSachKhachHangTheoSDT(String sdt) {
		List<KhachHang> dskh =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dskh = session.createNativeQuery("select * from KhachHang as nv where nv.sdtKH = "+sdt+"", KhachHang.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dskh;
	}

	@Override
	public KhachHang layDanhSachKhachHangTheoMa(String ma) {
		KhachHang kh =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			kh = session.get(KhachHang.class, ma);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return kh;
	}




	@Override
	public List<KhachHang> timKhachHangTheoTieuChi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KhachHang timKhachHangTheoCMND(String cmnd) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<KhachHang> timKhachHangTheoMail(String mail) {
		List<KhachHang> dskh =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dskh = session.createNativeQuery("select * from KhachHang as kh where kh.email = '"+mail+"' ", KhachHang.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dskh;
	}

	@Override
	public String setCodeEmployees() {
		String sql = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String maKH = null;
		sql = "select top 1 kh.maKH from [dbo].[KhachHang] as kh order by kh.maKH DESC";
		try {
			transaction.begin();
			maKH = (String) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		Integer count = Integer.parseInt(maKH.substring(2));
		String countStr = String.valueOf(count);
	
			if(countStr.length()==1 && count<9) {
				return "KH000000000"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "KH00000000"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "KH0000000"+(count+1);
			}
			else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
				return "KH000000"+(count+1);
			}
			
			else if((countStr.length()==5 && count<99999) || (countStr.length()==4 && count==99999)) {
				return "KH00000"+(count+1);
			}
			else if((countStr.length()==6 && count<999999) || (countStr.length()==5 && count==99999)) {
				return "KH0000"+(count+1);
			}
			else if((countStr.length()==7 && count<9999999) || (countStr.length()==6 && count==999999)) {
				return "KH000"+(count+1);
			}
			else if((countStr.length()==8 && count<99999999) || (countStr.length()==7 && count==9999999)) {
				return "KH00"+(count+1);
			}
			else if((countStr.length()==9 && count<999999999) || (countStr.length()==8 && count==99999999)) {
				return "KH0"+(count+1);
			}
			else if((countStr.length()==10 && count<9999999999L) || (countStr.length()==7 && count==9999999999L)) {
				return "KH"+(count+1);
			}
			else return "VQ";
	}

	@Override
	public Boolean xoaKhachHang(String makh) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			KhachHang kh = session.get(KhachHang.class, makh);
			session.delete(kh);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public Boolean themKhachHang(KhachHang kh) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(kh);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public Boolean suaKhachHang(KhachHang kh) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.saveOrUpdate(kh);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

//	public static String getMaKhachHang() {
//		String maKH = null;
//		Connection connection;
//		try {
//			String sql = "select top(1) CONVERT(int,SUBSTRING(maKH,3,5)) as maKH from KhachHang order by maKH desc";
//			connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			int dem = 1;
//			while (resultSet.next()) {
//				dem = resultSet.getInt("maKH") + 1;
//			}
//			maKH = String.format("KH%d", dem);
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return maKH;
//	}
//
//	public static ArrayList<KhachHang> getDSKhachHang() {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = "select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang";
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
//	public static boolean themKhachHang(KhachHang khachHang) {
//		boolean kq = false;
//		try {
//			String diaChi;
//			if (khachHang.getDiaChi().equals(""))
//				diaChi = "null";
//			else
//				diaChi = "N'" + khachHang.getDiaChi() + "'";
//			String soDT;
//			if (khachHang.getSoDT().equals(""))
//				soDT = "null";
//			else
//				soDT = "'" + khachHang.getSoDT() + "'";
//			String sql = String.format(
//					"insert KhachHang(maKH,tenKH,namSinh,soDT,gioiTinh,diaChi) values('%s',N'%s',%d,%s,N'%s',%s)",
//					khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getNamSinh(), soDT, khachHang.getGioiTinh(),
//					diaChi);
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			int result = statement.executeUpdate(sql);
//			if (result != 0)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static boolean capNhatKhachHang(KhachHang khachHang) {
//		boolean kq = false;
//		try {
//			String diaChi;
//			if (khachHang.getDiaChi().equals(""))
//				diaChi = "null";
//			else
//				diaChi = "N'" + khachHang.getDiaChi() + "'";
//			String soDT;
//			if (khachHang.getSoDT().equals(""))
//				soDT = "null";
//			else
//				soDT = "'" + khachHang.getSoDT() + "'";
//			String sql = String.format(
//					"update KhachHang set tenKH=N'%s',namSinh=%d,soDT=%s,gioiTinh=N'%s',diaChi=%s where maKH='%s'",
//					khachHang.getTenKH(), khachHang.getNamSinh(), soDT, khachHang.getGioiTinh(), diaChi,
//					khachHang.getMaKH());
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			int result = statement.executeUpdate(sql);
//			if (result != 0)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static boolean xoaKhachHang(String maKH) {
//		boolean kq = false;
//		try {
//			String sql = String.format("delete from KhachHang where maKH='%s'", maKH);
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			int result = statement.executeUpdate(sql);
//			if (result != 0)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static KhachHang timTheoMa(String maKH) {
//		KhachHang khachHang = null;
//		try {
//			String sql = String.format("select tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where maKH='%s'",
//					maKH);
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			String ten, gioitinh, sdt, diachi;
//			int namsinh;
//			while (resultSet.next()) {
//				ten = resultSet.getNString("tenKH");
//				namsinh = resultSet.getInt("namSinh");
//				sdt = resultSet.getString("soDT");
//				gioitinh = resultSet.getNString("gioiTinh");
//				diachi = resultSet.getNString("diaChi");
//				khachHang = new KhachHang(maKH, ten, namsinh, sdt, gioitinh, diachi);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return khachHang;
//	}
//
//	public static ArrayList<KhachHang> timTheoTenGioiTinhNamSinhSoDT(String tenKH, String gioiTinh, int namSinh,
//			String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and gioiTinh like N'%s' and namSinh=%d and soDT='%s'",
//					tenKH, gioiTinh, namSinh, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTenGioiTinhNamSinh(String tenKH, String gioiTinh, int namSinh) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and gioiTinh like N'%s' and namSinh=%d",
//					tenKH, gioiTinh, namSinh);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTenGioiTinhSoDT(String tenKH, String gioiTinh, String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and gioiTinh like N'%s' and soDT='%s'",
//					tenKH, gioiTinh, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTenNamSinhSoDT(String tenKH, int namSinh, String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and namSinh=%d and soDT='%s'",
//					tenKH, namSinh, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoGioiTinhNamSinhSoDT(String gioiTinh, int namSinh, String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where gioiTinh like N'%s' and namSinh=%d and soDT='%s'",
//					gioiTinh, namSinh, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTenGioiTinh(String tenKH, String gioiTinh) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and gioiTinh like N'%s'",
//					tenKH, gioiTinh);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTenNamSinh(String tenKH, int namSinh) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and namSinh=%d",
//					tenKH, namSinh);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTenSoDT(String tenKH, String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%' and soDT='%s'",
//					tenKH, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoGioiTinhNamSinh(String gioiTinh, int namSinh) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where gioiTinh like N'%s' and namSinh=%d",
//					gioiTinh, namSinh);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoGioiTinhSoDT(String gioiTinh, String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where gioiTinh like N'%s' and soDT='%s'",
//					gioiTinh, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoNamSinhSoDT(int namSinh, String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where namSinh=%d and soDT='%s'",
//					namSinh, soDT);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoTen(String tenKH) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where tenKH like N'%%%s%%'", tenKH);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoGioiTinh(String gioiTinh) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format(
//					"select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where gioiTinh like N'%s'",
//					gioiTinh);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoNamSinh(int namSinh) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format("select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where namSinh=%d",
//					namSinh);
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
//	}
//
//	public static ArrayList<KhachHang> timTheoSoDT(String soDT) {
//		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
//		KhachHang khachHang;
//		try {
//			String sql = String.format("select maKH,tenKH,namSinh,soDT,gioiTinh,diaChi from KhachHang where soDT='%s'",
//					soDT);
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
//	}
}
