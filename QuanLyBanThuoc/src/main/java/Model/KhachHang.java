package Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class KhachHang {
	@Id
	private String maKH;
	private String tenKH;
	private int namSinh;
	@Column(name="sdtKH")
	private String soDT;
	private String email;
	private String diaChi;
	
	@OneToMany(mappedBy = "khachHang")
	private List<HoaDon> hoaDon;
	

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenKH, int namSinh, String soDT, String email, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.namSinh = namSinh;
		this.soDT = soDT;
		this.email = email;
		this.diaChi = diaChi;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getGioiTinh() {
		return email;
	}

	public void setGioiTinh(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", namSinh=" + namSinh + ", soDT=" + soDT + ", email="
				+ email + ", diaChi=" + diaChi + ", hoaDon=" + hoaDon + "]";
	}


	public KhachHang() {
		// TODO Auto-generated constructor stub
	}
	
}