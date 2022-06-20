package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.NhanVien;

public class FrameChinh extends JFrame implements ActionListener, MouseListener, MenuListener{

	private JMenuBar menuBar;
	private JMenu menuTrangChu, menuThuoc, menuNhanVien, menuKhachHang, menuNSX, menuHoaDon, menuThongKe, menuHelp, menuLoaiThuoc;
	private JMenuItem miAbout;

	private CardLayout cardLayout;
	private JPanel pTrangChu, pthuoc, pKhachHang, pNSX, pNhanVien, pHoaDon, pThongKe, pLoaiThuoc;
	private NhanVien nhanVien;
	
	public static void main(String[] args) {
		 FrameChinh chinh=  new FrameChinh();
		     
	}

	public FrameChinh() throws HeadlessException {		
		buildUI();
		taoMenubar();
		taoCardLayout();
		themMenuListener();
		dangNhap();
	}

	private void buildUI() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1400, 1000));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void taoCardLayout() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);

		pTrangChu = new PanelTrangChu(this);
		pthuoc = new PanelQuanLyThuoc(this);
		pKhachHang = new PanelQuanLyKhachHang();
		pNSX = new PanelQuanLyNSX();
		
		pNhanVien = new PanelQuanLyNhanVien();
		pNhanVien.setBackground(new Color(0, 128, 128));
		
		pHoaDon = new PanelHoaDon();
		try {
			pThongKe = new PanelThongKe();
			pThongKe.setBackground(new Color(0, 128, 128));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.add("TrangChu", pTrangChu);
		this.add("Thuoc", pthuoc);
		this.add("KhachHang", pKhachHang);
		this.add("NSX", pNSX);
		this.add("NhanVien", pNhanVien);
		this.add("HoaDon", pHoaDon);
		this.add("ThongKe", pThongKe);
	}

	public void taoMenubar() {
		menuBar = new JMenuBar();
		menuTrangChu = new JMenu("TRANG CHỦ");
		menuTrangChu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuHoaDon = new JMenu("hÓA ĐƠN");
		menuHoaDon.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuThuoc = new JMenu("THUỐC");
		menuThuoc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuNhanVien = new JMenu("NHÂN VIÊN");
		menuNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuKhachHang = new JMenu("KHÁCH HÀNG");
		menuKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuThongKe = new JMenu("THỐNG KÊ");
		menuThongKe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuNSX = new JMenu("NHÀ SẢN XUẤT");
		menuNSX.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuLoaiThuoc = new JMenu("LOẠI THUỐC");
		menuLoaiThuoc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuHelp = new JMenu("TRỢ GIÚP");
		menuHelp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		miAbout = new JMenuItem("THÔNG TIN");
		miAbout.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		menuHelp.add(miAbout);
		menuBar.add(menuTrangChu);
		menuBar.add(menuHoaDon);
		menuBar.add(menuThuoc);
		menuBar.add(menuLoaiThuoc);
		menuBar.add(menuNhanVien);
		menuBar.add(menuKhachHang);
		menuBar.add(menuNSX);
		menuBar.add(menuThongKe);
		menuBar.add(menuHelp);
		this.setJMenuBar(menuBar);
	}

	public void themMenuListener() {
		menuTrangChu.addMenuListener(this);
		menuThuoc.addMenuListener(this);
		menuKhachHang.addMenuListener(this);
		menuNSX.addMenuListener(this);
		menuNhanVien.addMenuListener(this);
		menuHoaDon.addMenuListener(this);
		menuThongKe.addMenuListener(this);
		menuLoaiThuoc.addMenuListener(this);
		miAbout.addActionListener(this);
	}


	public void dangNhap() {
		this.setEnabled(false);
		DialogDangNhap dangNhap = new DialogDangNhap(this);
		dangNhap.setVisible(true);
		boolean flag = dangNhap.isVisible();
		while (flag) {
			flag = dangNhap.isVisible();
			System.out.flush();
			if (!flag) {
				nhanVien = dangNhap.getNhanVien();
				this.setEnabled(true);
				this.setVisible(true);
			}
		}
		if (nhanVien.getRole().equalsIgnoreCase("EMPLOYEE"))
			menuBar.remove(menuNhanVien);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(miAbout)) {
			DiaLogAbout about = new DiaLogAbout(this);
		}
	}

	@Override
	public void menuSelected(MenuEvent e) {
		Object object = e.getSource();
		if (object.equals(menuTrangChu)) {
			cardLayout.show(this.getContentPane(), "TrangChu");
		} else if (object.equals(menuThuoc)) {
			cardLayout.show(this.getContentPane(), "Thuoc");
			//((PanelQuanLyThuoc) pthuoc).lamMoiUI();
		} else if(object.equals(menuLoaiThuoc)) {
			cardLayout.show(this.getContentPane(), "LoaiThuoc");
		}		
		else if (object.equals(menuKhachHang)) {
			cardLayout.show(this.getContentPane(), "KhachHang");
			((PanelQuanLyKhachHang) pKhachHang).lamMoiUI();
		} else if (object.equals(menuNSX)) {
			cardLayout.show(this.getContentPane(), "NSX");
			//((PanelQuanLyNSX) pNSX).lamMoiUI();
		} else if (object.equals(menuNhanVien)) {
			cardLayout.show(this.getContentPane(), "NhanVien");
			//((PanelQuanLyNhanVien) pNhanVien).xoaTrang();
		} else if (object.equals(menuHoaDon)) {
			cardLayout.show(this.getContentPane(), "HoaDon");
			//((PanelHoaDon) pHoaDon).xoaRong();
			((PanelHoaDon) pHoaDon).setNhanVien(nhanVien);
		} else if (object.equals(menuThongKe)) {
			cardLayout.show(this.getContentPane(), "ThongKe");
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
