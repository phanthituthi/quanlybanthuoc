package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="CT_HoaDon")
@IdClass(CTHoaDonPK.class)
public class ChiTietHoaDon {
	
	@Id
	@ManyToOne
	@JoinColumn(name="maHD")
	private HoaDon hoaDon;
	@Id
	@ManyToOne
	@JoinColumn(name="maThuoc")
	private Thuoc thuoc;
	
	private double gia;
	private int soLuong;
	private String donViTinh;
	@Column(name="vAT")
	private double vat;
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public Thuoc getThuoc() {
		return thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public double getVat() {
		return vat;
	}
	public void setVat(double vat) {
		this.vat = vat;
	}
	public ChiTietHoaDon(HoaDon hoaDon, Thuoc thuoc, double gia, int soLuong, String donViTinh, double vat) {
		super();
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
		this.gia = gia;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.vat = vat;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", thuoc=" + thuoc + ", gia=" + gia + ", soLuong=" + soLuong
				+ ", donViTinh=" + donViTinh + ", vat=" + vat + "]";
	}

	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}
}