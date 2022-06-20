package Model;

import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import Model.Thuoc;
@Entity
public class NhaCungCap {
	@Id
	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String sdt;
	private String email;
	private String trangThai;

	@OneToMany(mappedBy = "nhaCungCap")
	private List<LoThuoc> danhSachLoThuoc;

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public List<LoThuoc> getDanhSachLoThuoc() {
		return danhSachLoThuoc;
	}

	public void setDanhSachLoThuoc(List<LoThuoc> danhSachLoThuoc) {
		this.danhSachLoThuoc = danhSachLoThuoc;
	}

	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt, String email, String trangThai) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sdt=" + sdt + ", email="
				+ email + ", trangThai=" + trangThai + ", danhSachLoThuoc=" + danhSachLoThuoc + "]";
	}

	public NhaCungCap() {
		// TODO Auto-generated constructor stub
	}
	

}