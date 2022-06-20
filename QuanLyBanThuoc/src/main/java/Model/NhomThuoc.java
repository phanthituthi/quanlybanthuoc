package Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class NhomThuoc {
	@Id
	private String maNhomThuoc;
	private String tenNhomThuoc;

	@OneToMany(mappedBy = "nhomThuoc")
	private List<Thuoc> thuoc;

	public String getMaNhomThuoc() {
		return maNhomThuoc;
	}

	public void setMaNhomThuoc(String maNhomThuoc) {
		this.maNhomThuoc = maNhomThuoc;
	}

	public String getTenNhomThuoc() {
		return tenNhomThuoc;
	}

	public void setTenNhomThuoc(String tenNhomThuoc) {
		this.tenNhomThuoc = tenNhomThuoc;
	}

	public List<Thuoc> getThuoc() {
		return thuoc;
	}

	public void setThuoc(List<Thuoc> thuoc) {
		this.thuoc = thuoc;
	}

	public NhomThuoc(String maNhomThuoc, String tenNhomThuoc) {
		super();
		this.maNhomThuoc = maNhomThuoc;
		this.tenNhomThuoc = tenNhomThuoc;
	}
	public NhomThuoc() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NhomThuoc [maNhomThuoc=" + maNhomThuoc + ", tenNhomThuoc=" + tenNhomThuoc + "]";
	}

	
}
