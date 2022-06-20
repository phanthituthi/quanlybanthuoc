package dto;

import java.sql.Date;

public class ThuocDTO {

	private String maLoThuoc;
	private String tenThuoc;
	private String tenLoaiThuoc;
	private String hoatChatChinh;
	private String tenNCC;
	private String xuatXu;
	private String ngaySanXuat;
	private String hanSuDung;
	private String donViTinh;
	private String donViQuyDoi;
	private int tyLeQuyDoi;
	private double giaBanChan;
	private double giaBanLe;
	private String lieuLuongDung;
	private String moTa;
	private double vat;
	private int soLuongTon;
	private boolean trangThai;
	

	
	public String getMaLoThuoc() {
		return maLoThuoc;
	}
	public void setMaLoThuoc(String maLoThuoc) {
		this.maLoThuoc = maLoThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getTenLoaiThuoc() {
		return tenLoaiThuoc;
	}
	public void setTenLoaiThuoc(String tenLoaiThuoc) {
		this.tenLoaiThuoc = tenLoaiThuoc;
	}
	public String getHoatChatChinh() {
		return hoatChatChinh;
	}
	public void setHoatChatChinh(String hoatChatChinh) {
		this.hoatChatChinh = hoatChatChinh;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getXuatXu() {
		return xuatXu;
	}
	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}
	public String getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(String ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public String getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(String hanSuDung) {
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
	public int getTyLeQuyDoi() {
		return tyLeQuyDoi;
	}
	public void setTyLeQuyDoi(int tyLeQuyDoi) {
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
	public boolean getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public ThuocDTO(String maLoThuoc, String tenThuoc, String tenLoaiThuoc, String hoatChatChinh, String tenNCC,
			String xuatXu, String ngaySanXuat, String hanSuDung, String donViTinh, String donViQuyDoi, int tyLeQuyDoi,
			double giaBanChan, double giaBanLe, String lieuLuongDung, String moTa, double vat, int soLuongTon,
			boolean trangThai) {
		super();
		this.maLoThuoc = maLoThuoc;
		this.tenThuoc = tenThuoc;
		this.tenLoaiThuoc = tenLoaiThuoc;
		this.hoatChatChinh = hoatChatChinh;
		this.tenNCC = tenNCC;
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
		this.trangThai = trangThai;
	}
	public ThuocDTO(String maLoThuoc) {
		super();
		this.maLoThuoc = maLoThuoc;
	}
	public ThuocDTO() {
		
	}
	@Override
	public String toString() {
		return "ThuocDTO [maLoThuoc=" + maLoThuoc + ", tenThuoc=" + tenThuoc + ", tenLoaiThuoc=" + tenLoaiThuoc
				+ ", hoatChatChinh=" + hoatChatChinh + ", tenNCC=" + tenNCC + ", xuatXu=" + xuatXu + ", ngaySanXuat="
				+ ngaySanXuat + ", hanSuDung=" + hanSuDung + ", donViTinh=" + donViTinh + ", donViQuyDoi=" + donViQuyDoi
				+ ", tyLeQuyDoi=" + tyLeQuyDoi + ", giaBanChan=" + giaBanChan + ", giaBanLe=" + giaBanLe
				+ ", lieuLuongDung=" + lieuLuongDung + ", moTa=" + moTa + ", vat=" + vat + ", soLuongTon=" + soLuongTon
				+ ", trangThai=" + trangThai + "]";
	}
	
}
