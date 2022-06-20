package DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Database.SessionFactoryUtil;
import Model.LoThuoc;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.Thuoc;
import Service.ThuocService;
import dto.ThuocDTO;

public class ThuocDAO implements ThuocService{
	
	private SessionFactory sessionFactory = null;
	
	public ThuocDAO() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}

	@Override
	public List<ThuocDTO> layDanhSachThuoc() {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC, \r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung\r\n"
				+ ", lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc] as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc\r\n"
				+ "join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc";
		try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}



	@Override
	public List<String> layDanhSachTenThuoc() {
		List<String> dsnv ;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select Thuoc.tenThuoc from Thuoc";
		try {
			transaction.begin();
			dsnv = (List<String>)session.createNativeQuery(sql).getResultList();
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
	public List<String> layDanhSachDVQD() {
		List<String> dsnv ;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.donViQuyDoi from [dbo].[LoThuoc] as lt group by lt.donViQuyDoi";
		try {
			transaction.begin();
			dsnv = (List<String>)session.createNativeQuery(sql).getResultList();
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
	public List<String> layDanhSachDVT() {
		List<String> dsnv ;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.donViTinh from [dbo].[LoThuoc] as lt group by lt.donViTinh";
		try {
			transaction.begin();
			dsnv = (List<String>)session.createNativeQuery(sql).getResultList();
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
	public List<ThuocDTO> layDanhSachThuocTheoTenThuoc(String ten) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "				lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung\r\n"
				+ ", lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc\r\n"
				+ "join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc where t.tenThuoc LIKE '%"+ten+"%'";
		try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> layDanhSachThuocTheoHoatChatChinh(String hcc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "				lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung\r\n"
				+ ", lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc\r\n"
				+ "join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc where t.hoatChatChinh LIKE '%"+hcc+"%'";
		try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> layDanhSachThuocTheoLoaiThuoc(String tenLoai) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "				lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung\r\n"
				+ ", lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc\r\n"
				+ "join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc where nt.tenNhomThuoc LIKE N'%"+tenLoai+"%'";
		try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> timThuocTheoNCC(String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "				lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung\r\n"
				+ ", lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc\r\n"
				+ "join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc where ncc.tenNCC LIKE N'%"+ncc+"%'";
		try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenAndLoaiAndHoatChatAndNSX(String tenT, String tenLoai, String hcc, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%' and nt.tenNhomThuoc like N'%"+tenLoai+"%'\r\n"
				+ "and t.hoatChatChinh  like '%"+hcc+"%' and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenLoaiVaHoatChat(String tenT, String tenLoai, String hcc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%' and nt.tenNhomThuoc like N'%"+tenLoai+"%'\r\n"
				+ "and t.hoatChatChinh  like '%"+hcc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenLoaiVaNSX(String tenT, String tenLoai, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%' and nt.tenNhomThuoc like N'%"+tenLoai+"%'\r\n"
				+ " and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenHoatChatVaNSX(String tenT, String hcc, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%'\r\n"
				+ "and t.hoatChatChinh  like '%"+hcc+"%' and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoLoaiHoatChatVaNSX(String loaiThuoc, String hcc, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where nt.tenNhomThuoc like N'%"+loaiThuoc+"%'\r\n"
				+ "and t.hoatChatChinh  like '%"+hcc+"%' and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenVaLoai(String tenT, String tenLoai) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%' and nt.tenNhomThuoc like N'%"+tenLoai+"%'\r\n";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenVaHoatChat(String tenT, String hcc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%'\r\n"
				+ "and t.hoatChatChinh  like '%"+hcc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoTenVaNSX(String tenT, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.tenThuoc like '%"+tenT+"%'\r\n"
				+ "and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoLoaiVaHoatChat(String tenLoai, String hcc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where nt.tenNhomThuoc like N'%"+tenLoai+"%'\r\n"
				+ "and t.hoatChatChinh  like '%"+hcc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoLoaiVaNSX(String tenLoai, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where nt.tenNhomThuoc like N'%"+tenLoai+"%'\r\n"
				+ "and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<ThuocDTO> xuLyTimTheoHoatChatVaNSX(String hcc, String ncc) {
		List<ThuocDTO> lstThuoc = new ArrayList<ThuocDTO>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select lt.maLoThuoc, t.tenThuoc, nt.tenNhomThuoc, t.hoatChatChinh ,ncc.tenNCC,\r\n"
				+ "lt.xuatXu, lt.ngaySanXuat, lt.hanSuDung, lt.donViTinh, lt.donViQuyDoi, lt.tyLeQuyDoi, lt.giaBanChan, lt.giaBanLe,\r\n"
				+ "lt.lieuLuongDung,lt.moTa, lt.VAT, lt.soLuongTon, t.trangThai from [dbo].[LoThuoc]\r\n"
				+ "as lt join [dbo].[Thuoc] as t on lt.maThuoc = t.maThuoc join [dbo].[NhaCungCap] as ncc on lt.maNCC = ncc.maNCC\r\n"
				+ "join [dbo].[NhomThuoc] as nt on t.maNhomThuoc = nt.maNhomThuoc \r\n"
				+ "where t.hoatChatChinh  like '%"+hcc+"%' and ncc.tenNCC like N'%"+ncc+"%'";
				try {
			transaction.begin();
			List<?> dsthuoc = session.createNativeQuery(sql).getResultList();
			transaction.commit();
			for (Object object : dsthuoc) {
				Object[] obj = (Object[])object;
				String maLoThuoc = String.valueOf(obj[0]);
				String tenThuoc = String.valueOf(obj[1]);
				String tenLoaiThuoc = String.valueOf(obj[2]);
				String hoatChatChinh = String.valueOf(obj[3]);
				String tenNCC = String.valueOf(obj[4]);
				String xuatXu = String.valueOf(obj[5]);
				String ngaySanXuat = String.valueOf(obj[6]);
				String hanSuDung = String.valueOf(obj[7]);	
				String donViTinh = String.valueOf(obj[8]);
				String donViQuyDoi = String.valueOf(obj[9]);
				int tyLeQuyDoi = Integer.parseInt(String.valueOf(obj[10]));
				double giaBanChan = Double.parseDouble(String.valueOf(obj[11]));
				double giaBanLe = Double.parseDouble(String.valueOf(obj[12]));
				String lieuLuongDung = String.valueOf(obj[13]);
				String moTa = String.valueOf(obj[14]);
				double vat = Double.parseDouble(String.valueOf(obj[15]));
				int soLuongTon = Integer.parseInt(String.valueOf(obj[16]));
				Boolean tt  = Boolean.parseBoolean(String.valueOf(obj[17]));
				ThuocDTO thuocDTO = new ThuocDTO(maLoThuoc, tenThuoc, tenLoaiThuoc, hoatChatChinh, tenNCC, xuatXu, ngaySanXuat, hanSuDung, donViTinh, donViQuyDoi, tyLeQuyDoi, giaBanChan, giaBanLe, lieuLuongDung, moTa, vat, soLuongTon, tt);
				lstThuoc.add(thuocDTO);
			}
			return lstThuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public Boolean themThuoc(Thuoc thuoc) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(thuoc);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public Boolean suaThuoc(Thuoc thuoc) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.saveOrUpdate(thuoc);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public Boolean themLoThuoc(LoThuoc loThuoc) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(loThuoc);
			transaction.commit();
			System.out.println("abc");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return false;
	}

	@Override
	public Boolean xoaLoThuoc(String maLoThuoc) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		LoThuoc loThuoc = session.get(LoThuoc.class, maLoThuoc);
		try {
			transaction.begin();
			session.delete(loThuoc);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public Boolean suaLoThuoc(LoThuoc loThuoc) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.saveOrUpdate(loThuoc);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public String getCodeMaLoThuoc() {
		String sql = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String maL = null;
		sql = "select top 1 lt.maLoThuoc from [dbo].[LoThuoc] as lt order by lt.maLoThuoc DESC";
		try {
			transaction.begin();
			maL = (String) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		Integer count = Integer.parseInt(maL.substring(2));
		String countStr = String.valueOf(count);
	
			if(countStr.length()==1 && count<9) {
				return "LT000000000"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "LT00000000"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "LT0000000"+(count+1);
			}
			else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
				return "LT000000"+(count+1);
			}
			
			else if((countStr.length()==5 && count<99999) || (countStr.length()==4 && count==99999)) {
				return "LT00000"+(count+1);
			}
			else if((countStr.length()==6 && count<999999) || (countStr.length()==5 && count==99999)) {
				return "LT0000"+(count+1);
			}
			else if((countStr.length()==7 && count<9999999) || (countStr.length()==6 && count==999999)) {
				return "LT000"+(count+1);
			}
			else if((countStr.length()==8 && count<99999999) || (countStr.length()==7 && count==9999999)) {
				return "LT00"+(count+1);
			}
			else if((countStr.length()==9 && count<999999999) || (countStr.length()==8 && count==99999999)) {
				return "LT0"+(count+1);
			}
			else if((countStr.length()==10 && count<9999999999L) || (countStr.length()==7 && count==9999999999L)) {
				return "LT"+(count+1);
			}
			else return "VQ";
	}

	@Override
	public String getCodeMaThuoc() {
		String sql = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String maT = null;
		sql = "select top 1 t.maThuoc from [dbo].[Thuoc] as t order by t.maThuoc DESC";
		try {
			transaction.begin();
			maT = (String) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		Integer count = Integer.parseInt(maT.substring(1));
		String countStr = String.valueOf(count);
	
			if(countStr.length()==1 && count<9) {
				return "T000000000"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "T00000000"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "T0000000"+(count+1);
			}
			else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
				return "T000000"+(count+1);
			}
			
			else if((countStr.length()==5 && count<99999) || (countStr.length()==4 && count==99999)) {
				return "T00000"+(count+1);
			}
			else if((countStr.length()==6 && count<999999) || (countStr.length()==5 && count==99999)) {
				return "T0000"+(count+1);
			}
			else if((countStr.length()==7 && count<9999999) || (countStr.length()==6 && count==999999)) {
				return "T000"+(count+1);
			}
			else if((countStr.length()==8 && count<99999999) || (countStr.length()==7 && count==9999999)) {
				return "T00"+(count+1);
			}
			else if((countStr.length()==9 && count<999999999) || (countStr.length()==8 && count==99999999)) {
				return "T0"+(count+1);
			}
			else if((countStr.length()==10 && count<9999999999L) || (countStr.length()==7 && count==9999999999L)) {
				return "T"+(count+1);
			}
			else return "VQ";
	}


	@Override
	public Thuoc timThuocTheoTen(String ten) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[Thuoc] as t where t.tenThuoc like '%"+ten+"%'";
		try {
			transaction.begin();
			Thuoc thuoc = session.createNativeQuery(sql, Thuoc.class).getSingleResult();
			transaction.commit();
			return thuoc;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	public static void main(String[] args) {
		ThuocDAO t = new ThuocDAO();
		System.out.println(t.timThuocTheoTen("Panadol Extra 20 mg").toString());
		//System.out.println(t.getCodeMaThuoc());
	}
}
