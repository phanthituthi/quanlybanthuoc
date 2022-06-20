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
import Model.NhomThuoc;
import Service.LoaiThuocService;

public class LoaiThuocDAO implements LoaiThuocService{
	private SessionFactory sessionFactory =null;
	
	public LoaiThuocDAO() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}

	@Override
	public List<NhomThuoc> layDanhSachLoaiThuoc() {
		List<NhomThuoc> dskh = new ArrayList<NhomThuoc>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[NhomThuoc]";
		try {
			transaction.begin();
			dskh = session.createNativeQuery(sql, NhomThuoc.class).getResultList();
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
	public NhomThuoc timNhomThuocTheoTen(String ten) {
		NhomThuoc nt = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[NhomThuoc] as nt where nt.tenNhomThuoc like N'"+ten+"'";
		try {
			transaction.begin();
			nt = session.createNativeQuery(sql, NhomThuoc.class).getSingleResult();
			transaction.commit();
			return nt;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

//	public static String getMaLoaiThuoc() {
//		String maLoaiThuoc = null;
//		Connection connection;
//		try {
//			String sql = "select top(1) CONVERT(int,SUBSTRING(maLoai,5,4)) as maLoai from LoaiThuoc order by maLoai desc";
//			connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			int dem = 1;
//			while (resultSet.next()) {
//				if (resultSet == null)
//					break;
//				dem = resultSet.getInt("maLoai") + 1;
//			}
//			maLoaiThuoc = String.format("LOAI%d", dem);
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return maLoaiThuoc;
//	}
//
//	public static ArrayList<NhomThuoc> getDSLoaiThuoc() {
//		ArrayList<NhomThuoc> arrayList = new ArrayList<NhomThuoc>();
//		NhomThuoc loaiThuoc;
//		String maloai, tenLoai, moTa;
//		String sql = "select maLoai,tenLoai,moTa from LoaiThuoc";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				maloai = resultSet.getString("maLoai");
//				tenLoai = resultSet.getNString("tenLoai");
//				moTa = resultSet.getNString("moTa");
//				loaiThuoc = new NhomThuoc(maloai, tenLoai, moTa);
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
//	public static boolean themLoaiThuoc(NhomThuoc loaiThuoc) {
//		boolean kq = false;
//		String sql = "insert LoaiThuoc(maLoai,tenLoai,moTa) values(?,?,?)";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, loaiThuoc.getMaLoai());
//			statement.setNString(2, loaiThuoc.getTenLoai());
//			statement.setNString(3, loaiThuoc.getMoTa());
//
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
//	public static boolean xoaLoaiThuoc(String maLoai) {
//		boolean kq = false;
//		String sql = "delete from LoaiThuoc where maLoai=?";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, maLoai);
//			int result = statement.executeUpdate();
//			if (result == 1)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static boolean capNhatLoaiThuoc(NhomThuoc loaiThuoc) {
//		boolean kq = false;
//		String sql = "update LoaiThuoc set tenLoai=?,moTa=? where maLoai=?";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setNString(1, loaiThuoc.getTenLoai());
//			statement.setNString(2, loaiThuoc.getMoTa());
//			statement.setString(3, loaiThuoc.getMaLoai());
//			int result = statement.executeUpdate();
//			if (result == 1)
//				kq = true;
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return kq;
//	}
//
//	public static NhomThuoc timLoaiThuocTheoMa(String maloai) {
//		NhomThuoc loaiThuoc = null;
//		String sql = "select tenLoai,moTa from LoaiThuoc where maLoai=?";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, maloai);
//			ResultSet resultSet = statement.executeQuery();
//			String tenLoai, moTa;
//			while (resultSet.next()) {
//				tenLoai = resultSet.getNString("tenLoai");
//				moTa = resultSet.getNString("moTa");
//				loaiThuoc = new NhomThuoc(maloai, tenLoai, moTa);
//			}
//			connection.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return loaiThuoc;
//	}
//
//	public static ArrayList<NhomThuoc> timLoaiThuocTheoTen(String tenloai) {
//		ArrayList<NhomThuoc> arrayList = new ArrayList<NhomThuoc>();
//		String sql = "select maLoai,tenLoai,moTa from LoaiThuoc where tenLoai like ?";
//		try {
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setNString(1, "%" + tenloai + "%");
//			ResultSet resultSet = statement.executeQuery();
//			NhomThuoc loaiThuoc;
//			while (resultSet.next()) {
//				loaiThuoc = new NhomThuoc(resultSet.getString("maLoai"), resultSet.getNString("tenLoai"),
//						resultSet.getNString("moTa"));
//				arrayList.add(loaiThuoc);
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		return arrayList;
//	}
}
