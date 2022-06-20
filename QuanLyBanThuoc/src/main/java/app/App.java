package app;

import DAO.NhanVienDAO;
import Service.NhanVienService;
import UI.PanelQuanLyNhanVien;

public class App {
	public static void main(String[] args) {
		NhanVienDAO nv = new NhanVienDAO();
//		System.out.println(nv.timNhanVienTheoMa("NV0002"));
		new PanelQuanLyNhanVien().setVisible(true);
	}
}
