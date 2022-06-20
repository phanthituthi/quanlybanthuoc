package Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NhanVien {
	@Id
	private String maNV;
	private String tenNV;
	private String ngaySinh;
	@Column(name="sdtNV")
	private String soDT;
	private String email;
	private String diaChi;
	private String cmnd;
	private String role;
	private String matKhau;
	private String trangThai;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDon> danhSachHoaDon;
	
	
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public List<HoaDon> getDanhSachHoaDon() {
		return danhSachHoaDon;
	}
	public void setDanhSachHoaDon(List<HoaDon> danhSachHoaDon) {
		this.danhSachHoaDon = danhSachHoaDon;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	
	public NhanVien(String maNV, String tenNV, String ngaySinh, String soDT, String email, String diaChi, String cmnd,
			String role, String matKhau, String trangThai) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.soDT = soDT;
		this.email = email;
		this.diaChi = diaChi;
		this.cmnd = cmnd;
		this.role = role;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
	}
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh + ", soDT=" + soDT + ", email="
				+ email + ", diaChi=" + diaChi + ", cmnd=" + cmnd + ", role=" + role + ", matKhau=" + matKhau
				+ ", trangThai=" + trangThai + "]";
	}
	
	

}