package Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LoThuoc {

	@Id
	private String maLoThuoc;
	private String xuatXu;
	private LocalDate ngaySanXuat;
	private LocalDate hanSuDung;
	private String donViTinh;
	private String donViQuyDoi;
	private int tyLeQuyDoi;
	private double giaBanChan;
	private double giaBanLe;
	private String lieuLuongDung;
	private String moTa;
	@Column(name="VAT")
	private double vat;
	private int soLuongTon;

	@ManyToOne
	@JoinColumn(name="maThuoc")
	private Thuoc maThuoc;
	
	@ManyToOne
	@JoinColumn(name="maNCC")
	private NhaCungCap nhaCungCap;

	public String getmaLoThuoc() {
		return maLoThuoc;
	}

	public void setmaLoThuoc(String maLoThuoc) {
		this.maLoThuoc = maLoThuoc;
	}

	public String getXuatXu() {
		return xuatXu;
	}

	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}

	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}

	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}

	public LocalDate getHanSuDung() {
		return hanSuDung;
	}

	public void setHanSuDung(LocalDate hanSuDung) {
		this.hanSuDung = hanSuDung;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getDonViQuyDoi() {
		return donViQuyDoi;
	}

	public void setDonViQuyDoi(String donViQuyDoi) {
		this.donViQuyDoi = donViQuyDoi;
	}

	public int gettyLeQuyDoi() {
		return tyLeQuyDoi;
	}

	public void settyLeQuyDoi(int tyLeQuyDoi) {
		this.tyLeQuyDoi = tyLeQuyDoi;
	}

	public double getGiaBanChan() {
		return giaBanChan;
	}

	public void setGiaBanChan(double giaBanChan) {
		this.giaBanChan = giaBanChan;
	}

	public double getGiaBanLe() {
		return giaBanLe;
	}

	public void setGiaBanLe(double giaBanLe) {
		this.giaBanLe = giaBanLe;
	}

	public String getLieuLuongDung() {
		return lieuLuongDung;
	}

	public void setLieuLuongDung(String lieuLuongDung) {
		this.lieuLuongDung = lieuLuongDung;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public Thuoc getMaThuoc() {
		return maThuoc;
	}

	public void setMaThuoc(Thuoc maThuoc) {
		this.maThuoc = maThuoc;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public LoThuoc(String maLoThuoc, String xuatXu, LocalDate ngaySanXuat, LocalDate hanSuDung, String donViTinh,
			String donViQuyDoi, int tyLeQuyDoi, double giaBanChan, double giaBanLe, String lieuLuongDung, String moTa,
			double vat, int soLuongTon) {
		super();
		this.maLoThuoc = maLoThuoc;
		this.xuatXu = xuatXu;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.donViTinh = donViTinh;
		this.donViQuyDoi = donViQuyDoi;
		this.tyLeQuyDoi = tyLeQuyDoi;
		this.giaBanChan = giaBanChan;
		this.giaBanLe = giaBanLe;
		this.lieuLuongDung = lieuLuongDung;
		this.moTa = moTa;
		this.vat = vat;
		this.soLuongTon = soLuongTon;
	}

	@Override
	public String toString() {
		return "LoThuoc [maLoThuoc=" + maLoThuoc + ", xuatXu=" + xuatXu + ", ngaySanXuat=" + ngaySanXuat + ", hanSuDung="
				+ hanSuDung + ", donViTinh=" + donViTinh + ", donViQuyDoi=" + donViQuyDoi + ", tyLeQuyDoi=" + tyLeQuyDoi
				+ ", giaBanChan=" + giaBanChan + ", giaBanLe=" + giaBanLe + ", lieuLuongDung=" + lieuLuongDung
				+ ", moTa=" + moTa + ", vat=" + vat + ", soLuongTon=" + soLuongTon + ", maThuoc=" + maThuoc
				+ ", nhaCungCap=" + nhaCungCap + "]";
	}
	public LoThuoc() {
		// TODO Auto-generated constructor stub
	}

}
