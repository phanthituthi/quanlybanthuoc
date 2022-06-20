package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import Database.SessionFactoryUtil;
import Model.NhanVien;
import Service.NhanVienService;

public class NhanVienDAO implements NhanVienService {
	private SessionFactory  sessionFactory = SessionFactoryUtil.getSessionFactory();

	@Override
	public List<NhanVien> layDanhSachNhanVien() {
		// TODO Auto-generated method stub
		List<NhanVien> dsnv = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dsnv = session.createQuery("from NhanVien",NhanVien.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dsnv;
	}
		
	
	@Override
	public Boolean xoaNhanVien(String manv) {
		// TODO Auto-generated method stub
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			NhanVien nv = session.get(NhanVien.class, manv);
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
	public Boolean themNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
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
	public Boolean suaNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
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
	public List<NhanVien> timNhanVienTheoTieuChi() {
		// TODO Auto-generated method stub
		return null;
	}




	
	@Override
	public NhanVien timNhanVienTheoCMND(String cmnd) {
		// TODO Auto-generated method stub
		NhanVien nv =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			nv = session.createQuery("from NhanVien where cmnd = "+cmnd+"", NhanVien.class).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return nv;
	}


	@Override
	public List<NhanVien> layDanhSachNhanVienTheoTen(String ten) {
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[NhanVien] as nv where nv.tenNV like N'%"+ten+"%'";
		try {
			transaction.begin();
			dsnv = session.createNativeQuery(sql, NhanVien.class).getResultList();
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
	public NhanVien layDanhSachNhanVienTheoSDT(String sdt) {
		// TODO Auto-generated method stub
				NhanVien nv =null;
				Session session = sessionFactory.getCurrentSession();
				Transaction transaction = session.getTransaction();
				try {
					transaction.begin();
					nv = session.createNativeQuery("select * from NhanVien as nv where nv.sdtNV = "+sdt+"", NhanVien.class).getSingleResult();
					transaction.commit();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					transaction.rollback();
				}
				return nv;
	}


	@Override
	public NhanVien layDanhSachNhanVienTheoMa(String ma) {
		NhanVien nv =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			nv = session.get(NhanVien.class, ma);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return nv;
	}


	@Override
	public String setCodeEmployees(NhanVien nv, String role) {
		String sql = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String ma = null;
		if(role.equals("ADMIN")) {
			sql = "select top 1 nv.maNV from [dbo].[NhanVien] as nv where nv.maNV like '%QL%' order by nv.maNV DESC";
		} else sql = "select top 1 nv.maNV from [dbo].[NhanVien] as nv where nv.maNV like '%NV%' order by nv.maNV DESC";
		try {
			transaction.begin();
			ma = (String)session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		Integer count = Integer.parseInt(ma.substring(2));
		String countStr = String.valueOf(count);
		if(role.equals("EMPLOYEE")) {
			if(countStr.length()==1 && count<9) {
				return "NV000"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "NV00"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "NV0"+(count+1);
			}
			else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
				return "NV"+(count+1);
			}
			else return "VQ";
		}
		else {
			if(countStr.length()==1 && count<9) {
				return "QL000"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "QL00"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "QL0"+(count+1);
			}
			else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
				return "QL"+(count+1);
			}
			else return "VQ";
		}
		
	}

	
public static void main(String[] args) {
	NhanVienDAO nhanVienDAO = new NhanVienDAO();
	System.out.println(nhanVienDAO.layDanhSachNhanVienTheoSDT("0568255707"));
	//System.out.println(nhanVienDAO.layDanhSachNhanVienTheoTen("tài"));
//		Scanner scan = new Scanner(System.in);
//		int count = scan.nextInt();
//		String countStr = String.valueOf(count);
//		if(countStr.length()==1 && count<9) {
//			System.out.println("NV000"+(count+1));
//		}
//		else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
//			System.out.println("NV00"+(count+1));
//		}
//		else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
//			System.out.println("NV0"+(count+1));
//		}
//		else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
//			System.out.println("NV"+(count+1));
//		}
	
}


@Override
public NhanVien timNhanVienTheoMail(String mail) {
	NhanVien nv =null;
	Session session = sessionFactory.getCurrentSession();
	Transaction transaction = session.getTransaction();
	try {
		transaction.begin();
		nv = session.createNativeQuery("select * from NhanVien as nv where nv.email = "+mail+"", NhanVien.class).getSingleResult();
		transaction.commit();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		transaction.rollback();
	}
	return nv;
}


	
	
//	
//	public static boolean xoaNhanVien(String manv) {
//		
//		try {
//			
//			//String add="delete ChiTietHoaDon where maHD in (select maHD from HoaDon where maNV='"+manv+"')   delete from HoaDon where MaNV='"+manv+"'  delete from NhanVien where MaNV='"+manv+"'";
//			String add="delete from NhanVien where MaNV='"+manv+"'";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//		statement.executeUpdate(sql);
//		} catch (ClassNotFoundException | SQLException e) {
//			return false;
//		}
//		return true;
//	}
//	
//public static boolean suaNhanVien(String maNV,String tenNV,String namSinh,String soDT,String gioiTinh,String diaChi, String chucVu,String matKhau,String trangThai) {
//		
//		try {
//			
//			String add="UPDATE NhanVien set maNV='"+maNV+"',tenNV=N'"+tenNV+"',namSinh='"+namSinh+"',soDT='"+soDT+"',gioiTinh=N'"+gioiTinh+"',diaChi=N'"+diaChi+"',chucVu=N'"+chucVu+"',matKhau='"+matKhau+"',trangThai=N'"+trangThai+"'  where MaNV='"+maNV+"'";
//			Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//			Statement statement = connection.createStatement();
//			String sql = String.format(add);
//		statement.executeUpdate(sql);
//		} catch (ClassNotFoundException | SQLException e) {
//			//e.printStackTrace();
//			return false;
//		}
//		
//return true;
//	}
//	
//public static boolean themNhanVien(String maNV,String tenNV,String namSinh,String soDT,String gioiTinh,String diaChi, String chucVu,String matKhau,String trangThai) {
//	
//	try {
//		
//		String add="insert NhanVien values('"+maNV+"',N'"+tenNV+"','"+namSinh+"','"+soDT+"',N'"+gioiTinh+"',N'"+diaChi+"',N'"+chucVu+"',N'"+matKhau+"',N'"+trangThai+"')";
//		Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//		Statement statement = connection.createStatement();
//		String sql = String.format(add);
//	statement.executeUpdate(sql);
//	} catch (ClassNotFoundException | SQLException e) {
//		//e.printStackTrace();
//		return false;
//	}
//	
//return true;
//}
//
//public static NhanVien timNhanVienTheoMa(String key) {
//	NhanVien a=null;
//	try {
//		
//		String add="SELECT maNV,tenNV,namSinh,soDT,gioiTinh,diaChi,chucVu,matKhau,trangThai from NhanVien where MaNV='"+key+"' or soDT='"+key+"'";
//		Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//		Statement statement = connection.createStatement();
//		String sql = String.format(add);
//		ResultSet resultSet = statement.executeQuery(sql);
//		while (resultSet.next()) {
//			String manv=resultSet.getString(1);
//			String ten = resultSet.getString(2);
//			String ns = resultSet.getString(3);
//			String sdt = resultSet.getString(4);
//			String gt = resultSet.getString(5);
//			String dc = resultSet.getString(6);
//			String cv = resultSet.getString(7);
//			String mk = resultSet.getString(8);
//			String tt = resultSet.getString(9);
//			a = new NhanVien(manv, ten,LocalDate.parse(ns).plusDays(2),sdt,gt,dc,cv,mk,tt);
//		}
//		
//	} catch (ClassNotFoundException | SQLException e) {
//		e.printStackTrace();
//	}
//return a;
//}
//	
//public static ArrayList<NhanVien> timNhanVienTheoTieuChi(String tennv,String gioitinh,String timsdt,String namsinh) {
//	ArrayList<NhanVien>Dsnv=new ArrayList<NhanVien>();
//	NhanVien nhanVien = null;
//	try {
//		Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//		Statement statement = connection.createStatement();
//		String sql = String.format("select maNV,tenNV,namSinh,soDT,gioiTinh,diaChi,chucVu,trangThai from NhanVien where soDT like '%%"+timsdt+"%%' and tenNV like N'%%"+tennv+"%%' and gioiTinh like N'%%"+gioitinh+"%%' and namSinh like '%%"+namsinh+"%%'");
//		ResultSet resultSet = statement.executeQuery(sql);
//
//		while (resultSet.next()) {
//			String manv=resultSet.getString(1);
//			String ten = resultSet.getString(2);
//			String ns = resultSet.getString(3);
//			String sdt = resultSet.getString(4);
//			String gt = resultSet.getString(5);
//			String dc = resultSet.getString(6);
//			String cv = resultSet.getString(7);
//			String tt = resultSet.getString(8);
//			nhanVien = new NhanVien(manv,ten,LocalDate.parse(ns).plusDays(2), sdt, gt, dc,cv,"",tt);
//					Dsnv.add(nhanVien);
//		}
//
//	} catch (ClassNotFoundException | SQLException e) {
//		e.printStackTrace();
//	}
//
//	return Dsnv;
//}
//public static String setCodeEmployees() {
//	int max=0;
//	String code="NV1";
//	ArrayList<String> listcode=new ArrayList<String>();
//	try {
//		
//		String add="SELECT maNV from NhanVien";
//		Connection connection = KetNoiVoiSQL.ketNoiVoiSQL();
//		Statement statement = connection.createStatement();
//		String sql = String.format(add);
//		ResultSet resultSet = statement.executeQuery(sql);
//		while (resultSet.next()) {
//			String manv=resultSet.getString(1);
//			String[] spl=manv.split("NV");
//			String kq=spl[1];
//			int kq1=Integer.parseInt(kq);
//			if(max<kq1)max=kq1;
//			code="NV"+(max+1);
//		}
//		
//	} catch (ClassNotFoundException | SQLException e) {
//		e.printStackTrace();
//	}
//return code;
//}
}
