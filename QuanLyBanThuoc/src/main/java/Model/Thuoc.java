package Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Thuoc {
	@Id
	private String maThuoc;
	private String tenThuoc;
	private String loaiThuoc;
	private String hoatChatChinh;
	private String trangThai;
	@ManyToOne
	@JoinColumn(name="maNhomThuoc")
	private NhomThuoc nhomThuoc;
	
	@OneToMany(mappedBy = "thuoc")
	private List<ChiTietHoaDon> chiTietHD;
	
	@OneToMany(mappedBy = "maThuoc")
	private List<LoThuoc> danhSachLoThuoc;
	
	public List<LoThuoc> getDanhSachLoThuoc() {
		return danhSachLoThuoc;
	}
	public void setDanhSachLoThuoc(List<LoThuoc> danhSachLoThuoc) {
		this.danhSachLoThuoc = danhSachLoThuoc;
	}
	public NhomThuoc getNhomThuoc() {
		return nhomThuoc;
	}
	public void setNhomThuoc(NhomThuoc nhomThuoc) {
		this.nhomThuoc = nhomThuoc;
	}
	public List<ChiTietHoaDon> getChiTietHD() {
		return chiTietHD;
	}
	public void setChiTietHD(List<ChiTietHoaDon> chiTietHD) {
		this.chiTietHD = chiTietHD;
	}
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	public String getHoatChatChinh() {
		return hoatChatChinh;
	}
	public void setHoatChatChinh(String hoatChatChinh) {
		this.hoatChatChinh = hoatChatChinh;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public Thuoc(String maThuoc, String tenThuoc, String loaiThuoc, String hoatChatChinh, String trangThai) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiThuoc = loaiThuoc;
		this.hoatChatChinh = hoatChatChinh;
		this.trangThai = trangThai;
	}

	public Thuoc() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", loaiThuoc=" + loaiThuoc + ", hoatChatChinh="
				+ hoatChatChinh + ", trangThai=" + trangThai + "]";
	}
	
	

}