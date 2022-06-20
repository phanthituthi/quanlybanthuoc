package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import Model.NhaCungCap;
import Model.NhanVien;
import Service.NhaSanXuatService;

public class NhaSanXuatDAO implements NhaSanXuatService{
	private SessionFactory sessionFactory = null;
	public NhaSanXuatDAO() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}
	@Override
	public List<NhaCungCap> layDanhSachNSX() {
		
		List<NhaCungCap> dsnsx = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dsnsx = session.createQuery("from NhaCungCap",NhaCungCap.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dsnsx;
		
	}
	@Override
	public List<NhaCungCap> layDanhSachNSXTheoTen(String ten) {
		List<NhaCungCap> dsnv = new ArrayList<NhaCungCap>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[NhaCungCap] as nv where nv.tenNCC like N'%"+ten+"%'";
		try {
			transaction.begin();
			dsnv = session.createNativeQuery(sql, NhaCungCap.class).getResultList();
			transaction.commit();
			return dsnv;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	@Override
	public List<NhaCungCap> layDanhSachNSXTheoSDT(String sdt) {
		List<NhaCungCap> dsncc =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dsncc = session.createNativeQuery("select * from NhaCungCap as nv where nv.sdt = "+sdt+"", NhaCungCap.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dsncc;
	}
	@Override
	public NhaCungCap layDanhSachNSXTheoMa(String ma) {
		NhaCungCap nv =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			nv = session.get(NhaCungCap.class, ma);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return nv;
	}
	@Override
	public Boolean xoaNSX(String mancc) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			NhaCungCap nv = session.get(NhaCungCap.class, mancc);
			session.delete(nv);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}
	@Override
	public Boolean themNSX(NhaCungCap nv) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(nv);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}
	@Override
	public Boolean suaNSX(NhaCungCap nv) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.saveOrUpdate(nv);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}
	@Override
	public NhaCungCap timNSXTheoCMND(String cmnd) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String setCodeEmployees() {
		String sql = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String maNCC = null;
		sql = "select top 1 ncc.maNCC from [dbo].[NhaCungCap] as ncc order by ncc.maNCC DESC";
		try {
			transaction.begin();
			maNCC = (String)session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		Integer count = Integer.parseInt(maNCC.substring(3));
		String countStr = String.valueOf(count);
			if(countStr.length()==1 && count<9 ) {
				return "NCC00"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "NCC0"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "NCC"+(count+1);
			}
			else return "VQ";
		
	}
	@Override
	public List<NhaCungCap> timNSXTheoMail(String mail) {
		List<NhaCungCap> dsncc =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dsncc = session.createNativeQuery("select * from NhaCungCap as kh where kh.email = '"+mail+"' ", NhaCungCap.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dsncc;
	}
	@Override
	public NhaCungCap timNSXTheoTen(String ten) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[NhaCungCap] as nv where nv.tenNCC like N'%"+ten+"%'";
		try {
			transaction.begin();
			NhaCungCap ncc = session.createNativeQuery(sql, NhaCungCap.class).getSingleResult();
			transaction.commit();
			return ncc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	
//	public static String getMaNSX() {
//		String maNSX = null;
//		try {
//			String sql = "select top(1) CONVERT(int,SUBSTRING(maNSX,4,5)) as maNSX from NhaSanXuat order by maNSX desc";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			int dem = 1;
//			while (resultSet.next()) {
//				if (resultSet == null)
//					break;
//				dem = resultSet.getInt("maNSX") + 1;
//			}
//			maNSX = String.format("NSX%d", dem);
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return maNSX;
//	}
//
//	public static ArrayList<NhaCungCap> getDSNSX() {
//		ArrayList<NhaCungCap> arrayList = new ArrayList<NhaCungCap>();
//		try {
//			String sql = "select maNSX,tenNSX,quocGiaSX,coSoSX from NhaSanXuat";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			String ma, ten, quocgia, coso;
//			NhaCungCap nhaSanXuat;
//			while (resultSet.next()) {
//				ma = resultSet.getString("maNSX");
//				ten = resultSet.getNString("tenNSX");
//				quocgia = resultSet.getNString("quocGiaSX");
//				coso = resultSet.getNString("coSoSX");
//				nhaSanXuat = new NhaCungCap(ma, ten, quocgia, coso);
//				arrayList.add(nhaSanXuat);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return arrayList;
//
//	}
//
//	public static boolean themNSX(NhaCungCap nhaSanXuat) {
//		boolean kq = false;
//		try {
//			String sql = "insert NhaSanXuat(maNSX,tenNSX,quocGiaSX,coSoSX) values(?,?,?,?)";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, nhaSanXuat.getMaNSX());
//			statement.setNString(2, nhaSanXuat.getTenNSX());
//			statement.setNString(3, nhaSanXuat.getQuocGiaSX());
//			statement.setNString(4, nhaSanXuat.getCoSoSX());
//			int result = statement.executeUpdate();
//			if (result != 0)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static boolean capNhatNSX(NhaCungCap nhaSanXuat) {
//		boolean kq = false;
//		try {
//			String sql = "update NhaSanXuat set tenNSX=?,quocGiaSX=?,coSoSX=? where maNSX=?";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setNString(1, nhaSanXuat.getTenNSX());
//			statement.setNString(2, nhaSanXuat.getQuocGiaSX());
//			statement.setNString(3, nhaSanXuat.getCoSoSX());
//			statement.setString(4, nhaSanXuat.getMaNSX());
//			int result = statement.executeUpdate();
//			if (result != 0)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static boolean xoaNSX(String maNSX) {
//		boolean kq = false;
//		try {
//			String sql = "delete from NhaSanXuat where maNSX=?";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, maNSX);
//			int result = statement.executeUpdate();
//			if (result != 0)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static NhaCungCap timTheoMa(String maNSX) {
//		NhaCungCap nhaSanXuat = null;
//		try {
//			String sql = "select tenNSX,quocGiaSX,coSoSX from NhaSanXuat where maNSX=?";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, maNSX);
//			ResultSet resultSet = statement.executeQuery();
//			String ten, quocgia, coso;
//			while (resultSet.next()) {
//				ten = resultSet.getNString("tenNSX");
//				quocgia = resultSet.getNString("quocGiaSX");
//				coso = resultSet.getNString("coSoSX");
//				nhaSanXuat = new NhaCungCap(maNSX, ten, quocgia, coso);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return nhaSanXuat;
//	}
//
//	public static ArrayList<NhaCungCap> timTheoTen(String tenNSX) {
//		ArrayList<NhaCungCap> arrayList = new ArrayList<NhaCungCap>();
//		NhaCungCap nhaSanXuat;
//		try {
//			String sql = "select maNSX,tenNSX,quocGiaSX,coSoSX from NhaSanXuat where tenNSX like ?";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setNString(1, "%" + tenNSX + "%");
//			ResultSet resultSet = statement.executeQuery();
//			String ma, ten, quocgia, coso;
//			while (resultSet.next()) {
//				ma = resultSet.getString("maNSX");
//				ten = resultSet.getNString("tenNSX");
//				quocgia = resultSet.getNString("quocGiaSX");
//				coso = resultSet.getNString("coSoSX");
//				nhaSanXuat = new NhaCungCap(ma, ten, quocgia, coso);
//				arrayList.add(nhaSanXuat);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return arrayList;
//	}
//
//	public static ArrayList<NhaCungCap> timTheoQuocGia(String quocGiaSX) {
//		ArrayList<NhaCungCap> arrayList = new ArrayList<NhaCungCap>();
//		NhaCungCap nhaSanXuat;
//		try {
//			String sql = "select maNSX,tenNSX,quocGiaSX,coSoSX from NhaSanXuat where quocGiaSX like ?";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setNString(1, "%" + quocGiaSX + "%");
//			ResultSet resultSet = statement.executeQuery();
//			String ma, ten, quocgia, coso;
//			while (resultSet.next()) {
//				ma = resultSet.getString("maNSX");
//				ten = resultSet.getNString("tenNSX");
//				quocgia = resultSet.getNString("quocGiaSX");
//				coso = resultSet.getNString("coSoSX");
//				nhaSanXuat = new NhaCungCap(ma, ten, quocgia, coso);
//				arrayList.add(nhaSanXuat);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return arrayList;
//	}
//
//	public static ArrayList<NhaCungCap> timTheoTenVaQuocGia(String tenNSX, String quocGiaSX) {
//		ArrayList<NhaCungCap> arrayList = new ArrayList<NhaCungCap>();
//		NhaCungCap nhaSanXuat;
//		try {
//			String sql = "select maNSX,tenNSX,quocGiaSX,coSoSX from NhaSanXuat where  tenNSX like ? and quocGiaSX like ?";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setNString(1, "%" + tenNSX + "%");
//			statement.setNString(2, "%" + quocGiaSX + "%");
//			ResultSet resultSet = statement.executeQuery();
//			String ma, ten, quocgia, coso;
//			while (resultSet.next()) {
//				ma = resultSet.getString("maNSX");
//				ten = resultSet.getNString("tenNSX");
//				quocgia = resultSet.getNString("quocGiaSX");
//				coso = resultSet.getNString("coSoSX");
//				nhaSanXuat = new NhaCungCap(ma, ten, quocgia, coso);
//				arrayList.add(nhaSanXuat);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return arrayList;
//	}
}
