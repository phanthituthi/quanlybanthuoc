package Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Model.NhanVien;
import Model.KhachHang;
import Model.ChiTietHoaDon;
@Entity
public class HoaDon {
	private static final float VAT = (float) 0.05;
	@Id
	private String maHD;
	private LocalDate ngayLapHD;
	private String loaiHD;
	private String ghiChu;
	
	@ManyToOne
	@JoinColumn(name="maNV")
	private NhanVien nhanVien;
	@ManyToOne
	@JoinColumn(name="maKH")
	private KhachHang khachHang;
	
	@OneToMany(mappedBy = "hoaDon")
	private List<ChiTietHoaDon> chiTietHD;
	
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public List<ChiTietHoaDon> getChiTietHD() {
		return chiTietHD;
	}
	public void setChiTietHD(List<ChiTietHoaDon> chiTietHD) {
		this.chiTietHD = chiTietHD;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public String getLoaiHD() {
		return loaiHD;
	}
	public void setLoaiHD(String loaiHD) {
		this.loaiHD = loaiHD;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public static float getVat() {
		return VAT;
	}
	public HoaDon(String maHD, LocalDate ngayLapHD, String loaiHD, String ghiChu) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.loaiHD = loaiHD;
		this.ghiChu = ghiChu;
	}
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", loaiHD=" + loaiHD + ", ghiChu=" + ghiChu
				+ ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", chiTietHD=" + chiTietHD + "]";
	}
	
}